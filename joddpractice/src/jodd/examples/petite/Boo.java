// Copyright (c) 2003-2012, Jodd Team (jodd.org). All Rights Reserved.

package jodd.examples.petite;

import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;
import jodd.petite.PetiteContainer;

@PetiteBean
public class Boo {

	@PetiteInject
	protected Goo goo;

	public void boo() {
		goo.goo();
		System.out.println("boo " + toString());
	}


	public static void main(String[] args) {

		PetiteContainer petite = new PetiteContainer();
		petite.registerPetiteBean(Foo.class, null, null, null, false);
		petite.registerPetiteBean(Goo.class, null, null, null, false);
		petite.registerPetiteBean(Boo.class, null, null, null, false);

		Foo foo = (Foo) petite.getBean("foo");

		foo.foo();
	}
}
