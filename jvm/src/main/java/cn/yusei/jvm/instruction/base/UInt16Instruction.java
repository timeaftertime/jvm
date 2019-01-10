package cn.yusei.jvm.instruction.base;

import java.io.IOException;

import cn.yusei.jvm.instruction.BytecodeReader;

public abstract class UInt16Instruction extends OneOperandInstruction {

	@Override
	public final int readOperand(BytecodeReader reader) throws IOException {
		return reader.readUint16();
	}

}
