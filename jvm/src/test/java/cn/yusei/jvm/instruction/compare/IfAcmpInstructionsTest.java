package cn.yusei.jvm.instruction.compare;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import cn.yusei.jvm.instruction.BytecodeReader;
import cn.yusei.jvm.instruction.base.Int16BranchInstruction;
import cn.yusei.jvm.instruction.compare.IfAcmpInstructions.IF_ACMPEQ;
import cn.yusei.jvm.instruction.compare.IfAcmpInstructions.IF_ACMPNE;
import cn.yusei.jvm.instruction.compare.IfAcmpInstructions.IF_ACMPX;
import cn.yusei.jvm.runtimespace.Frame;
import cn.yusei.jvm.runtimespace.OperandStack;
import cn.yusei.jvm.runtimespace.ThreadSpace;

public class IfAcmpInstructionsTest {

	private Int16BranchInstruction[] ifAcmps;
	private static final int MAX_LOCAL_VARS_TABLE_CAPACITY = 7;
	private static final int MAX_OPERAND_STACK_CAPACITY = 16;
	private Frame frame;
	private BytecodeReader reader;
	private byte[] codes = new byte[] {0, 6, 0, 5};

	@Before
	public void setUp() {
		ifAcmps = new IF_ACMPX[2];
		ifAcmps[0] = new IF_ACMPEQ();
		ifAcmps[1] = new IF_ACMPNE();
		frame = new Frame(new ThreadSpace(), MAX_LOCAL_VARS_TABLE_CAPACITY, MAX_OPERAND_STACK_CAPACITY);
		reader = new BytecodeReader(codes);
	}

	@Test
	public void readOperandsAndExecute() throws IOException {
		for(int i=0; i<2; i++) {
			ifAcmps[i].readOperands(reader);
			assertEquals((i + 1) * 2, reader.getPc());
		}
		OperandStack stack = frame.getOperandStack();
		stack.pushRef(new String());
		stack.pushRef(new String());
		stack.pushRef(this);
		stack.pushRef(this);
		assertEquals(0, frame.getThreadSpace().getPc());
		ifAcmps[0].execute(frame);
		frame.synchronizedThreadSpacePc();
		assertEquals(6, frame.getThreadSpace().getPc());
		ifAcmps[1].execute(frame);
		frame.synchronizedThreadSpacePc();
		assertEquals(11, frame.getThreadSpace().getPc());
	}
	
}
