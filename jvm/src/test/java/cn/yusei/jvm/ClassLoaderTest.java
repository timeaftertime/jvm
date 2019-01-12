package cn.yusei.jvm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class ClassLoaderTest {

	private ClassInfoLoader loader;
	private ClassInfo info;

	@Before
	public void setUp() throws ClassNotFoundException, IOException {
		loader = new ClassInfoLoader() {
			@Override
			protected String getDefaultClassPath() {
				return ClassLoaderTest.class.getResource("/").getPath().substring(1);
			}
		};
		info = loader.loadClass("cn.yusei.ClassTest");
	}

	@Test
	public void loadClass() throws ClassNotFoundException, IOException {
		assertNotNull(info);
		assertEquals("cn.yusei.ClassTest", info.getName());
		assertSame(info, loader.loadClass("cn.yusei.ClassTest"));
	}

	@Test
	public void calculateSoltIds() {
		assertEquals(0, info.getInstanceSlotCount());
		assertEquals(10, info.getStaticSlotCount());
	}

	@Test
	public void allocateAndInitStaticVars() {
		double delta = 0.01;
		assertEquals(1, info.getStaticVars().getInt(0));
		assertEquals(123, info.getStaticVars().getInt(1));
		assertEquals(88, info.getStaticVars().getInt(2));
		assertEquals(12345, info.getStaticVars().getInt(3));
		assertEquals(123456789, info.getStaticVars().getInt(4));
		assertEquals(12345678901L, info.getStaticVars().getLong(5));
		assertEquals(3.14f, info.getStaticVars().getFloat(7), delta);
		assertEquals(2.71828d, info.getStaticVars().getDouble(8), delta);
	}

	@Test
	public void loadClassFromJavaHomeEnv() throws ClassNotFoundException, IOException {
		assertNotNull(loader.loadClass("java.lang.Object"));
	}

}
