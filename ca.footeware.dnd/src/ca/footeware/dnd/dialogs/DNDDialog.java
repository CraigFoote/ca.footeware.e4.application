package ca.footeware.dnd.dialogs;

import java.util.ArrayList;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import ca.footeware.dnd.handlers.DialogHandler;

public class DNDDialog extends MessageDialog {

	public DNDDialog(DialogHandler dialogHandler, Shell parentShell, String dialogTitle, Image dialogTitleImage,
			String dialogMessage, int dialogImageType, int defaultIndex, String string) {
		super(parentShell, dialogTitle, dialogTitleImage, dialogMessage, dialogImageType, defaultIndex, string);
	}

	@Override
	protected Control createContents(Composite parent) {
		parent.setLayout(new GridLayout(2, true));

		java.util.List<String> leftInput = new ArrayList<>();
		leftInput.add("one");
		leftInput.add("two");

		java.util.List<String> rightInput = new ArrayList<>();

		ListViewer leftViewer = new ListViewer(parent, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
		GridDataFactory.defaultsFor(leftViewer.getControl()).grab(true, true).applyTo(leftViewer.getControl());
		leftViewer.setContentProvider(new ArrayContentProvider());
		leftViewer.setInput(leftInput);

		ListViewer rightViewer = new ListViewer(parent, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
		GridDataFactory.defaultsFor(rightViewer.getControl()).grab(true, true).applyTo(rightViewer.getControl());
		rightViewer.setContentProvider(new ArrayContentProvider());
		rightViewer.setInput(rightInput);

		int operations = DND.DROP_MOVE;
		Transfer[] transferTypes = new Transfer[] { LocalSelectionTransfer.getTransfer() };

		leftViewer.addDragSupport(operations, transferTypes, new DragSourceAdapter() {
			@Override
			public void dragSetData(DragSourceEvent event) {
				LocalSelectionTransfer.getTransfer().setSelection(leftViewer.getStructuredSelection());
			}
		});
		
		rightViewer.addDragSupport(operations, transferTypes, new DragSourceAdapter() {
			@Override
			public void dragSetData(DragSourceEvent event) {
				LocalSelectionTransfer.getTransfer().setSelection(rightViewer.getStructuredSelection());
			}
		});
		
		leftViewer.addDropSupport(operations, transferTypes, new DropTargetAdapter() {
			@Override
			public void drop(DropTargetEvent event) {
				IStructuredSelection structuredSelection = (IStructuredSelection) LocalSelectionTransfer.getTransfer()
						.getSelection();
				for (Object obj : structuredSelection.toList()) {
					leftViewer.add(obj);
					rightViewer.remove(obj);
				}
			}
		});

		rightViewer.addDropSupport(operations, transferTypes, new DropTargetAdapter() {
			@Override
			public void drop(DropTargetEvent event) {
				IStructuredSelection structuredSelection = (IStructuredSelection) LocalSelectionTransfer.getTransfer()
						.getSelection();
				for (Object obj : structuredSelection.toList()) {
					leftViewer.remove(obj);
					rightViewer.add(obj);
				}
			}
		});

		return parent;
	}

}