package cn.yusei.jvm.runtimespace.stack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import cn.yusei.jvm.ObjectRef;
import cn.yusei.jvm.slot.Slot;

public class OperandStackTest {

	private final int STACK_CAPACITY = 10;
	private OperandStack operandStack;

	@Before
	public void setUp() {
		operandStack = new OperandStack(STACK_CAPACITY);
	}

	@Test
	public void pushAndPop() {
		int var1 = 1;
		long var2 = 12345678901L;
		float var3 = 3.14f;
		double var4 = 3.1415926;
		ObjectRef var5 = ObjectRef.newObject(null, 0);
		double delta = 0.01;
		operandStack.pushInt(var1);
		operandStack.pushLong(var2);
		operandStack.pushFloat(var3);
		operandStack.pushDouble(var4);
		operandStack.pushRef(var5);
		assertEquals(var5, operandStack.popRef());
		assertEquals(var4, operandStack.popDouble(), delta);
		assertEquals(var3, operandStack.popFloat(), delta);
		assertEquals(var2, operandStack.popLong());
		assertEquals(var1, operandStack.popInt());
	}

	@Test
	public void emptyExceptionAndFullError() {
		try {
			operandStack.popInt();
		} catch (OperandStackEmptyError e1) {
			try {
				for (int i = 0; i <= STACK_CAPACITY; i++)
					operandStack.pushInt(i);
			} catch (OperandStackOverflowError e2) {
				return;
			}
		}
		fail();
	}

	@Test
	public void pushAndPopSlot() {
		operandStack.pushSlot(new Slot(1, null));
		Slot slot = operandStack.popSlot();
		assertEquals(1, slot.getValue());
		assertSame(null, slot.getRef());
	}

}
