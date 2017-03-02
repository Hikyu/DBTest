package com.oscar.dbtest.common.model;

import java.util.ArrayList;
import java.util.List;

import com.oscar.dbtest.common.model.NodeEvent.Action;
import com.oscar.dbtest.common.views.tree.ProjectRoot;

public class ProjectRegister {
	private static ProjectRegister instance = new ProjectRegister();
	private ProjectRoot root;
	private List<INavigationTreeListener> listeners;

	private ProjectRegister() {
		root = new ProjectRoot();
		listeners = new ArrayList<>();
		
		
		Project project = new Project("≤‚ ‘");
		project.addPath("Y:\\code\\cy\\DBTestTool\\user\\scripts");
		project.addPath("Y:\\code\\cy\\DBTestTool\\user\\dboption_scripts");
		root.addProject(project);
	}

	public static ProjectRegister getInstance() {
		return instance;
	}

	public ProjectRoot getRoot() {
		return root;
	}
	
	public List<Project> getProjects() {
		return root.getProjects();
	}

	public void addProject(Project project) {
		root.addProject(project);
		notifyListeners(new NodeEvent(project, Action.ADD));
	}

	public void removeProject(Project project) {
		root.removeProject(project.getId());
		notifyListeners(new NodeEvent(project, Action.REMOVE));
	}
	
	public void refershProjects() {
		notifyListeners(new NodeEvent(new Object(), Action.REFERSH));
	}
	
	private void notifyListeners(NodeEvent event) {
		for (INavigationTreeListener listener : listeners) {
			listener.nodeChanged(event);
		}
	}
	
	public void addListener(INavigationTreeListener listener) {
		listeners.add(listener);
	}
	
	public void removeListener(INavigationTreeListener listener) {
		listeners.remove(listener);
	}
}
