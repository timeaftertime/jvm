package cn.yusei.jvm.runtimespace.method;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import cn.yusei.jvm.ClassInfo;
import cn.yusei.jvm.ClassInfoLoader;

public class SymRefTest {

	private RTConstantPool pool;

	@Before
	public void setUp() throws ClassNotFoundException, IOException {
		pool = new ClassInfoLoader().loadClass("cn.yusei.ClassTest").getConstantPool();
	}

	@Test
	public void resolvedClass() throws ClassNotFoundException, IOException {
		SymRef classRef = pool.getClassRef(5);
		ClassInfo classInfo = classRef.resolvedClass();
		assertEquals(classInfo.getName(), classRef.getClassName().replace("/", "."));
		assertSame(classInfo, classRef.resolvedClass());
	}

}
