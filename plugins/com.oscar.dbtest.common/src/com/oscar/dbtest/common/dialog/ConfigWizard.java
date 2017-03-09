package com.oscar.dbtest.common.dialog;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import com.oscar.dbtest.common.model.Config;
import com.oscar.dbtest.common.model.ConfigRegister;
import com.oscar.dbtest.common.model.IProjectContainer;

public class ConfigWizard extends Wizard implements INewWizard {
	private Config config;
	private IProjectContainer project;

	/**
	 * Ä¬ÈÏÅäÖÃ
	 */
	public ConfigWizard() {
		config = ConfigRegister.getInstance().getDefault();
	}

	/**
	 * ÏîÄ¿ÅäÖÃ
	 * 
	 * @param project
	 */
	public ConfigWizard(IProjectContainer project) {
//		config = ConfigRegister.getInstance().getConfig(project.getId());
		this.project = project;
	}

	@Override
	public void addPages() {
		super.addPages();
		addPage(new ConnectConfigPage(config));
		addPage(new TestConfigPage(config));
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		String title = "Ä¬ÈÏÅäÖÃ";
		if (project != null) {
			title = "ÅäÖÃ  " + project.getName();
		}
		setWindowTitle(title);
	}

	@Override
	public boolean performFinish() {
		if (project != null) {
			ConfigRegister.getInstance().reSotreConfig(project.getId(), config);
		} else {
			ConfigRegister.getInstance().reStoreDefault(config);
		}
		return true;
	}

}
