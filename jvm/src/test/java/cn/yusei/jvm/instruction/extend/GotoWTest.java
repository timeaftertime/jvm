package cn.yusei.jvm.instruction.extend;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import cn.yusei.jvm.instruction.BytecodeReader;
import cn.yusei.jvm.instruction.base.Int32BranchInstruction;
import cn.yusei.jvm.runtimespace.Frame;
import cn.yusei.jvm.runtimespace.ThreadSpace;

public class GotoWTest {

	private Int32BranchInstruction gotoW;
	private static final int MAX_LOCAL_VARS_TABLE_CAPACITY = 7;
	private static final int MAX_OPERAND_STACK_CAPACITY = 16;
	private Frame frame;
	private BytecodeReader reader;
	private byte[] codes = new byte[] {0, 0, 0, 20};
	
	@Before
	public void setUp() {
		gotoW = new GOTO_W();
		frame = new Frame(new ThreadSpace(), MAX_LOCAL_VARS_TABLE_CAPACITY, MAX_OPERAND_STACK_CAPACITY);
		reader = new BytecodeReader(codes);
	}
	
	@Test
	public void readOperandsAndExecute() throws IOException {
		gotoW.readOperands(reader);
		assertEquals(4, reader.getPc());
		gotoW.execute(frame);
		frame.synchronizedThreadSpacePc();
		assertEquals(20, frame.getThreadSpace().getPc());
	}
	
}
