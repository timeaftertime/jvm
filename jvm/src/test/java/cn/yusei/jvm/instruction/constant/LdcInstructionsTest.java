package cn.yusei.jvm.instruction.constant;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import cn.yusei.jvm.ClassInfo;
import cn.yusei.jvm.ClassInfoLoader;
import cn.yusei.jvm.StringPool;
import cn.yusei.jvm.instruction.BytecodeReader;
import cn.yusei.jvm.instruction.base.UInt16Instruction;
import cn.yusei.jvm.instruction.base.UInt8Instruction;
import cn.yusei.jvm.instruction.constant.LdcInstructions.LDC;
import cn.yusei.jvm.instruction.constant.LdcInstructions.LDC2_W;
import cn.yusei.jvm.instruction.constant.LdcInstructions.LDC_W;
import cn.yusei.jvm.runtimespace.stack.Frame;

public class LdcInstructionsTest {

	private UInt8Instruction ldc;
	private UInt16Instruction ldc_w;
	private UInt16Instruction ldc2_w;
	private BytecodeReader reader;
	private byte[] codes = { 10, 0, 29, 0, 25 };
	private Frame frame;

	@Before
	public void setUp() throws ClassNotFoundException, IOException {
		ldc = new LDC();
		ldc_w = new LDC_W();
		ldc2_w = new LDC2_W();
		reader = new BytecodeReader(codes);
		ClassInfo info = new ClassInfoLoader().loadClass("cn.yusei.ClassTest");
		frame = new Frame(null, info.getMethods()[1]);
	}

	@Test
	public void readOperandsAndExecute() throws IOException {
		double delta = 0.01;
		ldc.readOperands(reader);
		ldc_w.readOperands(reader);
		ldc2_w.readOperands(reader);
		ldc.execute(frame);
		assertEquals(1, frame.getOperandStack().popInt());
		ldc_w.execute(frame);
		assertEquals(3.14f, frame.getOperandStack().popFloat(), delta);
		ldc2_w.execute(frame);
		assertEquals(12345678901L, frame.getOperandStack().popLong());
	}

	@Test
	public void ldcString() throws IOException {
		reader = new BytecodeReader(new byte[] { 3 });
		ldc.readOperands(reader);
		ldc.execute(frame);
		assertSame(StringPool.getString(frame.getMethod().getClassInfo().getLoader(), "Hello World!"),
				frame.getOperandStack().popRef());
	}

}
