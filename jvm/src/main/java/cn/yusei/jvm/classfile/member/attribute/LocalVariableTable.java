package cn.yusei.jvm.classfile.member.attribute;

public class LocalVariableTable {

	private int startPc;
	private int length;
	private int nameIndex;
	private int descriptorIndex;
	private int index;
	
	public LocalVariableTable(int startPc, int length, int nameIndex, int descriptorIndex, int index) {
		this.startPc = startPc;
		this.length = length;
		this.nameIndex = nameIndex;
		this.descriptorIndex = descriptorIndex;
		this.index = index;
	}

}
