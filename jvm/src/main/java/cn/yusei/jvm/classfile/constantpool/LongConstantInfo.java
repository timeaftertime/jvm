package cn.yusei.jvm.classfile.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

public class LongConstantInfo extends ValueConstantInfo<Long> {

	LongConstantInfo() {
	}
	
	@Override
	public int getTag() {
		return ConstantTag.LONG;
	}

	@Override
	public void readInfo(DataInputStream data) throws IOException {
		this.value = data.readLong();
	}
	
}
