package cn.yusei.jvm.runtimespace.method;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import cn.yusei.jvm.ClassInfoLoader;
import cn.yusei.jvm.Method;

public class MethodRefTest {

	private RTConstantPool pool;

	@Before
	public void setUp() throws ClassNotFoundException, IOException {
		pool = new ClassInfoLoader().loadClass("cn.yusei.ClassTest").getConstantPool();
	}

	@Test
	public void resolvedField() throws ClassNotFoundException, IOException {
		MethodRef methodRef = pool.getMethodRef(4);
		Method method = methodRef.resolvedMethod();
		assertEquals(methodRef.getName(), method.getName());
		assertEquals(methodRef.getDescriptor(), method.getDescriptor());
		assertSame(method, methodRef.resolvedMethod());
	}
	
}
