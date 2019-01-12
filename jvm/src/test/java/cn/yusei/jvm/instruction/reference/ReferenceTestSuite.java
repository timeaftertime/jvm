package cn.yusei.jvm.instruction.reference;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ NewTest.class, PutStaticTest.class, GetStaticTest.class, PutFieldTest.class, GetFieldTest.class,
		InstanceofTest.class, CheckCastTest.class, LdcInstructionsTest.class })
public class ReferenceTestSuite {

}
