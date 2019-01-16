package cn.yusei.jvm.instruction.reference;

import cn.yusei.jvm.ObjectRef;
import cn.yusei.jvm.instruction.base.NoOperandInstruction;
import cn.yusei.jvm.runtimespace.stack.Frame;
import cn.yusei.jvm.runtimespace.stack.OperandStack;

public class ARRAY_LENGTH extends NoOperandInstruction {

	@Override
	public void execute(Frame frame) {
		OperandStack stack = frame.getOperandStack();
		ObjectRef ref = stack.popRef();
		if (ref == null)
			throw new NullPointerException();
		stack.pushInt(ref.length());
	}

}
