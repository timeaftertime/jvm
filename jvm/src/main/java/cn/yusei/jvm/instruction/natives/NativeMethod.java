package cn.yusei.jvm.instruction.natives;

import cn.yusei.jvm.runtimespace.stack.Frame;

public interface NativeMethod {

	public void invoke(Frame frame);

}
