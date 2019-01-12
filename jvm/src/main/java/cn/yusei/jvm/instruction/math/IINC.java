package cn.yusei.jvm.instruction.math;

import java.io.IOException;

import cn.yusei.jvm.instruction.BytecodeReader;
import cn.yusei.jvm.instruction.Instruction;
import cn.yusei.jvm.runtimespace.stack.Frame;
import cn.yusei.jvm.runtimespace.stack.LocalVarsTable;

public class IINC implements Instruction {

	private int localVarsTableIndex;
	private int constant;
	
	@Override
	public void readOperands(BytecodeReader reader) throws IOException {
		localVarsTableIndex = reader.readUint8();
		constant = reader.readInt8();
	}

	@Override
	public void execute(Frame frame) {
		LocalVarsTable table = frame.getLocalVarsTable();
		table.setInt(localVarsTableIndex, table.getInt(localVarsTableIndex) + constant);
	}
	
	public void setIndex(int index) {
		localVarsTableIndex = index;
	}
	
	public void setConstant(int value) {
		constant = value;
	}

}
