package cn.yusei.jvm.classfile.member.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import cn.yusei.jvm.classfile.constantpool.ConstantPool;

/**
 * 表示常量表达式的值
 * 只出现在字段 Member 中
 * 用于通知虚拟机自动为静态变量赋值，只有被 static 属性修饰的变量才可以使用这项属性
 */
public class ConstantValueAttribute extends Attribute {

	private int constantValueIndex;
	
	public ConstantValueAttribute(int nameIndex, int length) {
		super(nameIndex, length);
		if(length != 2)
			throw new AssertionError("length 必须为 2：" + length);
	}

	@Override
	public void readInfo(int attrLen, DataInputStream data, ConstantPool constantPool) throws IOException {
		constantValueIndex = data.readUnsignedShort();
	}

}
