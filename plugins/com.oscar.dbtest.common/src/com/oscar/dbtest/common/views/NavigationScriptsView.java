package com.oscar.dbtest.common.views;

import java.io.File;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.editors.text.EditorsUI;
import org.eclipse.ui.ide.FileStoreEditorInput;
import org.eclipse.ui.part.ViewPart;

import com.oscar.dbtest.common.model.INavigationTreeListener;
import com.oscar.dbtest.common.model.NodeEvent;
import com.oscar.dbtest.common.model.Project;
import com.oscar.dbtest.common.model.ProjectRegister;
import com.oscar.dbtest.common.views.editor.ScriptEditor;
import com.oscar.dbtest.common.views.tree.FileModifiedLabelProvider;
import com.oscar.dbtest.common.views.tree.ScriptsTreeContentProvider;
import com.oscar.dbtest.common.views.tree.ScriptsTreeLabelProvider;

public class NavigationScriptsView extends ViewPart implements INavigationTreeListener {

	public static final String ID = "com.oscar.dbtest.common.views.NavigationScriptsView";

	private CheckboxTreeViewer viewer;

	public NavigationScriptsView() {
		ProjectRegister.getInstance().addListener(this);
	}

	@Override
	public void createPartControl(Composite parent) {
		int style = SWT.H_SCROLL | SWT.V_SCROLL | SWT.MULTI;
		viewer = new CheckboxTreeViewer(parent, style);
		viewer.getTree().setHeaderVisible(true);
		viewer.setContentProvider(new ScriptsTreeContentProvider());
		initColumn(viewer);
		viewer.setInput(ProjectRegister.getInstance().getRoot());
		getViewSite().setSelectionProvider(viewer);
		initListeners(viewer);
		hookContextMenu();

	}

	private void initColumn(final CheckboxTreeViewer treeViewer) {
		TreeViewerColumn mainColumn = new TreeViewerColumn(treeViewer, SWT.NONE);
		mainColumn.getColumn().setText("Name");
		mainColumn.getColumn().setWidth(300);
		mainColumn.setLabelProvider(new DelegatingStyledCellLabelProvider(new ScriptsTreeLabelProvider()));

		TreeViewerColumn modifiedColumn = new TreeViewerColumn(treeViewer, SWT.NONE);
		modifiedColumn.getColumn().setText("Last Modified");
		modifiedColumn.getColumn().setWidth(100);
		modifiedColumn.getColumn().setAlignment(SWT.RIGHT);
		modifiedColumn.setLabelProvider(new DelegatingStyledCellLabelProvider(new FileModifiedLabelProvider()));
	}

