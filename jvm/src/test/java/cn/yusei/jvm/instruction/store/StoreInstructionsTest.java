package cn.yusei.jvm.instruction.store;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import cn.yusei.jvm.instruction.BytecodeReader;
import cn.yusei.jvm.instruction.base.NoOperandInstruction;
import cn.yusei.jvm.instruction.base.UInt8Instruction;
import cn.yusei.jvm.instruction.store.StoreInstructions.ASTORE;
import cn.yusei.jvm.instruction.store.StoreInstructions.ASTORE_0;
import cn.yusei.jvm.instruction.store.StoreInstructions.ASTORE_1;
import cn.yusei.jvm.instruction.store.StoreInstructions.ASTORE_2;
import cn.yusei.jvm.instruction.store.StoreInstructions.ASTORE_3;
import cn.yusei.jvm.instruction.store.StoreInstructions.DSTORE;
import cn.yusei.jvm.instruction.store.StoreInstructions.DSTORE_0;
import cn.yusei.jvm.instruction.store.StoreInstructions.DSTORE_1;
import cn.yusei.jvm.instruction.store.StoreInstructions.DSTORE_2;
import cn.yusei.jvm.instruction.store.StoreInstructions.DSTORE_3;
import cn.yusei.jvm.instruction.store.StoreInstructions.FSTORE;
import cn.yusei.jvm.instruction.store.StoreInstructions.FSTORE_0;
import cn.yusei.jvm.instruction.store.StoreInstructions.FSTORE_1;
import cn.yusei.jvm.instruction.store.StoreInstructions.FSTORE_2;
import cn.yusei.jvm.instruction.store.StoreInstructions.FSTORE_3;
import cn.yusei.jvm.instruction.store.StoreInstructions.ISTORE;
import cn.yusei.jvm.instruction.store.StoreInstructions.ISTORE_0;
import cn.yusei.jvm.instruction.store.StoreInstructions.ISTORE_1;
import cn.yusei.jvm.instruction.store.StoreInstructions.ISTORE_2;
import cn.yusei.jvm.instruction.store.StoreInstructions.ISTORE_3;
import cn.yusei.jvm.instruction.store.StoreInstructions.LSTORE;
import cn.yusei.jvm.instruction.store.StoreInstructions.LSTORE_0;
import cn.yusei.jvm.instruction.store.StoreInstructions.LSTORE_1;
import cn.yusei.jvm.instruction.store.StoreInstructions.LSTORE_2;
import cn.yusei.jvm.instruction.store.StoreInstructions.LSTORE_3;
import cn.yusei.jvm.runtimespace.Frame;

public class StoreInstructionsTest {

	private UInt8Instruction istore;
	private NoOperandInstruction[] istore_x;
	private UInt8Instruction lstore;
	private NoOperandInstruction[] lstore_x;
	private UInt8Instruction fstore;
	private NoOperandInstruction[] fstore_x;
	private UInt8Instruction dstore;
	private NoOperandInstruction[] dstore_x;
	private UInt8Instruction astore;
	private NoOperandInstruction[] astore_x;

	private static final int MAX_LOCAL_VARS_TABLE_CAPACITY = 7;
	private static final int MAX_OPERAND_STACK_CAPACITY = 16;
	private BytecodeReader reader;
	private Frame frame;
	private byte[] codes = new byte[] { 5, 5, 5, 5, 5 };

	@Before
	public void setUp() {
		istore = new ISTORE();
		istore_x = new NoOperandInstruction[4];
		istore_x[0] = new ISTORE_0();
		istore_x[1] = new ISTORE_1();
		istore_x[2] = new ISTORE_2();
		istore_x[3] = new ISTORE_3();
		lstore = new LSTORE();
		lstore_x = new NoOperandInstruction[4];
		lstore_x[0] = new LSTORE_0();
		lstore_x[1] = new LSTORE_1();
		lstore_x[2] = new LSTORE_2();
		lstore_x[3] = new LSTORE_3();
		fstore = new FSTORE();
		fstore_x = new NoOperandInstruction[4];
		fstore_x[0] = new FSTORE_0();
		fstore_x[1] = new FSTORE_1();
		fstore_x[2] = new FSTORE_2();
		fstore_x[3] = new FSTORE_3();
		dstore = new DSTORE();
		dstore_x = new NoOperandInstruction[4];
		dstore_x[0] = new DSTORE_0();
		dstore_x[1] = new DSTORE_1();
		dstore_x[2] = new DSTORE_2();
		dstore_x[3] = new DSTORE_3();
		astore = new ASTORE();
		astore_x = new NoOperandInstruction[4];
		astore_x[0] = new ASTORE_0();
		astore_x[1] = new ASTORE_1();
		astore_x[2] = new ASTORE_2();
		astore_x[3] = new ASTORE_3();

		frame = new Frame(null, MAX_LOCAL_VARS_TABLE_CAPACITY, MAX_OPERAND_STACK_CAPACITY);
		reader = new BytecodeReader(codes);
	}

