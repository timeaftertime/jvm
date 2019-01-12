package cn.yusei.jvm.instruction.cast;

import cn.yusei.jvm.instruction.base.NoOperandInstruction;
import cn.yusei.jvm.runtimespace.stack.Frame;
import cn.yusei.jvm.runtimespace.stack.OperandStack;

public class I2Instructions {

	public static class I2L extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			stack.pushLong(stack.popInt());
		}

	}

	public static class I2F extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			stack.pushFloat(stack.popInt());
		}

	}

	public static class I2D extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			stack.pushDouble(stack.popInt());
		}

	}

}
