package cn.yusei.jvm.instruction.natives;

import java.io.IOException;

import cn.yusei.jvm.ClassInfo;
import cn.yusei.jvm.ClassInfoLoader;
import cn.yusei.jvm.instruction.ExecuteBytecodeError;
import cn.yusei.jvm.runtimespace.stack.Frame;

public class SystemInitialze implements NativeMethod {

	@Override
	public void invoke(Frame frame) {
		ClassInfoLoader loader = frame.getMethod().getClassInfo().getLoader();
		ClassInfo system;
		try {
			system = loader.loadClass("java.lang.System");
		} catch (ClassNotFoundException | IOException e) {
			throw new ExecuteBytecodeError(e);
		}
		frame.getThreadSpace().invokeMethod(system.getMethod("initializeSystemClass", "()V"));
	}

}
