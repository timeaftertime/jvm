package cn.yusei.jvm.instruction.constant;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import cn.yusei.jvm.instruction.BytecodeReader;
import cn.yusei.jvm.instruction.base.NoOperandInstruction;
import cn.yusei.jvm.instruction.constant.ConstInstructions.ACONST_NULL;
import cn.yusei.jvm.instruction.constant.ConstInstructions.DCONST_0;
import cn.yusei.jvm.instruction.constant.ConstInstructions.DCONST_1;
import cn.yusei.jvm.instruction.constant.ConstInstructions.FCONST_0;
import cn.yusei.jvm.instruction.constant.ConstInstructions.FCONST_1;
import cn.yusei.jvm.instruction.constant.ConstInstructions.FCONST_2;
import cn.yusei.jvm.instruction.constant.ConstInstructions.ICONST_0;
import cn.yusei.jvm.instruction.constant.ConstInstructions.ICONST_1;
import cn.yusei.jvm.instruction.constant.ConstInstructions.ICONST_2;
import cn.yusei.jvm.instruction.constant.ConstInstructions.ICONST_3;
import cn.yusei.jvm.instruction.constant.ConstInstructions.ICONST_4;
import cn.yusei.jvm.instruction.constant.ConstInstructions.ICONST_5;
import cn.yusei.jvm.instruction.constant.ConstInstructions.ICONST_M1;
import cn.yusei.jvm.instruction.constant.ConstInstructions.LCONST_0;
import cn.yusei.jvm.instruction.constant.ConstInstructions.LCONST_1;
import cn.yusei.jvm.runtimespace.stack.Frame;
import cn.yusei.jvm.testutil.MockFactory;

public class ConstInstructionsTest {

	private NoOperandInstruction aconst_null;
	private NoOperandInstruction dconst_0;
	private NoOperandInstruction dconst_1;
	private NoOperandInstruction fconst_0;
	private NoOperandInstruction fconst_1;
	private NoOperandInstruction fconst_2;
	private NoOperandInstruction iconst_m1;
	private NoOperandInstruction iconst_0;
	private NoOperandInstruction iconst_1;
	private NoOperandInstruction iconst_2;
	private NoOperandInstruction iconst_3;
	private NoOperandInstruction iconst_4;
	private NoOperandInstruction iconst_5;
	private NoOperandInstruction lconst_0;
	private NoOperandInstruction lconst_1;

	private BytecodeReader reader;

	private Frame frame;

	private static final int MAX_LOCAL_VARS_TABLE_CAPACITY = 10;
	private static final int MAX_OPERAND_STACK_CAPACITY = 10;

	@Before
	public void setUp() {
		aconst_null = new ACONST_NULL();
		dconst_0 = new DCONST_0();
		dconst_1 = new DCONST_1();
		fconst_0 = new FCONST_0();
		fconst_1 = new FCONST_1();
		fconst_2 = new FCONST_2();
		iconst_m1 = new ICONST_M1();
		iconst_0 = new ICONST_0();
		iconst_1 = new ICONST_1();
		iconst_2 = new ICONST_2();
		iconst_3 = new ICONST_3();
		iconst_4 = new ICONST_4();
		iconst_5 = new ICONST_5();
		lconst_0 = new LCONST_0();
		lconst_1 = new LCONST_1();
		reader = null;
		frame = new Frame(null, MockFactory.newMethod(MAX_LOCAL_VARS_TABLE_CAPACITY, MAX_OPERAND_STACK_CAPACITY));
	}

	@Test
	public void readOperands() throws IOException {
		aconst_null.readOperands(reader);
		dconst_0.readOperands(reader);
		dconst_1.readOperands(reader);
		fconst_0.readOperands(reader);
		fconst_1.readOperands(reader);
		fconst_2.readOperands(reader);
		iconst_m1.readOperands(reader);
		iconst_0.readOperands(reader);
		iconst_1.readOperands(reader);
		iconst_2.readOperands(reader);
		iconst_3.readOperands(reader);
		iconst_4.readOperands(reader);
		iconst_5.readOperands(reader);
		lconst_0.readOperands(reader);
		lconst_1.readOperands(reader);
	}

	@Test
	public void execute() {
		double delta = 0.01;
		aconst_null.execute(frame);
		assertEquals(1, frame.getOperandStack().size());
		assertSame(null, frame.getOperandStack().popRef());
		dconst_0.execute(frame);
		assertEquals(2, frame.getOperandStack().size());
		assertEquals(0, frame.getOperandStack().popDouble(), delta);
		dconst_1.execute(frame);
		assertEquals(2, frame.getOperandStack().size());
		assertEquals(1, frame.getOperandStack().popDouble(), delta);
		fconst_0.execute(frame);
		assertEquals(1, frame.getOperandStack().size());
		assertEquals(0f, frame.getOperandStack().popFloat(), delta);
		fconst_1.execute(frame);
		assertEquals(1, frame.getOperandStack().size());
		assertEquals(1f, frame.getOperandStack().popFloat(), delta);
		fconst_2.execute(frame);
		assertEquals(1, frame.getOperandStack().size());
		assertEquals(2f, frame.getOperandStack().popFloat(), delta);
		iconst_m1.execute(frame);
		assertEquals(1, frame.getOperandStack().size());
		assertEquals(-1, frame.getOperandStack().popInt());
		iconst_0.execute(frame);
		assertEquals(1, frame.getOperandStack().size());
		assertEquals(0, frame.getOperandStack().popInt());
		iconst_1.execute(frame);
		assertEquals(1, frame.getOperandStack().size());
		assertEquals(1, frame.getOperandStack().popInt());
		iconst_2.execute(frame);
		assertEquals(1, frame.getOperandStack().size());
		assertEquals(2, frame.getOperandStack().popInt());
		iconst_3.execute(frame);
		assertEquals(1, frame.getOperandStack().size());
		assertEquals(3, frame.getOperandStack().popInt());
		iconst_4.execute(frame);
		assertEquals(1, frame.getOperandStack().size());
		assertEquals(4, frame.getOperandStack().popInt());
		iconst_5.execute(frame);
		assertEquals(1, frame.getOperandStack().size());
		assertEquals(5, frame.getOperandStack().popInt());
		lconst_0.execute(frame);
		assertEquals(2, frame.getOperandStack().size());
		assertEquals(0, frame.getOperandStack().popLong());
		lconst_1.execute(frame);
		assertEquals(2, frame.getOperandStack().size());
		assertEquals(1, frame.getOperandStack().popLong());
	}

}
