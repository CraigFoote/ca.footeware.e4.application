/**
 * 
 */
package ca.footeware.e4.application.ui.dialogs;

import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Shell;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

/**
 * @author Footeware.ca
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
	protected Point getInitialSize() {
		return new Point(450, 400);
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayoutFactory.swtDefaults().numColumns(2).equalWidth(false).applyTo(composite);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(composite);

		ResourceManager resManager = new LocalResourceManager(JFaceResources.getResources(), composite);
		Bundle bundle = FrameworkUtil.getBundle(this.getClass());
		// use the org.eclipse.core.runtime.Path as import
		URL url = FileLocator.find(bundle, new Path("icons/about.png"), null);
		// get an imageDescriptor and create Image object
		ImageDescriptor imageDescriptor = ImageDescriptor.createFromURL(url);
		Image image = resManager.createImage(imageDescriptor);

		Label imageLabel = new Label(composite, SWT.NONE);
		imageLabel.setImage(image);

		Link link = new Link(composite, SWT.NONE);
		link.setText("<a>http://footeware.ca</a>");
		link.addListener(SWT.Selection, event -> Program.launch(event.text));
		GridDataFactory.swtDefaults().align(SWT.LEFT, SWT.TOP).applyTo(link);

		return super.createDialogArea(parent);
	}
}
