package com.oscar.dbtest.common.views.tree;

import java.io.File;
import java.text.DateFormat;
import java.util.Date;

import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;

import com.oscar.dbtest.common.model.Project;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StyledString;

public class FileModifiedLabelProvider extends LabelProvider implements IStyledLabelProvider {

	private static final DateFormat dateLabelFormat = DateFormat.getDateInstance();

	@Override
	public StyledString getStyledText(Object element) {
		StyledString styledString = null;
		if (element instanceof Project) {
			styledString = new StyledString("");
		}
		if (element instanceof File) {
			File file = (File) element;
			long lastModified = file.lastModified();
			styledString = new StyledString(dateLabelFormat.format(new Date(lastModified)));
		}
		return styledString;
	}
}