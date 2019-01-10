package cn.yusei.jvm.classfile.constantpool;

public class InterfaceMethodrefConstantInfo extends RefConstantInfo {

	
	InterfaceMethodrefConstantInfo() {
	}
	
	@Override
	public int getTag() {
		return ConstantTag.INTERFACE_METHODREF;
	}

}
