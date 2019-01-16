package cn.yusei.jvm.instruction.store;

import cn.yusei.jvm.ObjectRef;
import cn.yusei.jvm.instruction.base.NoOperandInstruction;
import cn.yusei.jvm.runtimespace.stack.Frame;
import cn.yusei.jvm.runtimespace.stack.OperandStack;

public class AStoreInstructions {

	public abstract static class XASTORE<T> extends NoOperandInstruction {

		private T value;

		@Override
		public final void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			value = getValue(frame);
			int index = stack.popInt();
			ObjectRef array = stack.popRef();
			if (array == null)
				throw new NullPointerException();
			if (index < 0 || index >= array.length())
				throw new ArrayIndexOutOfBoundsException(index);
			putValue(array, index, value);
		}

		public abstract T getValue(Frame frame);

		public abstract void putValue(ObjectRef array, int index, T value);

	}

	public static class BASTORE extends XASTORE<Byte> {

		@Override
		public Byte getValue(Frame frame) {
			return (byte) frame.getOperandStack().popInt();
		}

		@Override
		public void putValue(ObjectRef array, int index, Byte value) {
			array.bytes()[index] = value;
		}

	}

	public static class CASTORE extends XASTORE<Character> {

		@Override
		public Character getValue(Frame frame) {
			return (char) frame.getOperandStack().popInt();
		}

		@Override
		public void putValue(ObjectRef array, int index, Character value) {
			array.chars()[index] = value;
		}

	}

	public static class SASTORE extends XASTORE<Short> {

		@Override
		public Short getValue(Frame frame) {
			return (short) frame.getOperandStack().popInt();
		}

		@Override
		public void putValue(ObjectRef array, int index, Short value) {
			array.shorts()[index] = value;
		}

	}

	public static class IASTORE extends XASTORE<Integer> {

		@Override
		public Integer getValue(Frame frame) {
			return frame.getOperandStack().popInt();
		}

		@Override
		public void putValue(ObjectRef array, int index, Integer value) {
			array.ints()[index] = value;
		}

	}

	public static class FASTORE extends XASTORE<Float> {

		@Override
		public Float getValue(Frame frame) {
			return frame.getOperandStack().popFloat();
		}

		@Override
		public void putValue(ObjectRef array, int index, Float value) {
			array.floats()[index] = value;
		}

	}

	public static class LASTORE extends XASTORE<Long> {

		@Override
		public Long getValue(Frame frame) {
			return frame.getOperandStack().popLong();
		}

		@Override
		public void putValue(ObjectRef array, int index, Long value) {
			array.longs()[index] = value;
		}

	}

	public static class DASTORE extends XASTORE<Double> {

		@Override
		public Double getValue(Frame frame) {
			return frame.getOperandStack().popDouble();
		}

		@Override
		public void putValue(ObjectRef array, int index, Double value) {
			array.doubles()[index] = value;
		}

	}

	public static class AASTORE extends XASTORE<ObjectRef> {

		@Override
		public ObjectRef getValue(Frame frame) {
			return frame.getOperandStack().popRef();
		}

		@Override
		public void putValue(ObjectRef array, int index, ObjectRef value) {
			array.refs()[index] = value;
		}

	}

}
