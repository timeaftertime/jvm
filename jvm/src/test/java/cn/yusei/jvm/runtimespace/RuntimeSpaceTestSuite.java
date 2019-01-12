package cn.yusei.jvm.runtimespace;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import cn.yusei.jvm.runtimespace.method.MethodTestSuite;
import cn.yusei.jvm.runtimespace.stack.RuntimeStackTestSuite;

@RunWith(Suite.class)
@SuiteClasses({ ThreadSpaceTest.class, RuntimeStackTestSuite.class, MethodTestSuite.class })
public class RuntimeSpaceTestSuite {

}