	private void initListeners(final CheckboxTreeViewer treeViewer) {
		treeViewer.addCheckStateListener(new ICheckStateListener() {
			@Override
			public void checkStateChanged(final CheckStateChangedEvent event) {
				BusyIndicator.showWhile(Display.getDefault(), new Runnable() {

					@Override
					public void run() {
						treeViewer.setSubtreeChecked(event.getElement(), event.getChecked());
					}

				});
			}
		});

		treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection structSel = (IStructuredSelection) event.getSelection();
				if (!structSel.isEmpty()) {
					String desc = "";
					Object element = structSel.getFirstElement();
					if (element instanceof Project) {
						desc = ((Project) element).getName();
					}
					if (element instanceof File) {
						desc = ((File) element).getAbsolutePath();
					}
					getViewSite().getActionBars().getStatusLineManager().setMessage(desc);
				}
			}

		});

		viewer.addDoubleClickListener(new IDoubleClickListener() {

			@Override
			public void doubleClick(DoubleClickEvent event) {
				IStructuredSelection structSel = (IStructuredSelection) event.getSelection();
				if (!structSel.isEmpty()) {
					Object element = structSel.getFirstElement();
					if (element instanceof File) {
						File file = (File)element;
						if (file.isFile()) {
							IWorkbenchPage page = getViewSite().getPage();
							IFileStore fileStore;
							try {
								fileStore = EFS.getStore( file.toURI() );
								IEditorInput input = new FileStoreEditorInput(fileStore);
								page.openEditor(input, ScriptEditor.ID);
							} catch (CoreException e) {
								e.printStackTrace();
							}
						}
					}
				}

			}
		});
	}

	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager();
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);
	}

	@Override
	public void setFocus() {
		viewer.getControl().setFocus();
	}

	@Override
	public void nodeChanged(NodeEvent event) {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				viewer.refresh();
			}
		});
	}

	// private CheckboxTreeViewer viewer;
	// private DrillDownAdapter drillDownAdapter;
	// private Action action1;
	// private Action action2;
	// private Action doubleClickAction;
	//
	// /*
	// * The content provider class is responsible for providing objects to the
	// * view. It can wrap existing objects in adapters or simply return objects
	// * as-is. These objects may be sensitive to the current input of the view,
	// * or ignore it and always show the same content (like Task List, for
	// * example).
	// */
	//
	// class TreeObject implements IAdaptable {
	// private String name;
	// private TreeParent parent;
	//
	// public TreeObject(String name) {
	// this.name = name;
	// }
	//
	// public String getName() {
	// return name;
	// }
	//
	// public void setParent(TreeParent parent) {
	// this.parent = parent;
	// }
	//
	// public TreeParent getParent() {
	// return parent;
	// }
	//
	// public String toString() {
	// return getName();
	// }
	//
	// public <T> T getAdapter(Class<T> key) {
	// return null;
	// }
	// }
	//
	// class TreeParent extends TreeObject {
	// private ArrayList children;
	//
	// public TreeParent(String name) {
	// super(name);
	// children = new ArrayList();
	// }
	//
	// public void addChild(TreeObject child) {
	// children.add(child);
	// child.setParent(this);
	// }
	//
	// public void removeChild(TreeObject child) {
	// children.remove(child);
	// child.setParent(null);
	// }
	//
	// public TreeObject[] getChildren() {
	// return (TreeObject[]) children.toArray(new TreeObject[children.size()]);
	// }
	//
	// public boolean hasChildren() {
	// return children.size() > 0;
	// }
	// }
	//
	// class ViewContentProvider implements IStructuredContentProvider,
	// ITreeContentProvider {
	// private TreeParent invisibleRoot;
	//
	// public void inputChanged(Viewer v, Object oldInput, Object newInput) {
	// }
	//
	// public void dispose() {
	// }
	//
	// public Object[] getElements(Object parent) {
	// if (parent.equals(getViewSite())) {
	// if (invisibleRoot == null)
	// initialize();
	// return getChildren(invisibleRoot);
	// }
	// return getChildren(parent);
	// }
	//
	// public Object getParent(Object child) {
	// if (child instanceof TreeObject) {
	// return ((TreeObject) child).getParent();
	// }
	// return null;
	// }
	//
	// public Object[] getChildren(Object parent) {
	// if (parent instanceof TreeParent) {
	// return ((TreeParent) parent).getChildren();
	// }
	// return new Object[0];
	// }
	//
	// public boolean hasChildren(Object parent) {
	// if (parent instanceof TreeParent)
	// return ((TreeParent) parent).hasChildren();
	// return false;
	// }
	//
	// /*
	// * We will set up a dummy model to initialize tree heararchy. In a real
	// * code, you will connect to a real model and expose its hierarchy.
	// */
	// private void initialize() {
	// TreeObject to1 = new TreeObject("Leaf 1");
	// TreeObject to2 = new TreeObject("Leaf 2");
	// TreeObject to3 = new TreeObject("Leaf 3");
	// TreeParent p1 = new TreeParent("Parent 1");
	// p1.addChild(to1);
	// p1.addChild(to2);
	// p1.addChild(to3);
	//
	// TreeObject to4 = new TreeObject("Leaf 4");
	// TreeParent p2 = new TreeParent("Parent 2");
	// p2.addChild(to4);
	//
	// TreeParent root = new TreeParent("Root");
	// root.addChild(p1);
	// root.addChild(p2);
	//
	// invisibleRoot = new TreeParent("");
	// invisibleRoot.addChild(root);
	// }
	// }
	//
	// class ViewLabelProvider extends LabelProvider {
	//
	// public String getText(Object obj) {
	// return obj.toString();
	// }
	//
	// public Image getImage(Object obj) {
	// String imageKey = ISharedImages.IMG_OBJ_ELEMENT;
	// if (obj instanceof TreeParent)
	// imageKey = ISharedImages.IMG_OBJ_FOLDER;
	// return PlatformUI.getWorkbench().getSharedImages().getImage(imageKey);
	// }
	// }
	//
	// class NameSorter extends ViewerSorter {
	// }
	//
	// /**
	// * The constructor.
	// */
	// public NavigationScriptsView() {
	// }
	//
	// /**
	// * This is a callback that will allow us to create the viewer and
	// initialize
	// * it.
	// */
	// public void createPartControl(Composite parent) {
	// viewer = new CheckboxTreeViewer(parent, SWT.MULTI | SWT.H_SCROLL |
	// SWT.V_SCROLL);
	// drillDownAdapter = new DrillDownAdapter(viewer);
	// viewer.setContentProvider(new ViewContentProvider());
	// viewer.setLabelProvider(new ViewLabelProvider());
	// viewer.setSorter(new NameSorter());
	// viewer.setInput(getViewSite());
	// getSite().setSelectionProvider(viewer);
	// makeActions();
	// hookContextMenu();
	// hookDoubleClickAction();
	// // contributeToActionBars();
	// }
	//
	// private void hookContextMenu() {
	// MenuManager menuMgr = new MenuManager("#PopupMenu");
	// menuMgr.setRemoveAllWhenShown(true);
	// menuMgr.addMenuListener(new IMenuListener() {
	// public void menuAboutToShow(IMenuManager manager) {
	// NavigationScriptsView.this.fillContextMenu(manager);
	// }
	// });
	// Menu menu = menuMgr.createContextMenu(viewer.getControl());
	// viewer.getControl().setMenu(menu);
	// getSite().registerContextMenu(menuMgr, viewer);
	// }
	//
	// private void contributeToActionBars() {
	// IActionBars bars = getViewSite().getActionBars();
	// fillLocalPullDown(bars.getMenuManager());
	// fillLocalToolBar(bars.getToolBarManager());
	// }
	//
	// private void fillLocalPullDown(IMenuManager manager) {
	// manager.add(action1);
	// manager.add(new Separator());
	// manager.add(action2);
	// }
	//
	// private void fillContextMenu(IMenuManager manager) {
	// manager.add(action1);
	// manager.add(action2);
	// manager.add(new Separator());
	// drillDownAdapter.addNavigationActions(manager);
	// // Other plug-ins can contribute there actions here
	// manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	// }
	//
	// private void fillLocalToolBar(IToolBarManager manager) {
	// manager.add(action1);
	// manager.add(action2);
	// manager.add(new Separator());
	// drillDownAdapter.addNavigationActions(manager);
	// }
	//
	// private void makeActions() {
	// action1 = new Action() {
	// public void run() {
	// showMessage("Action 1 executed");
	// }
	// };
	// action1.setText("Action 1");
	// action1.setToolTipText("Action 1 tooltip");
	// action1.setImageDescriptor(
	// PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
	//
	// action2 = new Action() {
	// public void run() {
	// showMessage("Action 2 executed");
	// }
	// };
	// action2.setText("Action 2");
	// action2.setToolTipText("Action 2 tooltip");
	// action2.setImageDescriptor(
	// PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
	// doubleClickAction = new Action() {
	// public void run() {
	// ISelection selection = viewer.getSelection();
	// Object obj = ((IStructuredSelection) selection).getFirstElement();
	// showMessage("Double-click detected on " + obj.toString());
	// }
	// };
	// }
	//
	// private void hookDoubleClickAction() {
	// viewer.addDoubleClickListener(new IDoubleClickListener() {
	// public void doubleClick(DoubleClickEvent event) {
	// doubleClickAction.run();
	// }
	// });
	// }
	//
	// private void showMessage(String message) {
	// MessageDialog.openInformation(viewer.getControl().getShell(), "½Å±¾µ¼º½",
	// message);
	// }
	//
	// /**
	// * Passing the focus request to the viewer's control.
	// */
	// public void setFocus() {
	// viewer.getControl().setFocus();
	// }
}
