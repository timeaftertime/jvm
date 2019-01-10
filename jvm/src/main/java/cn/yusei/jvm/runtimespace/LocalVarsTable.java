package cn.yusei.jvm.runtimespace;

public class LocalVarsTable extends SlotsOperator {

	public LocalVarsTable(int capacity) {
		super(capacity);
	}

	@Override
	public void setInt(int index, int value) {
		super.setInt(index, value);
	}

	@Override
	public void setLong(int index, long value) {
		super.setLong(index, value);
	}

	@Override
	public void setFloat(int index, float value) {
		super.setFloat(index, value);
	}

	@Override
	public void setDouble(int index, double value) {
		super.setDouble(index, value);
	}

	@Override
	public void setRef(int index, Object ref) {
		super.setRef(index, ref);
	}

	@Override
	public int getInt(int index) {
		return super.getInt(index);
	}

	@Override
	public long getLong(int index) {
		return super.getLong(index);
	}

	@Override
	public float getFloat(int index) {
		return super.getFloat(index);
	}

	@Override
	public double getDouble(int index) {
		return super.getDouble(index);
	}

	@Override
	public Object getRef(int index) {
		return super.getRef(index);
	}
}
