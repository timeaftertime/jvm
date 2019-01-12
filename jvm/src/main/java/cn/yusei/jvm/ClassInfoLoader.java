package cn.yusei.jvm;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import cn.yusei.jvm.classfile.ClassMetadata;
import cn.yusei.jvm.classfile.ClassReader;
import cn.yusei.jvm.runtimespace.method.RTConstantPool;

public class ClassInfoLoader {

	private ClassReader reader;
	private Map<String, ClassInfo> loadedClassInfo;

	public ClassInfoLoader() {
		this(new String[0]);
	}

	public ClassInfoLoader(String[] classPaths) {
		HashSet<String> paths = new HashSet<String>();
		paths.addAll(Arrays.asList(classPaths));
		paths.addAll(Arrays.asList(System.getenv("classpath").split(File.pathSeparator)));
		paths.add(System.getenv("JAVA_HOME") + "/jre/lib/rt.jar");
		paths.add(getDefaultClassPath());
		reader = new ClassReader(paths.toArray(new String[0]));
		loadedClassInfo = new ConcurrentHashMap<>();
	}

	protected String getDefaultClassPath() {
		return ClassInfoLoader.class.getResource("/").getPath().substring(1);
	}

	public ClassInfo loadClass(String className) throws ClassNotFoundException, IOException {
		if (loadedClassInfo.containsKey(className))
			return loadedClassInfo.get(className);
		return load(className);
	}

	private ClassInfo load(String className) throws ClassNotFoundException, IOException {
		// 加载
		byte[] classByte = readClass(className);
		ClassInfo classInfo = defineClass(classByte);
		link(classInfo);
		return classInfo;
	}

	private byte[] readClass(String className) throws ClassNotFoundException, IOException {
		return reader.readClass(className);
	}

	private ClassInfo defineClass(byte[] classByte) throws IOException, ClassNotFoundException {
		ClassInfo classInfo = parseClass(classByte);
		resolveSuperClass(classInfo);
		resolveInterfaces(classInfo);
		loadedClassInfo.put(classInfo.getName(), classInfo);
		return classInfo;
	}

	private ClassInfo parseClass(byte[] classByte) throws IOException {
		return new ClassInfo(this, new ClassMetadata(classByte));

	}

	private void link(ClassInfo classInfo) {
		// 验证
		verify(classInfo);
		// 准备
		prepare(classInfo);
	}

	private void verify(ClassInfo classInfo) {
		// TODO 暂不进行验证
	}

	private void prepare(ClassInfo classInfo) {
		calculateInstanceFieldSoltIds(classInfo);
		calculateStaticFieldSoltIds(classInfo);
		allocateAndInitStaticVars(classInfo);
	}

	private void calculateInstanceFieldSoltIds(ClassInfo classInfo) {
		ClassInfo superClassInfo = classInfo.getSuperClassInfo();
		int slotId = superClassInfo == null ? 0 : superClassInfo.getInstanceSlotCount();
		for (Field field : classInfo.getFields()) {
			if (field.isStatic())
				continue;
			field.setSoltId(slotId++);
			if (field.needDoubleSlot())
				slotId++;
		}
		classInfo.setInstanceSlotCount(slotId);
	}

	private void calculateStaticFieldSoltIds(ClassInfo classInfo) {
		int slotId = 0;
		for (Field field : classInfo.getFields()) {
			if (!field.isStatic())
				continue;
			field.setSoltId(slotId++);
			if (field.needDoubleSlot())
				slotId++;
		}
		classInfo.setStaticSlotCount(slotId);
	}

	private void allocateAndInitStaticVars(ClassInfo classInfo) {
		// 本来应该给静态变量赋对应的初值，因为 Java 会自动给未赋值的变量赋默认，这里就不再写代码赋值
		classInfo.setStaticVars(new MemberSlots(classInfo.getStaticSlotCount()));
		for (Field field : classInfo.getFields()) {
			if (!field.isStatic() || !field.isFinal())
				continue;
			initStaticFinalVar(classInfo, field);
		}
	}

	private void initStaticFinalVar(ClassInfo classInfo, Field field) {
		if (field.getConstantValueIndex() == 0)
			return;
		RTConstantPool pool = classInfo.getConstantPool();
		MemberSlots staticVars = classInfo.getStaticVars();
		switch (field.getDescriptor()) {
		case "Z":
		case "B":
		case "C":
		case "S":
		case "I":
			staticVars.setInt(field.getSlotId(), pool.getInt(field.getConstantValueIndex()));
			break;
		case "F":
			staticVars.setFloat(field.getSlotId(), pool.getFloat(field.getConstantValueIndex()));
			break;
		case "J":
			staticVars.setLong(field.getSlotId(), pool.getLong(field.getConstantValueIndex()));
			break;
		case "D":
			staticVars.setDouble(field.getSlotId(), pool.getDouble(field.getConstantValueIndex()));
			break;
		case "Ljava/lang/String;":
			throw new RuntimeException("暂未实现");
		}
	}

	private void resolveSuperClass(ClassInfo classInfo) throws ClassNotFoundException, IOException {
		if (classInfo.getSuperClassInfo() != null && !classInfo.getSuperName().equals("java.lang.Object"))
			classInfo.setSuperClassInfo(loadClass(classInfo.getSuperName()));
	}

	private void resolveInterfaces(ClassInfo classInfo) throws ClassNotFoundException, IOException {
		String[] names = classInfo.getInterfacesName();
		int len = names.length;
		ClassInfo[] infos = new ClassInfo[len];
		for (int i = 0; i < len; i++) {
			infos[i] = loadClass(names[i]);
		}
		classInfo.setInterfacesClassInfo(infos);
	}
}
