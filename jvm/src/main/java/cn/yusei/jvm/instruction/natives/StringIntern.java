package cn.yusei.jvm.instruction.natives;

import cn.yusei.jvm.Field;
import cn.yusei.jvm.ObjectRef;
import cn.yusei.jvm.StringPool;
import cn.yusei.jvm.runtimespace.stack.Frame;

public class StringIntern implements NativeMethod {

	@Override
	public void invoke(Frame frame) {
		ObjectRef ref = frame.getLocalVarsTable().getRef(0);
		Field field = ref.getClassInfo().getField("value", "[C");
		frame.getOperandStack().pushRef(StringPool.getString(ref.getClassInfo().getLoader(),
				new String(ref.getMembers().getRef(field.getSlotId()).chars())));
	}

}
