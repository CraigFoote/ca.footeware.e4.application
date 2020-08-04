
package ca.footeware.e4.application.ui.handlers;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

public class ShowPerspectiveHandler {

	@Execute
	public void execute(@Named("ca.footeware.e4.application.ui.commandparameter.perspectiveid") String perspectiveId, EPartService partService) {
		partService.switchPerspective(perspectiveId);
	}

}