package cn.yusei.jvm.instruction.stack;

import cn.yusei.jvm.instruction.base.NoOperandInstruction;
import cn.yusei.jvm.runtimespace.Frame;

public class PopInstructions {

	public static class POP extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().popSlot();
		}

	}

	public static class POP2 extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().popSlot();
			frame.getOperandStack().popSlot();
		}

	}

}
