package com.oscar.dbtest.common.dialog;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;

import com.oscar.dbtest.common.model.Config;
import com.oscar.dbtest.common.views.UIUtils;

public class TestConfigPage extends WizardPage {
	private Config config;
	/*���ܲ���*/
	private Button setUpBtn;
	private Button testBtn;
	private Text timeoutText;
	private Button replaceParamBtn;
	private Button formatSqlBtn;
	private Button generateResultFileBtn;
	
	/*���ܲ���*/
	private Button orderBtn;
	private Button concurrentBtn;
	private Text connectNumText;
	private Text clientNumText;
	private Text scriptRunNumText;

	protected TestConfigPage(Config config) {
		super("��Ŀ����");
		setTitle("��Ŀ����");
		setDescription("���ù��ܲ��Ժ����ܲ���");
		this.config = config;
	}

	@Override
	public void createControl(Composite parent) {
		Composite parentGroup = new Composite(parent, SWT.NONE);
		parentGroup.setLayout(new GridLayout(1, false));
		parentGroup.setLayoutData(new GridData(GridData.FILL_BOTH));

		TabFolder tabFolder = new TabFolder(parentGroup, SWT.TOP | SWT.MULTI);
		tabFolder.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		createNormalTestGroup(tabFolder);
		createPerformanceGroup(tabFolder);
		tabFolder.setSelection(0);
		setControl(parentGroup);
	}

	private void createNormalTestGroup(TabFolder tabFolder) {
		TabItem normalTest = new TabItem(tabFolder, SWT.NONE);
		normalTest.setText("���ܲ���");

		Composite testContainer = new Composite(tabFolder, SWT.NONE);
		testContainer.setLayout(new GridLayout(1, false));
		testContainer.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		normalTest.setControl(testContainer);

		final Group testModel = UIUtils.createControlGroup(testContainer, "����ģʽ", 2, GridData.FILL_HORIZONTAL, 0);
		setUpBtn = new Button(testModel, SWT.RADIO);
		setUpBtn.setText("setUp");
		testBtn = new Button(testModel, SWT.RADIO);
		testBtn.setText("test");

		timeoutText = UIUtils.createLabelText(testContainer, "���ӳ�ʱ", "10");

		Composite btnContainer = new Composite(testContainer, SWT.NONE);
		btnContainer.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		btnContainer.setLayout(new GridLayout(3, false));
		
		formatSqlBtn = new Button(btnContainer, SWT.CHECK);
		formatSqlBtn.setText("��ʽ��sql");
		
		generateResultFileBtn = new Button(btnContainer, SWT.CHECK);
		generateResultFileBtn.setText("ֱ�Ӳ����ױ��ļ�");
		
		replaceParamBtn = new Button(btnContainer, SWT.CHECK);
		replaceParamBtn.setText("�滻����");
		
		// UIUtils.createLabelText(parent, label, value)
	}

	private void createPerformanceGroup(TabFolder tabFolder) {
		TabItem performanceTest = new TabItem(tabFolder, SWT.NONE);
		performanceTest.setText("���ܲ���");

		Composite testContainer = new Composite(tabFolder, SWT.NONE);
		testContainer.setLayout(new GridLayout(1, false));
		testContainer.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		performanceTest.setControl(testContainer);
		
		final Group testModel = UIUtils.createControlGroup(testContainer, "����ģʽ", 2, GridData.FILL_HORIZONTAL, 0);
		orderBtn = new Button(testModel, SWT.RADIO);
		orderBtn.setText("˳��");
		concurrentBtn = new Button(testModel, SWT.RADIO);
		concurrentBtn.setText("����");
		
		Composite textContainer = new Composite(testContainer, SWT.NONE);
		textContainer.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		textContainer.setLayout(new GridLayout(2, false));
		
		clientNumText = UIUtils.createLabelText(textContainer, "ģ��ͻ���", "10");
		connectNumText = UIUtils.createLabelText(textContainer, "������", "10");
		scriptRunNumText = UIUtils.createLabelText(textContainer, "�ű�ִ�д���", "10");

	}

}
