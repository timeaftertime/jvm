package cn.yusei.jvm.instruction.control;

import cn.yusei.jvm.instruction.base.Int16BranchInstruction;
import cn.yusei.jvm.runtimespace.stack.Frame;

public class GOTO extends Int16BranchInstruction {

	@Override
	protected boolean shouldJump(Frame frame) {
		return true;
	}

}
