/**
 *
 */
package ca.footeware.e4.application.ui.tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * @author <a href="http://Footeware.ca">Footeware.ca</a>
 *
 */
class ApplicationTest {

	private static SWTBot bot;

	@BeforeAll
	public static void beforeAll() {
		bot = new SWTBot();
	}

	@Test
	void testAboutDialog() {
		bot.menu("Help").menu("About").click();
		for (SWTBotShell shell : bot.shells()) {
			if (shell.getText().equals("About")) {
				assertTrue(shell.bot().link("<a>http://Footeware.ca</a>").isVisible());
				shell.bot().button("OK").click();
				break;
			}
		}
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
	void testToolbar() {
		assertTrue(bot.toolbarButton(0).isVisible());
		assertTrue(bot.toolbarButton(1).isVisible());
		assertTrue(bot.toolbarButton(2).isVisible());

		assertFalse(bot.toolbarButton(0).isEnabled());
		assertFalse(bot.toolbarButton(1).isEnabled());
		assertFalse(bot.toolbarButton(2).isEnabled());
	}
}
