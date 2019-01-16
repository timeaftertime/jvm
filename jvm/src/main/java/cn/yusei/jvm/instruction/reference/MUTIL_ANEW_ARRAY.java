package cn.yusei.jvm.instruction.reference;

import java.io.IOException;

import cn.yusei.jvm.ClassInfo;
import cn.yusei.jvm.ObjectRef;
import cn.yusei.jvm.instruction.BytecodeReader;
import cn.yusei.jvm.instruction.ExecuteBytecodeError;
import cn.yusei.jvm.instruction.Instruction;
import cn.yusei.jvm.runtimespace.method.RTConstantPool;
import cn.yusei.jvm.runtimespace.stack.Frame;
import cn.yusei.jvm.runtimespace.stack.OperandStack;

public class MUTIL_ANEW_ARRAY implements Instruction {

	private int index;
	private int dimension;
	
	@Override
	public void readOperands(BytecodeReader reader) throws IOException {
		index = reader.readUint16();
		dimension = reader.readUint8();
	}

	@Override
	public void execute(Frame frame) {
		RTConstantPool pool = frame.getMethod().getClassInfo().getConstantPool();
		OperandStack stack = frame.getOperandStack();
		ClassInfo elementType;
		ObjectRef array;
		try {
			elementType = pool.getClassRef(index).resolvedClass();
			int[] dimensions = popAndCheckDimension(stack);
			array = createMutilArray(dimensions, elementType);
		} catch (ClassNotFoundException | IOException e) {
			throw new ExecuteBytecodeError(e);
		}
		stack.pushRef(array);
	}

	private ObjectRef createMutilArray(int[] dimensions, ClassInfo elementType) throws ClassNotFoundException, IOException {
		ObjectRef array = elementType.newObjectRefAsArray(dimensions[0]);
		if(dimensions.length > 1) {
			int[] nextDimensions = new int[dimensions.length - 1];
			System.arraycopy(dimensions, 1, nextDimensions, 0, nextDimensions.length);
			for(int i=0; i<dimensions[0]; i++) {
				array.getMembers().setRef(i, createMutilArray(nextDimensions, elementType.getElementClass()));
			}
		}
		return array;
	}

	private int[] popAndCheckDimension(OperandStack stack) {
		int[] dimensions = new int[dimension];
		for(int i=dimension-1; i>=0; i--) {
			dimensions[i] = stack.popInt();
			if(dimensions[i] < 0)
				throw new NegativeArraySizeException();
		}
		return dimensions;
	}

}
