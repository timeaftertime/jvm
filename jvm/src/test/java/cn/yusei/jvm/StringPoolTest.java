package cn.yusei.jvm;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

import java.io.IOException;

import org.junit.Test;

public class StringPoolTest {

	@Test
	public void loadString() throws ClassNotFoundException, IOException {
		ClassInfoLoader loader = new ClassInfoLoader();
		ObjectRef ref = StringPool.getString(loader, "abc");
		assertNotNull(ref);
		assertSame(ref, StringPool.getString(loader, "abc"));
		assertNotSame(ref, StringPool.getString(loader, "Abc"));
		Field field = ref.getClassInfo().getField("value", "[C");
		assertArrayEquals("abc".toCharArray(), ref.getMembers().getRef(field.getSlotId()).chars());
	}

}
