package cn.yusei.jvm.instruction.reference;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import cn.yusei.jvm.ClassInfo;
import cn.yusei.jvm.ClassInfoLoader;
import cn.yusei.jvm.ObjectRef;
import cn.yusei.jvm.instruction.BytecodeReader;
import cn.yusei.jvm.instruction.base.UInt16Instruction;
import cn.yusei.jvm.runtimespace.stack.Frame;

public class PutFieldTest {

	private UInt16Instruction putstatic;
	private BytecodeReader reader;
	private byte[] codes = { 0, 2 };
	private Frame frame;

	@Before
	public void setUp() throws ClassNotFoundException, IOException {
		putstatic = new PUT_FIELD();
		reader = new BytecodeReader(codes);
		ClassInfo info = new ClassInfoLoader().loadClass("cn.yusei.PutAndGetField");
		frame = new Frame(null, info.getMethods()[1]);
	}

	@Test
	public void readOperandsAndExecute() throws IOException {
		ClassInfo classInfo = frame.getMethod().getClassInfo();
		putstatic.readOperands(reader);
		ObjectRef ref = classInfo.newObjectRef();
		frame.getOperandStack().pushRef(ref);
		frame.getOperandStack().pushInt(10);
		putstatic.execute(frame);
		assertEquals(10, ref.getMembers().getInt(0));
	}
	
}
