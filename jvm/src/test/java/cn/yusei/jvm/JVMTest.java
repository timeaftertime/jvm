package cn.yusei.jvm;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import cn.yusei.jvm.JVM;
import cn.yusei.jvm.commandline.CommandLineResolver;

public class JVMTest {

	private ByteArrayOutputStream out = null;
	private String LINE_SEPARATOR = System.getProperty("line.separator");

	@Before
	public void setUp() {
		out = new ByteArrayOutputStream();
		// 将标准输出重定向到 ByteArrayOutputStream 中
		System.setOut(new PrintStream(out));
	}

	@Test
	public void showVersion() throws ClassNotFoundException, IOException {
		JVM.main(new String[] { "-version" });
		assertEquals(JVM.VERSION + LINE_SEPARATOR, new String(out.toByteArray()));
	}

	@Test
	public void showHelp() throws ClassNotFoundException, IOException {
		JVM.main(new String[] { "-?" });
		assertEquals(JVM.HELP + LINE_SEPARATOR, new String(out.toByteArray()));
	}

	@Test
	public void showError() throws ClassNotFoundException, IOException {
		JVM.main(new String[] { "-cp" });
		JVM.main(new String[] { "-classpath" });
		JVM.main(new String[] { "-bbb", "xxx" });
		assertEquals(
				CommandLineResolver.LOST_CLASSPATH + "-cp" + LINE_SEPARATOR + CommandLineResolver.LOST_CLASSPATH
						+ "-classpath" + LINE_SEPARATOR + CommandLineResolver.UNKNOWN_OPTION + "-bbb" + LINE_SEPARATOR,
				new String(out.toByteArray()));
	}

	@Test
	public void startJVM() throws ClassNotFoundException, IOException {
		JVM.main(new String[] { "-cp", JVMTest.class.getResource("/").getPath().substring(1),
				"cn.yusei.InterpretAboutArray" });
	}

}