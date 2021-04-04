
package ca.footeware.e4.application.ui.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.widgets.Shell;

import ca.footeware.e4.application.ui.dialogs.AboutDialog;

/**
 * 
 * @author <a href="http://Footeware.ca">Footeware.ca</a>
 *
 */
public class AboutHandler {
	
	/**
	 * @param shell {@link Shell}
	 */
	@Execute
	public void execute(Shell shell) {
		AboutDialog dialog = new AboutDialog(shell);
		dialog.open();
	}

}