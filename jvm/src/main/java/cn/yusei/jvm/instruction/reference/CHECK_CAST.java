package cn.yusei.jvm.instruction.reference;

import java.io.IOException;

import cn.yusei.jvm.ExecuteBytecodeError;
import cn.yusei.jvm.ObjectRef;
import cn.yusei.jvm.instruction.base.UInt16Instruction;
import cn.yusei.jvm.runtimespace.method.ClassRef;
import cn.yusei.jvm.runtimespace.method.RTConstantPool;
import cn.yusei.jvm.runtimespace.stack.Frame;
import cn.yusei.jvm.runtimespace.stack.OperandStack;

public class CHECK_CAST extends UInt16Instruction {

	@Override
	public void execute(Frame frame) {
		OperandStack stack = frame.getOperandStack();
		ObjectRef objectRef = stack.popRef();
		stack.pushRef(objectRef);
		if (objectRef == null)
			return;
		RTConstantPool pool = frame.getMethod().getClassInfo().getConstantPool();
		ClassRef classRef = pool.getClassRef(operand);
		try {
			if(!objectRef.isInstanceOf(classRef.resolvedClass()))
				throw new ClassCastException();
		} catch (ClassNotFoundException | IOException e) {
			throw new ExecuteBytecodeError(e);
		}
	}

}
