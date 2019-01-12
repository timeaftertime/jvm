package cn.yusei.jvm.classfile.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

public class DoubleConstantInfo extends BaseValueConstantInfo<Double> {

	DoubleConstantInfo() {
	}
	
	@Override
	public int getTag() {
		return ConstantTag.DOUBLE;
	}

	@Override
	public void readInfo(DataInputStream data, ConstantPool pool) throws IOException {
		this.value = data.readDouble();
	}
	
}
