package cn.yusei.jvm.classfile.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

public class MethodTypeConstantInfo implements ConstantInfo {

	private int descriptorIndex;

	MethodTypeConstantInfo() {
	}

	@Override
	public int getTag() {
		return ConstantTag.METHOD_TYPE;
	}

	@Override
	public void readInfo(DataInputStream data, ConstantPool pool) throws IOException {
		descriptorIndex = data.readUnsignedShort();
	}

	public int getDescriptorIndex() {
		return descriptorIndex;
	}

}
