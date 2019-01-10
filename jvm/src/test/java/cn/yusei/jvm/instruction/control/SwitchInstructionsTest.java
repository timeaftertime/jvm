package cn.yusei.jvm.instruction.control;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import cn.yusei.jvm.instruction.BytecodeReader;
import cn.yusei.jvm.instruction.control.SwitchInstructions.LOOKUP_SWITCH;
import cn.yusei.jvm.instruction.control.SwitchInstructions.SwitchInstruction;
import cn.yusei.jvm.instruction.control.SwitchInstructions.TABLE_SWITCH;
import cn.yusei.jvm.runtimespace.Frame;
import cn.yusei.jvm.runtimespace.OperandStack;
import cn.yusei.jvm.runtimespace.ThreadSpace;

public class SwitchInstructionsTest {

	private SwitchInstruction tableSwitch;
	private SwitchInstruction lookupSwitch;
	private static final int MAX_LOCAL_VARS_TABLE_CAPACITY = 7;
	private static final int MAX_OPERAND_STACK_CAPACITY = 16;
	private Frame frame;
	private BytecodeReader reader;
	private byte[] codes = new byte[] {0, 1, 2, 3, 0, 0, 0, 0x7a, 0, 0, 0, 0x0, 0, 0, 0, 0x2, 0, 0, 0, 0x1, 0, 0, 0, 0x1, 0, 0, 0, 0x1,
			0, 1, 2, 3, 0, 0, 0, 0x7a, 0, 0, 0, 0x3, 0, 0, 0, 0x0, 0, 0, 0, 0x1, 0, 0, 0, 0x1, 0, 0, 0, 0x1, 0, 0, 0, 0x2, 0, 0, 0, 0x1};
	
	@Before
	public void setUp() {
		tableSwitch = new TABLE_SWITCH();
		lookupSwitch = new LOOKUP_SWITCH();
		frame = new Frame(new ThreadSpace(), MAX_LOCAL_VARS_TABLE_CAPACITY, MAX_OPERAND_STACK_CAPACITY);
		reader = new BytecodeReader(codes);
	}
	
	@Test
	public void readOperandsAndExecute() throws IOException {
		reader.readInt8();
		tableSwitch.readOperands(reader);
		assertEquals(28, reader.getPc());
		reader.readInt8();
		lookupSwitch.readOperands(reader);
		assertEquals(64, reader.getPc());
		OperandStack stack = frame.getOperandStack();
		int notExistsCase = 100;
		stack.pushInt(notExistsCase);
		tableSwitch.execute(frame);
		frame.synchronizedThreadSpacePc();
		assertEquals(0x7a, frame.getThreadSpace().getPc());
		for(int i=0 ;i<3; i++) {
			stack.pushInt(i);
			tableSwitch.execute(frame);
			frame.synchronizedThreadSpacePc();
			assertEquals(0x7a + i + 1, frame.getThreadSpace().getPc());
		}
		frame.setNextPc(0);
		frame.synchronizedThreadSpacePc();
		stack.pushInt(notExistsCase);
		lookupSwitch.execute(frame);
		frame.synchronizedThreadSpacePc();
		assertEquals(0x7a, frame.getThreadSpace().getPc());
		for(int i=0; i<3; i++) {
			stack.pushInt(i);
			lookupSwitch.execute(frame);
			frame.synchronizedThreadSpacePc();
			assertEquals(0x7a + i + 1, frame.getThreadSpace().getPc());
		}
	}
	
}
