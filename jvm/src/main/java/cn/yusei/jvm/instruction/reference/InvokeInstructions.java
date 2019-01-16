package cn.yusei.jvm.instruction.reference;

import java.io.IOException;

import cn.yusei.jvm.ClassInfo;
import cn.yusei.jvm.Method;
import cn.yusei.jvm.ObjectRef;
import cn.yusei.jvm.instruction.BytecodeReader;
import cn.yusei.jvm.instruction.ExecuteBytecodeError;
import cn.yusei.jvm.instruction.base.UInt16Instruction;
import cn.yusei.jvm.runtimespace.method.InterfaceMethodref;
import cn.yusei.jvm.runtimespace.method.MethodRef;
import cn.yusei.jvm.runtimespace.method.RTConstantPool;
import cn.yusei.jvm.runtimespace.stack.Frame;

public class InvokeInstructions {

	public static class INVOKE_STATIC extends UInt16Instruction {

		@Override
		public void execute(Frame frame) {
			ClassInfo currentClass = frame.getMethod().getClassInfo();
			RTConstantPool pool = currentClass.getConstantPool();
			MethodRef methodRef = pool.getMethodRef(operand);
			Method method;
			try {
				method = methodRef.resolvedMethod();
			} catch (ClassNotFoundException | IOException e) {
				throw new ExecuteBytecodeError(e);
			}
			if (method == null)
				throw new NoSuchMethodError(methodRef.getClassName() + "." + methodRef.getName());
			ClassInfo classInfo = method.getClassInfo();
			if (!classInfo.initStarted()) {
				frame.revertNextPc();
				classInfo.initClass(frame.getThreadSpace());
				return;
			}
			if (!method.isStatic())
				throw new IncompatibleClassChangeError();
			frame.getThreadSpace().invokeMethod(method);
		}

	}

	public static class INVOKE_SPECIAL extends UInt16Instruction {

		@Override
		public void execute(Frame frame) {
			ClassInfo currentClass = frame.getMethod().getClassInfo();
			MethodRef methodRef = currentClass.getConstantPool().getMethodRef(operand);
			try {
				ClassInfo targetClass = methodRef.resolvedClass();
				Method method = methodRef.resolvedMethod();
				if (method == null)
					throw new NoSuchMethodError(methodRef.getClassName() + "." + methodRef.getName());
				if (method.getName().equals("<init>") && targetClass != method.getClassInfo())
					throw new NoSuchMethodError();
				if (method.isStatic())
					throw new IncompatibleClassChangeError();
				ObjectRef invoker = frame.getOperandStack().getRefFromTop(method.getArgsSlotCount());
				if (invoker == null)
					throw new NullPointerException();
				// 下面两个判断，参考书上用了一个我看不懂的 if ，最后还是自己根据语义实现
				// 一个 protected 方法只能被同包或声明方法的类或其子类调用
				// 现在 currentClass 代码中调用该方法，说明 currentClass 是方法所属类的子类或同包的类
				if (method.isProtected() && currentClass.isSubClassOf(targetClass)
						&& !currentClass.getPackageName().equals(targetClass.getPackageName()))
					throw new IllegalAccessError();
				// invoker 的类必须是方法所属类或者其子类才会具有该方法
				if (invoker.getClassInfo() != targetClass && !invoker.getClassInfo().isSubClassOf(targetClass))
					throw new IllegalAccessError();
				// 如果调用的是父类中的方法，但不是构造方法，且当前类的 ACC_SUPER 标志被设置，需要在父类中查找该方法
				// 否则之前从符号引用中解析出的方法就是最终要调用的方法
				if (currentClass.isSuper() && currentClass.isSubClassOf(targetClass)
						&& !method.getName().equals("<init>"))
					method = methodRef.lookupInClass(currentClass.getSuperClassInfo());
				if (method == null || method.isAbstract())
					throw new AbstractMethodError();
				frame.getThreadSpace().invokeMethod(method);
			} catch (ClassNotFoundException | IOException e) {
				throw new ExecuteBytecodeError(e);
			}

		}

	}

