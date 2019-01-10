package cn.yusei.jvm.instruction.base;

import java.io.IOException;

import cn.yusei.jvm.instruction.BytecodeReader;
import cn.yusei.jvm.instruction.Instruction;

public abstract class OneOperandInstruction implements Instruction {

	protected int operand;

	public void setOperand(int operand) {
		this.operand = operand;
	}

	@Override
	public final void readOperands(BytecodeReader reader) throws IOException {
		operand = readOperand(reader);
	}

	protected abstract int readOperand(BytecodeReader reader) throws IOException;

}
