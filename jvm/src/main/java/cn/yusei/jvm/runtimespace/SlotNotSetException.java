package cn.yusei.jvm.runtimespace;

public class SlotNotSetException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SlotNotSetException(int index) {
		super("" + index);
	}
}
