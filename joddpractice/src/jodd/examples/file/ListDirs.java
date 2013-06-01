package jodd.examples.file;

import jodd.io.findfile.FindFile;

import java.io.File;

/**
 * List directories and skip files.
 */
public class ListDirs {

	public static void main(String[] args) {

		FindFile ff = new FindFile()
			.setRecursive(false)
			.setIncludeDirs(true)
			.setIncludeFiles(false)
			.searchPath("c:/");

		File f;
		while ((f = ff.nextFile()) != null) {
			System.out.println(f.getName());
		}
	}
}
