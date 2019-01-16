package cn.yusei.jvm.classfile.member.attribute;

public class AttributeFactory {

	private AttributeFactory() {
	}

	public static Attribute createAttribute(int attrNameIndex, String attrName, int attrLen) {
		switch (attrName) {
		case "Code":
			return new CodeAttribute(attrNameIndex, attrLen);
		case "ConstantValue":
			return new ConstantValueAttribute(attrNameIndex, attrLen);
		case "Deprecated":
			return new DeprecatedAttribute(attrNameIndex, attrLen);
		case "Exceptions":
			return new ExceptionsAttribute(attrNameIndex, attrLen);
		case "LineNumberTable":
			return new LineNumberTableAttribute(attrNameIndex, attrLen);
		case "LocalVariableTable":
			return new LocalVariableTableAttribute(attrNameIndex, attrLen);
		case "SourceFile":
			return new SourceFileAttribute(attrNameIndex, attrLen);
		case "Synthetic":
			return new SyntheticAttribute(attrNameIndex, attrLen);
		// TODO Java 1.8 一共有 23 种属性，这里只解析以上 8 种
		default:
			return new UnparsedAttribute(attrNameIndex, attrLen);
		}
	}

}
