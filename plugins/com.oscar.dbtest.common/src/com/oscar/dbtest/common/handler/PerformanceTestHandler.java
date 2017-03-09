package com.oscar.dbtest.common.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import com.oscar.dbtest.common.views.NormalTestView;
import com.oscar.dbtest.common.views.PerformanceTestView;

public class PerformanceTestHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow workbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		IWorkbenchPage activePage =  workbenchWindow.getActivePage();
        if (activePage == null) {
            return null;
        }
        try {
            IViewPart view = activePage.findView(PerformanceTestView.ID);
            if (view == null) {
                activePage.showView(PerformanceTestView.ID);
            } 
        } catch (PartInitException ex) {
           // UIUtils.showErrorDialog(null, CenterView.ID, CoreMessages.ToggleViewAction_cannot_open_view + CenterView.ID, ex);
        }
        return null;
	}

}
