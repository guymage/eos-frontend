package net.guymage.client.util;

import java.util.Date;

import com.google.gwt.user.client.Cookies;

import net.guymage.client.auth.UserCredentials;

public class CookiesUtil {

	private static final String COOKIES_NAME = "eos_name";

	private static final String COOKIES_PSSWD = "eos_psswd";

	private CookiesUtil() {
		// Non instanciable
	}

	/**
	 * Créé les cookies pour le user
	 *
	 * @param nom Login du user
	 * @param passwd Password du user
	 */
	public static void creerCookies(final String nom, final String passwd) {
		if (Cookies.isCookieEnabled()) {
			// Nb millisecondes pour 1 journée
			final long jour = 1000 * 60 * 60 * 24;
			// 2 mois de validité
			final Date expires = new Date(System.currentTimeMillis() + jour * 62);

			// Login
			Cookies.setCookie(COOKIES_NAME, nom, expires, null, "/", false);
			// Password
			Cookies.setCookie(COOKIES_PSSWD, passwd, expires, null, "/", false);
		}
	}

	/**
	 * Met à jour UserCredentials à partir du cookies.
	 */
	public static void loadCookies(){
		if (Cookies.isCookieEnabled()) {
			// On récupère les cookies
			final String nom = Cookies.getCookie(COOKIES_NAME);
			final String psswd = Cookies.getCookie(COOKIES_PSSWD);
			if (nom != null && psswd != null) {
				UserCredentials.INSTANCE.setUserName(nom);
				UserCredentials.INSTANCE.setPassword(psswd);
			}
		}
	}

}
