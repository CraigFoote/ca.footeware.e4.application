
package ca.footeware.dnd.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

import ca.footeware.dnd.dialogs.DNDDialog;

public class DialogHandler {
	
	@Execute
	public void execute(Shell shell) {
		Dialog dialog = new DNDDialog(this, shell, "DND", null, "DND ROX!", MessageDialog.NONE, 0, "OK");
		dialog.open();
	}

}