package cn.yusei.jvm.classfile.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

public class MethodHandleConstantInfo implements ConstantInfo {

	private int refKind;
	private int refIndex;

	MethodHandleConstantInfo() {
	}

	@Override
	public int getTag() {
		return ConstantTag.METHOD_HANDLE;
	}

	@Override
	public void readInfo(DataInputStream data, ConstantPool pool) throws IOException {
		refKind = data.readUnsignedByte();
		refIndex = data.readUnsignedShort();
	}

	public int getRefKind() {
		return refKind;
	}

	public int getRefIndex() {
		return refIndex;
	}

}
