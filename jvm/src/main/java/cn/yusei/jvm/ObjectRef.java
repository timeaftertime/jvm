package cn.yusei.jvm;

import java.io.IOException;
import java.lang.reflect.Array;

public class ObjectRef {

	private ClassInfo classInfo;
	private MemberSlots members;
	private Object elements;

	private ObjectRef() {
	}

	public static ObjectRef newObject(ClassInfo classInfo, int memberCount) {
		ObjectRef ref = new ObjectRef();
		ref.classInfo = classInfo;
		ref.members = new MemberSlots(memberCount);
		return ref;
	}

	public static ObjectRef newArray(ClassInfo classInfo, int memberCount) {
		return getInitArray(classInfo, memberCount);
	}

	private static ObjectRef getInitArray(ClassInfo classInfo, int memberCount) {
		ObjectRef ref = new ObjectRef();
		ref.classInfo = classInfo;
		switch (classInfo.getName()) {
		case "[Z":
			ref.elements = new boolean[memberCount];
			break;
		case "[B":
			ref.elements = new byte[memberCount];
			break;
		case "[C":
			ref.elements = new char[memberCount];
			break;
		case "[S":
			ref.elements = new short[memberCount];
			break;
		case "[I":
			ref.elements = new int[memberCount];
			break;
		case "[J":
			ref.elements = new long[memberCount];
			break;
		case "[F":
			ref.elements = new float[memberCount];
			break;
		case "[D":
			ref.elements = new double[memberCount];
			break;
		default:
			ref.elements = new ObjectRef[memberCount];
			break;
		}
		return ref;
	}

	public ClassInfo getClassInfo() {
		return classInfo;
	}

	public void setClassInfo(ClassInfo classInfo) {
		this.classInfo = classInfo;
	}

	public MemberSlots getMembers() {
		return members;
	}

	public void setMembers(MemberSlots members) {
		this.members = members;
	}

	public boolean isInstanceOf(ClassInfo toClass) throws ClassNotFoundException, IOException {
		return toClass.isAssignableFrom(classInfo);
	}

	public byte[] bytes() {
		return (byte[]) elements;
	}

	public char[] chars() {
		return (char[]) elements;
	}

	public short[] shorts() {
		return (short[]) elements;
	}

	public int[] ints() {
		return (int[]) elements;
	}

	public float[] floats() {
		return (float[]) elements;
	}

	public long[] longs() {
		return (long[]) elements;
	}

	public double[] doubles() {
		return (double[]) elements;
	}

	public ObjectRef[] refs() {
		return (ObjectRef[]) elements;
	}

	public int length() {
		return Array.getLength(elements);
	}

	public Object getElements() {
		return elements;
	}

	@Override
	public String toString() {
		return classInfo.toString();
	}

	public void setRefValue(String name, String descriptor, ObjectRef value) {
		for (Field field : classInfo.getFields()) {
			if (field.getName().equals(name) && field.getDescriptor().equals(descriptor))
				members.setRef(field.getSlotId(), value);
		}
	}

}
