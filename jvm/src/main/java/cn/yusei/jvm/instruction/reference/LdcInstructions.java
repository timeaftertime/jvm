package cn.yusei.jvm.instruction.reference;

import cn.yusei.jvm.instruction.base.UInt16Instruction;
import cn.yusei.jvm.instruction.base.UInt8Instruction;
import cn.yusei.jvm.runtimespace.method.RTConstantPool;
import cn.yusei.jvm.runtimespace.stack.Frame;
import cn.yusei.jvm.runtimespace.stack.OperandStack;

public class LdcInstructions {
	public static class LDC extends UInt8Instruction {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			RTConstantPool pool = frame.getMethod().getClassInfo().getConstantPool();
			Object val = pool.get(operand);
			if (val instanceof Integer) {
				stack.pushInt((int) val);
				return;
			}
			if (val instanceof Float) {
				stack.pushFloat((float) val);
				return;
			}
			throw new RuntimeException("暂未实现");
		}

	}

	public static class LDC_W extends UInt16Instruction {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			RTConstantPool pool = frame.getMethod().getClassInfo().getConstantPool();
			Object val = pool.get(operand);
			if (val instanceof Integer) {
				stack.pushInt((int) val);
				return;
			}
			if (val instanceof Float) {
				stack.pushFloat((float) val);
				return;
			}
			throw new RuntimeException("暂未实现");
		}

	}
	public static class LDC2_W extends UInt16Instruction {
		
		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			RTConstantPool pool = frame.getMethod().getClassInfo().getConstantPool();
			Object val = pool.get(operand);
			if (val instanceof Long) {
				stack.pushLong((long) val);
				return;
			}
			if (val instanceof Double) {
				stack.pushDouble((double) val);
				return;
			}
			throw new ClassFormatError();
		}
		
	}

}
