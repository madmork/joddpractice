// Copyright (c) 2003-2012, Jodd Team (jodd.org). All Rights Reserved.

package jodd.examples.proxetta.dci;

@Role
public class MyRole {

	private Entity self;

	public void doStuffInContext() {
		System.out.println(self.getFoo());
	}
}
