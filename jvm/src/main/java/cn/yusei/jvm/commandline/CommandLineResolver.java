package cn.yusei.jvm.commandline;

public class CommandLineResolver {

	public static final String VERSION = "-version";
	public static final String HELP = "-help";
	public static final String QUESTION_MARK = "-?";
	public static final String CLASSPATH = "-classpath";
	public static final String CLASSPATH_ABR = "-cp";
	
	public static final String LOST_CLASSPATH = "Must specific at least one path : ";
	public static final String UNKNOWN_OPTION = "Unknown option : ";
	
	public CommandLine parse(String[] args) {
		CommandLine cmd = new CommandLine();
		int len = args.length;
		for(int i=0; i<len; i++) {
			switch(args[i]) {
				case VERSION:
					cmd.setVersionCmd(true);
					return cmd;
				case HELP:
				case QUESTION_MARK:
					cmd.setHelpCmd(true);
					return cmd;
				case CLASSPATH:
				case CLASSPATH_ABR:
					if(i == len - 1) {
						cmd.markError(LOST_CLASSPATH + args[i]);
						break;
					}
					i++;
					cmd.setClassPath(args[i].split(";"));
					break;
				default:
					cmd.markError(UNKNOWN_OPTION + args[i]);
					return cmd;
			}
		}
		return cmd;
	}

}
