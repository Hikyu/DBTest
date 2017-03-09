package com.oscar.dbtest.common.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.handlers.HandlerUtil;

import com.oscar.dbtest.common.dialog.ConfigWizard;
import com.oscar.dbtest.common.model.Project;

public class ConfigProjectHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		final ISelection selection = HandlerUtil.getCurrentSelection(event);
		if (selection instanceof TreeSelection) {
			final IStructuredSelection structSelection = (IStructuredSelection) selection;
			if (structSelection.size() > 0) {
				Object element = structSelection.iterator().next();
				if (element instanceof Project) {
					WizardDialog dialog = new WizardDialog(HandlerUtil.getActiveWorkbenchWindow(event).getShell(),
							new ConfigWizard((Project) element));
					dialog.open();
				}
			}
		}
		return null;
	}

}
