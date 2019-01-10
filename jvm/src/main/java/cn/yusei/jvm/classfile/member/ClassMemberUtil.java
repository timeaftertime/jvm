package cn.yusei.jvm.classfile.member;

import java.io.DataInputStream;
import java.io.IOException;

import cn.yusei.jvm.classfile.constantpool.ConstantPool;
import cn.yusei.jvm.classfile.member.attribute.Attribute;
import cn.yusei.jvm.classfile.member.attribute.AttributeFactory;

public final class ClassMemberUtil {

	private ClassMemberUtil() {
	}

	public static Attribute[] readAttributes(DataInputStream data, ConstantPool constantPool) throws IOException {
		int attributeCount = data.readUnsignedShort();
		Attribute[] attributes = new Attribute[attributeCount];
		for (int i = 0; i < attributeCount; i++) {
			int attrNameIndex = data.readUnsignedShort();
			int attrLen = data.readInt();
			String attrName = constantPool.getUTF8(attrNameIndex).getInfo();
			Attribute attribute = AttributeFactory.createAttribute(attrNameIndex, attrName, attrLen);
			attribute.readInfo(attrLen, data, constantPool);
			attributes[i] = attribute;
		}
		return attributes;
	}

}
