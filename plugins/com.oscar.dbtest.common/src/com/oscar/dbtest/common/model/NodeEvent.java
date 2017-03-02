package com.oscar.dbtest.common.model;

public class NodeEvent {
	public enum Action {
		ADD, REMOVE, UPDATE,REFERSH
	}

	private Object source;
	private Action action;

	public NodeEvent(Object source, Action action) {
		this.action = action;
		this.source = source;
	}

	public Object getSource() {
		return source;
	}

	public Action getAction() {
		return action;
	}

}
