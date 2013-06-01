// Copyright (c) 2003-2012, Jodd Team (jodd.org). All Rights Reserved.

package jodd.examples.http;

import jodd.http.HttpRequest;
import jodd.http.HttpResponse;

import java.io.IOException;

/**
 * Retrieve jodd.org page.
 */
public class HttpGet {

	public static void main(String[] args) throws IOException {
		HttpRequest request = HttpRequest.get("http://jodd.org?id=1");
		request.header("User-Agent", "jodd");

		request.query("id", "173");

		System.out.println(request);

		HttpResponse httpResponse = request.send();

		System.out.println(httpResponse.body());
	}
}
