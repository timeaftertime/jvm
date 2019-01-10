package cn.yusei.jvm.instruction.stack;

import cn.yusei.jvm.instruction.base.NoOperandInstruction;
import cn.yusei.jvm.runtimespace.Frame;
import cn.yusei.jvm.runtimespace.OperandStack;
import cn.yusei.jvm.runtimespace.Slot;

public class SWAP extends NoOperandInstruction {

	@Override
	public void execute(Frame frame) {
		OperandStack stack = frame.getOperandStack();
		Slot slot1 = stack.popSlot();
		Slot slot2 = stack.popSlot();
		stack.pushSlot(slot1);
		stack.pushSlot(slot2);
	}

}
