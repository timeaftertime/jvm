package cn.yusei.jvm.instruction.cast;

import cn.yusei.jvm.instruction.base.NoOperandInstruction;
import cn.yusei.jvm.runtimespace.Frame;
import cn.yusei.jvm.runtimespace.OperandStack;

public class L2Instructions {

	public static class L2I extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			stack.pushInt((int) stack.popLong());
		}

	}

	public static class L2F extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			stack.pushFloat(stack.popLong());
		}

	}

	public static class L2D extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			stack.pushDouble(stack.popLong());
		}

	}
	
}
