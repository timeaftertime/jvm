package cn.yusei.jvm.instruction.natives;

import cn.yusei.jvm.ObjectRef;
import cn.yusei.jvm.runtimespace.stack.Frame;
import cn.yusei.jvm.runtimespace.stack.OperandStack;

public class ACDoPrivileged implements NativeMethod {

	@Override
	public void invoke(Frame frame) {
		ObjectRef action = frame.getLocalVarsTable().getRef(0);
		OperandStack stack = frame.getOperandStack();
		stack.pushRef(action);
		frame.getThreadSpace().invokeMethod(action.getClassInfo().getMethod("run", "()Ljava/lang/Object;"));
	}

}
