package com.oscar.dbtest.tool.routine.perspective;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.IPlaceholderFolderLayout;

import com.oscar.dbtest.common.views.NavigationScriptsView;

public class DBTestPerspective implements IPerspectiveFactory {
	public static final String FOLDER_NAVIGATION = "navigation";
	public static final String FOLDER_DETAIL = "detail";
	public static final String FOLDER_PROCESS = "process";
	@Override
	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
        //layout.setEditorAreaVisible(false);

        // Navigator
        IFolderLayout treeFolder = layout.createFolder(
            FOLDER_NAVIGATION,
            IPageLayout.LEFT,
            0.30f,
            editorArea);
        treeFolder.addView(NavigationScriptsView.ID);
        
        IPlaceholderFolderLayout right = layout.createPlaceholderFolder(
                FOLDER_DETAIL,
                IPageLayout.RIGHT,
                0.8f,
                editorArea);
        right.addPlaceholder("script content");
        
        IFolderLayout bottomRight = layout.createFolder(
        		FOLDER_PROCESS,
                IPageLayout.BOTTOM,
                0.4f,
                FOLDER_DETAIL);
        bottomRight.addPlaceholder("script ShellProcess");
	}

}
