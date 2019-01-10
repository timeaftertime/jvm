package cn.yusei.jvm.classfile.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

public class ClassConstantInfo implements ConstantInfo {

	private int nameIndex;

	ClassConstantInfo() {
	}

	@Override
	public int getTag() {
		return ConstantTag.CLASS;
	}

	@Override
	public void readInfo(DataInputStream data) throws IOException {
		nameIndex = data.readUnsignedShort();
	}

	public int getNameIndex() {
		return nameIndex;
	}

}
