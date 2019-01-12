package cn.yusei.jvm.runtimespace.method;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({  RTConstantPoolTest.class,
		SymRefTest.class, FieldRefTest.class })
public class MethodTestSuite {

}
