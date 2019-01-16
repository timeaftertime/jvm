package cn.yusei.jvm.instruction.control;

import cn.yusei.jvm.instruction.base.NoOperandInstruction;
import cn.yusei.jvm.runtimespace.ThreadSpace;
import cn.yusei.jvm.runtimespace.stack.Frame;

public class ReturnInstructions {

	public static class RETURN extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			frame.getThreadSpace().popFrame();
		}

	}

	public static class IRETURN extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			ThreadSpace threadSpace = frame.getThreadSpace();
			threadSpace.popFrame();
			Frame currentFrame = threadSpace.currentFrame();
			currentFrame.getOperandStack().pushInt(frame.getOperandStack().popInt());
		}

	}

	public static class FRETURN extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			ThreadSpace threadSpace = frame.getThreadSpace();
			threadSpace.popFrame();
			Frame currentFrame = threadSpace.currentFrame();
			currentFrame.getOperandStack().pushFloat(frame.getOperandStack().popFloat());
		}

	}

	public static class LRETURN extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			ThreadSpace threadSpace = frame.getThreadSpace();
			threadSpace.popFrame();
			Frame currentFrame = threadSpace.currentFrame();
			currentFrame.getOperandStack().pushLong(frame.getOperandStack().popLong());
		}

	}

	public static class DRETURN extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			ThreadSpace threadSpace = frame.getThreadSpace();
			threadSpace.popFrame();
			Frame currentFrame = threadSpace.currentFrame();
			currentFrame.getOperandStack().pushDouble(frame.getOperandStack().popDouble());
		}

	}

	public static class ARETURN extends NoOperandInstruction {

		@Override
		public void execute(Frame frame) {
			ThreadSpace threadSpace = frame.getThreadSpace();
			threadSpace.popFrame();
			Frame currentFrame = threadSpace.currentFrame();
			currentFrame.getOperandStack().pushRef(frame.getOperandStack().popRef());
		}

	}

}
