package cn.yusei.jvm.classfile.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

public class LongConstantInfo extends BaseValueConstantInfo<Long> {

	LongConstantInfo() {
	}
	
	@Override
	public int getTag() {
		return ConstantTag.LONG;
	}

	@Override
	public void readInfo(DataInputStream data, ConstantPool pool) throws IOException {
		this.value = data.readLong();
	}
	
}
