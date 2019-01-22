package cn.yusei.jvm.runtimespace;

import cn.yusei.jvm.Method;
import cn.yusei.jvm.runtimespace.stack.Frame;
import cn.yusei.jvm.runtimespace.stack.JVMStack;
import cn.yusei.jvm.runtimespace.stack.LocalVarsTable;
import cn.yusei.jvm.runtimespace.stack.OperandStack;

public class ThreadSpace {

	private int pc;

	private JVMStack stack;
	private static final int DEFAULT_MAX_STACK_SIZE = 100;

	public ThreadSpace() {
		this.pc = 0;
		this.stack = new JVMStack(DEFAULT_MAX_STACK_SIZE);
	}

	public void pushFrame(Method method) {
		stack.push(new Frame(this, method));
	}

	public Frame popFrame() {
		return stack.pop();
	}

	public Frame currentFrame() {
		return stack.peek();
	}

	public int getPc() {
		return pc;
	}

	public void setPc(int pc) {
		this.pc = pc;
	}

	public void invokeMethod(Method method) {
		Frame currentFrame = currentFrame();
		pushFrame(method);
		Frame newFrame = currentFrame();
		passArgs(currentFrame.getOperandStack(), newFrame.getLocalVarsTable(), method.getArgsSlotCount());
	}

	private void passArgs(OperandStack operandStack, LocalVarsTable localVarsTable, int argsSlotCount) {
		for (int i = argsSlotCount - 1; i >= 0; i--)
			localVarsTable.setSlot(i, operandStack.popSlot());
	}

	public boolean finished() {
		return stack.isEmpty();
	}

	@Override
	public String toString() {
		return "ThreadSpace [pc=" + pc + "]";
	}

	public void clearStack() {
		stack.clear();
	}

	public Frame[] getFrames() {
		return stack.toArray(new Frame[0]);
	}

}
