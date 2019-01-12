package cn.yusei.jvm.testutil;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import cn.yusei.jvm.Method;

public class MockFactory {

	public static Method newMethod(int maxLocals, int maxStack) {
		Method method = mock(Method.class);
		when(method.getMaxLocal()).thenReturn(maxLocals);
		when(method.getMaxStack()).thenReturn(maxStack);
		return method;
	}
	
}
