package cn.yusei.jvm.classfile.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

public class StringConstantInfo implements ConstantInfo {

	private int index;
	
	StringConstantInfo() {
	}
	
	@Override
	public int getTag() {
		return ConstantTag.STRING;
	}

	@Override
	public void readInfo(DataInputStream data) throws IOException {
		index = data.readUnsignedShort();
	}

	public int getIndex() {
		return index;
	}
	
}
