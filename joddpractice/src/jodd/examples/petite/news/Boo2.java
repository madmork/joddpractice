// Copyright (c) 2003-2012, Jodd Team (jodd.org). All Rights Reserved.

package jodd.examples.petite.news;

import jodd.petite.meta.PetiteBean;
import jodd.examples.petite.Boo;

@PetiteBean("boo")
public class Boo2 extends Boo {

	@Override
	public void boo() {
		goo.goo();
		System.out.println("!!!boo2 " + toString());
	}
}
