package cn.yusei.jvm.instruction.stack;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import cn.yusei.jvm.instruction.base.NoOperandInstruction;
import cn.yusei.jvm.runtimespace.stack.Frame;
import cn.yusei.jvm.testutil.MockFactory;

public class SWAPTest {

	private NoOperandInstruction swap;
	private static final int MAX_LOCAL_VARS_TABLE_CAPACITY = 7;
	private static final int MAX_OPERAND_STACK_CAPACITY = 16;
	private Frame frame;

	@Before
	public void setUp() {
		swap = new SWAP();
		frame = new Frame(null, MockFactory.newMethod(MAX_LOCAL_VARS_TABLE_CAPACITY, MAX_OPERAND_STACK_CAPACITY));
	}

	@Test
	public void readOperandsAndExecute() {
		swap.readOperands(null);
		frame.getOperandStack().pushInt(1);
		frame.getOperandStack().pushInt(2);
		swap.execute(frame);
		assertEquals(2, frame.getOperandStack().size());
		assertEquals(1, frame.getOperandStack().popInt());
		assertEquals(2, frame.getOperandStack().popInt());
	}

}
