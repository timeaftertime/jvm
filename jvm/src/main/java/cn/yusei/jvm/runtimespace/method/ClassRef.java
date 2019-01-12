package cn.yusei.jvm.runtimespace.method;

import cn.yusei.jvm.classfile.constantpool.ClassConstantInfo;
import cn.yusei.jvm.classfile.constantpool.ConstantPool;

public class ClassRef extends SymRef {

	private String className;
	
	public ClassRef(ConstantPool pool, RTConstantPool rtPool, ClassConstantInfo info) {
		super(pool, rtPool);
		className = pool.getUTF8(info.getNameIndex()).getValue().replace("/", ".");
	}

	@Override
	public String getClassName() {
		return className;
	}
}
