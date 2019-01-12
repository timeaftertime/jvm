package cn.yusei.jvm.instruction.compare;

import cn.yusei.jvm.instruction.base.Int16BranchInstruction;
import cn.yusei.jvm.runtimespace.stack.Frame;

public class IfIcmpInstructions {

	public static abstract class IF_ICMPX extends Int16BranchInstruction {

		@Override
		protected final boolean shouldJump(Frame frame) {
			return fitCondition(frame.getOperandStack().popInt(), frame.getOperandStack().popInt());
		}

		protected abstract boolean fitCondition(int op1, int op2);

	}

	public static class IF_ICMPEQ extends IF_ICMPX {

		@Override
		protected boolean fitCondition(int op1, int op2) {
			return op2 == op1;
		}

	}

	public static class IF_ICMPNE extends IF_ICMPX {

		@Override
		protected boolean fitCondition(int op1, int op2) {
			return op2 != op1;
		}

	}

	public static class IF_ICMPLT extends IF_ICMPX {

		@Override
		protected boolean fitCondition(int op1, int op2) {
			return op2 < op1;
		}

	}

	public static class IF_ICMPLE extends IF_ICMPX {

		@Override
		protected boolean fitCondition(int op1, int op2) {
			return op2 <= op1;
		}

	}

	public static class IF_ICMPGT extends IF_ICMPX {

		@Override
		protected boolean fitCondition(int op1, int op2) {
			return op2 > op1;
		}

	}

	public static class IF_ICMPGE extends IF_ICMPX {

		@Override
		protected boolean fitCondition(int op1, int op2) {
			return op2 >= op1;
		}

	}

}
