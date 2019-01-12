package cn.yusei.jvm.instruction.cast;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import cn.yusei.jvm.instruction.base.NoOperandInstruction;
import cn.yusei.jvm.instruction.cast.F2Instructions.F2D;
import cn.yusei.jvm.instruction.cast.F2Instructions.F2I;
import cn.yusei.jvm.instruction.cast.F2Instructions.F2L;
import cn.yusei.jvm.runtimespace.stack.Frame;
import cn.yusei.jvm.testutil.MockFactory;

public class F2InstructionsTest {

	private NoOperandInstruction[] f2xs;
	private static final int MAX_LOCAL_VARS_TABLE_CAPACITY = 7;
	private static final int MAX_OPERAND_STACK_CAPACITY = 16;
	private Frame frame;

	@Before
	public void setUp() {
		f2xs = new NoOperandInstruction[3];
		f2xs[0] = new F2I();
		f2xs[1] = new F2L();
		f2xs[2] = new F2D();
		frame = new Frame(null, MockFactory.newMethod(MAX_LOCAL_VARS_TABLE_CAPACITY, MAX_OPERAND_STACK_CAPACITY));
	}

	@Test
	public void readOperandsAndExecute() {
		double delta = 0.01;
		for (int i = 0; i < 3; i++)
			f2xs[i].readOperands(null);
		frame.getOperandStack().pushFloat(3.14f);
		f2xs[0].execute(frame);
		assertEquals((int) 3.14f, frame.getOperandStack().popInt(), delta);
		frame.getOperandStack().pushFloat(3.14f);
		f2xs[1].execute(frame);
		assertEquals((long) 3.14f, frame.getOperandStack().popLong(), delta);
		frame.getOperandStack().pushFloat(3.14f);
		f2xs[2].execute(frame);
		assertEquals((double) 3.14f, frame.getOperandStack().popDouble(), delta);
	}
}
