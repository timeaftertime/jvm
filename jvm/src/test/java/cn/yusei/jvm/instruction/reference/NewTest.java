package cn.yusei.jvm.instruction.reference;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import cn.yusei.jvm.ClassInfo;
import cn.yusei.jvm.ClassInfoLoader;
import cn.yusei.jvm.ObjectRef;
import cn.yusei.jvm.instruction.BytecodeReader;
import cn.yusei.jvm.instruction.base.UInt16Instruction;
import cn.yusei.jvm.runtimespace.ThreadSpace;
import cn.yusei.jvm.runtimespace.stack.Frame;

public class NewTest {

	private UInt16Instruction _new;
	private BytecodeReader reader;
	private byte[] codes = { 0, 2 };
	private Frame frame;

	@Before
	public void setUp() throws ClassNotFoundException, IOException {
		_new = new NEW();
		reader = new BytecodeReader(codes);
		ClassInfo info = new ClassInfoLoader().loadClass("cn.yusei.Add");
		info.startInit();
		frame = new Frame(new ThreadSpace(), info.getMethods()[0]);
	}

	@Test
	public void readOperandsAndExecute() throws IOException, ClassNotFoundException {
		_new.readOperands(reader);
		_new.execute(frame);
		ObjectRef ref = frame.getOperandStack().popRef();
		assertEquals("cn.yusei.Add", ref.getClassInfo().getName());
		assertEquals(0, ref.getMembers().getCapacity());
	}
}
