package cn.yusei.jvm.instruction.reference;

import java.io.IOException;

import cn.yusei.jvm.ClassInfo;
import cn.yusei.jvm.Field;
import cn.yusei.jvm.MemberSlots;
import cn.yusei.jvm.instruction.ExecuteBytecodeError;
import cn.yusei.jvm.instruction.base.UInt16Instruction;
import cn.yusei.jvm.runtimespace.method.RTConstantPool;
import cn.yusei.jvm.runtimespace.stack.Frame;

public class PUT_STATIC extends UInt16Instruction {

	@Override
	public void execute(Frame frame) {
		RTConstantPool pool = frame.getMethod().getClassInfo().getConstantPool();
		Field field;
		try {
			field = pool.getFieldRef(operand).resolvedField();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			throw new ExecuteBytecodeError(e);
		}
		ClassInfo classInfo = field.getClassInfo();
		if (!classInfo.initStarted()) {
			frame.revertNextPc();
			classInfo.initClass(frame.getThreadSpace());
			return;
		}
		if (field.isFinal()) {
			if (frame.getMethod().getClassInfo() != field.getClassInfo()
					|| !frame.getMethod().getName().equals("<clinit>"))
				throw new IllegalAccessError();
		}
		MemberSlots staticVars = field.getClassInfo().getStaticVars();
		switch (field.getDescriptor().charAt(0)) {
		case 'Z':
		case 'B':
		case 'C':
		case 'S':
		case 'I':
			staticVars.setInt(field.getSlotId(), frame.getOperandStack().popInt());
			break;
		case 'J':
			staticVars.setLong(field.getSlotId(), frame.getOperandStack().popLong());
			break;
		case 'F':
			staticVars.setFloat(field.getSlotId(), frame.getOperandStack().popFloat());
			break;
		case 'D':
			staticVars.setDouble(field.getSlotId(), frame.getOperandStack().popDouble());
			break;
		case 'L':
		case '[':
			staticVars.setRef(field.getSlotId(), frame.getOperandStack().popRef());
			break;
		}
	}

}
