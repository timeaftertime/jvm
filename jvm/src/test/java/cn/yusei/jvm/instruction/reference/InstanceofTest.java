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

public class InstanceofTest {

	private UInt16Instruction instanceOf;
	private BytecodeReader reader;
	private byte[] codes = { 0, 4 };
	private Frame frame;

	@Before
	public void setUp() throws ClassNotFoundException, IOException {
		instanceOf = new INSTANCE_OF();
		reader = new BytecodeReader(codes);
		ClassInfo info = new ClassInfoLoader().loadClass("cn.yusei.PutAndGetField");
		frame = new Frame(null, info.getMethods()[1]);
	}

	@Test
	public void readOperandsAndExecute() throws IOException {
		ClassInfo classInfo = frame.getMethod().getClassInfo();
		instanceOf.readOperands(reader);
		frame.getOperandStack().pushRef(classInfo.newObjectRef());
		instanceOf.execute(frame);
		assertEquals(1, frame.getOperandStack().popInt());
		frame.getOperandStack().pushRef(null);
		instanceOf.execute(frame);
		assertEquals(0, frame.getOperandStack().popInt());
	}
}
