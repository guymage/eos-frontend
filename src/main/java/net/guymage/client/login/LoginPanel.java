package net.guymage.client.login;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.SimpleCheckBox;
import com.google.gwt.user.client.ui.TextBox;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;

public class LoginPanel extends FlowLayoutContainer {

	private static final String FORM_ID = "login-form";
	private static final String USER_ID = "login-username";
	private static final String PASSWORD_ID = "login-password";
	private static final String CHECK_ID = "login-check";
	private static final String BUTTON_ID = "login-button";
	private static final String FIELDS_ID = "login-fields";
	private static final String WAIT_ID = "login-wait";

	private final LoginController controller;

	TextBox username;
	PasswordTextBox password;
	Button button;
	SimpleCheckBox stayConnected;
	Label divField;
	Label divWait;

	private FormPanel loginFormPanel = null;

	public LoginPanel() {
		super();
		controller = new LoginController(this);

	}

	/**
	 * Affiche initialise le forumaire de connexion
	 */
	public void init() {
		displayLoginForm();
	}

	private void displayLoginForm() {
		if (loginFormPanel == null) {
			username = TextBox.wrap(DOM.getElementById(USER_ID));
			username.setStyleName("gwt-TextBox");
			username.setVisible(true);
			password = PasswordTextBox.wrap(DOM.getElementById(PASSWORD_ID));
			password.setStyleName("gwt-PasswordTextBox");
			password.setVisible(true);
			button = Button.wrap(DOM.getElementById(BUTTON_ID));
			button.setVisible(true);
			stayConnected = SimpleCheckBox.wrap(DOM.getElementById(CHECK_ID));

			// On masque le message d'attente
			divWait = Label.wrap(DOM.getElementById(WAIT_ID));
			divWait.setVisible(false);

			// On affiche le formulaire
			divField = Label.wrap(DOM.getElementById(FIELDS_ID));
			divField.setVisible(true);

			// Récupération du panel du formulaire
			loginFormPanel = FormPanel.wrap(DOM.getElementById(FORM_ID), false);
			loginFormPanel.setAction("");
			loginFormPanel.setMethod(FormPanel.METHOD_POST);

			button.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					controller.login(username.getValue(), password.getValue(), stayConnected.getValue());
				}
			});
		}
	}


	/**
	 * Masque le formulaire
	 */
	public void hideForm() {
		if (loginFormPanel != null) {
			loginFormPanel.setVisible(false);
		}
		hide();
	}

}
