package cn.yusei.jvm.instruction.stack;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ PopInstructionsTest.class, DupInstructionsTest.class, SWAPTest.class })
public class StackTestSuite {

}
