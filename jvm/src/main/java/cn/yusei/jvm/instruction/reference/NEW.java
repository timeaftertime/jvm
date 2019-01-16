package cn.yusei.jvm.instruction.reference;

import java.io.IOException;

import cn.yusei.jvm.ClassInfo;
import cn.yusei.jvm.ObjectRef;
import cn.yusei.jvm.instruction.ExecuteBytecodeError;
import cn.yusei.jvm.instruction.base.UInt16Instruction;
import cn.yusei.jvm.runtimespace.method.ClassRef;
import cn.yusei.jvm.runtimespace.method.RTConstantPool;
import cn.yusei.jvm.runtimespace.stack.Frame;

public class NEW extends UInt16Instruction {

	@Override
	public void execute(Frame frame) {
		RTConstantPool pool = frame.getMethod().getClassInfo().getConstantPool();
		ClassRef classRef = pool.getClassRef(operand);
		ClassInfo classInfo;
		try {
			classInfo = classRef.resolvedClass();
			// 调用类的初始化方法
			if(!classInfo.initStarted()) {
				// revertNextPc 和 return 是让解释器执行类初始化方法，之后再重新从当前指令开始执行
				frame.revertNextPc();
				classInfo.initClass(frame.getThreadSpace());
				return;
			}
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			throw new ExecuteBytecodeError(e);
		}
		if(classInfo.isAbstract() || classInfo.isInterface())
			throw new InstantiationError();
		ObjectRef objectRef = classInfo.newObjectRef();
		frame.getOperandStack().pushRef(objectRef);
	}

}
