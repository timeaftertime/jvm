package cn.yusei.jvm.classfile.member;

import java.io.DataInputStream;
import java.io.IOException;

import cn.yusei.jvm.classfile.AccessMask;
import cn.yusei.jvm.classfile.constantpool.ConstantPool;
import cn.yusei.jvm.classfile.member.attribute.Attribute;
import cn.yusei.jvm.classfile.member.attribute.CodeAttribute;

public class ClassMember {

	protected AccessMask accessMask;
	protected int nameIndex;
	protected int descriptorIndex;
	protected Attribute[] attributes;
	private ConstantPool constantPool;
	
	public ClassMember(DataInputStream data, ConstantPool constantPool) throws IOException {
		accessMask = new AccessMask(data.readUnsignedShort());
		nameIndex = data.readUnsignedShort();
		descriptorIndex = data.readUnsignedShort();
		this.constantPool = constantPool;
		attributes = ClassMemberUtil.readAttributes(data, constantPool);
	}
	
	public String getName() {
		return constantPool.getUTF8(nameIndex).getInfo();
	}
	
	public String getDescriptor() {
		return constantPool.getUTF8(descriptorIndex).getInfo();
	}
	
	public CodeAttribute getCodeAttribute() {
		for(Attribute attribute : attributes) {
			if(attribute instanceof CodeAttribute)
				return (CodeAttribute) attribute;
		}
		return null;
	}
	
}
