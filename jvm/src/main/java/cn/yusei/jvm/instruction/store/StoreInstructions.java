package cn.yusei.jvm.instruction.store;

import cn.yusei.jvm.instruction.base.NoOperandInstruction;
import cn.yusei.jvm.instruction.base.UInt8Instruction;
import cn.yusei.jvm.runtimespace.Frame;

public class StoreInstructions {

	public static class ISTORE extends UInt8Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getLocalVarsTable().setInt(operand, frame.getOperandStack().popInt());
		}

	}

	public static class ISTORE_0 extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			frame.getLocalVarsTable().setInt(0, frame.getOperandStack().popInt());
		}

	}

	public static class ISTORE_1 extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			frame.getLocalVarsTable().setInt(1, frame.getOperandStack().popInt());
		}

	}

	public static class ISTORE_2 extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			frame.getLocalVarsTable().setInt(2, frame.getOperandStack().popInt());
		}

	}

	public static class ISTORE_3 extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			frame.getLocalVarsTable().setInt(3, frame.getOperandStack().popInt());
		}

	}

	public static class LSTORE extends UInt8Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getLocalVarsTable().setLong(operand, frame.getOperandStack().popLong());
		}

	}

	public static class LSTORE_0 extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			frame.getLocalVarsTable().setLong(0, frame.getOperandStack().popLong());
		}

	}

	public static class LSTORE_1 extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			frame.getLocalVarsTable().setLong(1, frame.getOperandStack().popLong());
		}

	}

	public static class LSTORE_2 extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			frame.getLocalVarsTable().setLong(2, frame.getOperandStack().popLong());
		}

	}

	public static class LSTORE_3 extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			frame.getLocalVarsTable().setLong(3, frame.getOperandStack().popLong());
		}

	}

	public static class FSTORE extends UInt8Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getLocalVarsTable().setFloat(operand, frame.getOperandStack().popFloat());
		}

	}

	public static class FSTORE_0 extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			frame.getLocalVarsTable().setFloat(0, frame.getOperandStack().popFloat());
		}

	}

	public static class FSTORE_1 extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			frame.getLocalVarsTable().setFloat(1, frame.getOperandStack().popFloat());
		}

	}

	public static class FSTORE_2 extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			frame.getLocalVarsTable().setFloat(2, frame.getOperandStack().popFloat());
		}

	}

	public static class FSTORE_3 extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			frame.getLocalVarsTable().setFloat(3, frame.getOperandStack().popFloat());
		}

	}

	public static class DSTORE extends UInt8Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getLocalVarsTable().setDouble(operand, frame.getOperandStack().popDouble());
		}

	}

	public static class DSTORE_0 extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			frame.getLocalVarsTable().setDouble(0, frame.getOperandStack().popDouble());
		}

	}

	public static class DSTORE_1 extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			frame.getLocalVarsTable().setDouble(1, frame.getOperandStack().popDouble());
		}

	}

	public static class DSTORE_2 extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			frame.getLocalVarsTable().setDouble(2, frame.getOperandStack().popDouble());
		}

	}

	public static class DSTORE_3 extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			frame.getLocalVarsTable().setDouble(3, frame.getOperandStack().popDouble());
		}

	}

	public static class ASTORE extends UInt8Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getLocalVarsTable().setRef(operand, frame.getOperandStack().popRef());
		}

	}

	public static class ASTORE_0 extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			frame.getLocalVarsTable().setRef(0, frame.getOperandStack().popRef());
		}

	}

	public static class ASTORE_1 extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			frame.getLocalVarsTable().setRef(1, frame.getOperandStack().popRef());
		}

	}

	public static class ASTORE_2 extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			frame.getLocalVarsTable().setRef(2, frame.getOperandStack().popRef());
		}

	}

	public static class ASTORE_3 extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			frame.getLocalVarsTable().setRef(3, frame.getOperandStack().popRef());
		}

	}

}
