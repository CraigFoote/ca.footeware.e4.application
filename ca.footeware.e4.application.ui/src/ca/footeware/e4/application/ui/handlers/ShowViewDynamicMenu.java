
package ca.footeware.e4.application.ui.handlers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.e4.ui.di.AboutToShow;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.commands.MCommand;
import org.eclipse.e4.ui.model.application.commands.MParameter;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.menu.MHandledMenuItem;
import org.eclipse.e4.ui.model.application.ui.menu.MMenuElement;
import org.eclipse.e4.ui.workbench.modeling.EModelService;

public class ShowViewDynamicMenu {

	@AboutToShow
	public void aboutToShow(List<MMenuElement> items, EModelService modelService, MApplication application) {
		List<MCommand> mCommands = modelService.findElements(application,
				"ca.footeware.e4.application.ui.command.showview", MCommand.class);
		MCommand showViewCommand = mCommands.get(0);

		List<MPart> mParts = modelService.findElements(application, null, MPart.class);
		Set<MPart> uniqueParts = new HashSet<>();
		Set<String> labels = new HashSet<>();
		for (MPart mPart : mParts) {
			String label = mPart.getLabel();
			if (!labels.contains(label)) {
				labels.add(label);
				uniqueParts.add(mPart);
			}
		}

		for (MPart mPart : uniqueParts) {
			String label = mPart.getLabel();
			String elementId = mPart.getElementId();
			String contributionURI = mPart.getContributionURI();

			MHandledMenuItem dynamicItem = modelService.createModelElement(MHandledMenuItem.class);
			dynamicItem.setLabel(label);

			MParameter mParameter = modelService.createModelElement(MParameter.class);
			mParameter.setName("ca.footeware.e4.application.ui.commandparameter.partid");
			mParameter.setValue(elementId);
			dynamicItem.getParameters().add(mParameter);

			mParameter = modelService.createModelElement(MParameter.class);
			mParameter.setName("ca.footeware.e4.application.ui.commandparameter.contributionURI");
			mParameter.setValue(contributionURI);
			dynamicItem.getParameters().add(mParameter);

			dynamicItem.setCommand(showViewCommand);

			items.add(dynamicItem);
		}
	}

}