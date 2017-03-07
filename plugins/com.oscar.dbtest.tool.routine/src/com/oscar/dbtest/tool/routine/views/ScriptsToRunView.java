package com.oscar.dbtest.tool.routine.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

public class ScriptsToRunView extends ViewPart {
	public static final String ID = "com.oscar.dbtest.tool.routine.ScriptsToRunView";

	public ScriptsToRunView() {
		// TODO 自动生成的构造函数存根
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout());
		Text text = new Text(parent, SWT.NONE);
		text.setText("hello");
	}

	@Override
	public void setFocus() {
		// TODO 自动生成的方法存根

	}

}
