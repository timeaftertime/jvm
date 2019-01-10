package cn.yusei.jvm.instruction.extend;

public class UnsupportedOpCodeError extends Error {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UnsupportedOpCodeError(String message) {
		super(message);
	}

}
