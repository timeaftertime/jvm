package cn.yusei.jvm.classfile.member.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import cn.yusei.jvm.classfile.constantpool.ConstantPool;

public class UnparsedAttribute extends Attribute {

	public UnparsedAttribute(int nameIndex, int length) {
		super(nameIndex, length);
	}

	private byte[] info;

	@Override
	public void readInfo(int attrLen, DataInputStream data, ConstantPool constantPool) throws IOException {
		info = new byte[length];
		data.read(info);
	}

}
