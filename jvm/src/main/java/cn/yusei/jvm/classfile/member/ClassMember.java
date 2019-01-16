package cn.yusei.jvm.classfile.member;

import java.io.DataInputStream;
import java.io.IOException;

import cn.yusei.jvm.classfile.AccessMask;
import cn.yusei.jvm.classfile.constantpool.ConstantPool;
import cn.yusei.jvm.classfile.member.attribute.Attribute;
import cn.yusei.jvm.classfile.member.attribute.CodeAttribute;
import cn.yusei.jvm.classfile.member.attribute.ConstantValueAttribute;

public class ClassMember {

	protected AccessMask accessMask;
	protected int nameIndex;
	protected int descriptorIndex;
	protected Attribute[] attributes;

	public ClassMember(DataInputStream data, ConstantPool constantPool) throws IOException {
		accessMask = new AccessMask(data.readUnsignedShort());
		nameIndex = data.readUnsignedShort();
		descriptorIndex = data.readUnsignedShort();
		attributes = ClassMemberUtil.readAttributes(data, constantPool);
	}

	public CodeAttribute getCodeAttribute() {
		for (Attribute attribute : attributes) {
			if (attribute instanceof CodeAttribute)
				return (CodeAttribute) attribute;
		}
		return null;
	}

	public ConstantValueAttribute getConstantValueAttribute() {
		for (Attribute attribute : attributes) {
			if (attribute instanceof ConstantValueAttribute)
				return (ConstantValueAttribute) attribute;
		}
		return null;
	}

	public AccessMask getAccessMask() {
		return accessMask;
	}

	public int getNameIndex() {
		return nameIndex;
	}

	public int getDescriptorIndex() {
		return descriptorIndex;
	}

	@Override
	public String toString() {
		return "ClassMember [nameIndex=" + nameIndex + ", descriptorIndex=" + descriptorIndex + "]";
	}

}
