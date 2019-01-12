package cn.yusei.jvm.instruction.math;

import cn.yusei.jvm.instruction.base.NoOperandInstruction;
import cn.yusei.jvm.runtimespace.stack.Frame;
import cn.yusei.jvm.runtimespace.stack.OperandStack;

public class AndInstructions {
	
	public static class IAND extends NoOperandInstruction {
		
		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			stack.pushInt(stack.popInt() & stack.popInt());
		}
		
	}

	public static class LAND extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			stack.pushLong(stack.popLong() & stack.popLong());
		}

	}

}
