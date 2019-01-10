package cn.yusei.jvm.instruction.math;

import cn.yusei.jvm.instruction.base.NoOperandInstruction;
import cn.yusei.jvm.runtimespace.Frame;
import cn.yusei.jvm.runtimespace.OperandStack;

public class ShInstructions {

	public static class ISHL extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			int op1 = stack.popInt();
			int op2 = stack.popInt();
			op1 &= 0x1f;
			stack.pushInt(op2 << op1);
		}
		
	}
	public static class ISHR extends NoOperandInstruction {
		
		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			int op1 = stack.popInt();
			int op2 = stack.popInt();
			op1 &= 0x1f;
			stack.pushInt(op2 >> op1);
		}
		
	}
	public static class IUSHR extends NoOperandInstruction {
		
		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			int op1 = stack.popInt();
			int op2 = stack.popInt();
			op1 &= 0x1f;
			stack.pushInt(op2 >>> op1);
		}
		
	}
	public static class LSHL extends NoOperandInstruction {
		
		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			int op1 = stack.popInt();
			long op2 = stack.popLong();
			op1 &= 0x3f;
			stack.pushLong(op2 << op1);
		}
		
	}
	public static class LSHR extends NoOperandInstruction {
		
		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			int op1 = stack.popInt();
			long op2 = stack.popLong();
			op1 &= 0x3f;
			stack.pushLong(op2 >> op1);
		}
		
	}
	public static class LUSHR extends NoOperandInstruction {
		
		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			int op1 = stack.popInt();
			long op2 = stack.popLong();
			op1 &= 0x3f;
			stack.pushLong(op2 >>> op1);
		}
		
	}
	
}
