package cn.yusei.jvm.instruction.reserved;

import cn.yusei.jvm.instruction.base.NoOperandInstruction;
import cn.yusei.jvm.instruction.natives.NativeMethodRegistry;
import cn.yusei.jvm.runtimespace.stack.Frame;

/**
 * 这个指令并不是 JVM 中的指令，其对应的字节码是 JVM 的保留（尚未使用的）字节码 本项目用这个指令来直接调用 Java 方法，以代替本地方法的调用
 */
public class INVOKE_NATIVE extends NoOperandInstruction {

	@Override
	public void execute(Frame frame) {
		NativeMethodRegistry.invoke(frame);
	}

}
