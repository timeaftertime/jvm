package cn.yusei.jvm.instruction.reference;

import java.io.IOException;

import cn.yusei.jvm.ClassInfo;
import cn.yusei.jvm.Field;
import cn.yusei.jvm.ObjectRef;
import cn.yusei.jvm.instruction.ExecuteBytecodeError;
import cn.yusei.jvm.instruction.base.UInt16Instruction;
import cn.yusei.jvm.runtimespace.method.RTConstantPool;
import cn.yusei.jvm.runtimespace.stack.Frame;

public class GET_FIELD extends UInt16Instruction {

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
		switch (field.getDescriptor().charAt(0)) {
		case 'Z':
		case 'B':
		case 'C':
		case 'S':
		case 'I': {
			ObjectRef ref = frame.getOperandStack().popRef();
			if (ref == null)
				throw new NullPointerException();
			frame.getOperandStack().pushInt(ref.getMembers().getInt(field.getSlotId()));
			break;
		}
		case 'J': {
			ObjectRef ref = frame.getOperandStack().popRef();
			if (ref == null)
				throw new NullPointerException();
			frame.getOperandStack().pushLong(ref.getMembers().getLong(field.getSlotId()));
			break;
		}
		case 'F': {
			ObjectRef ref = frame.getOperandStack().popRef();
			if (ref == null)
				throw new NullPointerException();
			frame.getOperandStack().pushFloat(ref.getMembers().getFloat(field.getSlotId()));
			break;
		}
		case 'D': {
			ObjectRef ref = frame.getOperandStack().popRef();
			if (ref == null)
				throw new NullPointerException();
			frame.getOperandStack().pushDouble(ref.getMembers().getDouble(field.getSlotId()));
			break;
		}
		case 'L':
		case '[': {
			ObjectRef ref = frame.getOperandStack().popRef();
			if (ref == null)
				throw new NullPointerException();
			frame.getOperandStack().pushRef(ref.getMembers().getRef(field.getSlotId()));
			break;
		}
		default:
			throw new RuntimeException("非法类型描述符：" + field.getDescriptor());
		}
	}

}
