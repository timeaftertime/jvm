package cn.yusei.jvm.instruction.constant;

import cn.yusei.jvm.StringPool;
import cn.yusei.jvm.instruction.base.UInt16Instruction;
import cn.yusei.jvm.instruction.base.UInt8Instruction;
import cn.yusei.jvm.runtimespace.method.ClassRef;
import cn.yusei.jvm.runtimespace.method.RTConstantPool;
import cn.yusei.jvm.runtimespace.stack.Frame;
import cn.yusei.jvm.runtimespace.stack.OperandStack;

public class LdcInstructions {
	public static class LDC extends UInt8Instruction {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			Object val = frame.getMethod().getClassInfo().getConstantPool().get(operand);
			if (val instanceof Integer) {
				stack.pushInt((int) val);
				return;
			}
			if (val instanceof Float) {
				stack.pushFloat((float) val);
				return;
			}
			if (val instanceof String) {
				stack.pushRef(StringPool.getString(frame.getMethod().getClassInfo().getLoader(), (String) val));
				return;
			}
			if (val instanceof ClassRef) {
				// 这里加载一个类引用到操作数栈，
				// 参考书的做法是为 ClassInfo 类加一个 jClass 字段，引用一个 java.lang.Class 类型对应 ClassInfo 的
				// ObjectRef，然后该 ObjectRef 加一个 extra 字段，引用该 ClassInfo ，表示 ObjectRef 的类型信息
				// 直接 getClassInfo 不就能获得类型信息了吗，为什么还要去加个 jClass 字段？
				// 这里暂不做实现
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
