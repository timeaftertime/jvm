package cn.yusei.jvm.instruction.math;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import cn.yusei.jvm.instruction.base.NoOperandInstruction;
import cn.yusei.jvm.instruction.math.AddInstructions.DADD;
import cn.yusei.jvm.instruction.math.AddInstructions.FADD;
import cn.yusei.jvm.instruction.math.AddInstructions.IADD;
import cn.yusei.jvm.instruction.math.AddInstructions.LADD;
import cn.yusei.jvm.runtimespace.stack.Frame;
import cn.yusei.jvm.testutil.MockFactory;

public class AddInstructionsTest {

	private NoOperandInstruction[] adds;
	private static final int MAX_LOCAL_VARS_TABLE_CAPACITY = 7;
	private static final int MAX_OPERAND_STACK_CAPACITY = 16;
	private Frame frame;
	
	@Before
	public void setUp() {
		adds = new NoOperandInstruction[4];
		adds[0] = new IADD();
		adds[1] = new LADD();
		adds[2] = new FADD();
		adds[3] = new DADD();
		frame = new Frame(null, MockFactory.newMethod(MAX_LOCAL_VARS_TABLE_CAPACITY, MAX_OPERAND_STACK_CAPACITY));
	}
	
	@Test
	public void readOperandsAndExecute() {
		double delta = 0.01;
		for(int i=0; i<4; i++)
			adds[i].readOperands(null);
		frame.getOperandStack().pushInt(1);
		frame.getOperandStack().pushInt(2);
		adds[0].execute(frame);
		assertEquals(3, frame.getOperandStack().popInt());
		frame.getOperandStack().pushLong(11111111111L);
		frame.getOperandStack().pushLong(22222222222L);
		adds[1].execute(frame);
		assertEquals(33333333333L, frame.getOperandStack().popLong());
		frame.getOperandStack().pushFloat(1.1f);
		frame.getOperandStack().pushFloat(2.1f);
		adds[2].execute(frame);
		assertEquals(3.2f, frame.getOperandStack().popFloat(), delta);
		frame.getOperandStack().pushDouble(1.11111111);
		frame.getOperandStack().pushDouble(2.11111111);
		adds[3].execute(frame);
		assertEquals(3.22222222, frame.getOperandStack().popDouble(), delta);
	}
	
}
