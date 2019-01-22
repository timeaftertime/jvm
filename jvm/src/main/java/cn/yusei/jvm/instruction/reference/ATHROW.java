package cn.yusei.jvm.instruction.reference;

import cn.yusei.jvm.ObjectRef;
import cn.yusei.jvm.StackTrace;
import cn.yusei.jvm.instruction.base.NoOperandInstruction;
import cn.yusei.jvm.runtimespace.ThreadSpace;
import cn.yusei.jvm.runtimespace.stack.Frame;
import cn.yusei.jvm.runtimespace.stack.OperandStack;

public class ATHROW extends NoOperandInstruction {

	@Override
	public void execute(Frame frame) {
		OperandStack stack = frame.getOperandStack();
		ObjectRef ex = stack.popRef();
		if (ex == null)
			throw new NullPointerException();
		ThreadSpace threadSpace = frame.getThreadSpace();
		if (!findAndGoToHandlePc(threadSpace, ex))
			handleUncaughtException(threadSpace, ex);
	}

	private boolean findAndGoToHandlePc(ThreadSpace threadSpace, ObjectRef ex) {
		while (!threadSpace.finished()) {
			Frame frame = threadSpace.currentFrame();
			int handlePc = frame.getMethod().findExceptionHandler(ex.getClassInfo(), frame.getNextPc() - 1);
			if (handlePc > 0) {
				frame.setNextPc(handlePc);
				frame.getOperandStack().clear();
				frame.getOperandStack().pushRef(ex);
				return true;
			}
			threadSpace.popFrame();
		}
		return false;
	}

	private void handleUncaughtException(ThreadSpace threadSpace, ObjectRef ex) {
		threadSpace.clearStack();
		if (ex.getStackTrace() == null)
			return;
		String message = ex.getRefValue("detailMessage", "Ljava/lang/String;").string();
		System.out.println(ex + ":" + message);
		for (StackTrace trace : ex.getStackTrace()) {
			System.out.println("\t" + trace);
		}
	}

}
