package cn.yusei.jvm.classfile.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

public class DoubleConstantInfo extends ValueConstantInfo<Double> {

	DoubleConstantInfo() {
	}
	
	@Override
	public int getTag() {
		return ConstantTag.DOUBLE;
	}

	@Override
	public void readInfo(DataInputStream data) throws IOException {
		this.value = data.readDouble();
	}
	
}
