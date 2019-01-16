package cn.yusei.jvm.instruction.reference;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import cn.yusei.jvm.ClassInfo;
import cn.yusei.jvm.ClassInfoLoader;
import cn.yusei.jvm.ObjectRef;
import cn.yusei.jvm.instruction.BytecodeReader;
import cn.yusei.jvm.instruction.base.UInt16Instruction;
import cn.yusei.jvm.instruction.reference.InvokeInstructions.INVOKE_SPECIAL;
import cn.yusei.jvm.instruction.reference.InvokeInstructions.INVOKE_STATIC;
import cn.yusei.jvm.instruction.reference.InvokeInstructions.INVOKE_VIRTUAL;
import cn.yusei.jvm.runtimespace.ThreadSpace;
import cn.yusei.jvm.runtimespace.stack.Frame;
import cn.yusei.jvm.runtimespace.stack.OperandStack;

public class InvokeInstructionsTest {

	private UInt16Instruction invoke_static;
	private UInt16Instruction invoke_special;
	private UInt16Instruction invoke_virtual;
	// 找不出 InterfaceMethodRef ，暂时无法测试
//	private UInt16Instruction invoke_interface;
	private ThreadSpace threadSpace;
	private Frame frame;
	private BytecodeReader reader;
	private byte[] codes = {0, 5, 0, 4, 0, 7, 0, 0};
	private ClassInfoLoader loader;

	@Before
	public void setUp() throws ClassNotFoundException, IOException {
		invoke_static = new INVOKE_STATIC();
		invoke_special = new INVOKE_SPECIAL();
		invoke_virtual = new INVOKE_VIRTUAL();
//		invoke_interface = new INVOKE_INTERFACE();
		threadSpace = new ThreadSpace();
		loader = new ClassInfoLoader();
		ClassInfo loadClass = loader.loadClass("cn.yusei.MethodInvoke");
		loadClass.startInit();
		threadSpace.pushFrame(loadClass.getMethods()[1]);
		frame = threadSpace.currentFrame();
		reader = new BytecodeReader(codes);
	}

	@Test
	public void invokeStatic() throws IOException {
		invoke_static.readOperands(reader);
		OperandStack stack = frame.getOperandStack();
		ObjectRef ref = ObjectRef.newObject(null, 0);
		stack.pushRef(ref);
		stack.pushInt(1);
		stack.pushInt(2);
		invoke_static.execute(frame);
		assertEquals("max", threadSpace.currentFrame().getMethod().getName());
		assertEquals("(II)I", threadSpace.currentFrame().getMethod().getDescriptor());
		assertEquals(1, threadSpace.currentFrame().getLocalVarsTable().getInt(0));
		assertEquals(2, threadSpace.currentFrame().getLocalVarsTable().getInt(1));
		invoke_static.readOperands(reader);
		try {
			invoke_static.execute(frame);
		}
		catch (IncompatibleClassChangeError e) {
			return;
		}
		fail();
	}
	
	@Test
	public void invokeSpecail() throws IOException, ClassNotFoundException {
		invoke_special.readOperands(reader);
		try {
			invoke_special.execute(frame);
		}
		catch (IncompatibleClassChangeError e) {
			invoke_special.readOperands(reader);
			OperandStack stack = frame.getOperandStack();
			ObjectRef ref = loader.loadClass("cn.yusei.MethodInvoke").newObjectRef();
			stack.pushRef(ref);
			stack.pushInt(1);
			stack.pushInt(2);
			invoke_special.execute(frame);
			assertSame(ref, threadSpace.currentFrame().getLocalVarsTable().getRef(0));
			assertEquals(1, threadSpace.currentFrame().getLocalVarsTable().getInt(1));
			assertEquals(2, threadSpace.currentFrame().getLocalVarsTable().getInt(2));
			return;
		}
		fail();
	}
	
	@Test
	public void invokeVirtual() throws IOException, ClassNotFoundException {
		invoke_virtual.readOperands(reader);
		try {
			invoke_virtual.execute(frame);
		}
		catch (IncompatibleClassChangeError e) {
			invoke_virtual.readOperands(reader);
			OperandStack stack = frame.getOperandStack();
			ObjectRef ref = loader.loadClass("cn.yusei.MethodInvoke").newObjectRef();
			stack.pushRef(ref);
			stack.pushInt(1);
			stack.pushInt(2);
			invoke_virtual.execute(frame);
			assertSame(ref, threadSpace.currentFrame().getLocalVarsTable().getRef(0));
			assertEquals(1, threadSpace.currentFrame().getLocalVarsTable().getInt(1));
			assertEquals(2, threadSpace.currentFrame().getLocalVarsTable().getInt(2));
			return;
		}
		fail();
	}
	
}
