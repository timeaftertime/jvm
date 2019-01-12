package cn.yusei.jvm.classfile.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

public class NameAndTypeConstantInfo implements ConstantInfo {

	private int nameIndex;
	private int descriptorIndex;
	
	NameAndTypeConstantInfo() {
	}
	
	@Override
	public int getTag() {
		return ConstantTag.NAME_AND_TYPE;
	}

	@Override
	public void readInfo(DataInputStream data, ConstantPool pool) throws IOException {
		nameIndex = data.readUnsignedShort();
		descriptorIndex = data.readUnsignedShort();
	}
	
	public int getNameIndex() {
		return nameIndex;
	}
	
	public int getDescriptorIndex() {
		return descriptorIndex;
	}

}
