package cn.yusei.jvm;

import cn.yusei.jvm.classfile.member.ClassMember;
import cn.yusei.jvm.classfile.member.attribute.CodeAttribute;
import cn.yusei.jvm.runtimespace.method.RTConstantPool;

public class Method extends Member {

	private int maxStack;
	private int maxLocal;
	private byte[] codes;

	public Method(ClassInfo owner, ClassMember member, RTConstantPool pool) {
		super(owner, member, pool);
		CodeAttribute attribute = member.getCodeAttribute();
		if (attribute == null)
			return;
		maxStack = attribute.getMaxStack();
		maxLocal = attribute.getMaxLocal();
		codes = attribute.getCodes();
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

}
