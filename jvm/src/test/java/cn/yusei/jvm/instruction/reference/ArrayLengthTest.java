package cn.yusei.jvm.instruction.reference;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import cn.yusei.jvm.ClassInfo;
import cn.yusei.jvm.ClassInfoLoader;
import cn.yusei.jvm.instruction.BytecodeReader;
import cn.yusei.jvm.instruction.base.NoOperandInstruction;
import cn.yusei.jvm.runtimespace.ThreadSpace;
import cn.yusei.jvm.runtimespace.stack.Frame;
import cn.yusei.jvm.runtimespace.stack.OperandStack;

public class ArrayLengthTest {

	private NoOperandInstruction array_length;
	private BytecodeReader reader;
	private byte[] codes = new byte[] { 10 };
	private ThreadSpace space;
	private ClassInfoLoader loader;

	@Before
	public void setUp() throws ClassNotFoundException, IOException {
		array_length = new ARRAY_LENGTH();
		reader = new BytecodeReader(codes);
		space = new ThreadSpace();
		loader = new ClassInfoLoader();
		space.pushFrame(loader.loadClass("cn.yusei.MethodInvoke").getMethods()[0]);
	}

	@Test
	public void readOperandsAndExecute() throws IOException, ClassNotFoundException {
		Frame frame = space.currentFrame();
		OperandStack stack = frame.getOperandStack();
		array_length.readOperands(reader);
		stack.pushRef(ClassInfo.newArrayClassInfo(loader, "[Lcn.yusei.ClassTest;").newObjectRefAsArray(6));
		array_length.execute(frame);
		assertEquals(6, stack.popInt());
		stack.pushRef(ClassInfo.newArrayClassInfo(loader, "[D").newObjectRefAsArray(6));
		array_length.execute(frame);
		assertEquals(6, stack.popInt());
	}

}
