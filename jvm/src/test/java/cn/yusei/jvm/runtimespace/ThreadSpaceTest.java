package cn.yusei.jvm.runtimespace;

import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;

public class ThreadSpaceTest {

	private ThreadSpace space;
	
	@Before
	public void setUp() {
		space = new ThreadSpace();
	}
	
	@Test
	public void pushAndPopFrame() {
		int len = 10;
		for(int i=0; i<len; i++)
			space.pushFrame(i, i);
		for(int i=len-1; i>=0; i--) {
			Frame frame = space.popFrame();
			assertSame(i, frame.getLocalVarsTable().getCapacity());
			assertSame(i, frame.getOperandStack().getCapacity());
		}
	}
	
}
