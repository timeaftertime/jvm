package cn.yusei.jvm;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StringPool {

	private static final Map<String, ObjectRef> internedStrings = new ConcurrentHashMap<>();

	public static ObjectRef getString(ClassInfoLoader loader, String string) {
		if (internedStrings.containsKey(string))
			return internedStrings.get(string);
		ClassInfo strClass;
		try {
			strClass = loader.loadClass("java.lang.String");
		} catch (ClassNotFoundException | IOException e) {
			throw new RuntimeException("获取字符串对象失败：" + string, e);
		}
		ObjectRef ref = strClass.newObjectRef();
		Field field = strClass.getField("value", "[C");
		ObjectRef charArray;
		try {
			charArray = loader.loadClass("[C").newObjectRefAsArray(string.length());
		} catch (ClassNotFoundException | IOException e) {
			throw new RuntimeException("获取字符串对象失败：" + string, e);
		}
		System.arraycopy(string.toCharArray(), 0, charArray.chars(), 0, string.length());
		ref.getMembers().setRef(field.getSlotId(), charArray);
		internedStrings.put(string, ref);
		return ref;
	}

}
