package cn.yusei.jvm.classfile.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

public class StringConstantInfo extends ValueConstantInfo<String> {

	private int index;
	private ConstantPool constantPool;
	
	StringConstantInfo() {
	}
	
	@Override
	public int getTag() {
		return ConstantTag.STRING;
	}

	@Override
	public void readInfo(DataInputStream data, ConstantPool pool) throws IOException {
		index = data.readUnsignedShort();
		constantPool = pool;
	}

	public int getIndex() {
		return index;
	}

	@Override
	public String getValue() {
		return constantPool.getUTF8(index).getValue();
	}
	
}
