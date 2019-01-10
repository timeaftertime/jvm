package cn.yusei.jvm.classfile.member.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import cn.yusei.jvm.classfile.constantpool.ConstantPool;

/**
 * 记录方法 throws 后面的异常
 */
public class ExceptionsAttribute extends Attribute {

	private int[] exceptionIndexTable;

	public ExceptionsAttribute(int nameIndex, int length) {
		super(nameIndex, length);
	}

	@Override
	public void readInfo(int attrLen, DataInputStream data, ConstantPool constantPool) throws IOException {
		// 2 个字节无符号异常类型索引，指向常量池一个 CONSTANT_Class_info 型常量
		int tableCount = data.readUnsignedShort();
		exceptionIndexTable = new int[tableCount];
		for(int i=0; i<tableCount; i++) {
			exceptionIndexTable[i] = data.readUnsignedShort();
		}
	}

}
