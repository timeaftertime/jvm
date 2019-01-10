package cn.yusei.jvm.instruction.base;

import cn.yusei.jvm.instruction.BytecodeReader;
import cn.yusei.jvm.instruction.Instruction;

public abstract class NoOperandInstruction implements Instruction {

	@Override
	public final void readOperands(BytecodeReader reader) {
	}

}
