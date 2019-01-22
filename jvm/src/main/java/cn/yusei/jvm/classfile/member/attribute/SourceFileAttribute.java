package cn.yusei.jvm.classfile.member.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import cn.yusei.jvm.classfile.constantpool.ConstantPool;

/**
 * 源文件名
 */
public class SourceFileAttribute extends Attribute {

	private int sourceFileIndex;

	public SourceFileAttribute(int nameIndex, int length) {
		super(nameIndex, length);
		if (length != 2)
			throw new AssertionError("length 必须为 2：" + length);
	}

	@Override
	public void readInfo(int attrLen, DataInputStream data, ConstantPool constantPool) throws IOException {
		sourceFileIndex = data.readUnsignedShort();
	}

	public int getSourceFileIndex() {
		return sourceFileIndex;
	}

}
