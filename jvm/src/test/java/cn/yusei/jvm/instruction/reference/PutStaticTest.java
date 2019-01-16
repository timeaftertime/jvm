package cn.yusei.jvm.instruction.reference;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import cn.yusei.jvm.ClassInfo;
import cn.yusei.jvm.ClassInfoLoader;
import cn.yusei.jvm.instruction.BytecodeReader;
import cn.yusei.jvm.instruction.base.UInt16Instruction;
import cn.yusei.jvm.runtimespace.ThreadSpace;
import cn.yusei.jvm.runtimespace.stack.Frame;

public class PutStaticTest {

	private UInt16Instruction putstatic;
	private BytecodeReader reader;
	private byte[] codes = { 0, 2 };
	private Frame frame;

	@Before
	public void setUp() throws ClassNotFoundException, IOException {
		putstatic = new PUT_STATIC();
		reader = new BytecodeReader(codes);
		ClassInfo info = new ClassInfoLoader().loadClass("cn.yusei.PutAndGetField");
		info.startInit();
		frame = new Frame(new ThreadSpace(), info.getMethods()[0]);
	}

	@Test
	public void readOperandsAndExecute() throws IOException {
		ClassInfo classInfo = frame.getMethod().getClassInfo();
		assertEquals(0, classInfo.getStaticVars().getInt(0));
		putstatic.readOperands(reader);
		frame.getOperandStack().pushInt(10);
		putstatic.execute(frame);
		assertEquals(10, classInfo.getStaticVars().getInt(0));
	}

}
