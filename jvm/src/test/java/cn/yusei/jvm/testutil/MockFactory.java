package cn.yusei.jvm.testutil;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import cn.yusei.jvm.ClassInfo;
import cn.yusei.jvm.Method;

public class MockFactory {

	public static Method newMethod(int maxLocals, int maxStack) {
		Method method = mock(Method.class);
		when(method.getMaxLocal()).thenReturn(maxLocals);
		when(method.getMaxStack()).thenReturn(maxStack);
		return method;
	}

	public static Method newMethod(String className, String name, String descriptor) {
		Method method = mock(Method.class);
		when(method.getName()).thenReturn(name);
		when(method.getDescriptor()).thenReturn(descriptor);
		ClassInfo info = newClassInfo(className);
		when(method.getClassInfo()).thenReturn(info);
		return method;
	}

	public static ClassInfo newClassInfo(String name) {
		ClassInfo classInfo = mock(ClassInfo.class);
		when(classInfo.getName()).thenReturn(name);
		return classInfo;
	}

}
