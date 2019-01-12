package cn.yusei.jvm.runtimespace.method;

import cn.yusei.jvm.classfile.constantpool.ConstantPool;

public abstract class MemberRef extends SymRef {

	public MemberRef(ConstantPool pool, RTConstantPool rtPool) {
		super(pool, rtPool);
	}
	
	public abstract String getName();
	
	public abstract String getDescriptor();

}
