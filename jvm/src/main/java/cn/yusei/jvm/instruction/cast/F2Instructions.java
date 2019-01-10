package cn.yusei.jvm.instruction.cast;

import cn.yusei.jvm.instruction.base.NoOperandInstruction;
import cn.yusei.jvm.runtimespace.Frame;
import cn.yusei.jvm.runtimespace.OperandStack;

public class F2Instructions {

	public static class F2I extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			stack.pushInt((int) stack.popFloat());
		}

	}

	public static class F2L extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			stack.pushLong((long) stack.popFloat());
		}

	}

	public static class F2D extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			stack.pushDouble(stack.popFloat());
		}

	}
}
