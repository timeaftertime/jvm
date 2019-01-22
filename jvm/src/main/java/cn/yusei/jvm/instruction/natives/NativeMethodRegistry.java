package cn.yusei.jvm.instruction.natives;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import cn.yusei.jvm.Method;
import cn.yusei.jvm.runtimespace.stack.Frame;

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
		register("java.lang.Object hashCode ()I", new ObjectHashCode());
		register("java.lang.Object registerNatives ()V", (frame) -> {
		});
		register("java.lang.Class registerNatives ()V", (frame) -> {
		});
		register("java.lang.System registerNatives ()V", (frame) -> {
		});
		register("java.lang.Thread registerNatives ()V", (frame) -> {
		});
		register("java.lang.Class getPrimitiveClass (Ljava/lang/String;)Ljava/lang/Class;",
				new ClassGetPrimitiveClass());
		register("java.lang.Class desiredAssertionStatus0 (Ljava/lang/Class;)Z", (frame) -> {
			frame.getOperandStack().pushInt(0);
		});
		register("java.lang.System initProperties (Ljava/util/Properties;)Ljava/util/Properties;", (frame) -> {
			frame.getOperandStack().pushRef(frame.getLocalVarsTable().getRef(0));
		});
		register("java.lang.System arraycopy (Ljava/lang/Object;ILjava/lang/Object;II)V", new SystemArrayCopy());
		register("java.lang.String intern ()Ljava/lang/String;", new StringIntern());
		register("java.lang.Throwable fillInStackTrace (I)Ljava/lang/Throwable;", new ThrowableFillInStackTrace());
		register("java.lang.Float floatToRawIntBits (F)I", (frame) -> {
			frame.getOperandStack().pushInt(Float.floatToRawIntBits(frame.getLocalVarsTable().getFloat(0)));
		});
		register("java.lang.Double doubleToRawLongBits (D)J", (frame) -> {
			frame.getOperandStack().pushLong(Double.doubleToRawLongBits(frame.getLocalVarsTable().getDouble(0)));
		});
		register("java.lang.Double longBitsToDouble (J)D", (frame) -> {
			frame.getOperandStack().pushDouble(Double.longBitsToDouble(frame.getLocalVarsTable().getLong(0)));
		});
		register("sun.misc.VM initialize ()V", new SystemInitialze());
		register("java.io.FileInputStream initIDs ()V", (frame) -> {
		});
		register("java.io.FileOutputStream initIDs ()V", (frame) -> {
		});
		register("java.io.FileDescriptor initIDs ()V", (frame) -> {
		});
		register("java.io.FileDescriptor set (I)J", (frame) -> {
			frame.getOperandStack().pushLong(1);
		});
		register("sun.misc.Unsafe registerNatives ()V", (frame) -> {
		});
		register("sun.misc.Unsafe arrayBaseOffset (Ljava/lang/Class;)I", (frame) -> {
			frame.getOperandStack().pushInt(0);
		});
		register("sun.misc.Unsafe arrayIndexScale (Ljava/lang/Class;)I", (frame) -> {
			frame.getOperandStack().pushInt(1);
		});
		register("sun.misc.Unsafe addressSize ()I", (frame) -> {
			frame.getOperandStack().pushInt(8);
		});
		register("sun.reflect.Reflection getCallerClass ()Ljava/lang/Class;", (frame) -> {
			frame.getOperandStack().pushRef(frame.getMethod().getClassInfo().getLoader().getJavaClass().newObjectRef());
		});
		register(
				"java.security.AccessController doPrivileged (Ljava/security/PrivilegedExceptionAction;)Ljava/lang/Object;",
				new ACDoPrivileged());
		register("java.security.AccessController doPrivileged (Ljava/security/PrivilegedAction;)Ljava/lang/Object;",
				new ACDoPrivileged());
	}

}
