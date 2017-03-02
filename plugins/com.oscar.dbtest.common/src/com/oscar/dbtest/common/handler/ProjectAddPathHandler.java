package com.oscar.dbtest.common.handler;

import java.util.Iterator;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.handlers.HandlerUtil;

import com.oscar.dbtest.common.model.Project;
import com.oscar.dbtest.common.model.ProjectRegister;

public class ProjectAddPathHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		final ISelection selection = HandlerUtil.getCurrentSelection(event);
		if (selection instanceof TreeSelection) {
			final IStructuredSelection structSelection = (IStructuredSelection) selection;
			for (Iterator<?> iter = structSelection.iterator(); iter.hasNext();) {
				Object element = iter.next();
				if (element instanceof Project) {
					Project project = (Project)element;
					InputDialog dialog = new InputDialog(Display.getDefault().getActiveShell(), "添加路径", "项目-" + project.getName() , "", null);
					if (dialog.open() == InputDialog.OK) {
						String valueStr = dialog.getValue();
						if (!project.getPaths().contains(valueStr)) {
							project.addPath(valueStr);
							ProjectRegister.getInstance().refershProjects();
						}
					}
					continue;
				}
			}
		}
		
		return null;
	}

}
