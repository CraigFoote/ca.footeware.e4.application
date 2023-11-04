/**
 *
 */
package ca.footeware.e4.application.ui.dialogs;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Shell;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.Version;

/**
 * Shows information about the application.
 *
 * @author <a href="http://Footeware.ca">Footeware.ca</a>
 *
 */
public class AboutDialog extends Dialog {

	/**
	 * Constructor.
	 *
	 * @param parentShell {@link Shell}
	 */
	public AboutDialog(Shell parentShell) {
		super(parentShell);
		setBlockOnOpen(true);
	}

	@Override
	protected Button createButton(Composite parent, int id, String label, boolean defaultButton) {
		if (id == IDialogConstants.CANCEL_ID) {
			return null;
		}
		Button button = super.createButton(parent, id, label, defaultButton);
		button.setFocus();
		return button;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		final Composite area = (Composite) super.createDialogArea(parent);

		final Composite container = new Composite(area, SWT.NONE);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL).grab(true, true).applyTo(container);
		GridLayoutFactory.swtDefaults().spacing(0, 10).applyTo(container);

		Image image;
		final InputStream in = getClass().getResourceAsStream("/icons/about.png");
		if (in != null) {
			try {
				image = new Image(Display.getDefault(), in);
				Label imageLabel = new Label(container, SWT.NONE);
				imageLabel.setImage(image);
			} finally {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
		}

		final Link webpageLink = new Link(container, SWT.NONE);
		webpageLink.setText("Another fine mess by <a href=\"http://footeware.ca\">Footeware.ca</a>");
		webpageLink.addListener(SWT.Selection, event -> Program.launch(event.text));
		GridDataFactory.swtDefaults().align(SWT.CENTER, SWT.CENTER).applyTo(webpageLink);

		final Link versionLink = new Link(container, SWT.NONE);
		versionLink.setText(
				"<a href=\"https://github.com/CraigFoote/ca.footeware.swt.textify/releases\">" + getVersion() + "</a>");
		GridDataFactory.swtDefaults().align(SWT.CENTER, SWT.CENTER).grab(true, false).applyTo(versionLink);
		versionLink.addListener(SWT.Selection, event -> Program.launch(event.text));

		return area;
	}

	/**
	 * Gets this plugin's version.
	 *
	 * @return {@link String}
	 */
	private String getVersion() {
		Version v = FrameworkUtil.getBundle(AboutDialog.class).getVersion();
		return String.format("Version %d.%d.%d", v.getMajor(), v.getMinor(), v.getMicro());
	}

	@Override
	protected void setShellStyle(int newShellStyle) {
		super.setShellStyle(SWT.APPLICATION_MODAL);
	}
}
