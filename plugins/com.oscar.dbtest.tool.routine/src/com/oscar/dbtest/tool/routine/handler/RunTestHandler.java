package com.oscar.dbtest.tool.routine.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import com.oscar.dbtest.tool.routine.views.ScriptsToRunView;

public class RunTestHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow workbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		IWorkbenchPage activePage =  workbenchWindow.getActivePage();
        if (activePage == null) {
            return null;
        }
        try {
            IViewPart view = activePage.findView(ScriptsToRunView.ID);
            if (view == null) {
                activePage.showView(ScriptsToRunView.ID);
            } 
        } catch (PartInitException ex) {
           // UIUtils.showErrorDialog(null, CenterView.ID, CoreMessages.ToggleViewAction_cannot_open_view + CenterView.ID, ex);
        }
        return null;
	}

}
