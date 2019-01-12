package cn.yusei.jvm.instruction.control;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import cn.yusei.jvm.instruction.BytecodeReader;
import cn.yusei.jvm.instruction.base.Int16BranchInstruction;
import cn.yusei.jvm.runtimespace.ThreadSpace;
import cn.yusei.jvm.runtimespace.stack.Frame;
import cn.yusei.jvm.testutil.MockFactory;

public class GotoTest {

	private Int16BranchInstruction _goto;
	private static final int MAX_LOCAL_VARS_TABLE_CAPACITY = 7;
	private static final int MAX_OPERAND_STACK_CAPACITY = 16;
	private Frame frame;
	private BytecodeReader reader;
	private byte[] codes = new byte[] {0, 20};
	
	@Before
	public void setUp() {
		_goto = new GOTO();
		frame = new Frame(new ThreadSpace(),
				MockFactory.newMethod(MAX_LOCAL_VARS_TABLE_CAPACITY, MAX_OPERAND_STACK_CAPACITY));
		reader = new BytecodeReader(codes);
	}
	
	@Test
	public void readOperandsAndExecute() throws IOException {
		_goto.readOperands(reader);
		assertEquals(2, reader.getPc());
		_goto.execute(frame);
		frame.synchronizedThreadSpacePc();
		assertEquals(20, frame.getThreadSpace().getPc());
	}
	
}
