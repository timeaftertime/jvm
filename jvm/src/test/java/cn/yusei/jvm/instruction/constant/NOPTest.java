package cn.yusei.jvm.instruction.constant;

import java.io.IOException;

import org.junit.Test;

import cn.yusei.jvm.instruction.Instruction;

public class NOPTest {

	private Instruction nop = new NOP();
	
	@Test
	public void readAndExecute() throws IOException {
		nop.readOperands(null);
		nop.execute(null);
	}
	
}
