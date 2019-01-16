package cn.yusei.jvm;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

public class ObjectRefTest {

	@Test
	public void getString() throws ClassNotFoundException, IOException {
		ClassInfoLoader loader = new ClassInfoLoader();
		ObjectRef ref = loader.loadClass("cn.yusei.StringStaticVar").newObjectRef();
		Field field = ref.getClassInfo().getField("hello", "Ljava/lang/String;");
		assertEquals("HELLO!", ref.getClassInfo().getStaticVars().getString(field.getSlotId()));
	}
	
}
