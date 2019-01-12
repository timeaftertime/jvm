package cn.yusei.jvm.instruction.math;

import cn.yusei.jvm.instruction.base.NoOperandInstruction;
import cn.yusei.jvm.runtimespace.stack.Frame;
import cn.yusei.jvm.runtimespace.stack.OperandStack;

public class OrInstructions {

	public static class IOR extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			stack.pushInt(stack.popInt() | stack.popInt());
		}

	}

	public static class LOR extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			stack.pushLong(stack.popLong() | stack.popLong());
		}

	}
}
