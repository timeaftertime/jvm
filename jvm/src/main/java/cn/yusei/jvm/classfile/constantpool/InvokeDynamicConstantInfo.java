package cn.yusei.jvm.classfile.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

public class InvokeDynamicConstantInfo implements ConstantInfo {

	private int bootstrapMethodAttrIndex;
	private int nameAndTypeIndex;

	InvokeDynamicConstantInfo() {
	}

	@Override
	public int getTag() {
		return ConstantTag.INVOKE_DYNAMIC;
	}

	@Override
	public void readInfo(DataInputStream data, ConstantPool pool) throws IOException {
		bootstrapMethodAttrIndex = data.readUnsignedShort();
		nameAndTypeIndex = data.readUnsignedShort();
	}

	public int getBootstrapMethodAttrIndex() {
		return bootstrapMethodAttrIndex;
	}

	public int getNameAndTypeIndex() {
		return nameAndTypeIndex;
	}

}
