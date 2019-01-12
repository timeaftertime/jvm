package cn.yusei.jvm.instruction.reference;

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
import cn.yusei.jvm.runtimespace.stack.Frame;

public class CheckCastTest {

	private UInt16Instruction checkCast;
	private BytecodeReader reader;
	private byte[] codes = { 0, 4 };
	private Frame frame;

	@Before
	public void setUp() throws ClassNotFoundException, IOException {
		checkCast = new CHECK_CAST();
		reader = new BytecodeReader(codes);
		ClassInfo info = new ClassInfoLoader().loadClass("cn.yusei.PutAndGetField");
		frame = new Frame(null, info.getMethods()[1]);
	}

	@Test
	public void readOperandsAndExecute() throws IOException, ClassNotFoundException {
		ClassInfo classInfo = frame.getMethod().getClassInfo();
		checkCast.readOperands(reader);
		ObjectRef ref = classInfo.newObjectRef();
		frame.getOperandStack().pushRef(ref);
		checkCast.execute(frame);
		assertSame(ref, frame.getOperandStack().popRef());
		frame.getOperandStack().pushRef(classInfo.getLoader().loadClass("cn.yusei.Hello").newObjectRef());
		try {
			checkCast.execute(frame);
		}
		catch (ClassCastException e) {
			return;
		}
		fail();
	}

}
