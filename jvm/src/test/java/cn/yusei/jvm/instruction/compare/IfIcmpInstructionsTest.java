package cn.yusei.jvm.instruction.compare;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import cn.yusei.jvm.instruction.BytecodeReader;
import cn.yusei.jvm.instruction.base.Int16BranchInstruction;
import cn.yusei.jvm.instruction.compare.IfIcmpInstructions.IF_ICMPEQ;
import cn.yusei.jvm.instruction.compare.IfIcmpInstructions.IF_ICMPGE;
import cn.yusei.jvm.instruction.compare.IfIcmpInstructions.IF_ICMPGT;
import cn.yusei.jvm.instruction.compare.IfIcmpInstructions.IF_ICMPLE;
import cn.yusei.jvm.instruction.compare.IfIcmpInstructions.IF_ICMPLT;
import cn.yusei.jvm.instruction.compare.IfIcmpInstructions.IF_ICMPNE;
import cn.yusei.jvm.instruction.compare.IfIcmpInstructions.IF_ICMPX;
import cn.yusei.jvm.runtimespace.ThreadSpace;
import cn.yusei.jvm.runtimespace.stack.Frame;
import cn.yusei.jvm.runtimespace.stack.OperandStack;
import cn.yusei.jvm.testutil.MockFactory;

public class IfIcmpInstructionsTest {

	private Int16BranchInstruction[] ifIcmps;
	private static final int MAX_LOCAL_VARS_TABLE_CAPACITY = 7;
	private static final int MAX_OPERAND_STACK_CAPACITY = 16;
	private Frame frame;
	private BytecodeReader reader;
	private byte[] codes = new byte[] { 0, 6, 0, 5, 0, 4, 0, 3, 0, 2, 0, 1 };

	@Before
	public void setUp() {
		ifIcmps = new IF_ICMPX[6];
		ifIcmps[0] = new IF_ICMPEQ();
		ifIcmps[1] = new IF_ICMPNE();
		ifIcmps[2] = new IF_ICMPLT();
		ifIcmps[3] = new IF_ICMPLE();
		ifIcmps[4] = new IF_ICMPGT();
		ifIcmps[5] = new IF_ICMPGE();
		frame = new Frame(new ThreadSpace(),
				MockFactory.newMethod(MAX_LOCAL_VARS_TABLE_CAPACITY, MAX_OPERAND_STACK_CAPACITY));
		reader = new BytecodeReader(codes);
	}

	@Test
	public void readOperandsAndExecute() throws IOException {
		for (int i = 0; i < 6; i++) {
			ifIcmps[i].readOperands(reader);
			assertEquals((i + 1) * 2, reader.getPc());
		}
		OperandStack stack = frame.getOperandStack();
		for (int i = 0; i < 6; i++) {
			stack.pushInt(1);
			stack.pushInt(2);
		}
		assertEquals(0, frame.getThreadSpace().getPc());
		ifIcmps[0].execute(frame);
		frame.synchronizedThreadSpacePc();
		assertEquals(0, frame.getThreadSpace().getPc());
		ifIcmps[1].execute(frame);
		frame.synchronizedThreadSpacePc();
		assertEquals(5, frame.getThreadSpace().getPc());
		ifIcmps[2].execute(frame);
		frame.synchronizedThreadSpacePc();
		assertEquals(9, frame.getThreadSpace().getPc());
		ifIcmps[3].execute(frame);
		frame.synchronizedThreadSpacePc();
		assertEquals(12, frame.getThreadSpace().getPc());
		ifIcmps[4].execute(frame);
		frame.synchronizedThreadSpacePc();
		assertEquals(12, frame.getThreadSpace().getPc());
		ifIcmps[5].execute(frame);
		frame.synchronizedThreadSpacePc();
		assertEquals(12, frame.getThreadSpace().getPc());
	}

}
