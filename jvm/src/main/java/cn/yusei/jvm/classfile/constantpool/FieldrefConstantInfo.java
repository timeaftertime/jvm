package cn.yusei.jvm.classfile.constantpool;

public class FieldrefConstantInfo extends RefConstantInfo {

	FieldrefConstantInfo() {
	}
	
	@Override
	public int getTag() {
		return ConstantTag.FIELDREF;
	}

}
