package cn.yusei.jvm.classfile.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

public class FloatConstantInfo extends BaseValueConstantInfo<Float> {

	FloatConstantInfo() {
	}
	
	@Override
	public int getTag() {
		return ConstantTag.FLOAT;
	}

	@Override
	public void readInfo(DataInputStream data, ConstantPool pool) throws IOException {
		this.value = data.readFloat();
	}

}
