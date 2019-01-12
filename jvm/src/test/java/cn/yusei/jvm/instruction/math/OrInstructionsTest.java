package cn.yusei.jvm.instruction.math;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import cn.yusei.jvm.instruction.base.NoOperandInstruction;
import cn.yusei.jvm.instruction.math.OrInstructions.IOR;
import cn.yusei.jvm.instruction.math.OrInstructions.LOR;
import cn.yusei.jvm.runtimespace.stack.Frame;
import cn.yusei.jvm.testutil.MockFactory;

public class OrInstructionsTest {

	private NoOperandInstruction[] ors;
	private static final int MAX_LOCAL_VARS_TABLE_CAPACITY = 7;
	private static final int MAX_OPERAND_STACK_CAPACITY = 16;
	private Frame frame;

	@Before
	public void setUp() {
		ors = new NoOperandInstruction[2];
		ors[0] = new IOR();
		ors[1] = new LOR();
		frame = new Frame(null, MockFactory.newMethod(MAX_LOCAL_VARS_TABLE_CAPACITY, MAX_OPERAND_STACK_CAPACITY));
	}

	@Test
	public void readOperandsAndExecute() {
		for (int i = 0; i < 2; i++)
			ors[i].readOperands(null);
		frame.getOperandStack().pushInt(207);
		frame.getOperandStack().pushInt(182);
		ors[0].execute(frame);
		assertEquals(207 | 182, frame.getOperandStack().popInt());
		frame.getOperandStack().pushLong(-8);
		frame.getOperandStack().pushLong(3);
		ors[1].execute(frame);
		assertEquals(-8L | 3L, frame.getOperandStack().popLong());
	}

}
