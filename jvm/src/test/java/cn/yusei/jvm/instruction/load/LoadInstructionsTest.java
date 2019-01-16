package cn.yusei.jvm.instruction.load;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.io.IOException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import cn.yusei.jvm.ObjectRef;
import cn.yusei.jvm.instruction.BytecodeReader;
import cn.yusei.jvm.instruction.base.NoOperandInstruction;
import cn.yusei.jvm.instruction.base.UInt8Instruction;
import cn.yusei.jvm.instruction.load.LoadInstructions.ALOAD;
import cn.yusei.jvm.instruction.load.LoadInstructions.ALOAD_0;
import cn.yusei.jvm.instruction.load.LoadInstructions.ALOAD_1;
import cn.yusei.jvm.instruction.load.LoadInstructions.ALOAD_2;
import cn.yusei.jvm.instruction.load.LoadInstructions.ALOAD_3;
import cn.yusei.jvm.instruction.load.LoadInstructions.DLOAD;
import cn.yusei.jvm.instruction.load.LoadInstructions.DLOAD_0;
import cn.yusei.jvm.instruction.load.LoadInstructions.DLOAD_1;
import cn.yusei.jvm.instruction.load.LoadInstructions.DLOAD_2;
import cn.yusei.jvm.instruction.load.LoadInstructions.DLOAD_3;
import cn.yusei.jvm.instruction.load.LoadInstructions.FLOAD;
import cn.yusei.jvm.instruction.load.LoadInstructions.FLOAD_0;
import cn.yusei.jvm.instruction.load.LoadInstructions.FLOAD_1;
import cn.yusei.jvm.instruction.load.LoadInstructions.FLOAD_2;
import cn.yusei.jvm.instruction.load.LoadInstructions.FLOAD_3;
import cn.yusei.jvm.instruction.load.LoadInstructions.ILOAD;
import cn.yusei.jvm.instruction.load.LoadInstructions.ILOAD_0;
import cn.yusei.jvm.instruction.load.LoadInstructions.ILOAD_1;
import cn.yusei.jvm.instruction.load.LoadInstructions.ILOAD_2;
import cn.yusei.jvm.instruction.load.LoadInstructions.ILOAD_3;
import cn.yusei.jvm.instruction.load.LoadInstructions.LLOAD;
import cn.yusei.jvm.instruction.load.LoadInstructions.LLOAD_0;
import cn.yusei.jvm.instruction.load.LoadInstructions.LLOAD_1;
import cn.yusei.jvm.instruction.load.LoadInstructions.LLOAD_2;
import cn.yusei.jvm.instruction.load.LoadInstructions.LLOAD_3;
import cn.yusei.jvm.runtimespace.stack.Frame;
import cn.yusei.jvm.runtimespace.stack.LocalVarsTable;
import cn.yusei.jvm.testutil.MockFactory;

public class LoadInstructionsTest {

	private UInt8Instruction iload;
	private NoOperandInstruction[] iload_xs;
	private UInt8Instruction lload;
	private NoOperandInstruction[] lload_xs;
	private UInt8Instruction fload;
	private NoOperandInstruction[] fload_xs;
	private UInt8Instruction dload;
	private NoOperandInstruction[] dload_xs;
	private UInt8Instruction aload;
	private NoOperandInstruction[] aload_xs;

	private static final int MAX_LOCAL_VARS_TABLE_CAPACITY = 10;
	private static final int MAX_OPERAND_STACK_CAPACITY = 10;
	private Frame frame;
	private BytecodeReader reader;
	private byte[] codes = new byte[] { 5, 5, 5, 5, 5 };
	private static ObjectRef[] refs = new ObjectRef[5];

	@BeforeClass
	public static void beforeClass() {
		for (int i = 0; i < 5; i++)
			refs[i] = ObjectRef.newObject(null, 0);
	}

