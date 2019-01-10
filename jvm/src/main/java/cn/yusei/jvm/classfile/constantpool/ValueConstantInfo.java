package cn.yusei.jvm.classfile.constantpool;

public abstract class ValueConstantInfo<T> implements ConstantInfo {
	
	protected T value;

	public T getValue() {
		return value;
	}
	
}
