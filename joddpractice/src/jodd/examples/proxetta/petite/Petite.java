// Copyright (c) 2003-2012, Jodd Team (jodd.org). All Rights Reserved.

package jodd.examples.proxetta.petite;

import jodd.petite.PetiteContainer;
import jodd.petite.BeanDefinition;
import jodd.petite.WiringMode;
import jodd.petite.scope.Scope;
import jodd.proxetta.ProxyPointcut;
import jodd.proxetta.MethodInfo;
import jodd.proxetta.ProxyAspect;
import jodd.examples.petite.Foo;
import jodd.examples.petite.FooImpl;
import jodd.examples.petite.Boo;
import jodd.examples.proxetta.LogProxyAdvice;
import jodd.proxetta.impl.ProxyProxetta;

public class Petite {

	public static void main(String[] args) {
		one();
	}

	static ProxyAspect pd4log = new ProxyAspect(LogProxyAdvice.class, new ProxyPointcut() {
		public boolean apply(MethodInfo msign) {
			return true;
		}
	});


	public static void one() {

		PetiteContainer petite = new PetiteContainer() {
			@Override
			public BeanDefinition registerPetiteBean(Class type, String name, Class<? extends Scope> scopeType, WiringMode wiringMode, boolean define) {
				type = ProxyProxetta.withAspects(pd4log).builder(type).define();
				return super.registerPetiteBean(type, name, scopeType, wiringMode, define);
			}
		};
		petite.getConfig().setDefaultWiringMode(WiringMode.OPTIONAL);


		petite.registerPetiteBean(FooImpl.class, "foo", null, null, false);
		petite.registerPetiteBean(Boo.class, null, null, null, false);

		Foo foo = (Foo) petite.getBean("foo");
		foo.foo();
	}

}
