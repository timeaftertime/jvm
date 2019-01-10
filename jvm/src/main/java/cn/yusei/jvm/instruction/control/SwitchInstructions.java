package cn.yusei.jvm.instruction.control;

import java.io.IOException;

import cn.yusei.jvm.instruction.BytecodeReader;
import cn.yusei.jvm.instruction.Instruction;
import cn.yusei.jvm.runtimespace.Frame;

public class SwitchInstructions {

	public abstract static class SwitchInstruction implements Instruction {

		protected int defaultOffset;
		private int offsetMap[];

		@Override
		public final void readOperands(BytecodeReader reader) throws IOException {
			reader.skipPadding(4);
			defaultOffset = reader.readInt32();
			offsetMap = readOffsetMap(reader);
		}

		protected abstract int[] readOffsetMap(BytecodeReader reader) throws IOException;

		@Override
		public void execute(Frame frame) {
			int key = frame.getOperandStack().popInt();
			for (int i = 0; i < offsetMap.length; i += 2) {
				if (key == offsetMap[i]) {
					frame.resetNextPc(offsetMap[i + 1]);
					return;
				}
			}
			frame.resetNextPc(defaultOffset);

		}

	}

	public static class TABLE_SWITCH extends SwitchInstruction {

		@Override
		protected int[] readOffsetMap(BytecodeReader reader) throws IOException {
			int low = reader.readInt32();
			int hig = reader.readInt32();
			int mapCount = hig - low + 1;
			int[] map = new int[mapCount * 2];
			int[] offsets = reader.readInt32s(mapCount);
			for(int i=0; i<mapCount; i++) {
				map[i * 2] = low + i;
				map[i * 2 + 1] = offsets[i];
			}
			return map;
		}

	}

	public static class LOOKUP_SWITCH extends SwitchInstruction {

		@Override
		protected int[] readOffsetMap(BytecodeReader reader) throws IOException {
			return reader.readInt32s(reader.readInt32() * 2);
		}

	}

}
