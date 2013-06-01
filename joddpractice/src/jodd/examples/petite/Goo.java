// Copyright (c) 2003-2012, Jodd Team (jodd.org). All Rights Reserved.

package jodd.examples.petite;

import jodd.petite.meta.PetiteBean;

@PetiteBean
public class Goo {

	public void goo() {
		System.out.println("goo! " + toString());
	}
}
