package cn.yusei.jvm;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ JVMTest.class, ClassInfoTest.class, ClassInfoLoaderTest.class, InterpreterTest.class, MethodTest.class,
		ObjectRefTest.class, StringPoolTest.class })

public class JVMTestSuite {

}
