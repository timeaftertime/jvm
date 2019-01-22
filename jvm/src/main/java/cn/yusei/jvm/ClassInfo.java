package cn.yusei.jvm;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import cn.yusei.jvm.classfile.AccessMask;
import cn.yusei.jvm.classfile.ClassMetadata;
import cn.yusei.jvm.classfile.constantpool.ClassConstantInfo;
import cn.yusei.jvm.classfile.constantpool.ConstantPool;
import cn.yusei.jvm.classfile.member.attribute.Attribute;
import cn.yusei.jvm.classfile.member.attribute.SourceFileAttribute;
import cn.yusei.jvm.runtimespace.ThreadSpace;
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
	private boolean initStarted = false;
	private String sourceFileName;

	public static final List<String> PRIMITIVE_NAMES = Collections
			.unmodifiableList(Arrays.asList("void", "boolean", "byte", "char", "int", "float", "double"));
	public static final List<String> PRIMITIVE_DESCRIPTORS = Collections
			.unmodifiableList(Arrays.asList("V", "Z", "B", "C", "I", "F", "D"));

	private ClassInfo() {
	};

	public ClassInfo(ClassInfoLoader loader, ClassMetadata metadata) {
		this.loader = loader;
		parseConstantPool(metadata);
		parseClassName(metadata);
		parseSuperClassName(metadata);
		parseInterfacesName(metadata);
		parseAccessMask(metadata);
		parseFields(metadata, pool);
		parseMethods(metadata, pool);
		parseSourceFileName(metadata, pool);
	}

	public static ClassInfo newPrimitiveClass(ClassInfoLoader loader, String className) throws ClassNotFoundException {
		if (!PRIMITIVE_NAMES.contains(className))
			throw new ClassNotFoundException(className);
		ClassInfo classInfo = new ClassInfo();
		classInfo.loader = loader;
		classInfo.access = new AccessMask(AccessMask.ACC_PUBLIC);
		classInfo.name = className;
		classInfo.startInit();
		return classInfo;
	}

	public static ClassInfo newArrayClassInfo(ClassInfoLoader loader, String className) {
		ClassInfo classInfo = new ClassInfo();
		classInfo.loader = loader;
		classInfo.access = new AccessMask(AccessMask.ACC_PUBLIC);
		classInfo.name = className.replace("/", ".");
		classInfo.superName = "java.lang.Object";
		classInfo.interfacesName = new String[] { "java.lang.Cloneable", "java.io.Serializable" };
		classInfo.startInit();
		return classInfo;
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

	private void parseSourceFileName(ClassMetadata metadata, RTConstantPool pool) {
		for (Attribute attr : metadata.getAttributes()) {
			if (attr instanceof SourceFileAttribute) {
				sourceFileName = pool.getString(((SourceFileAttribute) attr).getSourceFileIndex());
				return;
			}
		}
		// class 文件中不一定有 SourceFile ，决定于编译时的选项
		sourceFileName = "Unkown";
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

	public Field getField(String name, String descriptor) {
		for (Field field : fields) {
			if (field.getName().equals(name) && field.getDescriptor().equals(descriptor))
				return field;
		}
		if (getSuperClassInfo() != null)
			return getSuperClassInfo().getField(name, descriptor);
		return null;
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

	public boolean isSubClassOf(ClassInfo toClass) {
		for (ClassInfo nowClass = superClassInfo; nowClass != null; nowClass = nowClass.superClassInfo)
			if (toClass.equals(nowClass))
				return true;
		return false;
	}

	public ObjectRef newObjectRef() {
		return ObjectRef.newObject(this, getInstanceSlotCount());
	}

	/**
	 * 以当前类型创建一个数组，当前类型必须是一个数组
	 * 
	 * @param length
	 * @return
	 */
	public ObjectRef newObjectRefAsArray(int length) {
		if (!isArray())
			throw new RuntimeException(getName() + "不是数组类型");
		return ObjectRef.newArray(this, length);
	}

	/**
	 * 创建当前类型的数组
	 * 
	 * @param length
	 * @return
	 */
	public ObjectRef newObjectRefArray(int length) {
		return ObjectRef.newArray(getClassFromLoader(getArrayClassName()), length);
	}

	private String getArrayClassName() {
		if (getName().charAt(0) == '[' || PRIMITIVE_DESCRIPTORS.contains(getName()))
			return "[" + getName();
		return "[L" + getName() + ";";
	}

	/**
	 * 是否与参数的类或接口相同，或者是其超类或超接口
	 * 
	 * @param toClass
	 * @return
	 */
	public boolean isAssignableFrom(ClassInfo toClass) {
		if (this == toClass)
			return true;
		if (!toClass.isArray()) {
			if (!toClass.isInterface()) {
				if (!isInterface())
					return toClass.isSubClassOf(this);
				else
					return toClass.isImplements(this);
			} else {
				if (!isInterface())
					return getName().equals("java.lang.Object");
				else
					return toClass.isImplements(this);
			}
		} else {
			if (!isArray()) {
				if (!isInterface())
					return getName().equals("java.lang.Object");
				else
					return getName().equals("java.lang.Cloneable") || getName().equals("java.io.Serializable");
			} else {
				return getElementClass().isAssignableFrom(toClass.getElementClass());
			}
		}
	}

	public boolean isImplements(ClassInfo toClass) {
		for (ClassInfo inter : interfacesClassInfo) {
			if (inter == toClass || inter.isImplements(toClass))
				return true;
		}
		for (ClassInfo info = getSuperClassInfo(); info != null; info = info.getSuperClassInfo()) {
			if (info.isImplements(toClass))
				return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return getName();
	}

	public void startInit() {
		initStarted = true;
	}

	public boolean initStarted() {
		return initStarted;
	}

	public void initClass(ThreadSpace threadSpace) {
		startInit();
		scheduleClinit(threadSpace);
		initSuperClass(threadSpace);
	}

	private void scheduleClinit(ThreadSpace threadSpace) {
		Method clinit = getClinitMethod();
		if (clinit != null)
			threadSpace.pushFrame(clinit);
	}

	private Method getClinitMethod() {
		return getStaticMethod("<clinit>", "()V");
	}

	private Method getStaticMethod(String name, String descriptor) {
		for (Method method : methods) {
			if (!method.isStatic())
				continue;
			if (method.getName().equals(name) && method.getDescriptor().equals(descriptor))
				return method;
		}
		return null;
	}

	private void initSuperClass(ThreadSpace threadSpace) {
		if (superClassInfo != null && !superClassInfo.initStarted())
			superClassInfo.initClass(threadSpace);
	}

	public ClassInfo getElementClass() {
		return getClassFromLoader(getElementClassName());
	}

	private String getElementClassName() {
		String elementClassName = getName().substring(1);
		if (elementClassName.charAt(0) == '[')
			return elementClassName;
		if (elementClassName.charAt(0) == 'L')
			return elementClassName.substring(1, elementClassName.length() - 1);
		if (PRIMITIVE_DESCRIPTORS.contains(elementClassName))
			return elementClassName;
		throw new RuntimeException(getName() + "不是数组类型");
	}

	public boolean isArray() {
		return getName().startsWith("[");
	}

	private ClassInfo getClassFromLoader(String className) {
		try {
			return loader.loadClass(className);
		} catch (ClassNotFoundException | IOException e) {
			throw new RuntimeException("获取类信息 " + className + " 失败");
		}
	}

	public Method getMethod(String name, String descriptor) {
		for (Method method : methods) {
			if (method.getName().equals(name) && method.getDescriptor().equals(descriptor))
				return method;
		}
		return null;
	}

	public String getSourceFileName() {
		return sourceFileName;
	}

	public int distanceToObject() {
		int cnt = 0;
		ClassInfo nowClass = this;
		while (nowClass.getSuperClassInfo() != null) {
			nowClass = nowClass.getSuperClassInfo();
			cnt++;
		}
		return cnt;
	}

	public ObjectRef getRefValue(String name, String descriptor) {
		for (Field field : fields) {
			if (field.isStatic() && field.getName().equals(name) && field.getDescriptor().equals(descriptor)) {
				return getStaticVars().getRef(field.getSlotId());
			}
		}
		throw new NoSuchFieldError("静态字段 " + name + " " + descriptor);
	}

}
