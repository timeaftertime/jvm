package cn.yusei.jvm.classfile.member.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import cn.yusei.jvm.classfile.constantpool.ConstantPool;

public abstract class Attribute {

	// 属性（类）名的索引
	protected int nameIndex;
	// 属性的长度
	protected int length;
	
	public Attribute(int nameIndex, int length) {
		this.nameIndex = nameIndex;
		this.length = length;
	}
	
	public abstract void readInfo(int attrLen, DataInputStream data, ConstantPool constantPool) throws IOException;
	
}
