package cn.yusei.jvm.instruction.reference;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import cn.yusei.jvm.ClassInfoLoader;
import cn.yusei.jvm.ObjectRef;
import cn.yusei.jvm.instruction.base.NoOperandInstruction;
import cn.yusei.jvm.runtimespace.ThreadSpace;
import cn.yusei.jvm.runtimespace.stack.Frame;
import cn.yusei.jvm.runtimespace.stack.OperandStack;

public class AthrowTest {

	@Test
	public void athow() throws ClassNotFoundException, IOException {
		NoOperandInstruction athow = new ATHROW();
		athow.readOperands(null);
		ThreadSpace space = new ThreadSpace();
		ClassInfoLoader loader = new ClassInfoLoader();
		space.pushFrame(loader.loadClass("cn.yusei.ExceptionHandle").getMethod("main", "([Ljava/lang/String;)V"));
		Frame frame = space.currentFrame();
		OperandStack stack = frame.getOperandStack();
		frame.setNextPc(3);
		ObjectRef reEx = loader.loadClass("java.lang.RuntimeException").newObjectRef();
		stack.pushRef(reEx);
		athow.execute(frame);
		assertFalse(space.finished());
		assertEquals(10, frame.getNextPc());
		assertSame(reEx, stack.popRef());
		stack.pushRef(reEx);
		athow.execute(frame);
		assertTrue(space.finished());
	}

}
