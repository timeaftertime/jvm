package cn.yusei.jvm.classfile;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import cn.yusei.jvm.classfile.ClassMetadata;
import cn.yusei.jvm.classfile.constantpool.ClassConstantInfo;
import cn.yusei.jvm.classfile.constantpool.ConstantPool;
import cn.yusei.jvm.classfile.constantpool.DoubleConstantInfo;
import cn.yusei.jvm.classfile.constantpool.FieldrefConstantInfo;
import cn.yusei.jvm.classfile.constantpool.FloatConstantInfo;
import cn.yusei.jvm.classfile.constantpool.IntegerConstantInfo;
import cn.yusei.jvm.classfile.constantpool.LongConstantInfo;
import cn.yusei.jvm.classfile.constantpool.MethodrefConstantInfo;
import cn.yusei.jvm.classfile.constantpool.NameAndTypeConstantInfo;
import cn.yusei.jvm.classfile.constantpool.StringConstantInfo;
import cn.yusei.jvm.classfile.constantpool.UTF8ConstantInfo;

public class ClassMetadataTest {

	private static final String MAGIC_NUMBER = "CAFEBABE";
	private ClassMetadata helloMetaData;
	private ClassMetadata classTestMetaData;
	private ClassMetadata addMetaData;

	{
		try {
			helloMetaData = getClassMetadata("/cn/yusei/Hello.class");
			classTestMetaData = getClassMetadata("/cn/yusei/ClassTest.class");
			addMetaData = getClassMetadata("/cn/yusei/Add.class");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static ClassMetadata getClassMetadata(String url) throws IOException {
		byte[] data = getClassFileBytes(url);
		return new ClassMetadata(data);
	}

	private static byte[] getClassFileBytes(String url) throws IOException {
		try (InputStream ins = ClassMetadataTest.class.getResourceAsStream(url)) {
			byte[] data = new byte[ins.available()];
			ins.read(data);
			return data;
		}
	}

	@Test
	public void parseMagicNumber() throws IOException {
		byte[] data = getClassFileBytes("/cn/yusei/Hello.class");
		assertEquals(MAGIC_NUMBER, helloMetaData.getMagicNumber());
		data[0] += 1;
		try {
			new ClassMetadata(data);
		} catch (ClassFormatError e) {
			return;
		}
		fail();
	}

	@Test
	public void parseVersion() throws IOException {
		byte[] data = getClassFileBytes("/cn/yusei/Hello.class");
		assertEquals(52, helloMetaData.getMajorVersion());
		assertEquals(0, helloMetaData.getMinorVersion());
		data[7] = 60;
		try {
			new ClassMetadata(data);
		} catch (UnsupportedClassVersionError e) {
			return;
		}
		fail();
	}

	@Test
	public void parseConstantPoolSize() {
		assertEquals(59, classTestMetaData.getConstanPool().getSize());
		assertEquals(29, helloMetaData.getConstanPool().getSize());
		assertEquals(16, addMetaData.getConstanPool().getSize());
	}

	@Test
	public void parseConstantInfo() {
		ConstantPool pool = classTestMetaData.getConstanPool();
		MethodrefConstantInfo info1 = pool.getMethodrefInfo(1);
		assertEquals(6, info1.getClassIndex());
		assertEquals(44, info1.getNameAndTypeIndex());
		FieldrefConstantInfo info2 = pool.getFieldrefInfo(2);
		assertEquals(45, info2.getClassIndex());
		assertEquals(46, info2.getNameAndTypeIndex());
		StringConstantInfo info3 = pool.getStringInfo(3);
		assertEquals(47, info3.getIndex());
		ClassConstantInfo info5 = pool.getClassInfo(5);
		assertEquals(50, info5.getNameIndex());
		UTF8ConstantInfo info7 = pool.getUTF8(7);
		assertEquals("FLAG", info7.getValue());
		IntegerConstantInfo info10 = pool.getIntegerInfo(10);
		assertEquals(Integer.valueOf(1), info10.getValue());
		LongConstantInfo info25 = pool.getLongInfo(25);
		assertEquals(Long.valueOf(12345678901L), info25.getValue());
		FloatConstantInfo info29 = pool.getFloatInfo(29);
		assertEquals(Float.valueOf(Float.intBitsToFloat(0x4048F5C3)), info29.getValue());
		DoubleConstantInfo info32 = pool.getDoubleInfo(32);
		assertEquals(Double.valueOf(Double.longBitsToDouble(0x4005BF0995AAF790L)), info32.getValue());
		NameAndTypeConstantInfo info44 = pool.getNameAndType(44);
		assertEquals(34, info44.getNameIndex());
		assertEquals(35, info44.getDescriptorIndex());
	}

	@Test
	public void parseAccessMask() {
		assertFalse(classTestMetaData.getAccessMask().isInterface());
		assertFalse(classTestMetaData.getAccessMask().isEnum());
		assertFalse(classTestMetaData.getAccessMask().isAnnotation());
		assertFalse(classTestMetaData.getAccessMask().isFinal());
		assertFalse(classTestMetaData.getAccessMask().isAbstract());
		assertTrue(classTestMetaData.getAccessMask().isPublic());
	}

	@Test
	public void parseClassAndSuperClassIndex() {
		assertEquals(5, helloMetaData.getClassIndex());
		assertEquals(6, helloMetaData.getSuperClassIndex());
		assertEquals(5, classTestMetaData.getClassIndex());
		assertEquals(6, classTestMetaData.getSuperClassIndex());
		assertEquals(2, addMetaData.getClassIndex());
		assertEquals(3, addMetaData.getSuperClassIndex());
	}

	@Test
	public void parseInterfacesIndex() {
		assertEquals(0, helloMetaData.getInterfaceCount());
		assertEquals(0, classTestMetaData.getInterfaceCount());
		assertEquals(0, addMetaData.getInterfaceCount());
	}

	@Test
	public void parseFieldMembers() {
		assertEquals(8, classTestMetaData.getFieldMemberCount());
		assertEquals(0, helloMetaData.getFieldMemberCount());
		assertEquals(0, addMetaData.getFieldMemberCount());
	}

	@Test
	public void parseMethodMembers() {
		assertEquals(2, classTestMetaData.getMethodMemberCount());
		assertEquals(2, helloMetaData.getMethodMemberCount());
		assertEquals(3, addMetaData.getMethodMemberCount());
	}

	@Test
	public void parseAttributes() {
		assertEquals(1, classTestMetaData.getAttributes().length);
		assertEquals(1, helloMetaData.getAttributes().length);
		assertEquals(1, addMetaData.getAttributes().length);
	}

}
