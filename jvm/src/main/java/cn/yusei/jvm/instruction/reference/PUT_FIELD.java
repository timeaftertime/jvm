package cn.yusei.jvm.instruction.reference;

import java.io.IOException;

import cn.yusei.jvm.ClassInfo;
import cn.yusei.jvm.Field;
import cn.yusei.jvm.ObjectRef;
import cn.yusei.jvm.instruction.ExecuteBytecodeError;
import cn.yusei.jvm.instruction.base.UInt16Instruction;
import cn.yusei.jvm.runtimespace.method.RTConstantPool;
import cn.yusei.jvm.runtimespace.stack.Frame;

public class PUT_FIELD extends UInt16Instruction {

	@Override
	public void execute(Frame frame) {
		ClassInfo currentClass = frame.getMethod().getClassInfo();
		RTConstantPool pool = currentClass.getConstantPool();
		Field field;
		try {
			field = pool.getFieldRef(operand).resolvedField();
		} catch (ClassNotFoundException | IOException e) {
			throw new ExecuteBytecodeError(e);
		}
		if (field.isStatic())
			throw new IncompatibleClassChangeError();
		if (field.isFinal()) {
			if (currentClass != field.getClassInfo() || !frame.getMethod().getName().equals("<init>"))
				throw new IllegalAccessError();
		}
		switch (field.getDescriptor().charAt(0)) {
		case 'Z':
		case 'B':
		case 'C':
		case 'S':
		case 'I': {
			int val = frame.getOperandStack().popInt();
			ObjectRef ref = frame.getOperandStack().popRef();
			if (ref == null)
				throw new NullPointerException();
			ref.getMembers().setInt(field.getSlotId(), val);
			break;
		}
		case 'J': {
			long val = frame.getOperandStack().popLong();
			ObjectRef ref = frame.getOperandStack().popRef();
			if (ref == null)
				throw new NullPointerException();
			ref.getMembers().setLong(field.getSlotId(), val);
			break;
		}
		case 'F': {
			float val = frame.getOperandStack().popFloat();
			ObjectRef ref = frame.getOperandStack().popRef();
			if (ref == null)
				throw new NullPointerException();
			ref.getMembers().setFloat(field.getSlotId(), val);
			break;
		}
		case 'D': {
			double val = frame.getOperandStack().popDouble();
			ObjectRef ref = frame.getOperandStack().popRef();
			if (ref == null)
				throw new NullPointerException();
			ref.getMembers().setDouble(field.getSlotId(), val);
			break;
		}
		case 'L':
		case '[': {
			ObjectRef val = frame.getOperandStack().popRef();
			ObjectRef ref = frame.getOperandStack().popRef();
			if (ref == null)
				throw new NullPointerException();
			ref.getMembers().setRef(field.getSlotId(), val);
			break;
		}
		default:
			throw new IllegalArgumentException("非法描述符：" + field.getDescriptor());
		}
	}

}
