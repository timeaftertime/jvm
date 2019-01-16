package cn.yusei.jvm.instruction.natives;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import cn.yusei.jvm.Field;
import cn.yusei.jvm.Method;
import cn.yusei.jvm.ObjectRef;
import cn.yusei.jvm.StringPool;
import cn.yusei.jvm.runtimespace.stack.Frame;
import cn.yusei.jvm.runtimespace.stack.LocalVarsTable;

public class NativeMethodRegistry {

	private static Map<String, NativeMethod> nativeMethods = new ConcurrentHashMap<>();

	public static void invoke(Frame frame) {
		Method method = frame.getMethod();
		String key = method.getClassInfo().getName() + " " + method.getName() + " " + method.getDescriptor();
		if (!nativeMethods.containsKey(key))
			throw new NoSuchMethodError("未注册 native 方法 ：" + key);
		nativeMethods.get(key).invoke(frame);
	}

	public static void register(String key, NativeMethod method) {
		nativeMethods.put(key, method);
	}

	static {
		register("java.lang.Object hashCode ()I", new HashCode());

		register("java.lang.Object registerNatives ()V", (frame) -> {
		});

		register("java.lang.System registerNatives ()V", (frame) -> {
		});

		register("java.lang.System arraycopy (Ljava/lang/Object;ILjava/lang/Object;II)V", (frame) -> {
			LocalVarsTable locals = frame.getLocalVarsTable();
			ObjectRef src = locals.getRef(0);
			int srcPos = locals.getInt(1);
			ObjectRef dest = locals.getRef(2);
			int destPos = locals.getInt(3);
			int len = locals.getInt(4);
			if (src == null || dest == null)
				throw new NullPointerException();
			if (!src.getClassInfo().isArray() || !dest.getClassInfo().isArray())
				throw new ArrayStoreException("参数不是数组");
			if (srcPos < 0 || destPos < 0 || len < 0 || srcPos + len > src.length() || destPos + len > dest.length())
				throw new IndexOutOfBoundsException();
			System.arraycopy(src.getElements(), srcPos, dest.getElements(), destPos, len);
		});

		register("java.lang.String intern ()Ljava/lang/String;", (frame) -> {
			ObjectRef ref = frame.getLocalVarsTable().getRef(0);
			Field field = ref.getClassInfo().getField("value", "[C");
			frame.getOperandStack().pushRef(StringPool.getString(ref.getClassInfo().getLoader(),
					new String(ref.getMembers().getRef(field.getSlotId()).chars())));
		});
	}

}
