package cn.yusei.jvm.runtimespace.method;

import cn.yusei.jvm.classfile.constantpool.ConstantPool;
import cn.yusei.jvm.classfile.constantpool.MethodrefConstantInfo;

public class MethodRef extends MemberRef {

	private String className;
	private String name;
	private String descriptor;

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

}
