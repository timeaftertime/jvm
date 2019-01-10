package cn.yusei.jvm;

import cn.yusei.jvm.commandline.CommandLineResolver;
import cn.yusei.jvm.commandline.CommandLine;

public class JVM {

	public static final String VERSION = "JVM 1.0";
	
	public static final String HELP = "usage: jvm option";

	public static void main(String[] args) {
		CommandLine cmd = new CommandLineResolver().parse(args);
		if(cmd.isError()) {
			for(String message : cmd.getErrorMessages()) {
				System.out.println(message);
				return;
			}
		}
		if(cmd.isVersionCmd()) {
			System.out.println(VERSION);
			return;
		}
		if(cmd.isHelpCmd()) {
			System.out.println(HELP);
			return;
		}
	}
	
}
