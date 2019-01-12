package cn.yusei.jvm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Test;

import cn.yusei.jvm.instruction.extend.UnsupportedOpCodeError;

public class InterpreterTest {

	@Test
	public void interpret() throws IOException, ClassNotFoundException {
		ClassInfo classInfo = new ClassInfoLoader().loadClass("cn.yusei.CountSumFrom1To100");
		Method method = null;
		for(Method m : classInfo.getMethods()) {
			if(m.getName().equals("main") && m.getDescriptor().equals("([Ljava/lang/String;)V"))
				method = m;
		}
		assertNotNull(method);
		try {
			Interpreter.interpret(method);
		}
		catch (UnsupportedOpCodeError e) {
			assertEquals(0xb6 + "",  e.getMessage());
			return;
		}
		fail();
	}
	
}
