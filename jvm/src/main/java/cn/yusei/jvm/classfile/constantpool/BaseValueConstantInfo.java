package cn.yusei.jvm.classfile.constantpool;

public abstract class BaseValueConstantInfo<T> extends ValueConstantInfo<T> {

	protected T value;

	@Override
	public T getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "[value=" + value + "]";
	}

}
