
package ca.footeware.e4.application.ui.handlers;

import java.util.List;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.di.AboutToShow;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.commands.MCommand;
import org.eclipse.e4.ui.model.application.commands.MParameter;
import org.eclipse.e4.ui.model.application.ui.advanced.MPerspective;
import org.eclipse.e4.ui.model.application.ui.menu.MHandledMenuItem;
import org.eclipse.e4.ui.model.application.ui.menu.MMenuElement;
import org.eclipse.e4.ui.workbench.modeling.EModelService;

public class ShowPerspectiveDynamicMenu {

	@AboutToShow
	@Execute
	public void aboutToShow(List<MMenuElement> items, EModelService modelService, MApplication application) {
		List<MCommand> mCommands = modelService.findElements(application,
				"ca.footeware.e4.application.ui.command.showperspective", MCommand.class);
		MCommand showPerspectiveCommand = mCommands.get(0);

		List<MPerspective> mPerspectives = modelService.findElements(application, null, MPerspective.class);
		for (MPerspective mPerspective : mPerspectives) {
			String label = mPerspective.getLabel();
			String elementId = mPerspective.getElementId();

			MHandledMenuItem dynamicItem = modelService.createModelElement(MHandledMenuItem.class);
			dynamicItem.setLabel(label);

			MParameter mParameter = modelService.createModelElement(MParameter.class);
			mParameter.setName("ca.footeware.e4.application.ui.commandparameter.perspectiveid");
			mParameter.setValue(elementId);
			dynamicItem.getParameters().add(mParameter);
			dynamicItem.setCommand(showPerspectiveCommand);

			items.add(dynamicItem);
		}
	}

}