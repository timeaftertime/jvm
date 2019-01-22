package cn.yusei.jvm;

import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Test;

public class InterpreterTest {

	@Test
	public void interpretMethodInvoke() throws IOException, ClassNotFoundException {
		try {
			Interpreter.interpret("cn.yusei.MethodInvoke");
		}
		catch (NullPointerException e) {
			//TODO 初始化 System 类以去掉使用 System.out 时产生的 NullPointerException 
			return;
		}
		fail();
	}

	@Test
	public void interpretAboutArray() throws ClassNotFoundException, IOException {
		Interpreter.interpret("cn.yusei.InterpretAboutArray");
	}

	@Test
	public void printStackTrace() throws ClassNotFoundException, IOException {
		Interpreter.interpret("cn.yusei.ExceptionStackTrace");
	}

}
