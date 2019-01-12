package cn.yusei.jvm;

import java.io.IOException;

public class ObjectRef {

	private ClassInfo classInfo;
	private MemberSlots members;

	public ObjectRef() {
		this(null, 0);
	}
	
	public ObjectRef(ClassInfo classInfo, int memberCount) {
		this.classInfo = classInfo;
		members = new MemberSlots(memberCount);
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
		return classInfo.isAssignableFrom(toClass);
	}

}
