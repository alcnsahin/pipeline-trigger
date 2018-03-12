package tr.com.konsalt.concourse.api.auth;

public class AbstractAuthToken implements AuthClient {

	private String bearerToken;
	private String csrfToken;

	public String getBearerToken() {
		return bearerToken;
	}

	public void setBearerToken(String bearerToken) {
		this.bearerToken = "Bearer " + bearerToken;
	}

	public String getCsrfToken() {
		return csrfToken;
	}

	public void setCsrfToken(String csrfToken) {
		this.csrfToken = csrfToken;
	}
}
