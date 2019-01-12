package cn.yusei.jvm.runtimespace.method;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import cn.yusei.jvm.ClassInfo;
import cn.yusei.jvm.classfile.ClassMetadata;
import cn.yusei.jvm.classfile.ClassReader;
import cn.yusei.jvm.classfile.constantpool.ConstantPool;
import cn.yusei.jvm.classfile.constantpool.ConstantTag;
import cn.yusei.jvm.classfile.constantpool.FieldrefConstantInfo;
import cn.yusei.jvm.classfile.constantpool.MethodrefConstantInfo;
import cn.yusei.jvm.classfile.constantpool.NameAndTypeConstantInfo;
import cn.yusei.jvm.runtimespace.method.RTConstantPool;

public class RTConstantPoolTest {

	private RTConstantPool pool;
	private ConstantPool constantPool;

	@Before
	public void setUp() throws ClassNotFoundException, IOException {
		String[] classPath = { RTConstantPool.class.getResource("/").getPath().substring(1) };
		ClassMetadata metadata = new ClassMetadata(new ClassReader(classPath).readClass("cn.yusei.ClassTest"));
		ClassInfo classInfo = new ClassInfo(null, metadata);
		constantPool = metadata.getConstanPool();
		pool = new RTConstantPool(constantPool, classInfo);
	}

	@Test
	public void testCopy() {
		double delta = 0;
		int poolSize = constantPool.getSize();
		for (int i = 1; i < poolSize; i++) {
			switch (constantPool.get(i).getTag()) {
			case ConstantTag.INTEGER:
				assertEquals(constantPool.getIntegerInfo(i).getValue() + 0, pool.getInt(i));
				break;
			case ConstantTag.LONG:
				assertEquals(constantPool.getLongInfo(i).getValue() + 0, pool.getLong(i));
				i++;
				break;
			case ConstantTag.FLOAT:
				assertEquals(constantPool.getFloatInfo(i).getValue(), pool.getFloat(i), delta);
				break;
			case ConstantTag.DOUBLE:
				assertEquals(constantPool.getDoubleInfo(i).getValue(), pool.getDouble(i), delta);
				i++;
				break;
			case ConstantTag.UTF8:
				assertEquals(constantPool.getUTF8(i).getValue(), pool.getString(i));
				break;
			case ConstantTag.STRING:
				assertEquals(constantPool.getStringInfo(i).getValue(), pool.getString(i));
				break;
			case ConstantTag.CLASS:
				assertEquals(constantPool.getUTF8(constantPool.getClassInfo(i).getNameIndex()).getValue().replace("/", "."),
						pool.getClassRef(i).getClassName());
				break;
			case ConstantTag.FIELDREF: {
				FieldrefConstantInfo fieldrefInfo = constantPool.getFieldrefInfo(i);
				int classIndex = fieldrefInfo.getClassIndex();
				String className = constantPool.getUTF8(constantPool.getClassInfo(classIndex).getNameIndex())
						.getValue();
				NameAndTypeConstantInfo nameAndType = constantPool.getNameAndType(fieldrefInfo.getNameAndTypeIndex());
				String name = constantPool.getUTF8(nameAndType.getNameIndex()).getValue();
				String descriptor = constantPool.getUTF8(nameAndType.getDescriptorIndex()).getValue();
				assertEquals(className.replace("/", "."), pool.getFieldRef(i).getClassName());
				assertEquals(name, pool.getFieldRef(i).getName());
				assertEquals(descriptor, pool.getFieldRef(i).getDescriptor());
				break;
			}
			case ConstantTag.METHODREF: {
				MethodrefConstantInfo methodrefInfo = constantPool.getMethodrefInfo(i);
				int classIndex = methodrefInfo.getClassIndex();
				String className = constantPool.getUTF8(constantPool.getClassInfo(classIndex).getNameIndex())
						.getValue();
				NameAndTypeConstantInfo nameAndType = constantPool.getNameAndType(methodrefInfo.getNameAndTypeIndex());
				String name = constantPool.getUTF8(nameAndType.getNameIndex()).getValue();
				String descriptor = constantPool.getUTF8(nameAndType.getDescriptorIndex()).getValue();
				assertEquals(className.replace("/", "."), pool.getMethodRef(i).getClassName());
				assertEquals(name, pool.getMethodRef(i).getName());
				assertEquals(descriptor, pool.getMethodRef(i).getDescriptor());
				break;
			}
			}
		}
	}
}
