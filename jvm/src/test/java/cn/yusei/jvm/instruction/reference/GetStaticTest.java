package cn.yusei.jvm.instruction.reference;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import cn.yusei.jvm.ClassInfo;
import cn.yusei.jvm.ClassInfoLoader;
import cn.yusei.jvm.instruction.BytecodeReader;
import cn.yusei.jvm.instruction.base.UInt16Instruction;
import cn.yusei.jvm.runtimespace.stack.Frame;

public class GetStaticTest {

	private UInt16Instruction getstatic;
	private BytecodeReader reader;
	private byte[] codes = { 0, 3 };
	private Frame frame;

	@Before
	public void setUp() throws ClassNotFoundException, IOException {
		getstatic = new GET_STATIC();
		reader = new BytecodeReader(codes);
		ClassInfo info = new ClassInfoLoader().loadClass("cn.yusei.PutAndGetField");
		info.startInit();
		frame = new Frame(null, info.getMethods()[1]);
	}

	@Test
	public void readOperandsAndExecute() throws IOException {
		ClassInfo classInfo = frame.getMethod().getClassInfo();
		assertEquals(0, classInfo.getStaticVars().getInt(0));
		classInfo.getStaticVars().setInt(0, 10);
		getstatic.readOperands(reader);
		frame.getOperandStack().pushInt(10);
		getstatic.execute(frame);
		assertEquals(10, frame.getOperandStack().popInt());
	}

}
