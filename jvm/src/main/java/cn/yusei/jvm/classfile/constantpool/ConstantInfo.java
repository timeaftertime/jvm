package cn.yusei.jvm.classfile.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

public interface ConstantInfo {

	int getTag();

	void readInfo(DataInputStream data) throws IOException;
	
}
