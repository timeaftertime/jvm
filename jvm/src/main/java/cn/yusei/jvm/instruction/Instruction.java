package cn.yusei.jvm.instruction;

import java.io.IOException;

import cn.yusei.jvm.runtimespace.stack.Frame;

public interface Instruction {

	void readOperands(BytecodeReader reader) throws IOException;

	void execute(Frame frame);

}
