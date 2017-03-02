package com.oscar.dbtest.common.views.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.oscar.dbtest.common.model.Project;

public class ProjectRoot {
	private List<Project> projects;

	public ProjectRoot() {
		projects = new ArrayList<>();
	}

	public List<Project> getProjects() {
		return projects;
	}

	public Object[] getChildren() {
		return projects.toArray();
	}

	public void addProject(Project project) {
		projects.add(project);
	}

	public void removeProject(String id) {
		Iterator<Project> iterator = projects.iterator();
		while (iterator.hasNext()) {
			Project project = (Project) iterator.next();
			if (project.getId().equals(id)) {
				iterator.remove();
			}
		}
	}

}
