package com.oscar.dbtest.common.dialog;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import com.oscar.dbtest.common.model.IProjectContainer;
import com.oscar.dbtest.common.model.Project;
import com.oscar.dbtest.common.model.ProjectRegister;

public class ProjectWizard extends Wizard implements INewWizard {
	private Project project;
	
	public ProjectWizard() {
		project = new Project();
	}
	
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		setWindowTitle("创建测试项目");
	}

	@Override
	public void addPages() {
		super.addPages();
		addPage(new ProjectWizardPage(project));
	}

	@Override
	public boolean performFinish() {
		ProjectRegister.getInstance().addProject(project);
		return true;
	}

}
