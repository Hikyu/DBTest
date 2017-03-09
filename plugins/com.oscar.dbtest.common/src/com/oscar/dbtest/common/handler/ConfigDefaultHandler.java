package com.oscar.dbtest.common.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.handlers.HandlerUtil;

import com.oscar.dbtest.common.dialog.ConfigWizard;
import com.oscar.dbtest.common.model.Project;

public class ConfigDefaultHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		WizardDialog dialog = new WizardDialog(HandlerUtil.getActiveWorkbenchWindow(event).getShell(),
				new ConfigWizard());
		dialog.open();
		return null;
	}

}
