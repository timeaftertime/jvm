package cn.yusei.jvm.instruction.natives;

import cn.yusei.jvm.ClassInfoLoader;
import cn.yusei.jvm.runtimespace.stack.Frame;

public class ClassGetPrimitiveClass implements NativeMethod {

	@Override
	public void invoke(Frame frame) {
		ClassInfoLoader loader = frame.getMethod().getClassInfo().getLoader();
		frame.getOperandStack()
				.pushRef(loader.getPrimitiveClassInfo(frame.getLocalVarsTable().getRef(0).string()).newObjectRef());
	}

}
