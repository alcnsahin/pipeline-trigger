package tr.com.konsalt.concourse.configuration;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

public class ApplicationConfiguration {

	private static final String FILE_PATH = "target/classes/application.properties";
	private static ApplicationConfiguration instance;
	private String host;
	private String username;
	private String password;
	private String team;

	private ApplicationConfiguration() throws FileNotFoundException, URISyntaxException, IOException {
		Properties properties = new Properties();
		properties.load(new FileReader(System.getProperty("user.dir") + "/" + FILE_PATH));
		setHost(properties.getProperty("concourse.host"));
		setUsername(properties.getProperty("concourse.username"));
		setPassword(properties.getProperty("concourse.password"));
		setTeam(properties.getProperty("concourse.team"));
	}

	public static ApplicationConfiguration getInstance() throws FileNotFoundException, URISyntaxException, IOException {
		if (instance == null) {
			instance = new ApplicationConfiguration();
		}
		return instance;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

}
