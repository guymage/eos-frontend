package net.guymage.client.auth;

import org.fusesource.restygwt.client.dispatcher.DefaultFilterawareDispatcher;

/**
 * Classe permettant d'ajouter des dispatcher REST
 *
 * @author guymage
 *
 */
public class MyDispatchers extends DefaultFilterawareDispatcher {

	public MyDispatchers() {
		addFilter(new BasicAuthHeaderDispatcherFilter());
	}

}
