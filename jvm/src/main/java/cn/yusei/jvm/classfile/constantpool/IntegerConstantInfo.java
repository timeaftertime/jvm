package cn.yusei.jvm.classfile.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

public class IntegerConstantInfo extends BaseValueConstantInfo<Integer> {
	
	IntegerConstantInfo() {
	}

	@Override
	public int getTag() {
		return ConstantTag.INTEGER;
	}

	@Override
	public void readInfo(DataInputStream data, ConstantPool pool) throws IOException {
		this.value = data.readInt();
	}

}
