package com.oscar.dbtest.common.dialog;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.Text;

import com.oscar.dbtest.common.model.Config;
import com.oscar.dbtest.common.views.UIUtils;

public class ConnectConfigPage extends WizardPage {
	private Config config;
	/* �������� */
	private Text hostText;
	private Text portText;
	private Combo databaseNameCombo;
	private Text userNameText;
	private Text passwordText;

	public ConnectConfigPage(Config config) {
		super("��������");
		setTitle("��Ŀ����");
		setDescription("���ù�������");
		this.config = config;
	}

	@Override
	public void createControl(Composite parent) {
		Composite parentGroup = new Composite(parent, SWT.NONE);
		parentGroup.setLayout(new GridLayout(1, false));
		parentGroup.setLayoutData(new GridData(GridData.FILL_BOTH));
		final Group commonGroup = UIUtils.createControlGroup(parentGroup, "��������", 1, GridData.FILL_HORIZONTAL, 0);
		createCommonGroup(commonGroup);
		setControl(parentGroup);
	}

	private void createCommonGroup(Composite commonGroup) {
		Composite connect = new Composite(commonGroup, SWT.NONE);
		connect.setLayout(new GridLayout(5, false));
		connect.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label hostLabel = UIUtils.createControlLabel(connect, "����");
		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_END);
		hostLabel.setLayoutData(gd);

		hostText = new Text(connect, SWT.BORDER);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		hostText.setLayoutData(gd);
//		hostText.setText(config);

		UIUtils.createControlLabel(connect, "�˿�");
		portText = new Text(connect, SWT.BORDER);
		gd = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
		gd.widthHint = 40;
		portText.setLayoutData(gd);

		UIUtils.createControlLabel(connect, "���ݿ�");
		databaseNameCombo = new Combo(connect, SWT.DROP_DOWN);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 4;
		databaseNameCombo.setLayoutData(gd);
		
		UIUtils.createControlLabel(connect, "�û���");
		userNameText = new Text(connect, SWT.BORDER);
		userNameText.setLayoutData(gd);
		
		UIUtils.createControlLabel(connect, "����");
		passwordText = new Text(connect, SWT.BORDER);
		passwordText.setLayoutData(gd);

	}
	
	@Override
	public boolean isPageComplete() {
		// TODO Auto-generated method stub
		return super.isPageComplete();
	}
	
	

}
