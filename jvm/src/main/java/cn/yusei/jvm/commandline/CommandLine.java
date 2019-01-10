package cn.yusei.jvm.commandline;

import java.util.ArrayList;
import java.util.List;

public class CommandLine {

	private boolean versionCmd = false;
	private boolean helpCmd = false;

	private String[] classPath;
	
	private boolean error = false;
	
	private List<String> errorMessages = new ArrayList<>();

	public boolean isVersionCmd() {
		return versionCmd;
	}

	public void setVersionCmd(boolean versionCmd) {
		this.versionCmd = versionCmd;
	}

	public boolean isHelpCmd() {
		return helpCmd;
	}

	public void setHelpCmd(boolean helpCmd) {
		this.helpCmd = helpCmd;
	}

	public void setClassPath(String[] classPath) {
		this.classPath = classPath;
	}

	public String[] getClassPath() {
		return classPath;
	}
	
	public void markError(String message) {
		this.error = true;
		this.errorMessages.add(message);
	}
	
	public boolean isError() {
		return this.error;
	}

	public  List<String> getErrorMessages() {
		return new ArrayList<String>(errorMessages);
	}
	
}
