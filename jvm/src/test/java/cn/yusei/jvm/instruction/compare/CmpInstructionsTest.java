package cn.yusei.jvm.instruction.compare;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import cn.yusei.jvm.instruction.base.NoOperandInstruction;
import cn.yusei.jvm.instruction.compare.CmpInstructions.DCMPG;
import cn.yusei.jvm.instruction.compare.CmpInstructions.DCMPL;
import cn.yusei.jvm.instruction.compare.CmpInstructions.FCMPG;
import cn.yusei.jvm.instruction.compare.CmpInstructions.FCMPL;
import cn.yusei.jvm.instruction.compare.CmpInstructions.LCMP;
import cn.yusei.jvm.runtimespace.stack.Frame;
import cn.yusei.jvm.runtimespace.stack.OperandStack;
import cn.yusei.jvm.testutil.MockFactory;

public class CmpInstructionsTest {

	private NoOperandInstruction[] xcmpxs;
	private static final int MAX_LOCAL_VARS_TABLE_CAPACITY = 7;
	private static final int MAX_OPERAND_STACK_CAPACITY = 16;
	private Frame frame;
	
	@Before
	public void setUp() {
		xcmpxs = new NoOperandInstruction[5];
		xcmpxs[0] = new LCMP();
		xcmpxs[1] = new FCMPG();
		xcmpxs[2] = new FCMPL();
		xcmpxs[3] = new DCMPG();
		xcmpxs[4] = new DCMPL();
		frame = new Frame(null, MockFactory.newMethod(MAX_LOCAL_VARS_TABLE_CAPACITY, MAX_OPERAND_STACK_CAPACITY));
	}
	
	@Test
	public void readOperandsAndExecute() {
		for(int i=0; i<5; i++)
			xcmpxs[i].readOperands(null);
		OperandStack stack = frame.getOperandStack();
		stack.pushLong(100L);
		stack.pushLong(101L);
		xcmpxs[0].execute(frame);
		assertEquals(1, stack.size());
		assertEquals(-1, stack.popInt());
		stack.pushFloat(100.21f);
		stack.pushFloat(100f);
		xcmpxs[2].execute(frame);
		assertEquals(1, stack.size());
		assertEquals(1, stack.popInt());
		stack.pushFloat(100.21f);
		stack.pushFloat(100.22f);
		xcmpxs[2].execute(frame);
		assertEquals(1, stack.size());
		assertEquals(-1, stack.popInt());
		stack.pushDouble(100.21);
		stack.pushDouble(100.21);
		xcmpxs[3].execute(frame);
		assertEquals(1, stack.size());
		assertEquals(0, stack.popInt());
		stack.pushDouble(100.21);
		stack.pushDouble(100.21);
		xcmpxs[4].execute(frame);
		assertEquals(1, stack.size());
		assertEquals(0, stack.popInt());
	}
	
	@Test
	public void compareNaN() {
		OperandStack stack = frame.getOperandStack();
		stack.pushFloat(2.1f);
		stack.pushFloat(Float.NaN);
		xcmpxs[1].execute(frame);
		assertEquals(1, stack.size());
		assertEquals(1, stack.popInt());
		stack.pushFloat(2.1f);
		stack.pushFloat(Float.NaN);
		xcmpxs[2].execute(frame);
		assertEquals(1, stack.size());
		assertEquals(-1, stack.popInt());
		stack.pushDouble(Float.NaN);
		stack.pushDouble(Float.NaN);
		xcmpxs[3].execute(frame);
		assertEquals(1, stack.size());
		assertEquals(1, stack.popInt());
		stack.pushDouble(Float.NaN);
		stack.pushDouble(Float.NaN);
		xcmpxs[4].execute(frame);
		assertEquals(1, stack.size());
		assertEquals(-1, stack.popInt());
	}
	
}
