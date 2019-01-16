package cn.yusei.jvm;

import java.io.IOException;

import cn.yusei.jvm.instruction.BytecodeReader;
import cn.yusei.jvm.instruction.Instruction;
import cn.yusei.jvm.instruction.InstructionFactory;
import cn.yusei.jvm.runtimespace.ThreadSpace;

public class Interpreter {

	private static final String MAIN_METHOD_NAME = "main";
	private static final String MAIN_METHOD_DESCRIPTOR = "([Ljava/lang/String;)V";

	private static final ClassInfoLoader loader = new ClassInfoLoader();

	public static void interpret(String startClassName) throws IOException, ClassNotFoundException {
		Method mainMethod;
		mainMethod = loader.loadClass(startClassName).getMethod(MAIN_METHOD_NAME, MAIN_METHOD_DESCRIPTOR);
		if (mainMethod == null)
			throw new NoSuchMethodError(startClassName + " 中找不到 main 方法");
		ThreadSpace thread = new ThreadSpace();
		thread.pushFrame(mainMethod);
		loop(thread, mainMethod.getCodes());
	}

	private static void loop(ThreadSpace thread, byte[] codes) throws IOException {
		BytecodeReader reader = new BytecodeReader(codes);
		while (!thread.finished()) {
			int pc = thread.currentFrame().getNextPc();
			// 每次执行指令前，frame 将设置所在线程空间的 pc
			thread.currentFrame().synchronizedThreadSpacePc();
			reader.reset(thread.currentFrame().getMethod().getCodes(), pc);
			int opCode = reader.readUint8();
			Instruction inst = InstructionFactory.createInstruction(opCode);
			inst.readOperands(reader);
			// 这里只是改变了 Frame 的 pc ，即即将在下一条指令执行前被赋值给 ThreadSpace 的 pc
			// 如果指令没有改变下一个 pc ，那么下一个 pc 就是当前 reader 的位置
			thread.currentFrame().setNextPc(reader.getPc());
			inst.execute(thread.currentFrame());
		}
	}

}
