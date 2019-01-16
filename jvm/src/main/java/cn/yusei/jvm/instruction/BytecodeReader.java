package cn.yusei.jvm.instruction;

import java.io.EOFException;
import java.io.IOException;

public class BytecodeReader {

	private int pc;
	private byte[] codes;
	private int count;
	
	public BytecodeReader(byte[] codes) {
		pc = 0;
		this.codes = codes;
		count = codes.length;
	}

	public int readUint8() throws IOException {
		if(pc >= count)
			throw new EOFException();
		int ch = codes[pc++];
		return ch & 0xff;
	}

	public byte readInt8() throws IOException {
		return (byte) readUint8();
	}

	public short readInt16() throws IOException {
		int ch1 = readUint8();
        int ch2 = readUint8();
        return (short)((ch1 << 8) + (ch2 << 0));
	}
	
	public int readUint16() throws IOException {
		int ch1 = readUint8();
        int ch2 = readUint8();
        return (ch1 << 8) + (ch2 << 0);
	}

	public int readInt32() throws IOException {
		int ch1 = readUint8();
        int ch2 = readUint8();
        int ch3 = readUint8();
        int ch4 = readUint8();
        return ((ch1 << 24) + (ch2 << 16) + (ch3 << 8) + (ch4 << 0));
	}
	
	public int getPc() {
		return pc;
	}
	
	public void setPc(int pc) {
		this.pc = pc;
	}

	public void skipPadding(int padding) throws IOException {
		if(padding == 0)
			return;
		while(getPc() % padding != 0)
			readInt8();
	}

	public int[] readInt32s(int num) throws IOException {
		int[] int32s = new int[num];
		for(int i=0; i<num; i++)
			int32s[i] = readInt32();
		return int32s;
	}

	public void reset(byte[] codes, int pc) {
		this.codes = codes;
		this.pc = pc;
	}
}
