package cn.yusei.jvm;

import cn.yusei.jvm.classfile.member.ClassMember;
import cn.yusei.jvm.classfile.member.attribute.ConstantValueAttribute;
import cn.yusei.jvm.runtimespace.method.RTConstantPool;

public class Field extends Member {

	private int soltId;
	private int constantValueIndex;

	public Field(ClassInfo owner, ClassMember member, RTConstantPool pool) {
		super(owner, member, pool);
		ConstantValueAttribute constantValue = member.getConstantValueAttribute();
		if(constantValue != null)
			constantValueIndex = constantValue.getConstantValueIndex();
	}

	public boolean isVolatile() {
		return access.isVolatile();
	}

	public boolean isTransient() {
		return access.isTransient();
	}

	public boolean isEnum() {
		return access.isEnum();
	}

	public int getSlotId() {
		return soltId;
	}

	public void setSoltId(int soltId) {
		this.soltId = soltId;
	}

	public boolean needDoubleSlot() {
		return getDescriptor().equals("J") || getDescriptor().equals("D");
	}
	
	public int getConstantValueIndex() {
		return constantValueIndex;
	}
	
	@Override
	public String toString() {
		return getClassInfo().toString() + "." + getName() + " " + getDescriptor();
	}

}
