package cn.yusei.jvm.runtimespace.method;

import java.io.IOException;

import cn.yusei.jvm.ClassInfo;
import cn.yusei.jvm.Method;
import cn.yusei.jvm.classfile.constantpool.ConstantPool;
import cn.yusei.jvm.classfile.constantpool.MethodrefConstantInfo;

public class MethodRef extends MemberRef {

	private String className;
	private String name;
	private String descriptor;
	private Method method;

	public MethodRef(ConstantPool pool, RTConstantPool rtPool, MethodrefConstantInfo info) {
		super(pool, rtPool);
		className = pool.getUTF8(pool.getClassInfo(info.getClassIndex()).getNameIndex()).getValue().replace("/", ".");
		name = pool.getUTF8(pool.getNameAndType(info.getNameAndTypeIndex()).getNameIndex()).getValue();
		descriptor = pool.getUTF8(pool.getNameAndType(info.getNameAndTypeIndex()).getDescriptorIndex()).getValue();
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

	public synchronized Method resolvedMethod() throws ClassNotFoundException, IOException {
		if (method == null) {
			method = resolveMethod();
		}
		return method;
	}

	private Method resolveMethod() throws ClassNotFoundException, IOException {
		ClassInfo from = pool.getClassInfo();
		ClassInfo to = resolvedClass();
		if (to.isInterface())
			throw new IncompatibleClassChangeError();
		method = lookupMethod(to);
		if (method == null)
			throw new NoSuchMethodError(to.getName() + "." + name);
		if (!method.isAccessiableFrom(from))
			throw new IllegalAccessError();
		return method;
	}

	private Method lookupMethod(ClassInfo classInfo) {
		Method method = lookupInClass(classInfo);
		if (method == null)
			method = lookupMethodInInterfaces(classInfo);
		return method;
	}

	public Method lookupInClass(ClassInfo classInfo) {
		for (ClassInfo info = classInfo; info != null; info = info.getSuperClassInfo()) {
			for (Method m : info.getMethods()) {
				if (m.getName().equals(name) && m.getDescriptor().equals(descriptor))
					return m;
			}
		}
		return null;
	}

	private Method lookupMethodInInterfaces(ClassInfo classInfo) {
		for (ClassInfo info : classInfo.getInterfacesClassInfo()) {
			for (Method m : info.getMethods()) {
				if (m.getName().equals(name) && m.getDescriptor().equals(descriptor))
					return m;
			}
			Method m = lookupMethodInInterfaces(info);
			if (m != null)
				return m;
		}
		return null;
	}

}
