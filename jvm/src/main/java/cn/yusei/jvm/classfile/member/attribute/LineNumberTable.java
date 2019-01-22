package cn.yusei.jvm.classfile.member.attribute;

public class LineNumberTable {

	private int startPc;
	private int lineNumber;

	public LineNumberTable(int startPc, int lineNumber) {
		this.startPc = startPc;
		this.lineNumber = lineNumber;
	}

	public int getStartPc() {
		return startPc;
	}

	public int getLineNumber() {
		return lineNumber;
	}

}
