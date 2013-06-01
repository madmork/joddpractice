// Copyright (c) 2003-2012, Jodd Team (jodd.org). All Rights Reserved.

package jodd.examples.file;

import jodd.io.findfile.FindFile;
import jodd.io.findfile.WildcardFindFile;
import jodd.io.filter.WildcardFileFilter;
import jodd.util.SystemUtil;

import java.io.File;
import java.util.Iterator;

/**
 * Find files.
 */
public class ff {
	public static void main(String args[]) {
		System.out.println("---start---");

		System.out.println("searching temp folder");

		FindFile fs = new FindFile() {
			@Override
			protected boolean acceptFile(File file) {
				System.out.println("> " + file.getName());
				return true;
			}
		};
		fs.setIncludeDirs(true);
		fs.setRecursive(true);
		fs.setIncludeFiles(false);
		fs.searchPath(SystemUtil.getTempDir());
		fs.scan();

		System.out.println("\n\n\nsearching classpath");

		// ----------------------------------------------------------------

		FindFile ff = new WildcardFindFile("**")
				.setRecursive(true)
				.setIncludeDirs(true)
				.searchPath(SystemUtil.getClassPath());

		int i = 1;

		File f;
		while ((f = ff.nextFile()) != null) {
			if (f.isDirectory() == true) {
				System.out.println(i + ". >" + f.getName());
			} else {
				System.out.println(i + ". " + f.getName());
			}
			i++;
		}

		// ----------------------------------------------------------------

		System.out.println("---end---");
	}
}
