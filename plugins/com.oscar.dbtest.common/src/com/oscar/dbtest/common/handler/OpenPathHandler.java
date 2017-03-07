package com.oscar.dbtest.common.handler;
import java.io.File;
import java.util.Iterator;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.program.Program;
import org.eclipse.ui.handlers.HandlerUtil;

public class OpenPathHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		final ISelection selection = HandlerUtil.getCurrentSelection(event);
		if (selection instanceof TreeSelection) {
			final IStructuredSelection structSelection = (IStructuredSelection) selection;
			for (Iterator<?> iter = structSelection.iterator(); iter.hasNext();) {
				Object element = iter.next();
				if (element instanceof File) {
					File file = (File)element;
					String path;
					if (file.isFile()) {
						path = file.getParentFile().getAbsolutePath();
					} else {
						path = file.getAbsolutePath();
					}
					Program.launch(path);
				}
			}
		}
		return null;
	}

}
