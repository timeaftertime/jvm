package cn.yusei.jvm;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import cn.yusei.jvm.classfile.ClassFileTestSuite;
import cn.yusei.jvm.commandline.CommandLineTestSuite;
import cn.yusei.jvm.instruction.InstructionTestSuite;
import cn.yusei.jvm.runtimespace.RuntimeSpaceTestSuite;

@RunWith(Suite.class)
@SuiteClasses({ JVMTest.class, CommandLineTestSuite.class, ClassFileTestSuite.class, RuntimeSpaceTestSuite.class,
		InstructionTestSuite.class, InterpreterTest.class })
public class AllTestSuite {

}
