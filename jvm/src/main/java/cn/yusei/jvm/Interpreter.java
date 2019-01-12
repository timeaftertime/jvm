package cn.yusei.jvm;

import java.io.IOException;

import cn.yusei.jvm.instruction.BytecodeReader;
import cn.yusei.jvm.instruction.Instruction;
import cn.yusei.jvm.instruction.InstructionFactory;
import cn.yusei.jvm.runtimespace.ThreadSpace;
import cn.yusei.jvm.runtimespace.stack.Frame;

public class Interpreter {

	public static void interpret(Method method) throws IOException  {
		ThreadSpace thread = new ThreadSpace();
		thread.pushFrame(method);
		Frame frame = thread.popFrame();
		loop(frame, method.getCodes());
	}
	
	private static void loop(Frame frame, byte[] codes) throws IOException {
		BytecodeReader reader = new BytecodeReader(codes);
		while(true) {
			int pc = frame.getNextPc();
			// 每次执行指令前，frame 将设置所在线程空间的 pc
			frame.synchronizedThreadSpacePc();
			reader.setPc(pc);
			int opCode = reader.readUint8();
			Instruction inst = InstructionFactory.createInstruction(opCode);
			inst.readOperands(reader);
			// 这里只是改变了 Frame 的 pc ，即即将在下一条指令执行前被赋值给 ThreadSpace 的 pc
			// 如果指令没有改变下一个 pc ，那么下一个 pc 就是当前 reader 的位置
			frame.setNextPc(reader.getPc());
			inst.execute(frame);
		}
	}

}
