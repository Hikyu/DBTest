package com.oscar.dbtest.common.views.tree;

import java.io.File;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Image;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import com.oscar.dbtest.common.model.Project;

public class ScriptsTreeLabelProvider extends LabelProvider implements IStyledLabelProvider {
	private ResourceManager resourceManager;
	private static final String directoryImgPath = "icons/directory.png";
	private static final String projectImgPath = "icons/project.png";
	private static final String fileImgPath = "icons/file.png";
	
	@Override
	public StyledString getStyledText(Object element) {
		StyledString styledString = null;
		if (element instanceof Project) {
			Project project = (Project) element;
			styledString = new StyledString(project.getName());
		}
		if (element instanceof File) {
			File file = (File) element;
			styledString = new StyledString(getFileName(file));
			String[] files = file.list();
			if (files != null) {
				styledString.append(" ( " + files.length + " ) ", StyledString.COUNTER_STYLER);
			}
		}
		return styledString;
	}

	@Override
	public Image getImage(Object element) {
		ImageDescriptor image = null;
		if (element instanceof File) {
			if (((File) element).isDirectory()) {
				image = createImageDescriptor(directoryImgPath);
			} else {
				image = createImageDescriptor(fileImgPath);
			}
		}
		if (element instanceof Project) {
			image = createImageDescriptor(projectImgPath);
		}
		return getResourceManager().createImage(image);
	}

	private String getFileName(File file) {
		String name = file.getName();
		return name.isEmpty() ? file.getPath() : name;
	}

	private ResourceManager getResourceManager() {
		if (resourceManager == null) {
			resourceManager = new LocalResourceManager(JFaceResources.getResources());
		}
		return resourceManager;
	}

	private ImageDescriptor createImageDescriptor(String path) {
		Bundle bundle = FrameworkUtil.getBundle(ScriptsTreeLabelProvider.class);
		URL url = FileLocator.find(bundle, new Path(path), null);
		return ImageDescriptor.createFromURL(url);
	}
}
