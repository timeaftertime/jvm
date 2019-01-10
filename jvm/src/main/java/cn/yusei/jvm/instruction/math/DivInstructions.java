package cn.yusei.jvm.instruction.math;

import cn.yusei.jvm.instruction.base.NoOperandInstruction;
import cn.yusei.jvm.runtimespace.Frame;
import cn.yusei.jvm.runtimespace.OperandStack;

public class DivInstructions {

	public static class IDIV extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			int op1 = stack.popInt();
			int op2 = stack.popInt();
			stack.pushInt(op2 / op1);
		}

	}

	public static class LDIV extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			long op1 = stack.popLong();
			long op2 = stack.popLong();
			stack.pushLong(op2 / op1);
		}

	}

	public static class FDIV extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			float op1 = stack.popFloat();
			float op2 = stack.popFloat();
			stack.pushFloat(op2 / op1);
		}

	}

	public static class DDIV extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			double op1 = stack.popDouble();
			double op2 = stack.popDouble();
			stack.pushDouble(op2 / op1);
		}

	}
}
