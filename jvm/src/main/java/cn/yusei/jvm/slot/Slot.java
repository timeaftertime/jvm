package cn.yusei.jvm.slot;

import cn.yusei.jvm.ObjectRef;

public class Slot {

	private int value;
	private ObjectRef ref;

	public Slot(int value, ObjectRef ref) {
		this.value = value;
		this.ref = ref;
	}

	public int getValue() {
		return value;
	}

	public ObjectRef getRef() {
		return ref;
	}

	@Override
	public String toString() {
		return "Slot [value=" + value + ", ref=" + ref + "]";
	}

}
