// Copyright (c) 2003-2012, Jodd Team (jodd.org). All Rights Reserved.

package jodd.examples.jerry;

import jodd.io.FileUtil;
import jodd.io.NetUtil;
import jodd.jerry.Jerry;
import jodd.jerry.JerryFunction;
import jodd.util.SystemUtil;

import java.io.File;
import java.io.IOException;

/**
 * Downloads content from allmusic.com and list new releases.
 */
public class AllMusicNewReleases {

	public static void main(String[] args) throws IOException {
		File file = new File(SystemUtil.getTempDir(), "allmusic.html");
		NetUtil.downloadFile("http://allmusic.com", file);

		Jerry doc = Jerry.jerry(FileUtil.readString(file));

		doc.$("div#recent-releases ul li a").each(new JerryFunction() {
			public boolean onNode(Jerry $this, int index) {
				System.out.println("-----");
				System.out.println($this.attr("title"));
				return true;
			}
		});
	}
}
