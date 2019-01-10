package cn.yusei.jvm.classfile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class ClassReader {

	private static final String CLASS_FILE_SUFFIX = ".class"; 
	
	private String[] classPaths;
	
	public ClassReader(String[] classPaths) {
		List<String> paths = Arrays.asList(classPaths);
		HashSet<String> pathSet = new HashSet<String>();
		for(String path : paths) {
			pathSet.add(ensureEndsWithSeparator(toSystemPath(path)));
		}
		this.classPaths = pathSet.toArray(new String[0]);
	}

	private String toSystemPath(String path) {
		return path
				.replace("/", File.separator)
				.replace("\\", File.separator);
	}
	
	private String ensureEndsWithSeparator(String path) {
		return path.endsWith(File.separator) ? path : path + File.separator;
	}
	
	public byte[] readClass(String className) throws ClassNotFoundException {
		String classFileName = classNameToPath(className) + CLASS_FILE_SUFFIX;
		for(String path : classPaths) {
			File file = new File(path + classFileName);
			if(!file.exists())
				continue;
			try (FileInputStream ins = new FileInputStream(file)) {
				byte[] data = new byte[ins.available()];
				ins.read(data);
				return data;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		throw new ClassNotFoundException();
	}
	
	private String classNameToPath(String className) {
		return className.replace(".", File.separator);
	}

}
