package cn.yusei.jvm.runtimespace.stack;

import cn.yusei.jvm.slot.BaseSlotOperator;
import cn.yusei.jvm.slot.Slot;

public class LocalVarsTable extends BaseSlotOperator {

	public LocalVarsTable(int capacity) {
		super(capacity);
	}
	
	@Override
	public Slot getSlot(int index) {
		return super.getSlot(index);
	}

	@Override
	public void setSlot(int index, Slot slot) {
		super.setSlot(index, slot);
	}
	
}
