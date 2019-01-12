package cn.yusei.jvm.runtimespace.method;

import java.io.IOException;

import cn.yusei.jvm.ClassInfo;
import cn.yusei.jvm.Field;
import cn.yusei.jvm.classfile.constantpool.ConstantPool;
import cn.yusei.jvm.classfile.constantpool.FieldrefConstantInfo;

public class FieldRef extends MemberRef {

	private String className;
	private String name;
	private String descriptor;
	private Field field;

	public FieldRef(ConstantPool pool, RTConstantPool rtPool, FieldrefConstantInfo info) {
		super(pool, rtPool);
		className = pool.getUTF8(pool.getClassInfo(info.getClassIndex()).getNameIndex()).getValue().replace("/", ".");
		name = pool.getUTF8(pool.getNameAndType(info.getNameAndTypeIndex()).getNameIndex()).getValue();
		descriptor = pool.getUTF8(pool.getNameAndType(info.getNameAndTypeIndex()).getDescriptorIndex()).getValue();
		info.getNameAndTypeIndex();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDescriptor() {
		return descriptor;
	}

	@Override
	public String getClassName() {
		return className;
	}

	public synchronized Field resolvedField() throws ClassNotFoundException, IOException {
		if(field == null) {
			field = resolveField();
		}
		return field;
	}

	private Field resolveField() throws ClassNotFoundException, IOException {
		ClassInfo from = pool.getClassInfo();
		ClassInfo to = from.getLoader().loadClass(getClassName());
		Field field = lookupField(to, getName(), getDescriptor());
		if(field == null)
			throw new NoSuchFieldError(to.getName() + "." + getName());
		if(!field.isAccessiableFrom(from))
			throw new IllegalAccessError();
		return field;
	}

	private Field lookupField(ClassInfo classInfo, String name, String descriptor) {
		for(Field field : classInfo.getFields()) {
			if(field.getName().equals(name) && field.getDescriptor().equals(descriptor))
				return field;
		}
		for(ClassInfo info : classInfo.getInterfacesClassInfo()) {
			Field interfaceField = lookupField(info, name, descriptor);
			if(interfaceField != null)
				return interfaceField;
		}
		if(classInfo.getSuperClassInfo() != null) {
			return lookupField(classInfo.getSuperClassInfo(), name, descriptor);
		}
		return null;
	}

}