	@Before
	public void setUp() {
		iload = new ILOAD();
		iload_xs = new NoOperandInstruction[4];
		iload_xs[0] = new ILOAD_0();
		iload_xs[1] = new ILOAD_1();
		iload_xs[2] = new ILOAD_2();
		iload_xs[3] = new ILOAD_3();
		lload = new LLOAD();
		lload_xs = new NoOperandInstruction[4];
		lload_xs[0] = new LLOAD_0();
		lload_xs[1] = new LLOAD_1();
		lload_xs[2] = new LLOAD_2();
		lload_xs[3] = new LLOAD_3();
		fload = new FLOAD();
		fload_xs = new NoOperandInstruction[4];
		fload_xs[0] = new FLOAD_0();
		fload_xs[1] = new FLOAD_1();
		fload_xs[2] = new FLOAD_2();
		fload_xs[3] = new FLOAD_3();
		dload = new DLOAD();
		dload_xs = new NoOperandInstruction[4];
		dload_xs[0] = new DLOAD_0();
		dload_xs[1] = new DLOAD_1();
		dload_xs[2] = new DLOAD_2();
		dload_xs[3] = new DLOAD_3();
		aload = new ALOAD();
		aload_xs = new NoOperandInstruction[4];
		aload_xs[0] = new ALOAD_0();
		aload_xs[1] = new ALOAD_1();
		aload_xs[2] = new ALOAD_2();
		aload_xs[3] = new ALOAD_3();

		frame = new Frame(null, MockFactory.newMethod(MAX_LOCAL_VARS_TABLE_CAPACITY, MAX_OPERAND_STACK_CAPACITY));
		reader = new BytecodeReader(codes);
	}

	@Test
	public void readOperandsAndExecute() throws IOException {
		readOperandsTest();
		executeTest();
	}

	private void readOperandsTest() throws IOException {
		readUint8Test();
		readNoOperandsTest();
	}

	private void executeTest() {
		double delta = 0.001;
		initLocalVarsTableWithInt();
		iload.execute(frame);
		assertEquals(5, frame.getOperandStack().popInt());
		for (int i = 0; i < 4; i++) {
			iload_xs[i].execute(frame);
			assertEquals(i, frame.getOperandStack().popInt());
		}
		lload.execute(frame);
		assertEquals(0x0000000500000006L, frame.getOperandStack().popLong());
		for (int i = 0; i < 4; i++) {
			lload_xs[i].execute(frame);
			assertEquals(i * 0x0000000100000000L + (i + 1) * 0x0000000000000001L, frame.getOperandStack().popLong());
		}
		fload.execute(frame);
		assertEquals(Float.intBitsToFloat(0x00050006), frame.getOperandStack().popFloat(), delta);
		for (int i = 0; i < 4; i++) {
			fload_xs[i].execute(frame);
			assertEquals(Float.intBitsToFloat(i * 0x00010000 + (i + 1) * 0x00000001),
					frame.getOperandStack().popFloat(), delta);
		}
		dload.execute(frame);
		assertEquals(Double.longBitsToDouble(0x0000000500000006L), frame.getOperandStack().popDouble(), delta);
		for (int i = 0; i < 4; i++) {
			dload_xs[i].execute(frame);
			assertEquals(Double.longBitsToDouble(i * 0x0000000100000000L + (i + 1) * 0x0000000000000001L),
					frame.getOperandStack().popDouble(), delta);
		}
		initLocalVarsTableWithRef();
		aload.execute(frame);
		assertSame(refs[4], frame.getOperandStack().popRef());
		for (int i = 0; i < 4; i++) {
			aload_xs[i].execute(frame);
			assertEquals(refs[i], frame.getOperandStack().popRef());
		}
	}

	private void readUint8Test() throws IOException {
		iload.readOperands(reader);
		assertEquals(1, reader.getPc());
		lload.readOperands(reader);
		assertEquals(2, reader.getPc());
		fload.readOperands(reader);
		assertEquals(3, reader.getPc());
		dload.readOperands(reader);
		assertEquals(4, reader.getPc());
		aload.readOperands(reader);
		assertEquals(5, reader.getPc());
	}

	private void readNoOperandsTest() {
		for (int i = 0; i < 4; i++) {
			iload_xs[i].readOperands(reader);
			assertEquals(5, reader.getPc());
		}
		for (int i = 0; i < 4; i++) {
			lload_xs[i].readOperands(reader);
			assertEquals(5, reader.getPc());
		}
		for (int i = 0; i < 4; i++) {
			fload_xs[i].readOperands(reader);
			assertEquals(5, reader.getPc());
		}
		for (int i = 0; i < 4; i++) {
			dload_xs[i].readOperands(reader);
			assertEquals(5, reader.getPc());
		}
	}

	private void initLocalVarsTableWithInt() {
		LocalVarsTable table = frame.getLocalVarsTable();
		table.setInt(0, 0);
		table.setInt(1, 1);
		table.setInt(2, 2);
		table.setInt(3, 3);
		table.setInt(4, 4);
		table.setInt(5, 5);
		table.setInt(6, 6);
		table.setRef(9, refs[0]);
	}

	private void initLocalVarsTableWithRef() {
		LocalVarsTable table = frame.getLocalVarsTable();
		for (int i = 0; i < 5; i++)
			table.setRef(i, refs[i]);
		table.setRef(5, refs[4]);
	}
}
