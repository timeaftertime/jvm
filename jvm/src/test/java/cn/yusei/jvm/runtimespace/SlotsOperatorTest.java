package cn.yusei.jvm.runtimespace;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class SlotsOperatorTest {

	private static final int SLOTS_CAPACITY = 10;
	private SlotsOperator slots;
	
	@Before
	public void setUp() {
		slots = new SlotsOperator(SLOTS_CAPACITY) {};
	}
	
	@Test
	public void setAndGetSlots() {
		int index = 0;
		int var1 = 10;
		long var2 = 123456789123L;
		float var3 = 1234.56f;
		double var4 = 98765432.1;
		String var5 = "SlotsOperator";
		slots.setInt(index, var1);
		index++;
		slots.setLong(index, var2);
		index += 2;
		slots.setFloat(index, var3);
		index++;
		slots.setDouble(index, var4);
		index += 2;
		slots.setRef(index, var5);
		index++;
		double delta = 0.01;
		index--;
		assertEquals(var5, slots.getRef(index));
		index -= 2;
		assertEquals(var4, slots.getDouble(index), delta);
		index--;
		assertEquals(var3, slots.getFloat(index), delta);
		index -= 2;
		assertEquals(var2, slots.getLong(index));
		index--;
		assertEquals(var1, slots.getInt(index));
	}
	
	@Test
	public void illegalIndexAccessError() {
		try {
			slots.getInt(1);
		}
		catch (SlotNotSetException e1) {
			try {
				slots.getInt(-1);
			}
			catch (SlotIndexOutOfBoundsException e2) {
				try {
					slots.getInt(SLOTS_CAPACITY);
				}
				catch (SlotIndexOutOfBoundsException e3) {
					return;
				}
			}
		}
		fail();
	}
	
	@Test
	public void setAndGet() {
		Slot slot = new Slot(1, this);
		slots.setSlot(0, slot);
		assertSame(slot, slots.getSlot(0));
	}
	
}
