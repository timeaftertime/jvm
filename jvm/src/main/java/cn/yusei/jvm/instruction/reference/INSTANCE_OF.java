package cn.yusei.jvm.instruction.reference;

import java.io.IOException;

import cn.yusei.jvm.ExecuteBytecodeError;
import cn.yusei.jvm.ObjectRef;
import cn.yusei.jvm.instruction.base.UInt16Instruction;
import cn.yusei.jvm.runtimespace.method.ClassRef;
import cn.yusei.jvm.runtimespace.method.RTConstantPool;
import cn.yusei.jvm.runtimespace.stack.Frame;
import cn.yusei.jvm.runtimespace.stack.OperandStack;

public class INSTANCE_OF extends UInt16Instruction {

	@Override
	public void execute(Frame frame) {
		OperandStack stack = frame.getOperandStack();
		ObjectRef objectRef = stack.popRef();
		if (objectRef == null) {
			stack.pushInt(0);
			return;
		}
		RTConstantPool pool = frame.getMethod().getClassInfo().getConstantPool();
		ClassRef classRef = pool.getClassRef(operand);
		try {
			stack.pushInt(objectRef.isInstanceOf(classRef.resolvedClass()) ? 1 : 0);
		} catch (ClassNotFoundException | IOException e) {
			throw new ExecuteBytecodeError(e);
		}
	}

}
