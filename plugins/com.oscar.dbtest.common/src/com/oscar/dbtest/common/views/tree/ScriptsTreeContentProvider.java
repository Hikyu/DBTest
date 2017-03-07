package com.oscar.dbtest.common.views.tree;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.oscar.dbtest.common.model.Project;

public class ScriptsTreeContentProvider implements IStructuredContentProvider, ITreeContentProvider {
	private FilenameFilter filter;

	@Override
	public void dispose() {
		// TODO 自动生成的方法存根

	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO 自动生成的方法存根

	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof ProjectRoot) {
			ProjectRoot root = (ProjectRoot) parentElement;
			return root.getChildren();
		}
		if (parentElement instanceof Project) {
			Project project = (Project) parentElement;
			List<String> paths = project.getPaths();
			List<File> files = new ArrayList<>();
			for (String path : paths) {
				files.add(new File(path));
			}
			return files.toArray();
		}
		File file = (File) parentElement;
		return file.listFiles(getFilter());
	}

	private FilenameFilter getFilter() {
		if (filter == null) {
			filter = new FilenameFilter() {

				@Override
				public boolean accept(File dir, String name) {
					File file = new File(dir, name);
					if (file.isDirectory()) {
						if (name.endsWith(".")) {//除去  .  .. 目录
							return false;
						}
						return true;
					}
					if (name.endsWith(".run")) {
						return true;
					}
					return false;
				}
			};
		}
		return filter;
	}

	@Override
	public Object getParent(Object element) {
		if (element instanceof Project || element instanceof ProjectRoot) {
			return null;
		}
		File file = (File) element;
		// TODO project?
		return file.getParentFile();
	}

	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof ProjectRoot) {
			ProjectRoot root = (ProjectRoot) element;
			return root.getProjects().isEmpty() ? false : true;
		}
		if (element instanceof Project) {
			Project project = (Project) element;
			return project.getPaths().size() > 0 ? true : false;
		}
		File file = (File) element;
		if (file.isDirectory()) {
			return true;
		}
		return false;

	}

	@Override
	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
	}

}
