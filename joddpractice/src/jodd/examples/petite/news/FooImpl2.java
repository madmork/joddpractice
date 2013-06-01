// Copyright (c) 2003-2012, Jodd Team (jodd.org). All Rights Reserved.

package jodd.examples.petite.news;

import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;
import jodd.examples.petite.Foo;
import jodd.examples.petite.Boo;

@PetiteBean("foo")
public class FooImpl2 implements Foo {

	@PetiteInject
	Boo boo;

	public void foo() {
		if (boo != null) {
			boo.boo();
		}
		System.out.println("!!!foo2 " + toString());
	}
}
