package cn.yusei.jvm.instruction.extend;

import cn.yusei.jvm.instruction.base.Int32BranchInstruction;
import cn.yusei.jvm.runtimespace.stack.Frame;

public class GOTO_W extends Int32BranchInstruction {

	@Override
	protected boolean shouldJump(Frame frame) {
		return true;
	}

}
