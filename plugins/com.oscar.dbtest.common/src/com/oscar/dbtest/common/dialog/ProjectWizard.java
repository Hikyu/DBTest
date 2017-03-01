package com.oscar.dbtest.common.dialog;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

public class ProjectWizard extends Wizard implements INewWizard {

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		setWindowTitle("创建测试项目");
	}

	@Override
	public void addPages() {
		super.addPages();
		addPage(new ProjectWizardPage());
	}

	@Override
	public boolean performFinish() {
		// TODO 自动生成的方法存根
		return false;
	}

}
