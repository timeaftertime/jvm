package cn.yusei.jvm.runtimespace.stack;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ OperandStackTest.class, LocalVarsTableTest.class, JVMStackTest.class, })
public class RuntimeStackTestSuite {

}
