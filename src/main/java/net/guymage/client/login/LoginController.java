package net.guymage.client.login;

import java.util.List;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.Resource;
import org.fusesource.restygwt.client.RestServiceProxy;

import com.google.gwt.core.client.GWT;

import net.guymage.api.model.race.Race;
import net.guymage.client.auth.UserCredentials;
import net.guymage.client.service.IRaceService;

class LoginController {

	final private LoginPanel composite;

	public LoginController(final LoginPanel composite) {
		this.composite = composite;
	}

	/**
	 * Connexion du joueur
	 */
	public void login(final String login, final String passwd, final boolean stayConnected) {
		String url = "http://localhost:8080";
		Resource resource = new Resource(url);

		UserCredentials.INSTANCE.setUserName(login);
		UserCredentials.INSTANCE.setPassword(passwd);

		// On récupère la liste des races pour vérifier si le login est bon
		IRaceService service = GWT.create(IRaceService.class);
		((RestServiceProxy)service).setResource(resource);

		service.getAllRaces(new MethodCallback<List<Race>> () {

			@Override
			public void onSuccess(Method method, List<Race> response) {
				GWT.log("succes: "+response.toString());
			}

			@Override
			public void onFailure(Method method, Throwable exception) {
				GWT.log("Erreur: ", exception);
			}
		});
	}

}
