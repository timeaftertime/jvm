package cn.yusei.jvm.classfile;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class ClassReader {

	private static final String CLASS_FILE_SUFFIX = ".class";

	private String[] classPaths;

	public ClassReader(String[] classPaths) {
		List<String> paths = Arrays.asList(classPaths);
		HashSet<String> pathSet = new HashSet<String>();
		for (String path : paths) {
			pathSet.add(ensureNoSeparatorEnds(toSystemPath(path)));
		}
		this.classPaths = pathSet.toArray(new String[0]);
	}

	private String toSystemPath(String path) {
		return path.replace("/", File.separator).replace("\\", File.separator);
	}

	private String ensureNoSeparatorEnds(String path) {
		return path.endsWith(File.separator) ? path.substring(0, path.length()) : path;
	}

	public byte[] readClass(String className) throws ClassNotFoundException, IOException {
		byte[] data = null;
		String classFileName = classNameToPath(className) + CLASS_FILE_SUFFIX;
		for (String path : classPaths) {
			if (path.endsWith(".jar"))
				data = readFromJarFile(path, classFileName);
			else
				data = readNormally(path, classFileName);
			if (data != null)
				return data;
		}
		throw new ClassNotFoundException(className);
	}

	private byte[] readFromJarFile(String path, String className) {
		InputStream ins = null;
		try {
			URL url = new URL(
					"jar:file:" + path.replace(File.separator, "/") + "!/" + className.replace(File.separator, "/"));
			ins = url.openConnection().getInputStream();
			ins = new BufferedInputStream(ins);
			byte[] data = new byte[ins.available()];
			ins.read(data, 0, ins.available());
			return data;
		} catch (IOException e) {
		} finally {
			if (ins != null)
				try {
					ins.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return null;
	}

	private byte[] readNormally(String path, String classFileName) {
		File file = new File(path + File.separator + classFileName);
		if (!file.exists())
			return null;
		try (FileInputStream ins = new FileInputStream(file)) {
			byte[] data = new byte[ins.available()];
			ins.read(data);
			return data;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private String classNameToPath(String className) {
		return className.replace(".", File.separator);
	}

}
