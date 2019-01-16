package cn.yusei.jvm.instruction.reference;

import java.io.IOException;

import cn.yusei.jvm.ClassInfo;
import cn.yusei.jvm.Field;
import cn.yusei.jvm.MemberSlots;
import cn.yusei.jvm.instruction.ExecuteBytecodeError;
import cn.yusei.jvm.instruction.base.UInt16Instruction;
import cn.yusei.jvm.runtimespace.method.RTConstantPool;
import cn.yusei.jvm.runtimespace.stack.Frame;

public class GET_STATIC extends UInt16Instruction {

	@Override
	public void execute(Frame frame) {
		RTConstantPool pool = frame.getMethod().getClassInfo().getConstantPool();
		Field field;
		try {
			field = pool.getFieldRef(operand).resolvedField();
		} catch (ClassNotFoundException | IOException e) {
			throw new ExecuteBytecodeError(e);
		}
		ClassInfo classInfo = field.getClassInfo();
		if (!classInfo.initStarted()) {
			frame.revertNextPc();
			classInfo.initClass(frame.getThreadSpace());
			return;
		}
		if (!field.isStatic())
			throw new IncompatibleClassChangeError();
		// 注意这里使用的不是 frame 对应 method 的 ClassInfo ，而是用 field 所属的 ClassInfo
		MemberSlots staticVars = field.getClassInfo().getStaticVars();
		switch (field.getDescriptor().charAt(0)) {
		case 'Z':
		case 'B':
		case 'C':
		case 'S':
		case 'I':
			frame.getOperandStack().pushInt(staticVars.getInt(field.getSlotId()));
			break;
		case 'J':
			frame.getOperandStack().pushLong(staticVars.getLong(field.getSlotId()));
			break;
		case 'F':
			frame.getOperandStack().pushFloat(staticVars.getFloat(field.getSlotId()));
			break;
		case 'D':
			frame.getOperandStack().pushDouble(staticVars.getDouble(field.getSlotId()));
			break;
		case 'L':
		case '[':
			frame.getOperandStack().pushRef(staticVars.getRef(field.getSlotId()));
			break;
		}
	}

}
