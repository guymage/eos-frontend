package net.guymage.client;

import org.fusesource.restygwt.client.Defaults;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.sencha.gxt.widget.core.client.box.PromptMessageBox;

import net.guymage.client.auth.MyDispatchers;
import net.guymage.client.login.LoginPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class EyeOnStarshine implements EntryPoint {

	public final static LoginPanel loginPanel = new LoginPanel();

	//private final Messages messages = GWT.create(Messages.class);

	/**
	 * This is the entry point method.
	 */
	@Override
	public void onModuleLoad() {

		// set uncaught exception handler
		GWT.setUncaughtExceptionHandler(new GWT.UncaughtExceptionHandler() {
			@Override
			public void onUncaughtException(Throwable throwable) {

				StringBuilder text = new StringBuilder("Exception: ");
				while (throwable != null) {
					// Affichage de l'erreur dans la console.
					throwable.printStackTrace();

					final StackTraceElement[] stackTraceElements = throwable.getStackTrace();
					text.append(throwable.toString()).append("\n");
					int size = stackTraceElements.length;
					for (int i = 0; i < size; i++) {
						text.append("    at ").append(stackTraceElements[i]).append("\n");
					}
					throwable = throwable.getCause();
				}
				final PromptMessageBox box = new PromptMessageBox("Erreur d'éxécution",
						"Copiez cette erreur et postez là sur le forum en précisant si possible votre dernière action. <br/>Merci.");
				box.getTextField().setValue(text.toString());
				box.getTextField().setReadOnly(true);
				box.show();

				// On vire tous les loaders
				//LoadDialog.unloadAll();
			}
		});

		// use a deferred command so that the handler catches init() exceptions
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {

				// Dispatcher pour l'authentification
				Defaults.setDispatcher(new MyDispatchers());

				loginPanel.init();
			}
		});





	}
}
