package cn.yusei.jvm.instruction.cast;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ I2InstructionsTest.class, L2InstructionsTest.class, F2InstructionsTest.class,
		D2InstructionsTest.class })
public class CastTestSuite {

}
