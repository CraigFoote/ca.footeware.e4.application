package ca.footeware.e4.application.ui.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.workbench.IWorkbench;

/**
 * @author <a href="http://Footeware.ca">Footeware.ca</a>
 */
public class ExitHandler
{
	
	/**
	 * @param workbench
	 *            {@link IWorkbench}
	 */
	@Execute
	public static void execute(final IWorkbench workbench)
	{
		workbench.close();
	}
	
}
