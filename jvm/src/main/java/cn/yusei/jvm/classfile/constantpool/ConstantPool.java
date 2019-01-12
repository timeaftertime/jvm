package cn.yusei.jvm.classfile.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

public class ConstantPool {

	private ConstantInfo[] infos;
	
	public ConstantPool(DataInputStream data) throws IOException {
		// 2 个字节无符号的常量池大小 size
		int size = data.readUnsignedShort();
		infos = new ConstantInfo[size];
		// size - 1 个常量，且下标从 1 开始
		for(int i=1; i<size; i++) {
			// 1 个字节的常量类型
			int tag = data.readUnsignedByte();
			infos[i] = ConstantInfoFactory.createConstantInfo(tag);
			infos[i].readInfo(data, this);
			switch (infos[i].getTag()) {
				case ConstantTag.LONG:
				case ConstantTag.DOUBLE:
					i++;
			}
		}
	}
	
	public ConstantInfo get(int index) {
		if(index <= 0 || index >= infos.length)
			throw new ArrayIndexOutOfBoundsException(index);
		ConstantInfo info = infos[index];
		if(info == null)
			throw new IllegalArgumentException("序号为 " + index + " 的常量不存在");
		return info;
	}

	public int getSize() {
		return infos.length;
	}

	public MethodrefConstantInfo getMethodrefInfo(int index) {
		return (MethodrefConstantInfo) get(index);
	}

	public FieldrefConstantInfo getFieldrefInfo(int index) {
		return (FieldrefConstantInfo) get(index);
	}

	public StringConstantInfo getStringInfo(int index) {
		return (StringConstantInfo) get(index);
	}

	public ClassConstantInfo getClassInfo(int index) {
		return (ClassConstantInfo) get(index);
	}

	public FloatConstantInfo getFloatInfo(int index) {
		return (FloatConstantInfo) get(index);
	}

	public IntegerConstantInfo getIntegerInfo(int index) {
		return (IntegerConstantInfo) get(index);
	}

	public LongConstantInfo getLongInfo(int index) {
		return (LongConstantInfo) get(index);
	}

	public DoubleConstantInfo getDoubleInfo(int index) {
		return (DoubleConstantInfo) get(index);
	}

	public NameAndTypeConstantInfo getNameAndType(int index) {
		return (NameAndTypeConstantInfo) get(index);
	}

	public UTF8ConstantInfo getUTF8(int index) {
		return (UTF8ConstantInfo) get(index);
	}
	
}
