package cn.yusei.jvm.instruction.base;

import java.io.IOException;

import cn.yusei.jvm.instruction.BytecodeReader;

public abstract class Int32Instruction extends OneOperandInstruction {

	@Override
	protected int readOperand(BytecodeReader reader) throws IOException {
		return reader.readInt32();
	}

}
