package cn.yusei.jvm.instruction.natives;

import cn.yusei.jvm.ClassInfo;
import cn.yusei.jvm.Method;
import cn.yusei.jvm.ObjectRef;
import cn.yusei.jvm.StackTrace;
import cn.yusei.jvm.runtimespace.ThreadSpace;
import cn.yusei.jvm.runtimespace.stack.Frame;

public class ThrowableFillInStackTrace implements NativeMethod {

	@Override
	public void invoke(Frame frame) {
		ObjectRef ex = frame.getLocalVarsTable().getRef(0);
		frame.getOperandStack().pushRef(ex);
		ex.setStackTrace(createStackTrace(ex, frame.getThreadSpace()));
	}

	private StackTrace[] createStackTrace(ObjectRef ex, ThreadSpace thread) {
		// 此时在 jvm 栈中，发生异常的方法桢之上，还有 fillInStackTrace(int) 和 fillStackStack()
		// 两层以及这两桢下面，异常类的构造函数，层数决定于其继承层次
		int skip = ex.getClassInfo().distanceToObject() + 2;
		Frame[] frames = thread.getFrames();
		int len = frames.length - skip;
		StackTrace[] traces = new StackTrace[len];
		for (int i = 0; i < len; i++) {
			Method method = frames[len - 1 - i].getMethod();
			ClassInfo classInfo = method.getClassInfo();
			traces[i] = new StackTrace(classInfo.getSourceFileName(), classInfo.getName(), method.getName(),
					method.convertLineNumber(frames[i].getNextPc() - 1));
		}
		return traces;
	}

}
