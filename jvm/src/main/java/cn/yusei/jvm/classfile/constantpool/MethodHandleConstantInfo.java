package cn.yusei.jvm.classfile.constantpool;

import java.io.DataInputStream;

public class MethodHandleConstantInfo implements ConstantInfo {

	MethodHandleConstantInfo() {
	}
	
	@Override
	public int getTag() {
		return ConstantTag.METHOD_HANDLE;
	}

	@Override
	public void readInfo(DataInputStream data) {
		throw new RuntimeException("暂未实现该常量类型");
	}

}
