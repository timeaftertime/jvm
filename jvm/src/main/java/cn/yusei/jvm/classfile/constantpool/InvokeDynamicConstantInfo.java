package cn.yusei.jvm.classfile.constantpool;

import java.io.DataInputStream;

public class InvokeDynamicConstantInfo implements ConstantInfo {

	InvokeDynamicConstantInfo() {
	}
	
	@Override
	public int getTag() {
		return ConstantTag.INVOKE_DYNAMIC;
	}

	@Override
	public void readInfo(DataInputStream data) {
		throw new RuntimeException("暂未实现该常量类型");
	}

}
