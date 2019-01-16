package cn.yusei.jvm.classfile.constantpool;

public class ConstantInfoFactory {

	private ConstantInfoFactory() {}
	
	public static ConstantInfo createConstantInfo(int tag) {
		switch(tag) {
		case ConstantTag.UTF8:
			return new UTF8ConstantInfo();
		case ConstantTag.INTEGER:
			return new IntegerConstantInfo();
		case ConstantTag.FLOAT:
			return new FloatConstantInfo();
		case ConstantTag.LONG:
			return new LongConstantInfo();
		case ConstantTag.DOUBLE:
			return new DoubleConstantInfo();
		case ConstantTag.CLASS:
			return new ClassConstantInfo();
		case ConstantTag.STRING:
			return new StringConstantInfo();
		case ConstantTag.FIELDREF:
			return new FieldrefConstantInfo();
		case ConstantTag.METHODREF:
			return new MethodrefConstantInfo();
		case ConstantTag.INTERFACE_METHODREF:
			return new InterfaceMethodrefConstantInfo();
		case ConstantTag.NAME_AND_TYPE:
			return new NameAndTypeConstantInfo();
		// 以下三个为 Java7 为支持 invoke_dynamic 而加入的，目前只是读取数据，暂时没有用到
		case ConstantTag.METHOD_HANDLE:
			return new MethodHandleConstantInfo();
		case ConstantTag.METHOD_TYPE:
			return new MethodTypeConstantInfo();
		case ConstantTag.INVOKE_DYNAMIC:
			return new InvokeDynamicConstantInfo();
		default:
			throw new IllegalArgumentException("常量类型不合法： " + tag);
		}
	}
	
}
