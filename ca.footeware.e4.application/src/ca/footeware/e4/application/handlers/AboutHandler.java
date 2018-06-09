 
package ca.footeware.e4.application.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.widgets.Shell;

import ca.footeware.e4.application.dialogs.AboutDialog;

public class AboutHandler {
	@Execute
	public void execute(Shell shell) {
		AboutDialog dialog = new AboutDialog(shell);
		dialog.open();
	}
		
}