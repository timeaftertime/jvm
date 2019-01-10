package cn.yusei.jvm.instruction.cast;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import cn.yusei.jvm.instruction.base.NoOperandInstruction;
import cn.yusei.jvm.instruction.cast.L2Instructions.L2D;
import cn.yusei.jvm.instruction.cast.L2Instructions.L2F;
import cn.yusei.jvm.instruction.cast.L2Instructions.L2I;
import cn.yusei.jvm.runtimespace.Frame;

public class L2InstructionsTest {

	private NoOperandInstruction[] l2xs;
	private static final int MAX_LOCAL_VARS_TABLE_CAPACITY = 7;
	private static final int MAX_OPERAND_STACK_CAPACITY = 16;
	private Frame frame;
	
	@Before
	public void setUp() {
		l2xs = new NoOperandInstruction[3];
		l2xs[0] = new L2I();
		l2xs[1] = new L2F();
		l2xs[2] = new L2D();
		frame =  new Frame(null, MAX_LOCAL_VARS_TABLE_CAPACITY, MAX_OPERAND_STACK_CAPACITY);
	}
	
	@Test
	public void readOperandsAndExecute() {
		double delta = 0.01;
		for(int i=0; i<3; i++)
			l2xs[i].readOperands(null);
		frame.getOperandStack().pushLong(11111111111L);
		l2xs[0].execute(frame);
		assertEquals((int) 11111111111L, frame.getOperandStack().popInt());
		frame.getOperandStack().pushLong(11111111111L);
		l2xs[1].execute(frame);
		assertEquals((float) 11111111111L, frame.getOperandStack().popFloat(), delta);
		frame.getOperandStack().pushLong(11111111111L);
		l2xs[2].execute(frame);
		assertEquals((double) 11111111111L, frame.getOperandStack().popDouble(), delta);
	}
	
}