	@Test
	public void readOperandsAndExecute() throws IOException {
		readOperandsTest();
		executeTest();
	}

	private void readOperandsTest() throws IOException {
		readNoOperandsTest();
		readUInt8Test();
	}

	private void readNoOperandsTest() throws IOException {
		istore.readOperands(reader);
		assertEquals(1, reader.getPc());
		lstore.readOperands(reader);
		assertEquals(2, reader.getPc());
		fstore.readOperands(reader);
		assertEquals(3, reader.getPc());
		dstore.readOperands(reader);
		assertEquals(4, reader.getPc());
		astore.readOperands(reader);
		assertEquals(5, reader.getPc());
	}

	private void readUInt8Test() {
		for (int i = 0; i < 4; i++) {
			istore_x[i].readOperands(reader);
			assertEquals(5, reader.getPc());
		}
		for (int i = 0; i < 4; i++) {
			lstore_x[i].readOperands(reader);
			assertEquals(5, reader.getPc());
		}
		for (int i = 0; i < 4; i++) {
			fstore_x[i].readOperands(reader);
			assertEquals(5, reader.getPc());
		}
		for (int i = 0; i < 4; i++) {
			dstore_x[i].readOperands(reader);
			assertEquals(5, reader.getPc());
		}
		for (int i = 0; i < 4; i++) {
			astore_x[i].readOperands(reader);
			assertEquals(5, reader.getPc());
		}
	}

	private void executeTest() {
		executeLoadTest();
		executeUint8Test();
	}

	private void executeLoadTest() {
		double delta = 0.01;
		initOperandStack();
		istore.execute(frame);
		assertEquals(6, frame.getLocalVarsTable().getInt(5));
		lstore.execute(frame);
		assertEquals(0x0000000400000005L, frame.getLocalVarsTable().getLong(5));
		fstore.execute(frame);
		assertEquals(Float.intBitsToFloat(3), frame.getLocalVarsTable().getFloat(5), delta);
		dstore.execute(frame);
		assertEquals(Double.longBitsToDouble(0x0000000100000002L), frame.getLocalVarsTable().getDouble(5), delta);
		astore.execute(frame);
		assertEquals(this, frame.getLocalVarsTable().getRef(5));
	}

	private void initOperandStack() {
		frame.getOperandStack().pushRef(this);
		for (int i = 1; i <= 6; i++)
			frame.getOperandStack().pushInt(i);
	}

	private void executeUint8Test() {
		double delta = 0.01;
		for (int i = 0; i < 4; i++)
			frame.getOperandStack().pushInt(3 - i);
		for (int i = 0; i < 4; i++) {
			istore_x[i].execute(frame);
			assertEquals(i, frame.getLocalVarsTable().getInt(i));
		}
		for (int i = 0; i < 4; i++)
			frame.getOperandStack().pushLong(3 - i);
		for (int i = 0; i < 4; i++) {
			lstore_x[i].execute(frame);
			assertEquals(i, frame.getLocalVarsTable().getLong(i));
		}
		for (int i = 0; i < 4; i++)
			frame.getOperandStack().pushFloat(3 - i);
		for (int i = 0; i < 4; i++) {
			fstore_x[i].execute(frame);
			assertEquals(Float.valueOf(i), frame.getLocalVarsTable().getFloat(i), delta);
		}
		for (int i = 0; i < 4; i++)
			frame.getOperandStack().pushDouble(3 - i);
		for (int i = 0; i < 4; i++) {
			dstore_x[i].execute(frame);
			assertEquals(Double.valueOf(i), frame.getLocalVarsTable().getDouble(i), delta);
		}
		for (int i = 0; i < 4; i++)
			frame.getOperandStack().pushRef("StoreInstructions" + (3 - i));
		for (int i = 0; i < 4; i++) {
			astore_x[i].execute(frame);
			assertEquals("StoreInstructions" + i, frame.getLocalVarsTable().getRef(i));
		}
	}

}
