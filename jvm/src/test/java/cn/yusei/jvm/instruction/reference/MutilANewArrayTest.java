package cn.yusei.jvm.instruction.reference;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import cn.yusei.jvm.ClassInfoLoader;
import cn.yusei.jvm.ObjectRef;
import cn.yusei.jvm.instruction.BytecodeReader;
import cn.yusei.jvm.instruction.Instruction;
import cn.yusei.jvm.runtimespace.ThreadSpace;
import cn.yusei.jvm.runtimespace.stack.Frame;
import cn.yusei.jvm.runtimespace.stack.OperandStack;

public class MutilANewArrayTest {

	private Instruction mutil_anew_array;
	private BytecodeReader reader;
	private byte[] codes = {0, 2, 3};
	private ThreadSpace threadSpace;

	@Before
	public void setUp() throws ClassNotFoundException, IOException {
		mutil_anew_array = new MUTIL_ANEW_ARRAY();
		reader = new BytecodeReader(codes);
		threadSpace = new ThreadSpace();
		threadSpace.pushFrame(new ClassInfoLoader().loadClass("cn.yusei.ArrayTest").getMethods()[0]);
	}

	@Test
	public void createMultiDemensionArray() throws IOException {
		mutil_anew_array.readOperands(reader);
		assertEquals(3, reader.getPc());
		Frame frame = threadSpace.currentFrame();
		OperandStack stack = frame.getOperandStack();
		stack.pushInt(5);
		stack.pushInt(3);
		stack.pushInt(4);
		mutil_anew_array.execute(frame);
		ObjectRef array = stack.popRef();
		assertEquals(5, array.length());
		assertEquals("[[[Ljava/lang/Object;", array.getClassInfo().getName());
		assertEquals(3, array.getMembers().getRef(0).length());
		assertEquals("[[Ljava/lang/Object;", array.getMembers().getRef(0).getClassInfo().getName());
		assertEquals(4, array.getMembers().getRef(0).getMembers().getRef(0).length());
		assertEquals("[Ljava/lang/Object;", array.getMembers().getRef(0).getMembers().getRef(0).getClassInfo().getName());
		assertEquals("Ljava/lang/Object;", array.getMembers().getRef(0).getMembers().getRef(0).getMembers().getRef(0).getClassInfo().getName());
	}
}
