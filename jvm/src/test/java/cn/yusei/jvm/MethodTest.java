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
	public void parseMethodDescriptorHaveDoubleAndLong() throws ClassNotFoundException, IOException {
		Method sum = loader.loadClass("java.lang.Double").getMethod("doubleToRawLongBits", "(D)J");
		assertEquals(2, sum.getArgsSlotCount());
		assertTrue(Arrays.equals(new String[] { "D" }, sum.getArgsType()));
		assertEquals("J", sum.getReturnType());
	}

	@Test
	public void injectNativeMethod() throws ClassNotFoundException, IOException {
		ClassInfo objClass = loader.loadClass("java.lang.Object");
		Method method = objClass.getMethod("hashCode", "()I");
		assertEquals(4, method.getMaxStack());
		assertEquals(method.getArgsSlotCount(), method.getMaxLocal());
		assertArrayEquals(new byte[] { (byte) 0xfe, (byte) 0xac }, method.getCodes());
	}

	@Test
	public void parseExceptionTable() throws ClassNotFoundException, IOException {
		Method method = loader.loadClass("cn.yusei.ExceptionHandle").getMethod("main", "([Ljava/lang/String;)V");
		assertEquals(10, method.findExceptionHandler(loader.loadClass("java.lang.RuntimeException"), 2));
		assertEquals(-1, method.findExceptionHandler(loader.loadClass("java.lang.RuntimeException"), 7));
		assertEquals(10, method.findExceptionHandler(loader.loadClass("java.lang.RuntimeException"), 5));
		assertEquals(-1, method.findExceptionHandler(loader.loadClass("java.lang.Exception"), 5));
		assertEquals(10, method.findExceptionHandler(loader.loadClass("java.lang.ArrayIndexOutOfBoundsException"), 5));
	}

	@Test
	public void parseLineNumberTable() throws ClassNotFoundException, IOException {
		Method method = loader.loadClass("cn.yusei.ExceptionHandle").getMethod("main", "([Ljava/lang/String;)V");
		assertEquals(14, method.convertLineNumber(10));
		assertEquals(15, method.convertLineNumber(12));
		assertEquals(16, method.convertLineNumber(9));
		assertEquals(10, method.convertLineNumber(0));
		assertEquals(10, method.convertLineNumber(1));
		assertEquals(12, method.convertLineNumber(2));
		assertEquals(-2, loader.loadClass("java.lang.Object").getMethod("hashCode", "()I").convertLineNumber(2));
	}

}
