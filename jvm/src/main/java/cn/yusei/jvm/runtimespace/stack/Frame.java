package cn.yusei.jvm.runtimespace.stack;

import cn.yusei.jvm.Method;
import cn.yusei.jvm.runtimespace.ThreadSpace;

public class Frame {

	private Method method;
	private LocalVarsTable localVarsTable;
	private OperandStack operandStack;
	private ThreadSpace threadSpace;
	private int nextPc;

	public Frame(ThreadSpace threadSpace, Method method) {
		this.threadSpace = threadSpace;
		this.method = method;
		localVarsTable = new LocalVarsTable(method.getMaxLocal());
		operandStack = new OperandStack(method.getMaxStack());
	}

	public LocalVarsTable getLocalVarsTable() {
		return localVarsTable;
	}

	public OperandStack getOperandStack() {
		return operandStack;
	}

	public ThreadSpace getThreadSpace() {
		return threadSpace;
	}

	public void resetNextPc(int offset) {
		nextPc = threadSpace.getPc() + offset;
	}

	public int getNextPc() {
		return nextPc;
	}

	public void setNextPc(int pc) {
		nextPc = pc;
	}

	public void synchronizedThreadSpacePc() {
		threadSpace.setPc(nextPc);
	}

	public Method getMethod() {
		return method;
	}

}
