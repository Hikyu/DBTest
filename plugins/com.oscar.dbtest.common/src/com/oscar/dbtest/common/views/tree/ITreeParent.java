package com.oscar.dbtest.common.views.tree;

public interface ITreeParent<T> {
	public void addChild(T child);

	public void removeChild(T child);

	public T[] getChildren();

	public boolean hasChildren();
}
