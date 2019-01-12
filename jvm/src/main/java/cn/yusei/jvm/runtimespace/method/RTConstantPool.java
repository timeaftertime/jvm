package cn.yusei.jvm.runtimespace.method;

import cn.yusei.jvm.ClassInfo;
import cn.yusei.jvm.classfile.constantpool.ClassConstantInfo;
import cn.yusei.jvm.classfile.constantpool.ConstantInfo;
import cn.yusei.jvm.classfile.constantpool.ConstantPool;
import cn.yusei.jvm.classfile.constantpool.ConstantTag;
import cn.yusei.jvm.classfile.constantpool.FieldrefConstantInfo;
import cn.yusei.jvm.classfile.constantpool.MethodrefConstantInfo;
import cn.yusei.jvm.classfile.constantpool.ValueConstantInfo;

public class RTConstantPool {

	private Object[] constants;
	private ClassInfo classInfo;

	public RTConstantPool(ConstantPool pool, ClassInfo classInfo) {
		this.classInfo = classInfo;
		constants = new Object[pool.getSize()];
		for (int i = 1; i < pool.getSize(); i++) {
			ConstantInfo constant = pool.get(i);
			switch (constant.getTag()) {
			case ConstantTag.INTEGER:
			case ConstantTag.FLOAT:
			case ConstantTag.LONG:
			case ConstantTag.DOUBLE:
			case ConstantTag.STRING:
			case ConstantTag.UTF8:
				ValueConstantInfo<?> valueConstant = (ValueConstantInfo<?>) constant;
				constants[i] = valueConstant.getValue();
				break;
			case ConstantTag.CLASS:
				constants[i] = new ClassRef(pool, this, (ClassConstantInfo) constant);
				break;
			case ConstantTag.FIELDREF:
				constants[i] = new FieldRef(pool, this, (FieldrefConstantInfo) constant);
				break;
			case ConstantTag.METHODREF:
				constants[i] = new MethodRef(pool, this, (MethodrefConstantInfo) constant);
				break;
			}
			if (constant.getTag() == ConstantTag.LONG || constant.getTag() == ConstantTag.DOUBLE)
				i++;
		}
	}

	public Object get(int index) {
		return constants[index];
	}
	
	public int getInt(int index) {
		return (int) (constants[index] == null ? 0 : constants[index]);
	}

	public long getLong(int index) {
		return (long) (constants[index] == null ? 0 : constants[index]);
	}

	public float getFloat(int index) {
		return (float) (constants[index] == null ? 0 : constants[index]);
	}

	public double getDouble(int index) {
		return (double) (constants[index] == null ? 0 : constants[index]);
	}

	public String getString(int index) {
		return (String) constants[index];
	}

	public ClassRef getClassRef(int index) {
		return (ClassRef) constants[index];
	}

	public FieldRef getFieldRef(int index) {
		return (FieldRef) constants[index];
	}

	public MethodRef getMethodRef(int index) {
		return (MethodRef) constants[index];
	}

	public int size() {
		return constants.length;
	}

	public ClassInfo getClassInfo() {
		return classInfo;
	}
}
