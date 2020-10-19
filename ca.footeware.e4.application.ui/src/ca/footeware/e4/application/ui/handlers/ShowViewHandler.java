
package ca.footeware.e4.application.ui.handlers;

import javax.inject.Named;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.menu.MMenuItem;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;

public class ShowViewHandler {

	@Execute
	public void execute(@Named("ca.footeware.e4.application.ui.commandparameter.partid") String partId,
			@Named("ca.footeware.e4.application.ui.commandparameter.contributionURI") String contributionURI,
			EPartService partService, MMenuItem menuItem, IEclipseContext ctx, EModelService modelService,
			MApplication application) {

		MPart part = partService.createPart(partId);
		partService.showPart(partId, PartState.ACTIVATE);
		partService.activate(part, true);
	}

}