package cn.yusei.jvm.instruction.base;

import cn.yusei.jvm.runtimespace.Frame;

public abstract class Int16BranchInstruction extends Int16Instruction {

	@Override
	public void execute(Frame frame) {
		if (shouldJump(frame)) {
			frame.resetNextPc(operand);
		}
	}

	protected abstract boolean shouldJump(Frame frame);

}
