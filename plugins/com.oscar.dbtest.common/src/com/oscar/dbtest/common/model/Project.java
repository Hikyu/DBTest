package com.oscar.dbtest.common.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Project implements IProjectContainer {
	private UUID id;
	private String name;
	private List<String> paths;
	private String description;

	public Project(){
		id = UUID.randomUUID();
		paths = new ArrayList<>();
	}
	
	public Project(String name) {
		this();
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPaths(List<String> paths) {
		this.paths.addAll(paths);
	}

	@Override
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String getId() {
		return id.toString();
	}

	@Override
	public List<String> getPaths() {
		return paths;
	}
	
	public void addPath(String path) {
		paths.add(path);
	}


}
