package cn.yusei.jvm;

import cn.yusei.jvm.slot.BaseSlotOperator;

public class MemberSlots extends BaseSlotOperator {

	public MemberSlots(int capacity) {
		super(capacity);
	}

	/**
	 * 当前对象的 slotId 对应的 Slot 必须是一个对 String 的 ClassInfo 的引用
	 * 将类的一个 {@code java.lang.String } 对象直接返回
	 * 
	 * @param slotId
	 * @return
	 */
	public String getString(int slotId) {
		Field valueField = getRef(slotId).getClassInfo().getField("value", "[C");
		return new String(getRef(slotId).getMembers().getRef(valueField.getSlotId()).chars());
	}

}