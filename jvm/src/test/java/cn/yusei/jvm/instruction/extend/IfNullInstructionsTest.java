package cn.yusei.jvm.instruction.extend;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import cn.yusei.jvm.instruction.BytecodeReader;
import cn.yusei.jvm.instruction.base.Int16BranchInstruction;
import cn.yusei.jvm.instruction.extend.IfNullInstructions.IFNONNULL;
import cn.yusei.jvm.instruction.extend.IfNullInstructions.IFNULL;
import cn.yusei.jvm.instruction.extend.IfNullInstructions.IFXNULL;
import cn.yusei.jvm.runtimespace.Frame;
import cn.yusei.jvm.runtimespace.OperandStack;
import cn.yusei.jvm.runtimespace.ThreadSpace;

public class IfNullInstructionsTest {

	private Int16BranchInstruction[] ifXNulls;
	private static final int MAX_LOCAL_VARS_TABLE_CAPACITY = 7;
	private static final int MAX_OPERAND_STACK_CAPACITY = 16;
	private Frame frame;
	private BytecodeReader reader;
	private byte[] codes = new byte[] {0, 6, 0, 5};

	@Before
	public void setUp() {
		ifXNulls = new IFXNULL[2];
		ifXNulls[0] = new IFNULL();
		ifXNulls[1] = new IFNONNULL();
		frame = new Frame(new ThreadSpace(), MAX_LOCAL_VARS_TABLE_CAPACITY, MAX_OPERAND_STACK_CAPACITY);
		reader = new BytecodeReader(codes);
	}
	
	@Test
	public void readOperandsAndExecute() throws IOException {
		for(int i=0; i<2; i++) {
			ifXNulls[i].readOperands(reader);
			assertEquals((i + 1) * 2, reader.getPc());
		}
		OperandStack stack = frame.getOperandStack();
		stack.pushRef(this);
		stack.pushRef(null);
		assertEquals(0, frame.getThreadSpace().getPc());
		ifXNulls[0].execute(frame);
		frame.synchronizedThreadSpacePc();
		assertEquals(6, frame.getThreadSpace().getPc());
		ifXNulls[1].execute(frame);
		frame.synchronizedThreadSpacePc();
		assertEquals(11, frame.getThreadSpace().getPc());
	}
	
}
