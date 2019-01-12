package cn.yusei.jvm;

import java.io.IOException;

import cn.yusei.jvm.classfile.AccessMask;
import cn.yusei.jvm.classfile.ClassMetadata;
import cn.yusei.jvm.classfile.constantpool.ClassConstantInfo;
import cn.yusei.jvm.classfile.constantpool.ConstantPool;
import cn.yusei.jvm.runtimespace.method.RTConstantPool;

public class ClassInfo {

	private String name;
	private String superName;
	private String[] interfacesName;
	private ClassInfo superClassInfo;
	private ClassInfo[] interfacesClassInfo;
	private AccessMask access;
	private RTConstantPool pool;
	private Field[] fields;
	private Method[] methods;
	private ClassInfoLoader loader;
	private int instanceSlotCount;
	private int staticSlotCount;
	private MemberSlots staticVars;

	public ClassInfo(ClassInfoLoader loader, ClassMetadata metadata) {
		this.loader = loader;
		parseConstantPool(metadata);
		parseClassName(metadata);
		parseSuperClassName(metadata);
		parseInterfacesName(metadata);
		parseAccessMask(metadata);
		parseFields(metadata, pool);
		parseMethods(metadata, pool);
	}

	private void parseConstantPool(ClassMetadata metadata) {
		pool = new RTConstantPool(metadata.getConstanPool(), this);
	}

	private void parseClassName(ClassMetadata metadata) {
		name = getClassConstantInfoName(metadata.getClassIndex(), metadata);
	}

	private void parseSuperClassName(ClassMetadata metadata) {
		if (metadata.getSuperClassIndex() == 0)
			return;
		superName = getClassConstantInfoName(metadata.getSuperClassIndex(), metadata);
	}

	private void parseInterfacesName(ClassMetadata metadata) {
		int interfaceCount = metadata.getInterfaceCount();
		interfacesName = new String[interfaceCount];
		for (int i = 0; i < interfaceCount; i++)
			interfacesName[i] = getClassConstantInfoName(metadata.getInterfaceIndex(i), metadata);
	}

	private void parseAccessMask(ClassMetadata metadata) {
		access = metadata.getAccessMask();
	}

	private void parseFields(ClassMetadata metadata, RTConstantPool pool) {
		int fieldCount = metadata.getFieldMemberCount();
		fields = new Field[fieldCount];
		for (int i = 0; i < fieldCount; i++)
			fields[i] = new Field(this, metadata.getFieldClassMember(i), pool);
	}

	private void parseMethods(ClassMetadata metadata, RTConstantPool pool) {
		int methodCount = metadata.getMethodMemberCount();
		methods = new Method[methodCount];
		for (int i = 0; i < methodCount; i++)
			methods[i] = new Method(this, metadata.getMethodClassMember(i), pool);
	}

	private String getClassConstantInfoName(int index, ClassMetadata metadata) {
		ConstantPool constantPool = metadata.getConstanPool();
		ClassConstantInfo classInfo = constantPool.getClassInfo(index);
		return toDotSplitClassName(constantPool.getUTF8(classInfo.getNameIndex()).getValue());
	}

	private String toDotSplitClassName(String className) {
		return className.replace("/", ".");
	}

	public String getName() {
		return name;
	}

	public String getSuperName() {
		return superName;
	}

	public String[] getInterfacesName() {
		return interfacesName;
	}

	public boolean isPublic() {
		return access.isPublic();
	}

	public boolean isFinal() {
		return access.isFinal();
	}

	public boolean isSuper() {
		return access.isSuper();
	}

	public boolean isInterface() {
		return access.isInterface();
	}

	public boolean isAbstract() {
		return access.isAbstract();
	}

	public boolean isSynthetic() {
		return access.isSynthetic();
	}

	public boolean isAnnotation() {
		return access.isAnnotation();
	}

	public boolean isEnum() {
		return access.isEnum();
	}

	public Field[] getFields() {
		return fields;
	}

	public Method[] getMethods() {
		return methods;
	}

	public ClassInfo getSuperClassInfo() {
		return superClassInfo;
	}

	public void setSuperClassInfo(ClassInfo superClassInfo) {
		this.superClassInfo = superClassInfo;
	}

	public ClassInfo[] getInterfacesClassInfo() {
		return interfacesClassInfo;
	}

	public void setInterfacesClassInfo(ClassInfo[] interfacesClassInfo) {
		this.interfacesClassInfo = interfacesClassInfo;
	}

	public int getInstanceSlotCount() {
		return instanceSlotCount;
	}

	public void setInstanceSlotCount(int instanceSlotCount) {
		this.instanceSlotCount = instanceSlotCount;
	}

	public int getStaticSlotCount() {
		return staticSlotCount;
	}

	public void setStaticSlotCount(int staticSlotCount) {
		this.staticSlotCount = staticSlotCount;
	}

	public MemberSlots getStaticVars() {
		return staticVars;
	}

	public void setStaticVars(MemberSlots staticVars) {
		this.staticVars = staticVars;
	}

	public RTConstantPool getConstantPool() {
		return pool;
	}

	public ClassInfoLoader getLoader() {
		return loader;
	}

	public boolean isAccessiable(ClassInfo from) {
		return isPublic() || getPackageName().equals(from.getPackageName());
	}

	public String getPackageName() {
		return getName().substring(0, getName().lastIndexOf('.'));
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ClassInfo))
			return false;
		return ((ClassInfo) obj).name.equals(name);
	}

	public boolean isSubClassOf(ClassInfo toClass) throws ClassNotFoundException, IOException {
		for(ClassInfo nowClass = superClassInfo; nowClass!= null; nowClass = nowClass.superClassInfo)
			if(toClass.equals(nowClass))
				return  true;
		return false;
	}

	public ObjectRef newObjectRef() {
		return new ObjectRef(this, getInstanceSlotCount());
	}

	public boolean isAssignableFrom(ClassInfo toClass) throws ClassNotFoundException, IOException {
		if(this.equals(toClass))
			return true;
		if (toClass.isInterface()) {
			return isImplements(toClass);
		} else
			return isSubClassOf(toClass);
	}

	private boolean isImplements(ClassInfo toClass) {
		for (ClassInfo info = this; info != null; info = info.getSuperClassInfo()) {
			for (ClassInfo inter : info.interfacesClassInfo) {
				if (inter == toClass || info.isImplements(toClass))
					return true;
			}
		}
		return false;
	}

}
