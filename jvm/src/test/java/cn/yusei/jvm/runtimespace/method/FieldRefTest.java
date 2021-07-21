package cn.yusei.jvm.runtimespace.method;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import cn.yusei.jvm.ClassInfoLoader;
import cn.yusei.jvm.Field;

public class FieldRefTest {

	private RTConstantPool pool;

	@Before
	public void setUp() throws ClassNotFoundException, IOException {
		pool = new ClassInfoLoader().loadClass("cn.yusei.ClassTest").getConstantPool();
	}

	@Test
	public void resolvedField() throws ClassNotFoundException, IOException {
		FieldRef fieldRef = pool.getFieldRef(2);
		Field field = fieldRef.resolvedField();
		assertEquals(fieldRef.getName(), field.getName());
		assertEquals(fieldRef.getDescriptor(), field.getDescriptor());
		assertSame(field, fieldRef.resolvedField());
	}

}
