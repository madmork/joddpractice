// Copyright (c) 2003-2012, Jodd Team (jodd.org). All Rights Reserved.

package jodd.examples.petite;

import jodd.petite.PetiteContainer;
import jodd.petite.meta.PetiteInject;

public class Foo2 {

	@PetiteInject
	public Boo2 boo2;

	public void foo() {
		if (boo2 != null) {
			boo2.boo();
		}
		System.out.println("foo: " + toString());
	}


	public static void main(String[] args) {

		PetiteContainer petite = new PetiteContainer();
		petite.registerPetiteBean(Foo2.class, null, null, null, false);
		petite.registerPetiteBean(Boo2.class, null, null, null, false);

		Foo2 foo2 = (Foo2) petite.getBean("foo2");

		foo2.foo();
	}

	

}
