package cn.yusei.jvm.runtimespace;

public class Slot {

	private int value;
	private Object ref;

	public Slot(int value, Object ref) {
		this.value = value;
		this.ref = ref;
	}

	public int getValue() {
		return value;
	}

	public Object getRef() {
		return ref;
	}

}
