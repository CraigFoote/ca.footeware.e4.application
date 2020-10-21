/**
 * 
 */
package ca.footeware.e4.application.ui.tests;

import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.workbench.IWorkbench;
import org.eclipse.swtbot.e4.finder.widgets.SWTWorkbenchBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

/**
 * @author Footeware.ca
 *
 */
// @ExtendWith(SWTBotJunit5Extension.class)
class ApplicationTest {

	private static SWTWorkbenchBot bot;
	private static IEclipseContext context;

	@BeforeAll
	public static void beforeAll() {
		BundleContext bundleContext = FrameworkUtil.getBundle(ApplicationTest.class).getBundleContext();
		IEclipseContext serviceContext = EclipseContextFactory.getServiceContext(bundleContext);
		context = serviceContext.get(IWorkbench.class).getApplication().getContext();
		bot = new SWTWorkbenchBot(context);
	}

	@Test
	public void testAboutDialog() {
		bot.menu("Help").menu("About").click();
		for (SWTBotShell shell : bot.shells()) {
			System.err.println(shell.getText());
		}
		System.err.println();
	}

	@Test
	public void testToolbar() {
		Assertions.assertTrue(bot.toolbarButton(0).isVisible());
		Assertions.assertTrue(bot.toolbarButton(1).isVisible());
		Assertions.assertTrue(bot.toolbarButton(2).isVisible());

		Assertions.assertFalse(bot.toolbarButton(0).isEnabled());
		Assertions.assertFalse(bot.toolbarButton(1).isEnabled());
		Assertions.assertFalse(bot.toolbarButton(2).isEnabled());
	}

	@Test
	public void testEditMenu() {
		Assertions.assertTrue(bot.menu("Edit").menu("Cut").isVisible());
		Assertions.assertTrue(bot.menu("Edit").menu("Copy").isVisible());
		Assertions.assertTrue(bot.menu("Edit").menu("Paste").isVisible());

		Assertions.assertFalse(bot.menu("Edit").menu("Cut").isEnabled());
		Assertions.assertFalse(bot.menu("Edit").menu("Copy").isEnabled());
		Assertions.assertFalse(bot.menu("Edit").menu("Paste").isEnabled());
	}
}
