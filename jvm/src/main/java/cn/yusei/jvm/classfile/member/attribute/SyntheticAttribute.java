package cn.yusei.jvm.classfile.member.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import cn.yusei.jvm.classfile.constantpool.ConstantPool;

/**
 * 标记由系统产生
 */
public class SyntheticAttribute extends Attribute {

	public SyntheticAttribute(int nameIndex, int length) {
		super(nameIndex, length);
		if (length != 0)
			throw new AssertionError("length 必须为 0：" + length);
	}

	@Override
	public void readInfo(int attrLen, DataInputStream data, ConstantPool constantPool) throws IOException {
	}

}
