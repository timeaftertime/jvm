package cn.yusei.jvm.slot;

import java.util.Arrays;

import cn.yusei.jvm.ObjectRef;

public abstract class SlotsOperator {

	private Slot[] slots;
	
	public SlotsOperator(int capacity) {
		this.slots = new Slot[capacity];
		for(int i=0; i<capacity; i++)
			slots[i] = new Slot(0, null);
	}

	protected void setInt(int index, int value) {
		accessIndexCheck(index, 0);
		slots[index] = new Slot(value, null);
	}

	protected void setLong(int index, long value) {
		accessIndexCheck(index, 1);
		setInt(index, (int) (value >> 32));
		setInt(index + 1, (int) value);
	}

	protected void setFloat(int index, float value) {
		setInt(index, Float.floatToIntBits(value));
	}

	protected void setDouble(int index, double value) {
		setLong(index, Double.doubleToRawLongBits(value));
	}

	protected void setRef(int index, ObjectRef ref) {
		accessIndexCheck(index, 0);
		slots[index] = new Slot(0, ref);
	}

	protected ObjectRef getRef(int index) {
		accessIndexCheck(index, 0);
		return slots[index].getRef();
	}

	protected int getInt(int index) {
		accessIndexCheck(index, 0);
		return slots[index].getValue();
	}
	
	protected long getLong(int index) {
		accessIndexCheck(index, 1);
		return ((long) getInt(index) << 32) | (getInt(index + 1) & 0xFFFFFFFFL);
	}
	
	protected float getFloat(int index) {
		return Float.intBitsToFloat(getInt(index));
	}
	
	protected double getDouble(int index) {
		return Double.longBitsToDouble(getLong(index));
	}

	private void accessIndexCheck(int startIndex, int space) {
		int endIndex = startIndex + space;
		if(startIndex < 0 || startIndex >= slots.length)
			throw new SlotIndexOutOfBoundsException(startIndex);
		if(endIndex < 0 || endIndex >= slots.length)
			throw new SlotIndexOutOfBoundsException(endIndex);
	}
	
	public int getCapacity() {
		return slots.length;
	}

	protected void setSlot(int index, Slot slot) {
		accessIndexCheck(index, 0);
		slots[index] = slot;
	}

	protected Slot getSlot(int index) {
		accessIndexCheck(index, 0);
		return slots[index];
	}
	
	@Override
	public String toString() {
		return Arrays.toString(slots);
	}

}
