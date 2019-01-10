package cn.yusei.jvm.classfile.member.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import cn.yusei.jvm.classfile.constantpool.ConstantPool;

public class LocalVariableTableAttribute extends Attribute {

	private LocalVariableTable[] localVariableTables;

	public LocalVariableTableAttribute(int nameIndex, int length) {
		super(nameIndex, length);
	}

	@Override
	public void readInfo(int attrLen, DataInputStream data, ConstantPool constantPool) throws IOException {
		int localVariableTableLength = data.readUnsignedShort();
		localVariableTables = new LocalVariableTable[localVariableTableLength];
		for (int i = 0; i < localVariableTableLength; i++) {
			// 2 个字节无符号数，局部变量生命周期开始的字节码偏移量
			int startPc = data.readUnsignedShort();
			// 2 个字节无符号数，局部变量作用域覆盖范围
			int length = data.readUnsignedShort();
			// 2 个字节无符号数，局部变量的名称
			int nameIndex = data.readUnsignedShort();
			// 2 个字节无符号数，局部变量的描述符
			int descriptorIndex = data.readUnsignedShort();
			// 2 个字节无符号数，局部变量在栈桢变量表中 Slot 的位置
			int index = data.readUnsignedShort();
			localVariableTables[i] = new LocalVariableTable(startPc, length, nameIndex, descriptorIndex, index);
		}
	}

}
