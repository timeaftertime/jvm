package cn.yusei.jvm.instruction.constant;

import cn.yusei.jvm.instruction.base.NoOperandInstruction;
import cn.yusei.jvm.runtimespace.Frame;

public class ConstInstructions {

	public static class ACONST_NULL extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushRef(null);
		}

	}

	public static class DCONST_0 extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushDouble(0);
		}

	}

	public static class DCONST_1 extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushDouble(1);
		}

	}

	public static class FCONST_0 extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushFloat(0f);
		}

	}

	public static class FCONST_1 extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushFloat(1f);
		}

	}
	public static class FCONST_2 extends NoOperandInstruction {
		
		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushFloat(2f);
		}
		
	}

	public static class ICONST_M1 extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushInt(-1);
		}

	}

	public static class ICONST_0 extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushInt(0);
		}

	}

	public static class ICONST_1 extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushInt(1);
		}

	}

	public static class ICONST_2 extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushInt(2);
		}

	}

	public static class ICONST_3 extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushInt(3);
		}

	}

	public static class ICONST_4 extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushInt(4);
		}

	}

	public static class ICONST_5 extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushInt(5);
		}

	}

	public static class LCONST_0 extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushLong(0);
		}

	}

	public static class LCONST_1 extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushLong(1);
		}

	}

}
