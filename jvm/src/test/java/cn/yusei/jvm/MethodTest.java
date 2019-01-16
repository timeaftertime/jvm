package cn.yusei.jvm;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class MethodTest {

	private ClassInfoLoader loader;

	@Before
	public void setUp() {
		loader = new ClassInfoLoader();
	}

	@Test
	public void parseMethodDescriptor() throws ClassNotFoundException, IOException {
		Method initObject = loader.loadClass("cn.yusei.CountSumFrom1To100").getConstantPool().getMethodRef(1)
				.resolvedMethod();
		Method println = loader.loadClass("cn.yusei.CountSumFrom1To100").getConstantPool().getMethodRef(3)
				.resolvedMethod();
		Method sum = loader.loadClass("cn.yusei.InterpretAboutArray").getConstantPool().getMethodRef(5)
				.resolvedMethod();
		assertEquals(1, initObject.getArgsSlotCount());
		assertTrue(Arrays.equals(initObject.getArgsType(), new String[0]));
		assertEquals("V", initObject.getReturnType());
		assertEquals(2, println.getArgsSlotCount());
		assertTrue(Arrays.equals(println.getArgsType(), new String[] { "I" }));
		assertEquals("V", initObject.getReturnType());
		assertEquals(2, sum.getArgsSlotCount());
		assertTrue(Arrays.equals(new String[] { "[I" }, sum.getArgsType()));
	}

	@Test
	public void injectNativeMethod() throws ClassNotFoundException, IOException {
		ClassInfo objClass = new ClassInfoLoader().loadClass("java.lang.Object");
		Method method = objClass.getMethod("hashCode", "()I");
		assertEquals(1, method.getMaxStack());
		assertEquals(method.getArgsSlotCount(), method.getMaxLocal());
		assertArrayEquals(new byte[] { (byte) 0xfe, (byte) 0xac }, method.getCodes());
	}

}
