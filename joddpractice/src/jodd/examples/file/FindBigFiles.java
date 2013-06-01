// Copyright (c) 2003-2012, Jodd Team (jodd.org). All Rights Reserved.

package jodd.examples.file;

import jodd.io.findfile.FindFile;

import java.io.File;

/**
 * Find big files on C disk.
 */
public class FindBigFiles {
	public static void main(String[] args) {

		FindFile ff = new FindFile()
			.setRecursive(true)
			.setIncludeDirs(true)
			.searchPath("c:/");

		File f;
		while ((f = ff.nextFile()) != null) {
			if (f.isFile()) {
				double fileLenInMb = f.length() / 1024.0 / 1024;
				if (fileLenInMb > 100) {
					System.out.println(f.getAbsolutePath() + "=>" + fileLenInMb + "MB");
				}
			}
		}
		System.out.println("search is over");
	}
}
