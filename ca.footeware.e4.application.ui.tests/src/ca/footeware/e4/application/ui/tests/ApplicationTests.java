/**
 *
 */
package ca.footeware.e4.application.ui.tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotToolbarButton;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * @author <a href="https://Footeware.ca">Footeware.ca</a>
 *
 */
public class ApplicationTests {

	private static SWTBot bot;

	@BeforeAll
	public static void beforeAll() {
		bot = new SWTBot();
	}

	@Test
	public void testAboutDialog() {
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
	public void testToolbar() {
		assertTrue(bot.toolbarButton(0).isVisible());
		assertTrue(bot.toolbarButton(1).isVisible());
		assertTrue(bot.toolbarButton(2).isVisible());
		
		/*
		 * assertFalse(bot.toolbarButton(0).isEnabled()); 
		 * was sometimes failing, breaking up calls seems to fix it.
		 */		
		SWTBotToolbarButton toolbarButton = bot.toolbarButton(0);
		assertNotNull(toolbarButton, "toolbarButton(0) was null");
		assertFalse(toolbarButton.isEnabled(), "toolbarButton(0) was not disabled");

		toolbarButton = bot.toolbarButton(1);
		assertNotNull(toolbarButton, "toolbarButton(1) was null");
		assertFalse(toolbarButton.isEnabled(), "toolbarButton(1) was not disabled");

		toolbarButton = bot.toolbarButton(2);
		assertNotNull(toolbarButton, "toolbarButton(2) was null");
		assertFalse(toolbarButton.isEnabled(), "toolbarButton(2) was not disabled");
	}
}
