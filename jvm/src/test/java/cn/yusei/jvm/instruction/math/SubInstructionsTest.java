package cn.yusei.jvm.instruction.math;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import cn.yusei.jvm.instruction.base.NoOperandInstruction;
import cn.yusei.jvm.instruction.math.SubInstructions.DSUB;
import cn.yusei.jvm.instruction.math.SubInstructions.FSUB;
import cn.yusei.jvm.instruction.math.SubInstructions.ISUB;
import cn.yusei.jvm.instruction.math.SubInstructions.LSUB;
import cn.yusei.jvm.runtimespace.stack.Frame;
import cn.yusei.jvm.testutil.MockFactory;

public class SubInstructionsTest {

	private NoOperandInstruction[] subs;
	private static final int MAX_LOCAL_VARS_TABLE_CAPACITY = 7;
	private static final int MAX_OPERAND_STACK_CAPACITY = 16;
	private Frame frame;

	@Before
	public void setUp() {
		subs = new NoOperandInstruction[4];
		subs[0] = new ISUB();
		subs[1] = new LSUB();
		subs[2] = new FSUB();
		subs[3] = new DSUB();
		frame = new Frame(null, MockFactory.newMethod(MAX_LOCAL_VARS_TABLE_CAPACITY, MAX_OPERAND_STACK_CAPACITY));
	}

	@Test
	public void readOperandsAndExecute() {
		double delta = 0.01;
		for (int i = 0; i < 4; i++)
			subs[i].readOperands(null);
		frame.getOperandStack().pushInt(2);
		frame.getOperandStack().pushInt(1);
		subs[0].execute(frame);
		assertEquals(1, frame.getOperandStack().popInt());
		frame.getOperandStack().pushLong(22222222222L);
		frame.getOperandStack().pushLong(11111111111L);
		subs[1].execute(frame);
		assertEquals(11111111111L, frame.getOperandStack().popLong());
		frame.getOperandStack().pushFloat(2.1f);
		frame.getOperandStack().pushFloat(1.1f);
		subs[2].execute(frame);
		assertEquals(1.0f, frame.getOperandStack().popFloat(), delta);
		frame.getOperandStack().pushDouble(2.11111111);
		frame.getOperandStack().pushDouble(1.11111111);
		subs[3].execute(frame);
		assertEquals(1.00000000, frame.getOperandStack().popDouble(), delta);
	}

}
