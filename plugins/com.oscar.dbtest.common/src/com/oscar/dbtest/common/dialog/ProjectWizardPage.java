package com.oscar.dbtest.common.dialog;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import com.oscar.dbtest.common.model.Project;
import com.oscar.dbtest.common.views.UIUtils;

public class ProjectWizardPage extends WizardPage {
	private Project project;
	private String name;
	private String path;

	public ProjectWizardPage(Project project) {
		super("创建测试项目");
		this.project = project;
		setTitle("创建新的测试项目");
		setDescription("配置测试项目");
	}

	@Override
	public boolean isPageComplete() {
		return !isEmpty(path) && !isEmpty(name);
	}

	private boolean isEmpty(String s) {
		return s == null || s.length() == 0;
	}

	@Override
	public void createControl(Composite parent) {
		Composite placeholder = UIUtils.createPlaceholder(parent, 1);
		Composite configGroup = UIUtils.createControlGroup(placeholder, "测试项目", 2, GridData.FILL_HORIZONTAL, 0);

		final Text nameText = UIUtils.createLabelText(configGroup, "名称", ""); // $NON-NLS-2$
		nameText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				name = nameText.getText();
				project.setName(name);
				updateState();
			}
		});

		final Text pathText = UIUtils.createPathLabelText(configGroup, "路径", ""); // $NON-NLS-2$
		pathText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				path = pathText.getText();
				project.addPath(path);
				updateState();
			}
		});
		final Text desText = UIUtils.createLabelText(configGroup, "描述", ""); // $NON-NLS-2$
		desText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				project.setDescription(desText.getText());
				updateState();
			}
		});
		setControl(placeholder);

	}

	protected void updateState() {
		getContainer().updateButtons();
	}

}
