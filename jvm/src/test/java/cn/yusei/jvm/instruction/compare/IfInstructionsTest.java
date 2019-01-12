package cn.yusei.jvm.instruction.compare;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import cn.yusei.jvm.instruction.BytecodeReader;
import cn.yusei.jvm.instruction.base.Int16BranchInstruction;
import cn.yusei.jvm.instruction.compare.IfInstructions.IFEQ;
import cn.yusei.jvm.instruction.compare.IfInstructions.IFGE;
import cn.yusei.jvm.instruction.compare.IfInstructions.IFGT;
import cn.yusei.jvm.instruction.compare.IfInstructions.IFLE;
import cn.yusei.jvm.instruction.compare.IfInstructions.IFLT;
import cn.yusei.jvm.instruction.compare.IfInstructions.IFNE;
import cn.yusei.jvm.instruction.compare.IfInstructions.IFX;
import cn.yusei.jvm.runtimespace.ThreadSpace;
import cn.yusei.jvm.runtimespace.stack.Frame;
import cn.yusei.jvm.runtimespace.stack.OperandStack;
import cn.yusei.jvm.testutil.MockFactory;

public class IfInstructionsTest {

	private Int16BranchInstruction[] ifCmps;
	private static final int MAX_LOCAL_VARS_TABLE_CAPACITY = 7;
	private static final int MAX_OPERAND_STACK_CAPACITY = 16;
	private Frame frame;
	private BytecodeReader reader;
	private byte[] codes = new byte[] { 0, 6, 0, 5, 0, 4, 0, 3, 0, 2, 0, 1 };

	@Before
	public void setUp() {
		ifCmps = new IFX[6];
		ifCmps[0] = new IFEQ();
		ifCmps[1] = new IFNE();
		ifCmps[2] = new IFLT();
		ifCmps[3] = new IFLE();
		ifCmps[4] = new IFGT();
		ifCmps[5] = new IFGE();
		frame = new Frame(new ThreadSpace(),
				MockFactory.newMethod(MAX_LOCAL_VARS_TABLE_CAPACITY, MAX_OPERAND_STACK_CAPACITY));
		reader = new BytecodeReader(codes);
	}

	@Test
	public void readOperandsAndExecute() throws IOException {
		for (int i = 0; i < 6; i++) {
			ifCmps[i].readOperands(reader);
			assertEquals((i + 1) * 2, reader.getPc());
		}
		OperandStack stack = frame.getOperandStack();
		for (int i = 0; i < 6; i++)
			stack.pushInt(1);
		assertEquals(0, frame.getThreadSpace().getPc());
		ifCmps[0].execute(frame);
		frame.synchronizedThreadSpacePc();
		assertEquals(0, frame.getThreadSpace().getPc());
		ifCmps[1].execute(frame);
		frame.synchronizedThreadSpacePc();
		assertEquals(5, frame.getThreadSpace().getPc());
		ifCmps[2].execute(frame);
		frame.synchronizedThreadSpacePc();
		assertEquals(5, frame.getThreadSpace().getPc());
		ifCmps[3].execute(frame);
		frame.synchronizedThreadSpacePc();
		assertEquals(5, frame.getThreadSpace().getPc());
		ifCmps[4].execute(frame);
		frame.synchronizedThreadSpacePc();
		assertEquals(7, frame.getThreadSpace().getPc());
		ifCmps[5].execute(frame);
		frame.synchronizedThreadSpacePc();
		assertEquals(8, frame.getThreadSpace().getPc());
	}

}