	public static class INVOKE_VIRTUAL extends UInt16Instruction {

		@Override
		public void execute(Frame frame) {
			ClassInfo currentClass = frame.getMethod().getClassInfo();
			MethodRef methodRef = currentClass.getConstantPool().getMethodRef(operand);
			try {
				ClassInfo targetClass = methodRef.resolvedClass();
				Method method = methodRef.resolvedMethod();
				if (method == null)
					throw new NoSuchMethodError(methodRef.getClassName() + "." + methodRef.getName());
				if (method.getName().equals("<init>") && methodRef.resolvedClass() != currentClass)
					throw new NoSuchMethodError();
				if (method.isStatic())
					throw new IncompatibleClassChangeError();
				ObjectRef invoker = frame.getOperandStack().getRefFromTop(method.getArgsSlotCount());
				if (invoker == null)
					throw new NullPointerException();
				if (method.isProtected() && currentClass.isSubClassOf(targetClass)
						&& !currentClass.getPackageName().equals(targetClass.getPackageName()))
					throw new IllegalAccessError();
				// invoker 的类必须是方法所属类或者其子类才会具有该方法
				if (invoker.getClassInfo() != targetClass && !invoker.getClassInfo().isSubClassOf(targetClass))
					throw new IllegalAccessError();
				// invoke_virtual 不会进行虚函数查找，直接在当前类中找
				method = methodRef.lookupInClass(targetClass);
				if (method == null || method.isAbstract())
					throw new AbstractMethodError();
				frame.getThreadSpace().invokeMethod(method);
			} catch (ClassNotFoundException | IOException e) {
				throw new ExecuteBytecodeError(e);
			}
		}

	}

	public static class INVOKE_INTERFACE extends UInt16Instruction {

		@Override
		protected int readOperand(BytecodeReader reader) throws IOException {
			int readOperand = super.readOperand(reader);
			// 由于历史原因而存在，函数的参数个数，现在可以通过描述符来计算
			reader.readUint8();
			// 用于 Oracle 某些 Java 虚拟机的实现，必须为 0
			reader.readUint8();
			return readOperand;
		}

		@Override
		public void execute(Frame frame) {
			ClassInfo currentClass = frame.getMethod().getClassInfo();
			InterfaceMethodref methodRef = currentClass.getConstantPool().getInterfaceMethodRef(operand);
			try {
				ClassInfo targetClass = methodRef.resolvedClass();
				Method method = methodRef.resolvedInterfaceMethod();
				if (method == null)
					throw new NoSuchMethodError(methodRef.getClassName() + "." + methodRef.getName());
				// JDK8 后已经支持接口定义静态方法和默认方法，这里只实现 JDK7 的规范
				if (method.isStatic() || method.isPrivate())
					throw new IncompatibleClassChangeError();
				ObjectRef invoker = frame.getOperandStack().getRefFromTop(method.getArgsSlotCount());
				if (invoker == null)
					throw new NullPointerException();
				if (method.isProtected() && currentClass.isSubClassOf(targetClass)
						&& !currentClass.getPackageName().equals(targetClass.getPackageName()))
					throw new IllegalAccessError();
				// invoker 的类必须实现该接口
				if (!invoker.getClassInfo().isImplements(targetClass))
					throw new IncompatibleClassChangeError();
				// invoke_virtual 不会进行虚函数查找，直接在当前类中找
				method = methodRef.lookupInClass(invoker.getClassInfo());
				if (method == null || method.isAbstract())
					throw new AbstractMethodError();
				if (!method.isPublic())
					throw new IllegalAccessError();
				frame.getThreadSpace().invokeMethod(method);
			} catch (ClassNotFoundException | IOException e) {
				throw new ExecuteBytecodeError(e);
			}
		}

	}

}
