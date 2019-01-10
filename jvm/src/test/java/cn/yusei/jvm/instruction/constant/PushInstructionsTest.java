package cn.yusei.jvm.instruction.constant;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import cn.yusei.jvm.instruction.BytecodeReader;
import cn.yusei.jvm.instruction.base.Int16Instruction;
import cn.yusei.jvm.instruction.base.Int8Instrcution;
import cn.yusei.jvm.instruction.constant.PushInstructions.BIPUSH;
import cn.yusei.jvm.instruction.constant.PushInstructions.SIPUSH;
import cn.yusei.jvm.runtimespace.Frame;

public class PushInstructionsTest {

	private Int8Instrcution bipush;
	private Int16Instruction sipush;
	
	private Frame frame;
	
	private static final int MAX_LOCAL_VARS_TABLE_SIZE = 10;
	private static final int MAX_OPERAND_STACK_SIZE = 10;
	
	@Before
	public void setUp() throws IOException {
		bipush = new BIPUSH();
		sipush = new SIPUSH();
		bipush.readOperands(new BytecodeReader(new byte[] {1}));
		sipush.readOperands(new BytecodeReader(new byte[] {1, 1}));
		frame = new Frame(null, MAX_LOCAL_VARS_TABLE_SIZE, MAX_OPERAND_STACK_SIZE);
	}
	
	@Test
	public void execute() {
		bipush.execute(frame);
		assertEquals(1, frame.getOperandStack().popInt());
		sipush.execute(frame);
		assertEquals(257, frame.getOperandStack().popInt());
	}
	
}
