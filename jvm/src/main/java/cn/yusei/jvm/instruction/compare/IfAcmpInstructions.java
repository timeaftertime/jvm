package cn.yusei.jvm.instruction.compare;

import cn.yusei.jvm.instruction.base.Int16BranchInstruction;
import cn.yusei.jvm.runtimespace.Frame;

public class IfAcmpInstructions {

	public abstract static class IF_ACMPX extends Int16BranchInstruction {

		@Override
		protected final boolean shouldJump(Frame frame) {
			return fitCondition(frame.getOperandStack().popRef(), frame.getOperandStack().popRef());
		}

		protected abstract boolean fitCondition(Object op1, Object op2);

	}

	public static class IF_ACMPEQ extends IF_ACMPX {

		@Override
		protected boolean fitCondition(Object op1, Object op2) {
			return op2 == op1;
		}

	}

	public static class IF_ACMPNE extends IF_ACMPX {

		@Override
		protected boolean fitCondition(Object op1, Object op2) {
			return op2 != op1;
		}

	}

}
