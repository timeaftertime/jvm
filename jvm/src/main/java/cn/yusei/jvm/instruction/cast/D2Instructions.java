package cn.yusei.jvm.instruction.cast;

import cn.yusei.jvm.instruction.base.NoOperandInstruction;
import cn.yusei.jvm.runtimespace.stack.Frame;
import cn.yusei.jvm.runtimespace.stack.OperandStack;

public class D2Instructions {

	public static class D2I extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			stack.pushInt((int) stack.popDouble());
		}

	}

	public static class D2L extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			stack.pushLong((long) stack.popDouble());
		}

	}

	public static class D2F extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			stack.pushFloat((float) stack.popDouble());
		}

	}
}
