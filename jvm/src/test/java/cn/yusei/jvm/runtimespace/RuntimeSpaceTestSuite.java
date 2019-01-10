package cn.yusei.jvm.runtimespace;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ SlotsOperatorTest.class, OperandStackTest.class, LocalVarsTableTest.class, JVMStackTest.class,
		ThreadSpaceTest.class })
public class RuntimeSpaceTestSuite {

}
