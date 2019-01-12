package cn.yusei.jvm;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import cn.yusei.jvm.classfile.ClassMetadata;
import cn.yusei.jvm.classfile.ClassReader;
import cn.yusei.jvm.classfile.constantpool.ConstantPool;
import cn.yusei.jvm.classfile.member.ClassMember;

public class ClassInfoTest {

	private ClassMetadata metadata;
	private ClassInfo classInfo;

	@Before
	public void setUp() throws ClassNotFoundException, IOException {
		String[] classPath = new String[] {ClassInfoTest.class.getResource("/").getPath().substring(1)};
		metadata = new ClassMetadata(new ClassReader(classPath).readClass("cn.yusei.ClassTest"));
		classInfo = new ClassInfo(null, metadata);
	}
	
	@Test
	public void copyValueConstants() {
		ConstantPool pool = metadata.getConstanPool();
		assertEquals("cn.yusei.ClassTest", classInfo.getName());
		assertEquals("java.lang.Object", classInfo.getSuperName());
		int interfaceCount = metadata.getInterfaceCount();
		String[] interfacesName = classInfo.getInterfacesName();
		assertEquals(0, interfacesName.length);
		for(int i=0; i<interfaceCount; i++)
			assertEquals(pool.getUTF8(pool.getClassInfo(metadata.getInterfaceIndex(i)).getNameIndex()).getValue(), interfacesName[i]);
		assertEquals(metadata.getAccessMask().isPublic(), classInfo.isPublic());
		assertEquals(metadata.getAccessMask().isFinal(), classInfo.isFinal());
		assertEquals(metadata.getAccessMask().isSuper(), classInfo.isSuper());
		assertEquals(metadata.getAccessMask().isInterface(), classInfo.isInterface());
		assertEquals(metadata.getAccessMask().isAbstract(), classInfo.isAbstract());
		assertEquals(metadata.getAccessMask().isSynthetic(), classInfo.isSynthetic());
		assertEquals(metadata.getAccessMask().isAnnotation(), classInfo.isAnnotation());
		assertEquals(metadata.getAccessMask().isEnum(), classInfo.isEnum());
	}
	
	@Test
	public void copyMemberConstants() {
		Field[] fields = classInfo.getFields();
		assertEquals(8, fields.length);
		for(int i=0; i<fields.length; i++) {
			ClassMember field = metadata.getFieldClassMember(fields[i].getName(), fields[i].getDescriptor());
			assertEquals(field.getAccessMask().isPublic(), fields[i].isPublic());
			assertEquals(field.getAccessMask().isPrivate(), fields[i].isPrivate());
			assertEquals(field.getAccessMask().isProtected(), fields[i].isProtected());
			assertEquals(field.getAccessMask().isStatic(), fields[i].isStatic());
			assertEquals(field.getAccessMask().isFinal(), fields[i].isFinal());
			assertEquals(field.getAccessMask().isVolatile(), fields[i].isVolatile());
			assertEquals(field.getAccessMask().isTransient(), fields[i].isTransient());
			assertEquals(field.getAccessMask().isSynthetic(), fields[i].isSynthetic());
			assertEquals(field.getAccessMask().isEnum(), fields[i].isEnum());
		}
		Method[] methods = classInfo.getMethods();
		for(int i=0; i<methods.length; i++) {
			ClassMember method = metadata.getMethodClassMember(methods[i].getName(), methods[i].getDescriptor());
			assertEquals(method.getAccessMask().isPublic(), methods[i].isPublic());
			assertEquals(method.getAccessMask().isPrivate(), methods[i].isPrivate());
			assertEquals(method.getAccessMask().isProtected(), methods[i].isProtected());
			assertEquals(method.getAccessMask().isStatic(), methods[i].isStatic());
			assertEquals(method.getAccessMask().isFinal(), methods[i].isFinal());
			assertEquals(method.getAccessMask().isSynchronized(), methods[i].isSynchronized());
			assertEquals(method.getAccessMask().isBridge(), methods[i].isBridge());
			assertEquals(method.getAccessMask().isVarargs(), methods[i].isVarargs());
			assertEquals(method.getAccessMask().isNative(), methods[i].isNative());
			assertEquals(method.getAccessMask().isAbstract(), methods[i].isAbstract());
			assertEquals(method.getAccessMask().isStatic(), methods[i].isStatic());
			assertEquals(method.getAccessMask().isSynthetic(), methods[i].isSynthetic());
		}
		assertEquals(2, methods.length);
	}
}
