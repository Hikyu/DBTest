package com.oscar.dbtest.common.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import com.oscar.dbtest.common.model.ProjectRegister;

public class NavigationRefershHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ProjectRegister.getInstance().refershProjects();
		return null;
	}

}
