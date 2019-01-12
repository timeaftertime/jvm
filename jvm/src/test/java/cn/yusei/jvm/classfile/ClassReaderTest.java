package cn.yusei.jvm.classfile;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cn.yusei.jvm.classfile.ClassReader;

public class ClassReaderTest {

	private List<String> classPaths;
	
	@Before
	public void setUp() {
		classPaths = new ArrayList<>();
		classPaths.addAll(Arrays.asList(System.getenv("JAVA_HOME") + "/jre/lib/rt.jar"));
	}

	@Test
	public void readClass() throws ClassNotFoundException, IOException {
		classPaths.add(ClassReaderTest.class.getResource("/").getPath().substring(1));
		ClassReader reader = new ClassReader(classPaths.toArray(new String[0]));
		byte[] classData = reader.readClass("cn.yusei.Hello");
		InputStream is = ClassReaderTest.class.getResourceAsStream("/cn/yusei/Hello.class");
		byte[] expectedData = new byte[is.available()];
		is.read(expectedData);
		is.close();
		assertTrue(Arrays.equals(expectedData, classData));
	}
	
	@Test
	public void readFromJarFile() throws ClassNotFoundException, IOException {
		assertNotNull(new ClassReader(classPaths.toArray(new String[0])).readClass("java.lang.Object"));
	}
	
}
