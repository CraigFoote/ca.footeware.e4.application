/**
 * 
 */
package ca.footeware.e4.application.ui.tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.workbench.IWorkbench;
import org.eclipse.swtbot.e4.finder.widgets.SWTWorkbenchBot;
import org.eclipse.swtbot.swt.finder.junit5.SWTBotJunit5Extension;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

/**
 * @author Footeware.ca
 *
 */
@ExtendWith(SWTBotJunit5Extension.class)
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
	void testAboutDialog() {
		bot.menu("Help").menu("About").click();
		for (SWTBotShell shell : bot.shells()) {
			if (shell.getText().equals("About")) {
				assertTrue(shell.bot().link("<a>http://footeware.ca</a>").isVisible());
				shell.bot().button("OK").click();
			}
		}
	}

	@Test
	void testToolbar() {
		assertTrue(bot.toolbarButton(0).isVisible());
		assertTrue(bot.toolbarButton(1).isVisible());
		assertTrue(bot.toolbarButton(2).isVisible());

		assertFalse(bot.toolbarButton(0).isEnabled());
		assertFalse(bot.toolbarButton(1).isEnabled());
		assertFalse(bot.toolbarButton(2).isEnabled());
	}

	@Test
	public void testEditMenu() {
		assertTrue(bot.menu("Edit").menu("Cut").isVisible());
		assertTrue(bot.menu("Edit").menu("Copy").isVisible());
		assertTrue(bot.menu("Edit").menu("Paste").isVisible());

		assertFalse(bot.menu("Edit").menu("Cut").isEnabled());
		assertFalse(bot.menu("Edit").menu("Copy").isEnabled());
		assertFalse(bot.menu("Edit").menu("Paste").isEnabled());
	}
	
	@Test
	@Disabled
	public void testWindowMenu() {
		assertTrue(bot.menu("Window").menu("Show View").isVisible());
		assertTrue(bot.menu("Window").menu("Show View").isEnabled());

		assertTrue(bot.menu("Window").menu("Perspective").isVisible());
		assertTrue(bot.menu("Window").menu("Perspective").isEnabled());
	}
}
