package cn.yusei.jvm.classfile.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

public class FloatConstantInfo extends ValueConstantInfo<Float> {

	FloatConstantInfo() {
	}
	
	@Override
	public int getTag() {
		return ConstantTag.FLOAT;
	}

	@Override
	public void readInfo(DataInputStream data) throws IOException {
		this.value = data.readFloat();
	}

}
