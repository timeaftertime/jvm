package cn.yusei.jvm.instruction.store;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import cn.yusei.jvm.ClassInfoLoader;
import cn.yusei.jvm.ObjectRef;
import cn.yusei.jvm.instruction.base.NoOperandInstruction;
import cn.yusei.jvm.instruction.store.AStoreInstructions.AASTORE;
import cn.yusei.jvm.instruction.store.AStoreInstructions.BASTORE;
import cn.yusei.jvm.instruction.store.AStoreInstructions.CASTORE;
import cn.yusei.jvm.instruction.store.AStoreInstructions.DASTORE;
import cn.yusei.jvm.instruction.store.AStoreInstructions.FASTORE;
import cn.yusei.jvm.instruction.store.AStoreInstructions.IASTORE;
import cn.yusei.jvm.instruction.store.AStoreInstructions.LASTORE;
import cn.yusei.jvm.instruction.store.AStoreInstructions.SASTORE;
import cn.yusei.jvm.runtimespace.ThreadSpace;
import cn.yusei.jvm.runtimespace.stack.Frame;
import cn.yusei.jvm.runtimespace.stack.OperandStack;
import cn.yusei.jvm.testutil.MockFactory;

public class AStoreInstructionsTest {

	private NoOperandInstruction[] xastore;
	private ThreadSpace threadSpace;
	private ClassInfoLoader loader;

	@Before
	public void setUp() throws ClassNotFoundException, IOException {
		xastore = new NoOperandInstruction[8];
		xastore[0] = new BASTORE();
		xastore[1] = new CASTORE();
		xastore[2] = new SASTORE();
		xastore[3] = new IASTORE();
		xastore[4] = new FASTORE();
		xastore[5] = new LASTORE();
		xastore[6] = new DASTORE();
		xastore[7] = new AASTORE();
		threadSpace = new ThreadSpace();
		loader = new ClassInfoLoader();
		threadSpace.pushFrame(MockFactory.newMethod(0, 4));
	}

	@Test
	public void readNoOperand() {
		for (int i = 0; i < 8; i++)
			xastore[i].readOperands(null);
	}

	@Test
	public void baload() throws ClassNotFoundException, IOException {
		Frame frame = threadSpace.currentFrame();
		OperandStack stack = frame.getOperandStack();
		ObjectRef array = loader.loadClass("[B").newObjectRefAsArray(5);
		for (int i = 0; i < 5; i++)
			array.bytes()[i] = (byte) i;
		stack.pushRef(array);
		stack.pushInt(3);
		stack.pushInt(5);
		xastore[0].execute(frame);
		assertEquals(5, array.bytes()[3]);
	}

	@Test
	public void caload() throws ClassNotFoundException, IOException {
		Frame frame = threadSpace.currentFrame();
		OperandStack stack = frame.getOperandStack();
		ObjectRef array = loader.loadClass("[C").newObjectRefAsArray(5);
		for (int i = 0; i < 5; i++)
			array.chars()[i] = (char) i;
		stack.pushRef(array);
		stack.pushInt(3);
		stack.pushInt(5);
		xastore[1].execute(frame);
		assertEquals(5, array.chars()[3]);
	}

	@Test
	public void saload() throws ClassNotFoundException, IOException {
		Frame frame = threadSpace.currentFrame();
		OperandStack stack = frame.getOperandStack();
		ObjectRef array = loader.loadClass("[S").newObjectRefAsArray(5);
		for (int i = 0; i < 5; i++)
			array.shorts()[i] = (short) i;
		stack.pushRef(array);
		stack.pushInt(3);
		stack.pushInt(5);
		xastore[2].execute(frame);
		assertEquals(5, array.shorts()[3]);
	}

	@Test
	public void iaload() throws ClassNotFoundException, IOException {
		Frame frame = threadSpace.currentFrame();
		OperandStack stack = frame.getOperandStack();
		ObjectRef array = loader.loadClass("[I").newObjectRefAsArray(5);
		for (int i = 0; i < 5; i++)
			array.ints()[i] = i;
		stack.pushRef(array);
		stack.pushInt(3);
		stack.pushInt(5);
		xastore[3].execute(frame);
		assertEquals(5, array.ints()[3]);
	}

	@Test
	public void faload() throws ClassNotFoundException, IOException {
		double delta = 0.01;
		Frame frame = threadSpace.currentFrame();
		OperandStack stack = frame.getOperandStack();
		ObjectRef array = loader.loadClass("[F").newObjectRefAsArray(5);
		for (int i = 0; i < 5; i++)
			array.floats()[i] = i + 3.14f;
		stack.pushRef(array);
		stack.pushInt(0);
		stack.pushFloat(3.12222f);
		xastore[4].execute(frame);
		assertEquals(3.12222f, array.floats()[0], delta);
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
		stack.pushLong(12345654321L);
		xastore[5].execute(frame);
		assertEquals(12345654321L, array.longs()[3]);
	}

	@Test
	public void daload() throws ClassNotFoundException, IOException {
		double delta = 0.01;
		Frame frame = threadSpace.currentFrame();
		OperandStack stack = frame.getOperandStack();
		ObjectRef array = loader.loadClass("[D").newObjectRefAsArray(5);
		for (int i = 0; i < 5; i++)
			array.doubles()[i] = i + 0.999;
		stack.pushRef(array);
		stack.pushInt(3);
		stack.pushDouble(666.999);
		xastore[6].execute(frame);
		assertEquals(666.999, array.doubles()[3], delta);
	}

	@Test
	public void aaload() throws ClassNotFoundException, IOException {
		Frame frame = threadSpace.currentFrame();
		OperandStack stack = frame.getOperandStack();
		ObjectRef array = loader.loadClass("[Lcn/yusei/MethodInvoke;").newObjectRefAsArray(5);
		stack.pushRef(array);
		stack.pushInt(3);
		ObjectRef ref = ObjectRef.newObject(null, 0);
		stack.pushRef(ref);
		xastore[7].execute(frame);
		assertSame(ref, array.refs()[3]);
	}

}
