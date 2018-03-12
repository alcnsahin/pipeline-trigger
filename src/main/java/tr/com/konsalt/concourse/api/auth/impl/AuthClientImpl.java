package tr.com.konsalt.concourse.api.auth.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

import tr.com.konsalt.concourse.api.auth.*;
import tr.com.konsalt.concourse.configuration.ApplicationConfiguration;

public class AuthClientImpl extends AbstractAuthToken {

	private String host;
	private String team;
	private String username;
	private String password;
	private Client client = Client.create();
	private ApplicationConfiguration configuration;

	public AuthClientImpl() throws FileNotFoundException, URISyntaxException, IOException {
		configuration = ApplicationConfiguration.getInstance();
		this.host = configuration.getHost();
		this.team = configuration.getTeam();
		this.username = configuration.getUsername();
		this.password = configuration.getPassword();
	}

	public void getTokens() {
		client.addFilter(new HTTPBasicAuthFilter(username, password));
		WebResource webResource = client.resource(host + "/api/v1/teams/" + team + "/auth/token");
		ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		if (response.getStatus() != 200) {
			throw new RuntimeException("HTTP Error: " + response.getStatus());
		}

		// set csrf token
		setCsrfToken(response.getHeaders().getFirst("X-Csrf-Token"));

		// set bearer token
		JsonParser parser = new JsonParser();
		JsonElement json = parser.parse(response.getEntity(String.class));
		JsonElement bearer = null;
		if (json.isJsonObject()) {
			JsonObject jsonObject = json.getAsJsonObject();
			bearer = jsonObject.get("value");
		}
		setBearerToken(bearer.getAsString());
	}

	public void buildJob(String pipeline, String job) {
		System.out.println(getBearerToken());
		System.out.println(getCsrfToken());

		WebResource resource = client
				.resource(host + "/api/v1/teams/" + team + "/pipelines/" + pipeline + "/jobs/" + job + "/builds");
		ClientResponse response = resource.accept(MediaType.APPLICATION_JSON).header("Authorization", getBearerToken())
				.header("X-Csrf-Token", getCsrfToken()).post(ClientResponse.class);
		if (response.getStatus() != 200) {
			throw new RuntimeException("HTTP Error: " + response.toString());
		}
		System.out.println(response.getEntity(String.class));
	}

}
