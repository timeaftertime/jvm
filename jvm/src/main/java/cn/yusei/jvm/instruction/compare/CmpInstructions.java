package cn.yusei.jvm.instruction.compare;

import cn.yusei.jvm.instruction.base.NoOperandInstruction;
import cn.yusei.jvm.runtimespace.stack.Frame;
import cn.yusei.jvm.runtimespace.stack.OperandStack;

public class CmpInstructions {

	public static class LCMP extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			long op1 = stack.popLong();
			long op2 = stack.popLong();
			if(op2 > op1)
				stack.pushInt(1);
			else if(op2 == op1)
				stack.pushInt(0);
			else
				stack.pushInt(-1);
		}

	}

	public static class FCMPG extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			float op1 = stack.popFloat();
			float op2 = stack.popFloat();
			if(op2 > op1)
				stack.pushInt(1);
			else if(op2 == op1)
				stack.pushInt(0);
			else if(op2 < op1)
				stack.pushInt(-1);
			else
				stack.pushInt(1);
		}

	}

	public static class FCMPL extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			float op1 = stack.popFloat();
			float op2 = stack.popFloat();
			if(op2 > op1)
				stack.pushInt(1);
			else if(op2 == op1)
				stack.pushInt(0);
			else if(op2 < op1)
				stack.pushInt(-1);
			else
				stack.pushInt(-1);
		}

	}

	public static class DCMPG extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			double op1 = stack.popDouble();
			double op2 = stack.popDouble();
			if(op2 > op1)
				stack.pushInt(1);
			else if(op2 == op1)
				stack.pushInt(0);
			else if(op2 < op1)
				stack.pushInt(-1);
			else
				stack.pushInt(1);
		}

	}

	public static class DCMPL extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			double op1 = stack.popDouble();
			double op2 = stack.popDouble();
			if(op2 > op1)
				stack.pushInt(1);
			else if(op2 == op1)
				stack.pushInt(0);
			else if(op2 < op1)
				stack.pushInt(-1);
			else
				stack.pushInt(-1);
		}

	}

}
