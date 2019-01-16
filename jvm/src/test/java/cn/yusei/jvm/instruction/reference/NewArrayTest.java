package cn.yusei.jvm.instruction.reference;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import cn.yusei.jvm.ClassInfoLoader;
import cn.yusei.jvm.ObjectRef;
import cn.yusei.jvm.instruction.BytecodeReader;
import cn.yusei.jvm.instruction.base.UInt8Instruction;
import cn.yusei.jvm.runtimespace.ThreadSpace;
import cn.yusei.jvm.runtimespace.stack.Frame;
import cn.yusei.jvm.runtimespace.stack.OperandStack;

public class NewArrayTest {

	private UInt8Instruction new_array;
	private BytecodeReader reader;
	private byte[] codes = new byte[] { 10 };
	private ThreadSpace space;
	private ClassInfoLoader loader;

	@Before
	public void setUp() throws ClassNotFoundException, IOException {
		new_array = new NEW_ARRAY();
		reader = new BytecodeReader(codes);
		space = new ThreadSpace();
		loader = new ClassInfoLoader();
		space.pushFrame(loader.loadClass("cn.yusei.MethodInvoke").getMethods()[0]);
	}

	@Test
	public void readOperandsAndExecute() throws IOException {
		Frame frame = space.currentFrame();
		OperandStack stack = frame.getOperandStack();
		new_array.readOperands(reader);
		stack.pushInt(9);
		new_array.execute(frame);
		ObjectRef popRef = stack.popRef();
		assertEquals("[I", popRef.getClassInfo().getName());
		assertEquals(9, popRef.length());
		stack.pushInt(-1);
		try {
			new_array.execute(frame);
		} catch (NegativeArraySizeException e) {
			assertEquals(-1 + "", e.getMessage());
			return;
		}
		fail();
	}

}
