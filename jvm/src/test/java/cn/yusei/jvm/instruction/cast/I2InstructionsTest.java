package cn.yusei.jvm.instruction.cast;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import cn.yusei.jvm.instruction.base.NoOperandInstruction;
import cn.yusei.jvm.instruction.cast.I2Instructions.I2D;
import cn.yusei.jvm.instruction.cast.I2Instructions.I2F;
import cn.yusei.jvm.instruction.cast.I2Instructions.I2L;
import cn.yusei.jvm.runtimespace.Frame;

public class I2InstructionsTest {

	private NoOperandInstruction[] i2xs;
	private static final int MAX_LOCAL_VARS_TABLE_CAPACITY = 7;
	private static final int MAX_OPERAND_STACK_CAPACITY = 16;
	private Frame frame;
	
	@Before
	public void setUp() {
		i2xs = new NoOperandInstruction[3];
		i2xs[0] = new I2L();
		i2xs[1] = new I2F();
		i2xs[2] = new I2D();
		frame =  new Frame(null, MAX_LOCAL_VARS_TABLE_CAPACITY, MAX_OPERAND_STACK_CAPACITY);
	}
	
	@Test
	public void readOperandsAndExecute() {
		double delta = 0.01;
		for(int i=0; i<3; i++)
			i2xs[i].readOperands(null);
		frame.getOperandStack().pushInt(1);
		i2xs[0].execute(frame);
		assertEquals(1L, frame.getOperandStack().popLong());
		frame.getOperandStack().pushInt(1);
		i2xs[1].execute(frame);
		assertEquals(1f, frame.getOperandStack().popFloat(), delta);
		frame.getOperandStack().pushInt(1);
		i2xs[2].execute(frame);
		assertEquals(1, frame.getOperandStack().popDouble(), delta);
	}
	
}
