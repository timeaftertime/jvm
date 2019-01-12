package cn.yusei.jvm.runtimespace.method;

import java.io.IOException;

import cn.yusei.jvm.ClassInfo;
import cn.yusei.jvm.classfile.constantpool.ConstantPool;

public abstract class SymRef {

	protected ClassInfo ownerClassInfo;
	protected RTConstantPool pool;
	
	public SymRef(ConstantPool pool, RTConstantPool rtPool) {
		this.pool = rtPool;
	}
	
	public abstract String getClassName() ;

	public synchronized ClassInfo resolvedClass() throws ClassNotFoundException, IOException {
		if(ownerClassInfo == null) {
			ownerClassInfo = resolveClass();
		}
		return ownerClassInfo;
	}

	private ClassInfo resolveClass() throws ClassNotFoundException, IOException {
		ClassInfo from = pool.getClassInfo();
		ClassInfo to = from.getLoader().loadClass(getClassName());
		if(!to.isAccessiable(from))
			throw new IllegalAccessError();
		return to;
	}
	
}
