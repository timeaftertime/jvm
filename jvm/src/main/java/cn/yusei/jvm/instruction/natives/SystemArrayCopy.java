package cn.yusei.jvm.instruction.natives;

import cn.yusei.jvm.ObjectRef;
import cn.yusei.jvm.runtimespace.stack.Frame;
import cn.yusei.jvm.runtimespace.stack.LocalVarsTable;

public class SystemArrayCopy implements NativeMethod {

	@Override
	public void invoke(Frame frame) {
		LocalVarsTable locals = frame.getLocalVarsTable();
		ObjectRef src = locals.getRef(0);
		int srcPos = locals.getInt(1);
		ObjectRef dest = locals.getRef(2);
		int destPos = locals.getInt(3);
		int len = locals.getInt(4);
		if (src == null || dest == null)
			throw new NullPointerException();
		if (!src.getClassInfo().isArray() || !dest.getClassInfo().isArray())
			throw new ArrayStoreException("参数不是数组");
		if (srcPos < 0 || destPos < 0 || len < 0 || srcPos + len > src.length() || destPos + len > dest.length())
			throw new IndexOutOfBoundsException();
		System.arraycopy(src.getElements(), srcPos, dest.getElements(), destPos, len);
	}

}
