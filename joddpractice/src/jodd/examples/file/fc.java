// Copyright (c) 2003-2012, Jodd Team (jodd.org). All Rights Reserved.

package jodd.examples.file;

import jodd.io.findfile.ClassScanner;
import jodd.io.StreamUtil;

import java.io.InputStream;

/**
 * Find classes.
 */
public class fc {

	public static void main(String args[]) throws Exception {
		System.out.println("start");

		ClassScanner cs = new ClassScanner() {
			@Override
			protected void onEntry(EntryData entryData) throws Exception {
				InputStream inputStream = entryData.openInputStream();
				byte[] bytes = StreamUtil.readAvailableBytes(inputStream);
				System.out.println("---> " + entryData.getName() + ':' + entryData.getArchiveName() + "\t\t" + bytes.length);
			}
		};
		cs.setIncludeResources(true);
		cs.setIgnoreException(true);

		// define scan path
		cs.scan("foo.jar", "E:\\src\\eclipse_java\\s1java\\bin\\cn\\s1java\\ch1");

		System.out.println("end");
	}

}
