package cn.yusei.jvm.runtimespace;

public class Frame {

	private LocalVarsTable localVarsTable;
	private OperandStack operandStack;
	private ThreadSpace threadSpace;
	private int nextPc;

	public Frame(ThreadSpace threadSpace, int localVarsTableCapacity, int operandStackCapacity) {
		this.threadSpace = threadSpace;
		localVarsTable = new LocalVarsTable(localVarsTableCapacity);
		operandStack = new OperandStack(operandStackCapacity);
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

}
