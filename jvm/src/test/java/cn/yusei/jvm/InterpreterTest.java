package cn.yusei.jvm;

import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Test;

public class InterpreterTest {

	@Test
	public void interpretMethodInvoke() throws IOException, ClassNotFoundException {
		try {
			// 遇到 System.out.println 将因为 out 为 null 而退出
			Interpreter.interpret("cn.yusei.MethodInvoke");
		} catch (NullPointerException e) {
			return;
		}
		fail();
	}

	@Test
	public void interpretAboutArray() throws ClassNotFoundException, IOException {
		Interpreter.interpret("cn.yusei.InterpretAboutArray");
	}

}
