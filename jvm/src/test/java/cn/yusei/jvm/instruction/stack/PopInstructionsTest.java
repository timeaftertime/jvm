package cn.yusei.jvm.instruction.stack;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import cn.yusei.jvm.instruction.base.NoOperandInstruction;
import cn.yusei.jvm.instruction.stack.PopInstructions.POP;
import cn.yusei.jvm.instruction.stack.PopInstructions.POP2;
import cn.yusei.jvm.runtimespace.stack.Frame;
import cn.yusei.jvm.slot.Slot;
import cn.yusei.jvm.testutil.MockFactory;

public class PopInstructionsTest {

	private NoOperandInstruction pop;
	private NoOperandInstruction pop2;

	private static final int MAX_LOCAL_VARS_TABLE_CAPACITY = 7;
	private static final int MAX_OPERAND_STACK_CAPACITY = 16;
	private Frame frame;

	@Before
	public void setUp() {
		pop = new POP();
		pop2 = new POP2();
		frame = new Frame(null, MockFactory.newMethod(MAX_LOCAL_VARS_TABLE_CAPACITY, MAX_OPERAND_STACK_CAPACITY));
	}

	@Test
	public void readOperandsAndExecute() {
		pop.readOperands(null);
		pop2.readOperands(null);
		frame.getOperandStack().pushSlot(new Slot(0, null));
		frame.getOperandStack().pushSlot(new Slot(0, null));
		frame.getOperandStack().pushSlot(new Slot(0, null));
		assertEquals(3, frame.getOperandStack().size());
		pop.execute(frame);
		assertEquals(2, frame.getOperandStack().size());
		pop2.execute(frame);
		assertEquals(0, frame.getOperandStack().size());
	}

}
