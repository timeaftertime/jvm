package cn.yusei.jvm.classfile.member.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import cn.yusei.jvm.classfile.constantpool.ConstantPool;
import cn.yusei.jvm.classfile.member.ClassMemberUtil;

public class CodeAttribute extends Attribute {

	private int maxStack;
	private int maxLocal;
	private byte[] codes;
	private ExceptionTableAttribute[] exceptionTables;
	private Attribute[] attributes;

	public CodeAttribute(int nameIndex, int length) {
		super(nameIndex, length);
	}

	@Override
	public void readInfo(int attrLen, DataInputStream data, ConstantPool constantPool) throws IOException {
		// 2 个字节无符号数，操作数栈最大深度
		maxStack = data.readUnsignedShort();
		// 2 个字节无符号数，局部变量表大小
		maxLocal = data.readUnsignedShort();
		// 4 个字节无符号数，字节码长度 n
		int codeLength = data.readInt();
		codes = new byte[codeLength];
		// n 个字节的字节码
		data.read(codes);
		readExceptionTables(data);
		// 《Java 虚拟机规范 （JavaSE 7 版）》：在版本号大于等于 50.0 的 Class 文件，若一个方法的 Code 属性中没有
		// StackMapTable 属性，
		// 那就意味着它带有一个隐式的 StackMap 属性，这个 StackMap 属性的作用等同于 numberOfEntries 为 0 的
		// StackMapTable。
		// 一个方法的 Code 属性最多只能有一个 StackMapTable 属性，否则将抛出 ClassFormatError
		attributes = ClassMemberUtil.readAttributes(data, constantPool);
	}

	private void readExceptionTables(DataInputStream data) throws IOException {
		// 2 个字节的异常处理表长度 n
		int exceptionTableLength = data.readUnsignedShort();
		// n 个异常处理表
		exceptionTables = new ExceptionTableAttribute[exceptionTableLength];
		for (int i = 0; i < exceptionTableLength; i++) {
			int startPc = data.readUnsignedShort();
			int endPc = data.readUnsignedShort();
			int handlerPc = data.readUnsignedShort();
			int catchType = data.readUnsignedShort();
			exceptionTables[i] = new ExceptionTableAttribute(startPc, endPc, handlerPc, catchType);
		}
	}

	public int getMaxLocal() {
		return maxLocal;
	}

	public int getMaxStack() {
		return maxStack;
	}

	public byte[] getCodes() {
		return codes;
	}

	public ExceptionTableAttribute[] getExceptionAttributes() {
		return exceptionTables;
	}
	
	public LineNumberTableAttribute getLineNumberAttribute() {
		for(Attribute attr : attributes) {
			if(attr instanceof LineNumberTableAttribute)
				return (LineNumberTableAttribute) attr;
		}
		return null;
	}
}
