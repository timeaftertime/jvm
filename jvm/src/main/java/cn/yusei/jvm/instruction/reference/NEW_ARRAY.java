package cn.yusei.jvm.instruction.reference;

import java.io.IOException;

import cn.yusei.jvm.ObjectRef;
import cn.yusei.jvm.instruction.ExecuteBytecodeError;
import cn.yusei.jvm.instruction.base.UInt8Instruction;
import cn.yusei.jvm.runtimespace.stack.Frame;
import cn.yusei.jvm.runtimespace.stack.OperandStack;

public class NEW_ARRAY extends UInt8Instruction {

	private static final int AT_BOOLEAN = 4;
	private static final int AT_CHAR = 5;
	private static final int AT_FLOAT = 6;
	private static final int AT_DOUBLE = 7;
	private static final int AT_BYTE = 8;
	private static final int AT_SHORT = 9;
	private static final int AT_INT = 10;
	private static final int AT_LONG = 11;

	@Override
	public void execute(Frame frame) {
		OperandStack stack = frame.getOperandStack();
		int length = stack.popInt();
		if (length < 0)
			throw new NegativeArraySizeException(length + "");
		ObjectRef ref;
		try {
			ref = frame.getMethod().getClassInfo().getLoader().loadClass(getTypeName(operand))
					.newObjectRefAsArray(length);
		} catch (ClassNotFoundException | IOException e) {
			throw new ExecuteBytecodeError(e);
		}
		stack.pushRef(ref);
	}

	private static String getTypeName(int type) {
		switch (type) {
		case AT_BOOLEAN:
			return "[Z";
		case AT_BYTE:
			return "[Z";
		case AT_CHAR:
			return "[C";
		case AT_SHORT:
			return "[S";
		case AT_INT:
			return "[I";
		case AT_LONG:
			return "[J";
		case AT_FLOAT:
			return "[F";
		case AT_DOUBLE:
			return "[D";
		default:
			throw new IllegalArgumentException(type + "");
		}
	}

}
