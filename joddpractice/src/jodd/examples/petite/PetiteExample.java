// Copyright (c) 2003-2012, Jodd Team (jodd.org). All Rights Reserved.

package jodd.examples.petite;

import jodd.petite.WiringMode;
import jodd.petite.PetiteContainer;
import jodd.petite.PetiteException;
import jodd.petite.BeanDefinition;
import jodd.petite.config.AutomagicPetiteConfigurator;
import jodd.petite.scope.Scope;
import jodd.util.ClassLoaderUtil;
import jodd.util.ThreadUtil;

public class PetiteExample {
	public static void main(String[] args) {
		System.out.println("\n\n---1---------------------------------------------------------------");
		one();
		System.out.println("\n\n---2---------------------------------------------------------------");
		two();
		System.out.println("\n\n---5---------------------------------------------------------------");
		three();
	}

	public static void one() {

		// manual registration
		PetiteContainer petite = new PetiteContainer();
		petite.registerPetiteBean(FooImpl.class, null, null, null, false);
		petite.registerPetiteBean(Goo.class, null, null, null, false);
		petite.registerPetiteBean(Boo.class, null, null, null, false);
		petite.registerPetiteBean(Zoo.class, null, null, null, false);

		Foo foo = (Foo) petite.getBean("foo");
		System.out.println("> foo");
		foo.foo();

		foo = (Foo) petite.getBean("foo");
		System.out.println("> foo");
		foo.foo();

		Zoo zoo = (Zoo) petite.getBean("zoo");
		System.out.println("> zoo");
		zoo.zoo();

		zoo = (Zoo) petite.getBean("zoo");
		System.out.println("> zoo (proto scope, new instance)");
		zoo.zoo();
	}


	public static void two() {

		// automagic configuration
		PetiteContainer petite = new PetiteContainer();
		AutomagicPetiteConfigurator petiteCfg = new AutomagicPetiteConfigurator();
		petiteCfg.setIncludedEntries("jodd.examples.petite.*");
		petiteCfg.setExcludedEntries("jodd.examples.petite.news.*");
		petiteCfg.configure(petite);

		System.out.println("> Get foo");
		Foo foo = (Foo) petite.getBean("foo");
		foo.foo();

		System.out.println("> Get zoo");
		Zoo zoo = (Zoo) petite.getBean("zoo");
		zoo.zoo();

		System.out.println("> Remove foo, but reference still exists in zoo");
		petite.removeBean("foo");
		zoo.zoo();
		System.out.println("> Get zoo again, will throw an exception since no foo anymore");
		try {
			zoo = (Zoo) petite.getBean("zoo");
			zoo.zoo();
		} catch (PetiteException pex) {
			System.out.println(pex);
		}

		System.out.println("> Register foo again");
		petite.registerPetiteBean(FooImpl.class, null, null, null, false);
		System.out.println("> Get zoo, foo is new");
		zoo = (Zoo) petite.getBean("zoo");
		zoo.zoo();

	}


	static void three() {

		// create petite container
		final PetiteContainer petite = new PetiteContainer() {

			@Override
			public BeanDefinition registerPetiteBean(Class type, String name, Class<? extends Scope> scopeType, WiringMode wiringMode, boolean define) {
				System.out.println(":::: " + type + "            " + type.getClassLoader());
				return super.registerPetiteBean(type, name, scopeType, wiringMode, define);
			}
		};
		petite.registerPetiteBean(FooImpl.class, null, null, null, false);
		petite.registerPetiteBean(Goo.class, null, null, null, false);
		petite.registerPetiteBean(Boo.class, null, null, null, false);
		petite.registerPetiteBean(Zoo.class, null, null, null, false);

		// get
		System.out.println("> Get ZOO");
		Zoo zoo = (Zoo) petite.getBean("zoo");
		System.out.println(zoo.getClass().getClassLoader());
		zoo.zoo();

		System.out.println("\n\n============ CHANGE FOOIMPL or ZOO and COMPILE IN 10 SEC FROM NOW!\n\n");
		ThreadUtil.sleep(1000);

		Class a = ClassLoaderUtil.findClass("jodd.examples.petite.Zoo", ClassLoaderUtil.getDefaultClasspath());
		System.out.println(a);
		System.out.println(a.getClassLoader());

		System.out.println("\n\n");
		Object result = petite.getBean("zoo");
		System.out.println("GOT> : " + result.getClass());
		System.out.println("GOT> : " + result.getClass().getClassLoader());
		zoo = (Zoo) result;
		zoo.zoo();
	}

}
