package cn.yusei.jvm.instruction.natives;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cn.yusei.jvm.Interpreter;
import cn.yusei.jvm.runtimespace.stack.Frame;
import cn.yusei.jvm.testutil.MessageHolder;
import cn.yusei.jvm.testutil.MockFactory;

public class NativeMethodRegistryTest {

	@Before
	public void setUp() {
		MessageHolder.clear();
	}

	@Test
	public void registerAndInvokeMethod() {
		NativeMethodRegistry.register("methodKey  ", (f) -> {
			MessageHolder.addMessage("invoke....");
		});
		NativeMethodRegistry.invoke(new Frame(null, MockFactory.newMethod("methodKey", "", "")));
		List<String> messages = MessageHolder.getMessages();
		assertEquals(1, messages.size());
		assertEquals("invoke....", messages.get(0));
	}
	
	@Test
	public void arraycopy() throws ClassNotFoundException, IOException {
		Interpreter.interpret("cn.yusei.ArrayCopy");
	}
	
	@Test
	public void StringIntern() throws ClassNotFoundException, IOException {
		Interpreter.interpret("cn.yusei.StringIntern");
	}

}
