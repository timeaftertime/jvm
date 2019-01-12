package cn.yusei.jvm.runtimespace;

import cn.yusei.jvm.Method;
import cn.yusei.jvm.runtimespace.stack.Frame;
import cn.yusei.jvm.runtimespace.stack.JVMStack;

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

	public int getPc() {
		return pc;
	}

	public void setPc(int pc) {
		this.pc = pc;
	}

}
