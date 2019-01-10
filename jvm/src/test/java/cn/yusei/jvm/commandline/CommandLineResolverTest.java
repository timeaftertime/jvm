package cn.yusei.jvm.commandline;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import cn.yusei.jvm.commandline.CommandLineResolver;

public class CommandLineResolverTest {

	private CommandLineResolver resolver = new CommandLineResolver();
	
	@Test
	public void versionCommand() {
		String[] args = {"-version"};
		CommandLine command = resolver.parse(args);
		assertTrue(command.isVersionCmd());
	}
	
	@Test
	public void helpCommand() {
		CommandLine cmd1 = resolver.parse(new String[]{"-help"});
		CommandLine cmd2 = resolver.parse(new String[]{"-?"});
		assertTrue(cmd1.isHelpCmd());
		assertTrue(cmd2.isHelpCmd());
	}
	
	@Test
	public void justUseFormerCommand() {
		CommandLine cmd1 = resolver.parse(new String[] {"-version", "-?"});
		CommandLine cmd2 = resolver.parse(new String[] {"-?", "-version"});
		assertTrue(cmd1.isVersionCmd());
		assertFalse(cmd1.isHelpCmd());
		assertTrue(cmd2.isHelpCmd());
		assertFalse(cmd2.isVersionCmd());
	}
	
	@Test
	public void specificClassPath() {
		CommandLine cmd1 = resolver.parse(new String[]{"-cp", "d:/newDir;d:/"});
		CommandLine cmd2 = resolver.parse(new String[] {"-classpath", "d:/newDir;d:/"});
		assertTrue(Arrays.equals(cmd1.getClassPath(), new String[] {"d:/newDir", "d:/"}));
		assertTrue(Arrays.equals(cmd2.getClassPath(), new String[] {"d:/newDir", "d:/"}));
	}
	
	@Test
	public void markErrorMessage() {
		CommandLine cmd1 = resolver.parse(new String[] {"-cp"});
		CommandLine cmd2 = resolver.parse(new String[] {"-classpath"});
		CommandLine cmd3 = resolver.parse(new String[] {"-aaa", "bbb"});
		assertTrue(cmd1.isError());
		assertTrue(cmd2.isError());
		assertTrue(cmd3.isError());
		assertEquals(CommandLineResolver.LOST_CLASSPATH + "-cp", cmd1.getErrorMessages().get(0));
		assertEquals(CommandLineResolver.LOST_CLASSPATH + "-classpath", cmd2.getErrorMessages().get(0));
		assertEquals(CommandLineResolver.UNKNOWN_OPTION + "-aaa", cmd3.getErrorMessages().get(0));
	}
}
