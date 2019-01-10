package cn.yusei.jvm.instruction.load;

import cn.yusei.jvm.instruction.base.NoOperandInstruction;
import cn.yusei.jvm.instruction.base.UInt8Instruction;
import cn.yusei.jvm.runtimespace.Frame;

public class LoadInstructions {

	public static class ILOAD extends UInt8Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushInt(frame.getLocalVarsTable().getInt(operand));
		}

	}

	public static class ILOAD_0 extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushInt(frame.getLocalVarsTable().getInt(0));
		}

	}

	public static class ILOAD_1 extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushInt(frame.getLocalVarsTable().getInt(1));
		}

	}

	public static class ILOAD_2 extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushInt(frame.getLocalVarsTable().getInt(2));
		}

	}

	public static class ILOAD_3 extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushInt(frame.getLocalVarsTable().getInt(3));
		}

	}

	public static class LLOAD extends UInt8Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushLong(frame.getLocalVarsTable().getLong(operand));
		}

	}

	public static class LLOAD_0 extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushLong(frame.getLocalVarsTable().getLong(0));
		}

	}

	public static class LLOAD_1 extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushLong(frame.getLocalVarsTable().getLong(1));
		}

	}

	public static class LLOAD_2 extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushLong(frame.getLocalVarsTable().getLong(2));
		}

	}

	public static class LLOAD_3 extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushLong(frame.getLocalVarsTable().getLong(3));
		}

	}

	public static class FLOAD extends UInt8Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushFloat(frame.getLocalVarsTable().getFloat(operand));
		}

	}

	public static class FLOAD_0 extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushFloat(frame.getLocalVarsTable().getFloat(0));
		}

	}

	public static class FLOAD_1 extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushFloat(frame.getLocalVarsTable().getFloat(1));
		}

	}

	public static class FLOAD_2 extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushFloat(frame.getLocalVarsTable().getFloat(2));
		}

	}

	public static class FLOAD_3 extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushFloat(frame.getLocalVarsTable().getFloat(3));
		}

	}

	public static class DLOAD extends UInt8Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushDouble(frame.getLocalVarsTable().getDouble(operand));
		}

	}

	public static class DLOAD_0 extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushDouble(frame.getLocalVarsTable().getDouble(0));
		}

	}

	public static class DLOAD_1 extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushDouble(frame.getLocalVarsTable().getDouble(1));
		}

	}

	public static class DLOAD_2 extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushDouble(frame.getLocalVarsTable().getDouble(2));
		}

	}

	public static class DLOAD_3 extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushDouble(frame.getLocalVarsTable().getDouble(3));
		}

	}

	public static class ALOAD extends UInt8Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushRef(frame.getLocalVarsTable().getRef(operand));
		}

	}

	public static class ALOAD_0 extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushRef(frame.getLocalVarsTable().getRef(0));
		}

	}

	public static class ALOAD_1 extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushRef(frame.getLocalVarsTable().getRef(1));
		}

	}

	public static class ALOAD_2 extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushRef(frame.getLocalVarsTable().getRef(2));
		}

	}

	public static class ALOAD_3 extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushRef(frame.getLocalVarsTable().getRef(3));
		}

	}

}
