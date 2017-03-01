/*
 * DBeaver - Universal Database Manager
 * Copyright (C) 2010-2016 Serge Rieder (serge@jkiss.org)
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License (version 2)
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package com.oscar.dbtest.common.model;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * UI Utils
 */
public class UIUtils {

	public static final String DEFAULT_TIMESTAMP_PATTERN = "yyyy.MM.dd HH:mm"; //$NON-NLS-1$
	public static final String INLINE_WIDGET_EDITOR_ID = "org.jkiss.dbeaver.ui.InlineWidgetEditor"; //$NON-NLS-1$

	public static Text createLabelText(Composite parent, String label, String value) {
		return createLabelText(parent, label, value, SWT.BORDER);
	}

	public static Text createLabelText(Composite parent, String label, String value, int style) {
		return createLabelText(parent, label, value, style, new GridData(GridData.FILL_HORIZONTAL));
	}

	public static Text createLabelText(Composite parent, String label, String value, int style, Object layoutData) {
		createControlLabel(parent, label);

		Text text = new Text(parent, style);
		if (value != null) {
			text.setText(value);
		}

		if (layoutData != null) {
			text.setLayoutData(layoutData);
		}

		return text;
	}

	public static Label createControlLabel(Composite parent, String label) {
		Label textLabel = new Label(parent, SWT.NONE);
		textLabel.setText(label + ": "); //$NON-NLS-1$
		textLabel.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_CENTER));
		return textLabel;
	}

	public static Composite createPlaceholder(Composite parent, int columns) {
		return createPlaceholder(parent, columns, 0);
	}

	public static Composite createPlaceholder(Composite parent, int columns, int spacing) {
		Composite ph = new Composite(parent, SWT.NONE);
		GridLayout gl = new GridLayout(columns, false);
		gl.verticalSpacing = spacing;
		gl.horizontalSpacing = spacing;
		gl.marginHeight = 0;
		gl.marginWidth = 0;
		ph.setLayout(gl);
		return ph;
	}
	
	public static Group createControlGroup(Composite parent, String label, int columns, int layoutStyle, int widthHint)
    {
        Group group = new Group(parent, SWT.NONE);
        group.setText(label);

        GridData gd = new GridData(layoutStyle);
        if (widthHint > 0) {
            gd.widthHint = widthHint;
        }
        group.setLayoutData(gd);

        GridLayout gl = new GridLayout(columns, false);
        group.setLayout(gl);

        return group;
    }

}
