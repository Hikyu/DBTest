package com.oscar.dbtest.common.handler;

import java.util.Iterator;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.handlers.HandlerUtil;

import com.oscar.dbtest.common.model.Project;
import com.oscar.dbtest.common.model.ProjectRegister;

public class ProjectRemoveHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		final ISelection selection = HandlerUtil.getCurrentSelection(event);
		if (selection instanceof TreeSelection) {
			final IStructuredSelection structSelection = (IStructuredSelection) selection;
			for (Iterator<?> iter = structSelection.iterator(); iter.hasNext();) {
				Object element = iter.next();
				if (element instanceof Project) {
					ProjectRegister.getInstance().removeProject((Project) element);
					continue;
				}
			}
		}
		return null;
	}

}
