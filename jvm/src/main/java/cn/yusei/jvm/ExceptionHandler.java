package cn.yusei.jvm;

import java.io.IOException;

import cn.yusei.jvm.classfile.member.attribute.ExceptionTableAttribute;
import cn.yusei.jvm.runtimespace.method.ClassRef;
import cn.yusei.jvm.runtimespace.method.RTConstantPool;

public class ExceptionHandler {

	private ExceptionTableAttribute[] tables;

	public ExceptionHandler(ExceptionTableAttribute[] exceptionAttributes) {
		tables = exceptionAttributes;
	}

	public int findExceptionHandler(ClassInfo exception, int pc, RTConstantPool pool) {
		for (ExceptionTableAttribute table : tables) {
			int start = table.getStartPc();
			int end = table.getEndPc();
			if (pc < start || pc >= end)
				continue;
			if (table.getCatchType() == 0)
				return table.getHandlerPc();
			ClassInfo canHandle;
			ClassRef classRef = pool.getClassRef(table.getCatchType());
			try {
				canHandle = classRef.resolvedClass();
			} catch (ClassNotFoundException | IOException e) {
				throw new RuntimeException("获取异常类型 " + classRef.getClassName() + " 失败");
			}
			if (canHandle == exception || exception.isSubClassOf(canHandle))
				return table.getHandlerPc();
		}
		return -1;
	}

}
