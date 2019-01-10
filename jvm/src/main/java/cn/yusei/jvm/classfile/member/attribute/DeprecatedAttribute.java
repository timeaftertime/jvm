package cn.yusei.jvm.classfile.member.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import cn.yusei.jvm.classfile.constantpool.ConstantPool;

/**
 * 标记弃用 @Deprecated
 */
public class DeprecatedAttribute extends Attribute {

	public DeprecatedAttribute(int nameIndex, int length) {
		super(nameIndex, length);
		if(length != 0)
			throw new AssertionError("length 必须为 0：" + length);
	}

	@Override
	public void readInfo(int attrLen, DataInputStream data, ConstantPool constantPool) throws IOException {
	}

}
