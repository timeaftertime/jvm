package cn.yusei.jvm.instruction.compare;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CmpInstructionsTest.class, IfInstructionsTest.class, IfIcmpInstructionsTest.class,
		IfAcmpInstructionsTest.class })
public class CompareTestSuite {

}
