package cn.yusei.jvm.instruction.extend;

import cn.yusei.jvm.instruction.base.Int16BranchInstruction;
import cn.yusei.jvm.runtimespace.Frame;

public class IfNullInstructions {

	public static abstract class IFXNULL extends Int16BranchInstruction {

		@Override
		protected final boolean shouldJump(Frame frame) {
			return fitCondition(frame.getOperandStack().popRef());
		}

		protected abstract boolean fitCondition(Object ref);
	}

	public static class IFNULL extends IFXNULL {

		@Override
		protected boolean fitCondition(Object ref) {
			return ref == null;
		}

	}

	public static class IFNONNULL extends IFXNULL {

		@Override
		protected boolean fitCondition(Object ref) {
			return ref != null;
		}

	}

}
