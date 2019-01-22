package cn.yusei.jvm;

import java.util.ArrayList;

import cn.yusei.jvm.classfile.member.ClassMember;
import cn.yusei.jvm.classfile.member.attribute.CodeAttribute;
import cn.yusei.jvm.classfile.member.attribute.LineNumberTableAttribute;
import cn.yusei.jvm.runtimespace.method.RTConstantPool;

public class Method extends Member {

	private int maxStack;
	private int maxLocal;
	private byte[] codes;
	private String[] argsType;
	private String returnType;
	private int argsSlotCount;
	private ExceptionHandler handler;
	private LineNumberConvert convert;

	public Method(ClassInfo owner, ClassMember member, RTConstantPool pool) {
		super(owner, member, pool);
		if (getDescriptor() != null)
			parseDescriptor();
		if (isNative()) {
			injectNativeMethod();
			return;
		}
		CodeAttribute attribute = member.getCodeAttribute();
		if (attribute == null)
			return;
		maxStack = attribute.getMaxStack();
		maxLocal = attribute.getMaxLocal();
		codes = attribute.getCodes();
		handler = new ExceptionHandler(attribute.getExceptionAttributes());
		LineNumberTableAttribute lineNumberAttribute = attribute.getLineNumberAttribute();
		if (lineNumberAttribute != null)
			convert = new LineNumberConvert(lineNumberAttribute.getLineNumberTables());
	}

	private void injectNativeMethod() {
		maxStack = 4;
		maxLocal = argsSlotCount;
		// native 方法没有字节码
		// 这里根据返回类型设置不同的字节码
		// 其中 0xfe 是 JVM 的保留指令码，本项目用来作为 INVOKE_NATIVE 指令
		// 其后的字节码则是各种返回的 RETURN 命令
		switch (returnType.charAt(0)) {
		case 'V':
			codes = new byte[] { (byte) 0xfe, (byte) 0xb1 };
			break;
		case 'D':
			codes = new byte[] { (byte) 0xfe, (byte) 0xaf };
			break;
		case 'F':
			codes = new byte[] { (byte) 0xfe, (byte) 0xae };
			break;
		case 'J':
			codes = new byte[] { (byte) 0xfe, (byte) 0xad };
			break;
		case 'L':
			codes = new byte[] { (byte) 0xfe, (byte) 0xb0 };
			break;
		default:
			codes = new byte[] { (byte) 0xfe, (byte) 0xac };
			break;
		}
	}

	private void parseDescriptor() {
		ArrayList<String> args = new ArrayList<String>();
		int cnt = isStatic() ? 0 : 1;
		String nextArg = null;
		int nowIndex = 1;
		while ((nextArg = getNextArg(nowIndex)) != null) {
			cnt++;
			if (nextArg.equals("D") || nextArg.equals("J"))
				cnt++;
			args.add(nextArg);
			nowIndex += nextArg.length();
		}
		returnType = getDescriptor().substring(nowIndex + 1);
		argsSlotCount = cnt;
		argsType = args.toArray(new String[0]);
	}

	private String getNextArg(int nowIndex) {
		char ch = getDescriptor().charAt(nowIndex);
		switch (ch) {
		case 'L':
			String descriptor = getDescriptor();
			StringBuilder sb = new StringBuilder();
			for (int i = nowIndex;; i++) {
				sb.append(descriptor.charAt(i));
				if (descriptor.charAt(i) == ';')
					break;
			}
			return sb.toString();
		case '[':
			return "[" + getNextArg(nowIndex + 1);
		case ')':
			return null;
		default:
			return "" + ch;
		}
	}

	public boolean isSynchronized() {
		return access.isSynchronized();
	}

	public boolean isBridge() {
		return access.isBridge();
	}

	public boolean isVarargs() {
		return access.isVarargs();
	}

	public boolean isNative() {
		return access.isNative();
	}

	public boolean isAbstract() {
		return access.isAbstract();
	}

	public int getMaxStack() {
		return maxStack;
	}

	public int getMaxLocal() {
		return maxLocal;
	}

	public byte[] getCodes() {
		return codes;
	}

	public int getArgsSlotCount() {
		return argsSlotCount;
	}

	public String[] getArgsType() {
		return argsType;
	}

	public String getReturnType() {
		return returnType;
	}

	@Override
	public String toString() {
		return getClassInfo().toString() + "." + getName() + " " + getDescriptor();
	}

	public int findExceptionHandler(ClassInfo exception, int pc) {
		return handler.findExceptionHandler(exception, pc, getClassInfo().getConstantPool());
	}

	public int convertLineNumber(int pc) {
		// native 方法没有字节码，自然没有行号
		if (isNative())
			return -2;
		// class 文件中不一定有行号表，决定于编译时的选项
		if (convert == null)
			return -1;
		return convert.convertLineNumber(pc);
	}

}
