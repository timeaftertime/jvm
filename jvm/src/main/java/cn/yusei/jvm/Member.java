package cn.yusei.jvm;

import java.io.IOException;

import cn.yusei.jvm.classfile.AccessMask;
import cn.yusei.jvm.classfile.member.ClassMember;
import cn.yusei.jvm.runtimespace.method.RTConstantPool;

public class Member {

	protected ClassInfo owner;
	protected AccessMask access;
	private String name;
	private String descriptor;

	public Member(ClassInfo owner, ClassMember member, RTConstantPool pool) {
		this.owner = owner;
		access = member.getAccessMask();
		name = pool.getString(member.getNameIndex());
		descriptor = pool.getString(member.getDescriptorIndex());
	}

	public String getName() {
		return name;
	}

	public String getDescriptor() {
		return descriptor;
	}

	public boolean isPublic() {
		return access.isPublic();
	}

	public boolean isPrivate() {
		return access.isPrivate();
	}

	public boolean isProtected() {
		return access.isProtected();
	}

	public boolean isStatic() {
		return access.isStatic();
	}

	public boolean isFinal() {
		return access.isFinal();
	}

	public boolean isSynthetic() {
		return access.isSynthetic();
	}

	public boolean isAccessiableFrom(ClassInfo from) throws ClassNotFoundException, IOException {
		if (isPublic())
			return true;
		if (isProtected()) {
			return owner == from || from.isSubClassOf(owner) || from.getPackageName().equals(owner.getPackageName());
		}
		if (!isPrivate())
			return from.getPackageName().equals(owner.getPackageName());
		return owner == from;
	}

	/**
	 * 获取该成员属于哪个类
	 * 
	 * @return
	 */
	public ClassInfo getClassInfo() {
		return owner;
	}
}
