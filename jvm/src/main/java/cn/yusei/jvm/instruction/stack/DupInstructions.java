package cn.yusei.jvm.instruction.stack;

import cn.yusei.jvm.instruction.base.NoOperandInstruction;
import cn.yusei.jvm.runtimespace.Frame;
import cn.yusei.jvm.runtimespace.OperandStack;
import cn.yusei.jvm.runtimespace.Slot;

public class DupInstructions {

	public static class DUP extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			Slot slot = stack.popSlot();
			stack.pushSlot(slot);
			stack.pushSlot(slot);
		}

	}

	public static class DUP_X1 extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			Slot slot = stack.popSlot();
			Slot offset1 = stack.popSlot();
			stack.pushSlot(slot);
			stack.pushSlot(offset1);
			stack.pushSlot(slot);
		}

	}

	public static class DUP_X2 extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			Slot slot = stack.popSlot();
			Slot offset1 = stack.popSlot();
			Slot offset2 = stack.popSlot();
			stack.pushSlot(slot);
			stack.pushSlot(offset2);
			stack.pushSlot(offset1);
			stack.pushSlot(slot);
		}

	}

	public static class DUP2 extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			Slot slot2 = stack.popSlot();
			Slot slot1 = stack.popSlot();
			stack.pushSlot(slot1);
			stack.pushSlot(slot2);
			stack.pushSlot(slot1);
			stack.pushSlot(slot2);
		}

	}

	public static class DUP2_X1 extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			Slot slot2 = stack.popSlot();
			Slot slot1 = stack.popSlot();
			Slot offset1 = stack.popSlot();
			stack.pushSlot(slot1);
			stack.pushSlot(slot2);
			stack.pushSlot(offset1);
			stack.pushSlot(slot1);
			stack.pushSlot(slot2);
		}

	}

	public static class DUP2_X2 extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			Slot slot2 = stack.popSlot();
			Slot slot1 = stack.popSlot();
			Slot offset1 = stack.popSlot();
			Slot offset2 = stack.popSlot();
			stack.pushSlot(slot1);
			stack.pushSlot(slot2);
			stack.pushSlot(offset2);
			stack.pushSlot(offset1);
			stack.pushSlot(slot1);
			stack.pushSlot(slot2);
		}

	}

}
