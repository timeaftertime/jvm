package cn.yusei.jvm.instruction.load;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import cn.yusei.jvm.ClassInfoLoader;
import cn.yusei.jvm.ObjectRef;
import cn.yusei.jvm.instruction.base.NoOperandInstruction;
import cn.yusei.jvm.instruction.load.ALoadInstructions.AALOAD;
import cn.yusei.jvm.instruction.load.ALoadInstructions.BALOAD;
import cn.yusei.jvm.instruction.load.ALoadInstructions.CALOAD;
import cn.yusei.jvm.instruction.load.ALoadInstructions.DALOAD;
import cn.yusei.jvm.instruction.load.ALoadInstructions.FALOAD;
import cn.yusei.jvm.instruction.load.ALoadInstructions.IALOAD;
import cn.yusei.jvm.instruction.load.ALoadInstructions.LALOAD;
import cn.yusei.jvm.instruction.load.ALoadInstructions.SALOAD;
import cn.yusei.jvm.runtimespace.ThreadSpace;
import cn.yusei.jvm.runtimespace.stack.Frame;
import cn.yusei.jvm.runtimespace.stack.OperandStack;

public class ALoadInstructionsTest {

	private NoOperandInstruction[] xaload;
	private ThreadSpace threadSpace;
	private ClassInfoLoader loader;

	@Before
	public void setUp() throws ClassNotFoundException, IOException {
		xaload = new NoOperandInstruction[8];
		xaload[0] = new BALOAD();
		xaload[1] = new CALOAD();
		xaload[2] = new SALOAD();
		xaload[3] = new IALOAD();
		xaload[4] = new FALOAD();
		xaload[5] = new LALOAD();
		xaload[6] = new DALOAD();
		xaload[7] = new AALOAD();
		threadSpace = new ThreadSpace();
		loader = new ClassInfoLoader();
		threadSpace.pushFrame(loader.loadClass("cn.yusei.MethodInvoke").getMethods()[1]);
	}

	@Test
	public void readNoOperand() {
		for (int i = 0; i < 8; i++)
			xaload[i].readOperands(null);
	}

	@Test
	public void baload() throws ClassNotFoundException, IOException {
		Frame frame = threadSpace.currentFrame();
		OperandStack stack = frame.getOperandStack();
		ObjectRef array = loader.loadClass("[B").newObjectRefAsArray(5);
		for (int i = 0; i < 5; i++)
			array.bytes()[i] = (byte) i;
		stack.pushRef(array);
		stack.pushInt(2);
		xaload[0].execute(frame);
		assertEquals(2, stack.popInt());
	}

	@Test
	public void caload() throws ClassNotFoundException, IOException {
		Frame frame = threadSpace.currentFrame();
		OperandStack stack = frame.getOperandStack();
		ObjectRef array = loader.loadClass("[C").newObjectRefAsArray(5);
		for (int i = 0; i < 5; i++)
			array.chars()[i] = (char) i;
		stack.pushRef(array);
		stack.pushInt(2);
		xaload[1].execute(frame);
		assertEquals(2, stack.popInt());
	}

	@Test
	public void saload() throws ClassNotFoundException, IOException {
		Frame frame = threadSpace.currentFrame();
		OperandStack stack = frame.getOperandStack();
		ObjectRef array = loader.loadClass("[S").newObjectRefAsArray(5);
		for (int i = 0; i < 5; i++)
			array.shorts()[i] = (short) i;
		stack.pushRef(array);
		stack.pushInt(2);
		xaload[2].execute(frame);
		assertEquals(2, stack.popInt());
	}

	@Test
	public void iaload() throws ClassNotFoundException, IOException {
		Frame frame = threadSpace.currentFrame();
		OperandStack stack = frame.getOperandStack();
		ObjectRef array = loader.loadClass("[I").newObjectRefAsArray(5);
		for (int i = 0; i < 5; i++)
			array.ints()[i] = i;
		stack.pushRef(array);
		stack.pushInt(2);
		xaload[3].execute(frame);
		assertEquals(2, stack.popInt());
	}

	@Test
	public void faload() throws ClassNotFoundException, IOException {
		double delta = 0.01;
		Frame frame = threadSpace.currentFrame();
		OperandStack stack = frame.getOperandStack();
		ObjectRef array = loader.loadClass("[F").newObjectRefAsArray(5);
		for (int i = 0; i < 5; i++)
			array.floats()[i] =  i + 3.14f;
		stack.pushRef(array);
		stack.pushInt(0);
		xaload[4].execute(frame);
		assertEquals(3.14f, stack.popFloat(), delta);
	}

	@Test
	public void laload() throws ClassNotFoundException, IOException {
		Frame frame = threadSpace.currentFrame();
		OperandStack stack = frame.getOperandStack();
		ObjectRef array = loader.loadClass("[J").newObjectRefAsArray(5);
		for (int i = 0; i < 5; i++)
			array.longs()[i] = i + 1234567890L;
		stack.pushRef(array);
		stack.pushInt(3);
		xaload[5].execute(frame);
		assertEquals(1234567893L, stack.popLong());
	}

	@Test
	public void daload() throws ClassNotFoundException, IOException {
		double delta = 0.01;
		Frame frame = threadSpace.currentFrame();
		OperandStack stack = frame.getOperandStack();
		ObjectRef array = loader.loadClass("[D").newObjectRefAsArray(5);
		for (int i = 0; i < 5; i++)
			array.doubles()[i] =  i + 0.999;
		stack.pushRef(array);
		stack.pushInt(3);
		xaload[6].execute(frame);
		assertEquals(3.999, stack.popDouble(), delta);
	}

	@Test
	public void aaload() throws ClassNotFoundException, IOException {
		Frame frame = threadSpace.currentFrame();
		OperandStack stack = frame.getOperandStack();
		ObjectRef array = loader.loadClass("[Lcn/yusei/MethodInvoke;").newObjectRefAsArray(5);
		stack.pushRef(array);
		stack.pushInt(3);
		for (int i = 0; i < 5; i++)
			array.refs()[i] = ObjectRef.newObject(null, 0);
		xaload[7].execute(frame);
		assertSame(array.refs()[3], stack.popRef());
	}

}
