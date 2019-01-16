package cn.yusei.jvm.instruction.control;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import java.util.EmptyStackException;

import org.junit.Before;
import org.junit.Test;

import cn.yusei.jvm.ObjectRef;
import cn.yusei.jvm.instruction.base.NoOperandInstruction;
import cn.yusei.jvm.instruction.control.ReturnInstructions.ARETURN;
import cn.yusei.jvm.instruction.control.ReturnInstructions.DRETURN;
import cn.yusei.jvm.instruction.control.ReturnInstructions.FRETURN;
import cn.yusei.jvm.instruction.control.ReturnInstructions.IRETURN;
import cn.yusei.jvm.instruction.control.ReturnInstructions.LRETURN;
import cn.yusei.jvm.instruction.control.ReturnInstructions.RETURN;
import cn.yusei.jvm.runtimespace.ThreadSpace;
import cn.yusei.jvm.testutil.MockFactory;

public class ReturnInstructionsTest {

	private NoOperandInstruction[] x_returns;
	
	@Before
	public void setUp() {
		x_returns = new NoOperandInstruction[6];
		x_returns[0] = new RETURN();
		x_returns[1] = new IRETURN();
		x_returns[2] = new FRETURN();
		x_returns[3] = new LRETURN();
		x_returns[4] = new DRETURN();
		x_returns[5] = new ARETURN();
	}
	
	@Test
	public void readOperands() {
		for(int i=0; i<6; i++)
			x_returns[i].readOperands(null);
	}
	
	@Test
	public void execute() {
		double delta = 0.01;
		ThreadSpace space = new ThreadSpace();
		for(int i=0; i<6; i++)
			space.pushFrame(MockFactory.newMethod(0, 2));
		space.currentFrame().getOperandStack().pushInt(10);
		x_returns[1].execute(space.currentFrame());
		assertEquals(10, space.currentFrame().getOperandStack().popInt());
		space.currentFrame().getOperandStack().pushFloat(3.14f);
		x_returns[2].execute(space.currentFrame());
		assertEquals(3.14f, space.currentFrame().getOperandStack().popFloat(), delta);
		space.currentFrame().getOperandStack().pushLong(9876543210L);
		x_returns[3].execute(space.currentFrame());
		assertEquals(9876543210L, space.currentFrame().getOperandStack().popLong());
		space.currentFrame().getOperandStack().pushDouble(999.666);
		x_returns[4].execute(space.currentFrame());
		assertEquals(999.666, space.currentFrame().getOperandStack().popDouble(), delta);
		ObjectRef ref = ObjectRef.newObject(null, 0);
		space.currentFrame().getOperandStack().pushRef(ref);
		x_returns[5].execute(space.currentFrame());
		assertSame(ref, space.currentFrame().getOperandStack().popRef());
		x_returns[0].execute(space.currentFrame());
		try {
			space.currentFrame();
		}
		catch (EmptyStackException e) {
			return;
		}
		fail();
	}
	
}
