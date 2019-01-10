package cn.yusei.jvm.instruction.cast;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import cn.yusei.jvm.instruction.base.NoOperandInstruction;
import cn.yusei.jvm.instruction.cast.D2Instructions.D2F;
import cn.yusei.jvm.instruction.cast.D2Instructions.D2I;
import cn.yusei.jvm.instruction.cast.D2Instructions.D2L;
import cn.yusei.jvm.runtimespace.Frame;

public class D2InstructionsTest {

	private NoOperandInstruction[] d2xs;
	private static final int MAX_LOCAL_VARS_TABLE_CAPACITY = 7;
	private static final int MAX_OPERAND_STACK_CAPACITY = 16;
	private Frame frame;

	@Before
	public void setUp() {
		d2xs = new NoOperandInstruction[3];
		d2xs[0] = new D2I();
		d2xs[1] = new D2L();
		d2xs[2] = new D2F();
		frame = new Frame(null, MAX_LOCAL_VARS_TABLE_CAPACITY, MAX_OPERAND_STACK_CAPACITY);
	}

	@Test
	public void readOperandsAndExecute() {
		double delta = 0.01;
		for (int i = 0; i < 3; i++)
			d2xs[i].readOperands(null);
		frame.getOperandStack().pushDouble(3.14);
		d2xs[0].execute(frame);
		assertEquals((int) 3.14, frame.getOperandStack().popInt(), delta);
		frame.getOperandStack().pushDouble(3.14);
		d2xs[1].execute(frame);
		assertEquals((long) 3.14, frame.getOperandStack().popLong(), delta);
		frame.getOperandStack().pushDouble(3.14);
		d2xs[2].execute(frame);
		assertEquals((float) 3.14, frame.getOperandStack().popFloat(), delta);
	}
}
