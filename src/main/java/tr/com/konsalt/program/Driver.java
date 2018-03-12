package tr.com.konsalt.program;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

import tr.com.konsalt.concourse.api.auth.impl.AuthClientImpl;

public class Driver {

	public static void main(String[] args) throws FileNotFoundException, URISyntaxException, IOException {
		AuthClientImpl client = new AuthClientImpl();
		client.getTokens();
		client.buildJob("hello-world", "hello-world");
	}

}
