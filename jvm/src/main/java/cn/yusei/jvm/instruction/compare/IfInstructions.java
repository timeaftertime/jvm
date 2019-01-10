package cn.yusei.jvm.instruction.compare;

import cn.yusei.jvm.instruction.base.Int16BranchInstruction;
import cn.yusei.jvm.runtimespace.Frame;

public class IfInstructions {

	public abstract static class IFX extends Int16BranchInstruction {

		@Override
		protected final boolean shouldJump(Frame frame) {
			return fitCondition(0, frame.getOperandStack().popInt());
		}

		public abstract boolean fitCondition(int op1, int op2);

	}

	public static class IFEQ extends IFX {

		@Override
		public boolean fitCondition(int op1, int op2) {
			return op2 == op1;
		}

	}

	public static class IFNE extends IFX {

		@Override
		public boolean fitCondition(int op1, int op2) {
			return op2 != op1;
		}

	}

	public static class IFLT extends IFX {

		@Override
		public boolean fitCondition(int op1, int op2) {
			return op2 < op1;
		}

	}

	public static class IFLE extends IFX {

		@Override
		public boolean fitCondition(int op1, int op2) {
			return op2 <= op1;
		}

	}

	public static class IFGT extends IFX {

		@Override
		public boolean fitCondition(int op1, int op2) {
			return op2 > op1;
		}

	}

	public static class IFGE extends IFX {

		@Override
		public boolean fitCondition(int op1, int op2) {
			return op2 >= op1;
		}

	}

}
