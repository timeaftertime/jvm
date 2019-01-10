package cn.yusei.jvm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;

import cn.yusei.jvm.classfile.ClassMetaData;
import cn.yusei.jvm.classfile.member.attribute.CodeAttribute;
import cn.yusei.jvm.instruction.extend.UnsupportedOpCodeError;

public class InterpreterTest {

	private ClassMetaData metaData;
	private CodeAttribute code;
	
	@Before
	public void setUp() throws IOException {
		try(InputStream ins = InterpreterTest.class.getResourceAsStream("/cn/yusei/CountSumFrom1To100.class")) {
			byte[] classData = new byte[ins.available()];
			ins.read(classData);
			metaData = new ClassMetaData(classData);
		}
		code = metaData.getMethodClassMember("main", "([Ljava/lang/String;)V").getCodeAttribute();
	}
	
	@Test
	public void interpret() throws IOException {
		try {
			Interpreter.interpret(code);
		}
		catch (UnsupportedOpCodeError e) {
			assertEquals(0xb2 + "",  e.getMessage());
			return;
		}
		fail();
	}
	
}
