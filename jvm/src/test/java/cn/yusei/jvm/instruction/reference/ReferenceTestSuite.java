package cn.yusei.jvm.instruction.reference;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import cn.yusei.jvm.instruction.constant.LdcInstructionsTest;

@RunWith(Suite.class)
@SuiteClasses({ NewTest.class, PutStaticTest.class, GetStaticTest.class, PutFieldTest.class, GetFieldTest.class,
		InstanceofTest.class, CheckCastTest.class, LdcInstructionsTest.class, InvokeInstructionsTest.class,
		NewArrayTest.class, AnewArrayTest.class, ArrayLengthTest.class })
public class ReferenceTestSuite {

}
