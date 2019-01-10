package cn.yusei.jvm.runtimespace;

public class SlotIndexOutOfBoundsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SlotIndexOutOfBoundsException(int index) {
		super("" + index);
	}

}
