package cn.yusei.jvm;

public class StackTrace {

	private String fileName;
	private String className;
	private String method;
	private int line;

	public StackTrace(String fileName, String className, String method, int line) {
		super();
		this.fileName = fileName;
		this.className = className;
		this.method = method;
		this.line = line;
	}

	@Override
	public String toString() {
		return "at " + className + "." + method + " (" + fileName + ":" + line + ")";
	}

}
