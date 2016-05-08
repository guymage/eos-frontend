package net.guymage.client.auth;

import java.io.UnsupportedEncodingException;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.dispatcher.DispatcherFilter;

import com.google.gwt.http.client.RequestBuilder;
import com.googlecode.gwt.crypto.bouncycastle.util.encoders.Base64;

/**
 * Dispatcher qui ajoute les credentials dans le header d'une requête
 *
 * @author guymage
 *
 */
public class BasicAuthHeaderDispatcherFilter implements DispatcherFilter {

	public static final String AUTHORIZATION_HEADER = "Authorization";

	@Override
	public boolean filter(Method method, RequestBuilder builder) {
		try {

			if(UserCredentials.INSTANCE.getUserName() != null){
				String basicAuthHeaderValue = createBasicAuthHeader(
						UserCredentials.INSTANCE.getUserName(),
						UserCredentials.INSTANCE.getPassword());

				builder.setHeader(AUTHORIZATION_HEADER, basicAuthHeaderValue);
			}
		} catch (UnsupportedEncodingException e) {
			return false;
		}
		return true;
	}

	private String createBasicAuthHeader(String userName, String password)
			throws UnsupportedEncodingException {
		String credentials = userName + ":" + password;
		String encodedCredentials = new String(Base64.encode(credentials
				.getBytes()), "UTF-8");

		return "Basic " + encodedCredentials;
	}
}