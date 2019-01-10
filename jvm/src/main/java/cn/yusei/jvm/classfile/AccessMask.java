package cn.yusei.jvm.classfile;

public class AccessMask {

	private static final int ACC_PUBLIC = 0x0001;
	private static final int ACC_PRIVATE = 0x0002;
	private static final int ACC_PROTECTED = 0x0004;
	private static final int ACC_STATIC = 0x0008;
	private static final int ACC_FINAL = 0x0010;
	private static final int ACC_SUPER = 0x0020;
	private static final int ACC_SYNCHRONIZED = 0x0020;
	private static final int ACC_VOLATILE = 0x0040;
	private static final int ACC_BRIDGE = 0x0040;
	private static final int ACC_VARARGS = 0x0080;
	private static final int ACC_TRANSIENT = 0x0080;
	private static final int ACC_NATIVE = 0x0100;
	private static final int ACC_INTERFACE = 0x0200;
	private static final int ACC_ABSTRACT = 0x0400;
	private static final int ACC_STRICTFP = 0x0800;
	private static final int ACC_SYNTHETIC = 0x1000;
	private static final int ACC_ANNOTATION = 0x2000;
	private static final int ACC_ENUM = 0x4000;

	private int mask;

	public AccessMask(int mask) {
		this.mask = mask;
	}

	public boolean isPublic() {
		return (mask & ACC_PUBLIC) == ACC_PUBLIC;
	}

	public boolean isPrivate() {
		return (mask & ACC_PRIVATE) == ACC_PRIVATE;
	}

	public boolean isProtected() {
		return (mask & ACC_PROTECTED) == ACC_PROTECTED;
	}

	public boolean isStatic() {
		return (mask & ACC_STATIC) == ACC_STATIC;
	}

	public boolean isFinal() {
		return (mask & ACC_FINAL) == ACC_FINAL;
	}

	public boolean isSuper() {
		return (mask & ACC_SUPER) == ACC_SUPER;
	}

	public boolean isSynchronized() {
		return (mask & ACC_SYNCHRONIZED) == ACC_SYNCHRONIZED;
	}

	public boolean isVolatile() {
		return (mask & ACC_VOLATILE) == ACC_VOLATILE;
	}

	/**
	 * 是否为编译器产生的桥接方法
	 * @return
	 */
	public boolean isBridge() {
		return (mask & ACC_BRIDGE) == ACC_BRIDGE;
	}

	/**
	 * 是否接受不定参数
	 * @return
	 */
	public boolean isVarargs() {
		return (mask & ACC_VARARGS) == ACC_VARARGS;
	}

	public boolean isTransient() {
		return (mask & ACC_TRANSIENT) == ACC_TRANSIENT;
	}

	public boolean isNative() {
		return (mask & ACC_NATIVE) == ACC_NATIVE;
	}

	public boolean isInterface() {
		return (mask & ACC_INTERFACE) == ACC_INTERFACE;
	}

	public boolean isAbstract() {
		return (mask & ACC_ABSTRACT) == ACC_ABSTRACT;
	}

	/**
	 * 是否为 strictfp
	 * @return
	 */
	public boolean isStrictfp() {
		return (mask & ACC_STRICTFP) == ACC_STRICTFP;
	}
	
	public boolean isSynthetic() {
		return (mask & ACC_SYNTHETIC) == ACC_SYNTHETIC;
	}

	public boolean isAnnotation() {
		return (mask & ACC_ANNOTATION) == ACC_ANNOTATION;
	}

	public boolean isEnum() {
		return (mask & ACC_ENUM) == ACC_ENUM;
	}

}
