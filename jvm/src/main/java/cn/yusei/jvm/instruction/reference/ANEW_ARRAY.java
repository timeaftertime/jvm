package cn.yusei.jvm.instruction.reference;

import java.io.IOException;

import cn.yusei.jvm.ClassInfo;
import cn.yusei.jvm.ObjectRef;
import cn.yusei.jvm.instruction.ExecuteBytecodeError;
import cn.yusei.jvm.instruction.base.UInt16Instruction;
import cn.yusei.jvm.runtimespace.method.RTConstantPool;
import cn.yusei.jvm.runtimespace.stack.Frame;
import cn.yusei.jvm.runtimespace.stack.OperandStack;

public class ANEW_ARRAY extends UInt16Instruction {

	@Override
	public void execute(Frame frame) {
		OperandStack stack = frame.getOperandStack();
		int length = stack.popInt();
		if(length < 0)
			throw new NegativeArraySizeException(length + "");
		RTConstantPool pool = frame.getMethod().getClassInfo().getConstantPool();
		ClassInfo eleType;
		ObjectRef ref;
		try {
			eleType = pool.getClassRef(operand).resolvedClass();
			ref = frame.getMethod().getClassInfo().getLoader().loadClass("[L" + eleType.getName() + ";").newObjectRefAsArray(length);
		} catch (ClassNotFoundException | IOException e) {
			throw new ExecuteBytecodeError(e);
		}
		stack.pushRef(ref);
	}

}
