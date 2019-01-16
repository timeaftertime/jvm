package cn.yusei.jvm.instruction.reserved;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import cn.yusei.jvm.ClassInfo;
import cn.yusei.jvm.ClassInfoLoader;
import cn.yusei.jvm.ObjectRef;
import cn.yusei.jvm.instruction.base.NoOperandInstruction;
import cn.yusei.jvm.runtimespace.ThreadSpace;
import cn.yusei.jvm.runtimespace.stack.Frame;

public class InvokeNativeTest {

	@Test
	public void invokeNativeMethod() throws ClassNotFoundException, IOException {
		NoOperandInstruction invoke_native = new INVOKE_NATIVE();
		ThreadSpace space = new ThreadSpace();
		ClassInfoLoader loader = new ClassInfoLoader();
		ClassInfo classInfo = loader.loadClass("java.lang.Object");
		space.pushFrame(classInfo.getMethod("hashCode", "()I"));
		Frame frame = space.currentFrame();
		ObjectRef ref = classInfo.newObjectRef();
		frame.getLocalVarsTable().setRef(0, ref);
		invoke_native.execute(frame);
		assertEquals(ref.hashCode(), frame.getOperandStack().popInt());
	}

}
