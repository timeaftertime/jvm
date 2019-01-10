package cn.yusei.jvm.instruction.extend;

import java.io.IOException;

import cn.yusei.jvm.instruction.BytecodeReader;
import cn.yusei.jvm.instruction.Instruction;
import cn.yusei.jvm.instruction.base.OneOperandInstruction;
import cn.yusei.jvm.instruction.load.LoadInstructions.ALOAD;
import cn.yusei.jvm.instruction.load.LoadInstructions.DLOAD;
import cn.yusei.jvm.instruction.load.LoadInstructions.FLOAD;
import cn.yusei.jvm.instruction.load.LoadInstructions.ILOAD;
import cn.yusei.jvm.instruction.load.LoadInstructions.LLOAD;
import cn.yusei.jvm.instruction.math.IINC;
import cn.yusei.jvm.instruction.store.StoreInstructions.ASTORE;
import cn.yusei.jvm.instruction.store.StoreInstructions.DSTORE;
import cn.yusei.jvm.instruction.store.StoreInstructions.FSTORE;
import cn.yusei.jvm.instruction.store.StoreInstructions.ISTORE;
import cn.yusei.jvm.instruction.store.StoreInstructions.LSTORE;
import cn.yusei.jvm.runtimespace.Frame;

public class WIDE implements Instruction {

	private Instruction changedInstruction;
	
	@Override
	public void readOperands(BytecodeReader reader) throws IOException {
		int opCode = reader.readUint8();
		OneOperandInstruction OneOperandInstruction = getOneOperandInstruction(opCode);
		if(OneOperandInstruction != null) {
			OneOperandInstruction.setOperand(reader.readUint16());
			changedInstruction = OneOperandInstruction;
			return; 
		}
		switch(opCode) {
		case 0x84:
			IINC iinc = new IINC();
			iinc.setIndex(reader.readUint16());
			iinc.setConstant(reader.readInt16());
			changedInstruction = iinc;
			break;
		default:
			throw new UnsupportedOpCodeError(opCode + "");
		}
	}

	private OneOperandInstruction getOneOperandInstruction(int opCode) {
		switch (opCode) {
		case 0x15:
			 return new ILOAD();
		case 0x16:
			return new LLOAD();
		case 0x17:
			return new FLOAD();
		case 0x18:
			return new DLOAD();
		case 0x19:
			return new ALOAD();
		case 0x36:
			return new ISTORE();
		case 0x37:
			return new LSTORE();
		case 0x38:
			return new FSTORE();
		case 0x39:
			return new DSTORE();
		case 0x3a:
			return new ASTORE();
		}
		return null;
	}
	
	@Override
	public void execute(Frame frame) {
		changedInstruction.execute(frame);
	}

}
