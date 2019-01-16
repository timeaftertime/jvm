package cn.yusei.jvm.instruction.load;

import cn.yusei.jvm.ObjectRef;
import cn.yusei.jvm.instruction.base.NoOperandInstruction;
import cn.yusei.jvm.runtimespace.stack.Frame;
import cn.yusei.jvm.runtimespace.stack.OperandStack;

public class ALoadInstructions {

	public abstract static class XALOAD<T> extends NoOperandInstruction {

		@Override
		public final void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			int index = stack.popInt();
			ObjectRef array = stack.popRef();
			if (array == null)
				throw new NullPointerException();
			if (index < 0 || index >= array.length())
				throw new ArrayIndexOutOfBoundsException(index);
			pushArrayElement(getArrayElement(array, index), stack);
		}

		protected abstract T getArrayElement(ObjectRef array, int index);

		protected abstract void pushArrayElement(T element, OperandStack stack);

	}

	public static class BALOAD extends XALOAD<Byte> {

		@Override
		protected Byte getArrayElement(ObjectRef array, int index) {
			return array.bytes()[index];
		}

		@Override
		protected void pushArrayElement(Byte element, OperandStack stack) {
			stack.pushInt(element);
		}

	}

	public static class CALOAD extends XALOAD<Character> {

		@Override
		protected Character getArrayElement(ObjectRef array, int index) {
			return array.chars()[index];
		}

		@Override
		protected void pushArrayElement(Character element, OperandStack stack) {
			stack.pushInt(element);
		}

	}

	public static class SALOAD extends XALOAD<Short> {

		@Override
		protected Short getArrayElement(ObjectRef array, int index) {
			return array.shorts()[index];
		}

		@Override
		protected void pushArrayElement(Short element, OperandStack stack) {
			stack.pushInt(element);
		}

	}

	public static class IALOAD extends XALOAD<Integer> {

		@Override
		protected Integer getArrayElement(ObjectRef array, int index) {
			return array.ints()[index];
		}

		@Override
		protected void pushArrayElement(Integer element, OperandStack stack) {
			stack.pushInt(element);
		}

	}

	public static class FALOAD extends XALOAD<Float> {

		@Override
		protected Float getArrayElement(ObjectRef array, int index) {
			return array.floats()[index];
		}

		@Override
		protected void pushArrayElement(Float element, OperandStack stack) {
			stack.pushFloat(element);
		}

	}

	public static class LALOAD extends XALOAD<Long> {

		@Override
		protected Long getArrayElement(ObjectRef array, int index) {
			return array.longs()[index];
		}

		@Override
		protected void pushArrayElement(Long element, OperandStack stack) {
			stack.pushLong(element);
		}

	}

	public static class DALOAD extends XALOAD<Double> {

		@Override
		protected Double getArrayElement(ObjectRef array, int index) {
			return array.doubles()[index];
		}

		@Override
		protected void pushArrayElement(Double element, OperandStack stack) {
			stack.pushDouble(element);
		}

	}

	public static class AALOAD extends XALOAD<ObjectRef> {

		@Override
		protected ObjectRef getArrayElement(ObjectRef array, int index) {
			return array.refs()[index];
		}

		@Override
		protected void pushArrayElement(ObjectRef element, OperandStack stack) {
			stack.pushRef(element);
		}

	}

}
