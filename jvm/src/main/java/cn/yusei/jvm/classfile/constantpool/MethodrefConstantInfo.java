package cn.yusei.jvm.classfile.constantpool;

public class MethodrefConstantInfo extends RefConstantInfo {

	MethodrefConstantInfo() {
	}
	
	@Override
	public int getTag() {
		return ConstantTag.METHODREF;
	}

}
