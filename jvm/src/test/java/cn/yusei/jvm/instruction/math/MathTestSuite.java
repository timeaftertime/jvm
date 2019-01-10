package cn.yusei.jvm.instruction.math;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AddInstructionsTest.class, SubInstructionsTest.class, MulInstructionsTest.class,
		DivInstructionsTest.class, RemInstructionsTest.class, ShInstructionsTest.class, AndInstructionsTest.class,
		OrInstructionsTest.class, XorInstructionsTest.class, IINCTest.class })
public class MathTestSuite {

}
