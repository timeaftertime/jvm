package cn.yusei.jvm.classfile;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import cn.yusei.jvm.classfile.constantpool.ConstantPool;
import cn.yusei.jvm.classfile.member.ClassMember;
import cn.yusei.jvm.classfile.member.ClassMemberUtil;
import cn.yusei.jvm.classfile.member.attribute.Attribute;

public class ClassMetadata {

	private static final String MAGIC_NUMBER = "CAFEBABE";
	private int majorVersion;
	private int minorVersion;
	private ConstantPool constantPool;
	private AccessMask accessMask;
	private int classIndex;
	private int superClassIndex;
	private int[] interfacesIndex;
	private ClassMember fieldMembers[];
	private ClassMember methodMembers[];
	private Attribute[] attributes;

	public ClassMetadata(byte[] classData) throws IOException {
		try (DataInputStream data = new DataInputStream(new ByteArrayInputStream(classData))) {
			// 4 个字节魔数
			readAndCheckMagicNumber(data);
			// 2个字节次版本号和 2 个字节主版本号，都为无符号类型
			readAndCheckClassVersion(data);
			// 2 个字节无符号的常量池大小（值为 n），接着 n - 1 个常量
			readAndParseConstantPool(data);
			// 2 个字节类访问标志，表名 class 文件是类还是接口，访问级别是 public 还是 private
			readAccessMask(data);
			// 2 个字节类索引号
			// 2 个字节超类索引号，都为无符号类型
			readClassAndSuperClassIndex(data);
			// 2 个字节无符号的接口索引数量 n ，接着 n 个 2 个字节的接口索引号
			readInterfacesIndex(data);
			// 2 个字节无符号的字段成员数量 n，接着 n 个成员信息
			readFieldMembers(data);
			// 2 个字节无符号的方法成员数量 n，接着 n 个成员信息
			readMethodMembers(data);
			// 2 个字节无符号的属性数量 n ，接着 n 个属性信息
			readAttributes(data);
		}
	}

	private void readAndCheckMagicNumber(DataInputStream data) throws IOException {
		for (int i = 0; i < 4; i++) {
			if (data.readUnsignedByte() != Integer.valueOf(MAGIC_NUMBER.substring(2 * i, 2 * i + 2), 16))
				throw new ClassFormatError();
		}
	}

	private void readAndCheckClassVersion(DataInputStream data) throws IOException {
		int minorVersion = data.readUnsignedShort();
		int majorVersion = data.readUnsignedShort();
		switch (majorVersion) {
		case 45:
			break;
		case 46:
		case 47:
		case 48:
		case 49:
		case 50:
		case 51:
		case 52:
			if (minorVersion != 0)
				throw new UnsupportedClassVersionError();
			break;
		default:
			throw new UnsupportedClassVersionError();
		}
		this.majorVersion = majorVersion;
		this.minorVersion = minorVersion;
	}

	private void readAccessMask(DataInputStream data) throws IOException {
		this.accessMask = new AccessMask(data.readUnsignedShort());
	}

	public void readAndParseConstantPool(DataInputStream data) throws IOException {
		constantPool = new ConstantPool(data);
	}

	private void readClassAndSuperClassIndex(DataInputStream data) throws IOException {
		classIndex = data.readUnsignedShort();
		superClassIndex = data.readUnsignedShort();
	}

	private void readInterfacesIndex(DataInputStream data) throws IOException {
		int interfaceCount = data.readUnsignedShort();
		interfacesIndex = new int[interfaceCount];
		for (int i = 0; i < interfaceCount; i++) {
			interfacesIndex[i] = data.readUnsignedShort();
		}
	}

	private void readFieldMembers(DataInputStream data) throws IOException {
		fieldMembers = readClassMembers(data);
	}

	private void readMethodMembers(DataInputStream data) throws IOException {
		methodMembers = readClassMembers(data);
	}

	private ClassMember[] readClassMembers(DataInputStream data) throws IOException {
		int memberCount = data.readUnsignedShort();
		ClassMember[] members = new ClassMember[memberCount];
		for (int i = 0; i < memberCount; i++) {
			members[i] = readClassMember(data);
		}
		return members;
	}

	private ClassMember readClassMember(DataInputStream data) throws IOException {
		return new ClassMember(data, constantPool);
	}

	private void readAttributes(DataInputStream data) throws IOException {
		attributes = ClassMemberUtil.readAttributes(data, constantPool);
	}

	public String getMagicNumber() {
		return MAGIC_NUMBER;
	}

	public int getMajorVersion() {
		return majorVersion;
	}

	public int getMinorVersion() {
		return minorVersion;
	}

	public ConstantPool getConstanPool() {
		return constantPool;
	}

	public AccessMask getAccessMask() {
		return accessMask;
	}

	public int getClassIndex() {
		return classIndex;
	}

	public int getSuperClassIndex() {
		return superClassIndex;
	}

	public int getInterfaceCount() {
		return interfacesIndex.length;
	}

	public int getFieldMemberCount() {
		return fieldMembers.length;
	}

	public int getMethodMemberCount() {
		return methodMembers.length;
	}

	public ClassMember getFieldClassMember(String name, String descriptor) {
		for (ClassMember member : fieldMembers) {
			if (constantPool.getUTF8(member.getNameIndex()).getValue().equals(name)
					&& constantPool.getUTF8(member.getDescriptorIndex()).getValue().equals(descriptor))
				return member;
		}
		return null;
	}

	public ClassMember getMethodClassMember(String name, String descriptor) {
		for (ClassMember member : methodMembers) {
			if (constantPool.getUTF8(member.getNameIndex()).getValue().equals(name)
					&& constantPool.getUTF8(member.getDescriptorIndex()).getValue().equals(descriptor))
				return member;
		}
		return null;
	}

	public int getAttributeCount() {
		return attributes.length;
	}

	public int getInterfaceIndex(int index) {
		return interfacesIndex[index];
	}

	public ClassMember getFieldClassMember(int index) {
		return fieldMembers[index];
	}

	public ClassMember getMethodClassMember(int index) {
		return methodMembers[index];
	}

}
