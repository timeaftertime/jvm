package cn.yusei.jvm.instruction;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import cn.yusei.jvm.instruction.cast.CastTestSuite;
import cn.yusei.jvm.instruction.compare.CompareTestSuite;
import cn.yusei.jvm.instruction.constant.ConstantTestSuite;
import cn.yusei.jvm.instruction.control.ControlTestSuite;
import cn.yusei.jvm.instruction.extend.ExtendTestSuite;
import cn.yusei.jvm.instruction.load.LoadTestSuite;
import cn.yusei.jvm.instruction.math.MathTestSuite;
import cn.yusei.jvm.instruction.stack.StackTestSuite;
import cn.yusei.jvm.instruction.store.StoreTestSuite;

@RunWith(Suite.class)
@SuiteClasses({ ByteCodeReaderTest.class, ConstantTestSuite.class, LoadTestSuite.class, StoreTestSuite.class,
		StackTestSuite.class, MathTestSuite.class, CastTestSuite.class, CompareTestSuite.class, ControlTestSuite.class,
		ExtendTestSuite.class })
public class InstructionTestSuite {

}
