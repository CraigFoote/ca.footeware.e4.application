
package ca.footeware.e4.application.ui.handlers;

import java.util.List;

import javax.inject.Named;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.MDirtyable;
import org.eclipse.e4.ui.model.application.ui.basic.MBasicFactory;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.model.application.ui.menu.MMenuItem;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

public class ShowViewHandler {

	@Execute
	public void execute(@Named("ca.footeware.e4.application.ui.commandparameter.partid") String partId,
			@Named("ca.footeware.e4.application.ui.commandparameter.contributionURI") String contributionURI,
			EPartService partService, MMenuItem menuItem, IEclipseContext ctx, EModelService modelService,
			MApplication application) {

		IEclipseContext newPartContext = ctx.createChild(partId + "_context");
		MPart newPart = MBasicFactory.INSTANCE.createPart();
		newPartContext.set(MDirtyable.class, newPart);
		newPart.setContext(newPartContext);
		newPart.setContributionURI(contributionURI);
		newPart.setCloseable(true);
		newPart.setLabel(menuItem.getLabel());
		List<MPartStack> partStackList = modelService.findElements(application, null, MPartStack.class, null);
		MPartStack mPartStack = partStackList.get(0);
		mPartStack.setVisible(true);
		mPartStack.getChildren().add(newPart);
		partService.activate(newPart, true);
	}

}