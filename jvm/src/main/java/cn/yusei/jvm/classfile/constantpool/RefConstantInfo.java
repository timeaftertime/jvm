package cn.yusei.jvm.classfile.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

public abstract class RefConstantInfo implements ConstantInfo {

	protected int classIndex;
	protected int nameAndTypeIndex;
	
	@Override
	public void readInfo(DataInputStream data, ConstantPool pool) throws IOException {
		classIndex = data.readUnsignedShort();
		nameAndTypeIndex = data.readUnsignedShort();
	}
	
	public int getClassIndex() {
		return classIndex;
	}
	
	public int getNameAndTypeIndex() {
		return nameAndTypeIndex;
	}
	
}
