package com.oscar.dbtest.common.model;

import java.util.UUID;

public class ProjectContainer {
	private UUID id;
	private String name;
	private String path;
	private String description;

	@SuppressWarnings("unused")
	private ProjectContainer(){}
	
	public ProjectContainer(String name) {
		id = UUID.randomUUID();
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getId() {
		return id.toString();
	}

}
