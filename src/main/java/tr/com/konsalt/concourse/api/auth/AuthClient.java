package tr.com.konsalt.concourse.api.auth;

public interface AuthClient {

	public void setBearerToken(String bearerToken);

	public String getBearerToken();

	public void setCsrfToken(String csrfToken);

	public String getCsrfToken();
}
