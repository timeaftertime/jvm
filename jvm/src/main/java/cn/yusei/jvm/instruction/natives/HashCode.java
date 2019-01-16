package cn.yusei.jvm.instruction.natives;

import cn.yusei.jvm.runtimespace.stack.Frame;

public class HashCode implements NativeMethod {

	@Override
	public void invoke(Frame frame) {
		frame.getOperandStack().pushInt(frame.getLocalVarsTable().getRef(0).hashCode());;
	}

}
