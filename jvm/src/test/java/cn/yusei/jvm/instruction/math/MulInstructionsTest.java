package cn.yusei.jvm.instruction.math;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import cn.yusei.jvm.instruction.base.NoOperandInstruction;
import cn.yusei.jvm.instruction.math.MulInstructions.DMUL;
import cn.yusei.jvm.instruction.math.MulInstructions.FMUL;
import cn.yusei.jvm.instruction.math.MulInstructions.IMUL;
import cn.yusei.jvm.instruction.math.MulInstructions.LMUL;
import cn.yusei.jvm.runtimespace.Frame;

public class MulInstructionsTest {
	
	private NoOperandInstruction[] muls;
	private static final int MAX_LOCAL_VARS_TABLE_CAPACITY = 7;
	private static final int MAX_OPERAND_STACK_CAPACITY = 16;
	private Frame frame;
	
	@Before
	public void setUp() {
		muls = new NoOperandInstruction[4];
		muls[0] = new IMUL();
		muls[1] = new LMUL();
		muls[2] = new FMUL();
		muls[3] = new DMUL();
		frame =  new Frame(null, MAX_LOCAL_VARS_TABLE_CAPACITY, MAX_OPERAND_STACK_CAPACITY);
	}
	
	@Test
	public void readOperandsAndExecute() {
		double delta = 0.01;
		for(int i=0; i<4; i++)
			muls[i].readOperands(null);
		frame.getOperandStack().pushInt(2);
		frame.getOperandStack().pushInt(1);
		muls[0].execute(frame);
		assertEquals(2, frame.getOperandStack().popInt());
		frame.getOperandStack().pushLong(22222222222L);
		frame.getOperandStack().pushLong(11111111111L);
		muls[1].execute(frame);
		assertEquals(22222222222L * 11111111111L, frame.getOperandStack().popLong());
		frame.getOperandStack().pushFloat(2.1f);
		frame.getOperandStack().pushFloat(1.1f);
		muls[2].execute(frame);
		assertEquals(2.1f * 1.1f, frame.getOperandStack().popFloat(), delta);
		frame.getOperandStack().pushDouble(2.11111111);
		frame.getOperandStack().pushDouble(1.11111111);
		muls[3].execute(frame);
		assertEquals(2.11111111 * 1.1111111, frame.getOperandStack().popDouble(), delta);
	}

}
