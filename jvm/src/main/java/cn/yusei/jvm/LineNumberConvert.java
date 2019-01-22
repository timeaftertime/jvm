package cn.yusei.jvm;

import cn.yusei.jvm.classfile.member.attribute.LineNumberTable;

public class LineNumberConvert {

	private LineNumberTable[] tables;

	public LineNumberConvert(LineNumberTable[] lineNumberTables) {
		tables = lineNumberTables;
	}

	public int convertLineNumber(int pc) {
		for (int i = tables.length - 1; i >= 0; i--) {
			if (pc >= tables[i].getStartPc())
				return tables[i].getLineNumber();
		}
		return -1;
	}

}
