package cn.yusei.jvm.instruction.constant;

import cn.yusei.jvm.instruction.base.Int16Instruction;
import cn.yusei.jvm.instruction.base.Int8Instrcution;
import cn.yusei.jvm.runtimespace.stack.Frame;

public class PushInstructions {

	public static class SIPUSH extends Int16Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushInt(operand);
		}

	}

	public static class BIPUSH extends Int8Instrcution {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushInt(operand);
		}

	}

}
