package cn.yusei.jvm.classfile.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

public class UTF8ConstantInfo implements ConstantInfo {

	private String info;
	
	UTF8ConstantInfo() {
	}

	@Override
	public int getTag() {
		return ConstantTag.UTF8;
	}

	@Override
	public void readInfo(DataInputStream data) throws IOException {
		// 2 个字节无符号数
		// length 个字节的 MUTF8 编码的字符串
		info = data.readUTF();
	}

	public String getInfo() {
		return info;
	}

}
