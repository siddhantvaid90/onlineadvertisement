package newspaper.advertisement.system.jwt;

import java.io.Serializable;

public class TokenEntity implements Serializable {
	private final String token;

	public TokenEntity(String token) {
		this.token = token;
	}

	public String getToken() {
		return this.token;
	}
}
