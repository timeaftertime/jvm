package cn.yusei.jvm.instruction.base;

import java.io.IOException;

import cn.yusei.jvm.instruction.BytecodeReader;

public abstract class Int8Instrcution extends OneOperandInstruction {

	@Override
	protected final int readOperand(BytecodeReader reader) throws IOException {
		return reader.readInt8();
	}

}
