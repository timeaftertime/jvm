package cn.yusei.jvm.instruction.load;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

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
import cn.yusei.jvm.runtimespace.Frame;
import cn.yusei.jvm.runtimespace.LocalVarsTable;

public class LoadInstructionsTest {
	
	private UInt8Instruction iload;
	private NoOperandInstruction iload_0;
	private NoOperandInstruction iload_1;
	private NoOperandInstruction iload_2;
	private NoOperandInstruction iload_3;
	private UInt8Instruction lload;
	private NoOperandInstruction lload_0;
	private NoOperandInstruction lload_1;
	private NoOperandInstruction lload_2;
	private NoOperandInstruction lload_3;
	private UInt8Instruction fload;
	private NoOperandInstruction fload_0;
	private NoOperandInstruction fload_1;
	private NoOperandInstruction fload_2;
	private NoOperandInstruction fload_3;
	private UInt8Instruction dload;
	private NoOperandInstruction dload_0;
	private NoOperandInstruction dload_1;
	private NoOperandInstruction dload_2;
	private NoOperandInstruction dload_3;
	private UInt8Instruction aload;
	private NoOperandInstruction aload_0;
	private NoOperandInstruction aload_1;
	private NoOperandInstruction aload_2;
	private NoOperandInstruction aload_3;
	
	private static final int MAX_LOCAL_VARS_TABLE_CAPACITY = 10;
	private static final int MAX_OPERAND_STACK_CAPACITY = 10;
	private Frame frame;
	private BytecodeReader reader;
	private byte[] codes = new byte[] {5, 5, 5, 5, 5};
	
