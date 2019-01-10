package cn.yusei.jvm.classfile;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ClassMetadataTest.class, ClassReaderTest.class })
public class ClassFileTestSuite {

}
