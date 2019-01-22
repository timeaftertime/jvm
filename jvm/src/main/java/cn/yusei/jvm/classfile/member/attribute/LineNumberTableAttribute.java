package cn.yusei.jvm.classfile.member.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import cn.yusei.jvm.classfile.constantpool.ConstantPool;

public class LineNumberTableAttribute extends Attribute {

	private LineNumberTable[] lineNumberTables;

	public LineNumberTableAttribute(int nameIndex, int length) {
		super(nameIndex, length);
	}

	@Override
	public void readInfo(int attrLen, DataInputStream data, ConstantPool constantPool) throws IOException {
		int lineNumberTableLength = data.readUnsignedShort();
		lineNumberTables = new LineNumberTable[lineNumberTableLength];
		for (int i = 0; i < lineNumberTableLength; i++) {
			int startPc = data.readUnsignedShort();
			int lineNumber = data.readUnsignedShort();
			lineNumberTables[i] = new LineNumberTable(startPc, lineNumber);
		}
	}

	public LineNumberTable[] getLineNumberTables() {
		return lineNumberTables;
	}

}
