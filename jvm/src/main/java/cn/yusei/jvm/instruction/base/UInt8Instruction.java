package cn.yusei.jvm.instruction.base;

import java.io.IOException;

import cn.yusei.jvm.instruction.BytecodeReader;

public abstract class UInt8Instruction extends OneOperandInstruction {

	@Override
	public int readOperand(BytecodeReader reader) throws IOException {
		return reader.readUint8();
	}

}
