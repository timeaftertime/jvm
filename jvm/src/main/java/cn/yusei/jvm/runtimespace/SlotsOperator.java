package cn.yusei.jvm.runtimespace;

public abstract class SlotsOperator {

	private Slot[] slots;
	
	public SlotsOperator(int capacity) {
		this.slots = new Slot[capacity];
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

	protected void setRef(int index, Object ref) {
		accessIndexCheck(index, 0);
		slots[index] = new Slot(0, ref);
	}

	protected Object getRef(int index) {
		readIndexCheck(index, 0);
		return slots[index].getRef();
	}

	protected int getInt(int index) {
		readIndexCheck(index, 0);
		return slots[index].getValue();
	}
	
	protected long getLong(int index) {
		readIndexCheck(index, 1);
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
	
	private void readIndexCheck(int startIndex, int space) {
		accessIndexCheck(startIndex, space);
		int endIndex = startIndex + space;
		for(int i=startIndex; i<=endIndex; i++) {
			if(slots[i] == null)
				throw new SlotNotSetException(i);
		}
	}
	
	public int getCapacity() {
		return slots.length;
	}

	protected void setSlot(int index, Slot slot) {
		accessIndexCheck(index, 0);
		slots[index] = slot;
	}

	protected Slot getSlot(int index) {
		readIndexCheck(index, 0);
		return slots[index];
	}

}
