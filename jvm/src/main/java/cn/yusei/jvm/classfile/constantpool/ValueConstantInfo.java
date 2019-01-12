package cn.yusei.jvm.classfile.constantpool;

public abstract class ValueConstantInfo<T> implements ConstantInfo {
	
	public abstract T getValue();
}