	@Before
	public void setUp() {
		iload = new ILOAD();
		iload_0 = new ILOAD_0();
		iload_1 = new ILOAD_1();
		iload_2 = new ILOAD_2();
		iload_3 = new ILOAD_3();
		lload = new LLOAD();
		lload_0 = new LLOAD_0();
		lload_1 = new LLOAD_1();
		lload_2 = new LLOAD_2();
		lload_3 = new LLOAD_3();
		fload = new FLOAD();
		fload_0 = new FLOAD_0();
		fload_1 = new FLOAD_1();
		fload_2 = new FLOAD_2();
		fload_3 = new FLOAD_3();
		dload = new DLOAD();
		dload_0 = new DLOAD_0();
		dload_1 = new DLOAD_1();
		dload_2 = new DLOAD_2();
		dload_3 = new DLOAD_3();
		aload = new ALOAD();
		aload_0 = new ALOAD_0();
		aload_1 = new ALOAD_1();
		aload_2 = new ALOAD_2();
		aload_3 = new ALOAD_3();
		
		frame = new Frame(null, MAX_LOCAL_VARS_TABLE_CAPACITY, MAX_OPERAND_STACK_CAPACITY);
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
		iload_0.execute(frame);
		assertEquals(0, frame.getOperandStack().popInt());
		iload_1.execute(frame);
		assertEquals(1, frame.getOperandStack().popInt());
		iload_2.execute(frame);
		assertEquals(2, frame.getOperandStack().popInt());
		iload_3.execute(frame);
		assertEquals(3, frame.getOperandStack().popInt());
		lload.execute(frame);
		assertEquals(0x0000000500000006L, frame.getOperandStack().popLong());
		lload_0.execute(frame);
		assertEquals(0x0000000000000001L, frame.getOperandStack().popLong());
		lload_1.execute(frame);
		assertEquals(0x0000000100000002L, frame.getOperandStack().popLong());
		lload_2.execute(frame);
		assertEquals(0x0000000200000003L, frame.getOperandStack().popLong());
		lload_3.execute(frame);
		assertEquals(0x0000000300000004L, frame.getOperandStack().popLong());
		fload.execute(frame);
		assertEquals(Float.intBitsToFloat(0x00050006), frame.getOperandStack().popFloat(), delta);
		fload_0.execute(frame);
		assertEquals(Float.intBitsToFloat(0x00000001), frame.getOperandStack().popFloat(), delta);
		fload_1.execute(frame);
		assertEquals(Float.intBitsToFloat(0x00010002), frame.getOperandStack().popFloat(), delta);
		fload_2.execute(frame);
		assertEquals(Float.intBitsToFloat(0x00020003), frame.getOperandStack().popFloat(), delta);
		fload_3.execute(frame);
		assertEquals(Float.intBitsToFloat(0x00030004), frame.getOperandStack().popFloat(), delta);
		dload.execute(frame);
		assertEquals(Double.longBitsToDouble(0x0000000500000006L), frame.getOperandStack().popDouble(), delta);
		dload_0.execute(frame);
		assertEquals(Double.longBitsToDouble(0x0000000000000001L), frame.getOperandStack().popDouble(), delta);
		dload_1.execute(frame);
		assertEquals(Double.longBitsToDouble(0x0000000100000002L), frame.getOperandStack().popDouble(), delta);
		dload_2.execute(frame);
		assertEquals(Double.longBitsToDouble(0x0000000200000003L), frame.getOperandStack().popDouble(), delta);
		dload_3.execute(frame);
		assertEquals(Double.longBitsToDouble(0x0000000300000004L), frame.getOperandStack().popDouble(), delta);
		initLocalVarsTableWithRef();
		aload.execute(frame);
		assertSame(this, frame.getOperandStack().popRef());
		aload_0.execute(frame);
		assertEquals(new String("LoadInstruction0"), frame.getOperandStack().popRef());
		aload_1.execute(frame);
		assertEquals(new String("LoadInstruction1"), frame.getOperandStack().popRef());
		aload_2.execute(frame);
		assertEquals(new String("LoadInstruction2"), frame.getOperandStack().popRef());
		aload_3.execute(frame);
		assertEquals(new String("LoadInstruction3"), frame.getOperandStack().popRef());
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
		iload_0.readOperands(reader);
		assertEquals(5, reader.getPc());
		iload_1.readOperands(reader);
		assertEquals(5, reader.getPc());
		iload_2.readOperands(reader);
		assertEquals(5, reader.getPc());
		iload_3.readOperands(reader);
		assertEquals(5, reader.getPc());
		lload_0.readOperands(reader);
		assertEquals(5, reader.getPc());
		lload_1.readOperands(reader);
		assertEquals(5, reader.getPc());
		lload_2.readOperands(reader);
		assertEquals(5, reader.getPc());
		lload_3.readOperands(reader);
		assertEquals(5, reader.getPc());
		fload_0.readOperands(reader);
		assertEquals(5, reader.getPc());
		fload_1.readOperands(reader);
		assertEquals(5, reader.getPc());
		fload_2.readOperands(reader);
		assertEquals(5, reader.getPc());
		fload_3.readOperands(reader);
		assertEquals(5, reader.getPc());
		dload_0.readOperands(reader);
		assertEquals(5, reader.getPc());
		dload_1.readOperands(reader);
		assertEquals(5, reader.getPc());
		dload_2.readOperands(reader);
		assertEquals(5, reader.getPc());
		dload_3.readOperands(reader);
		assertEquals(5, reader.getPc());
		aload_0.readOperands(reader);
		assertEquals(5, reader.getPc());
		aload_1.readOperands(reader);
		assertEquals(5, reader.getPc());
		aload_2.readOperands(reader);
		assertEquals(5, reader.getPc());
		aload_3.readOperands(reader);
		assertEquals(5, reader.getPc());
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
		table.setRef(9, this);
	}
	
	private void initLocalVarsTableWithRef() {
		LocalVarsTable table = frame.getLocalVarsTable();
		table.setRef(0, new String("LoadInstruction0"));
		table.setRef(1, new String("LoadInstruction1"));
		table.setRef(2, new String("LoadInstruction2"));
		table.setRef(3, new String("LoadInstruction3"));
		table.setRef(5, this);
	}
}
