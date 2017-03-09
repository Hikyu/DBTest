package com.oscar.dbtest.common;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.IPlaceholderFolderLayout;

import com.oscar.dbtest.common.views.NavigationScriptsView;
import com.oscar.dbtest.common.views.NormalTestView;
import com.oscar.dbtest.common.views.PerformanceTestView;
import com.oscar.dbtest.common.views.editor.ScriptEditor;

public class Perspective implements IPerspectiveFactory {

	public static final String FOLDER_NAVIGATION = "navigation";
	public static final String FOLDER_CONFIG = "config";
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
        layout.getViewLayout(NavigationScriptsView.ID).setCloseable(false);//ÆÁ±Î¹Ø±Õ°´Å¥
        
//        IFolderLayout bottomLeft = layout.createFolder(
//                FOLDER_CONFIG,
//                IPageLayout.BOTTOM,
//                0.7f,
//                FOLDER_NAVIGATION);
//       bottomLeft.addPlaceholder(ScriptEditor.ID);
//        
        IPlaceholderFolderLayout right = layout.createPlaceholderFolder(
        		FOLDER_PROCESS,
                IPageLayout.BOTTOM,
                0.60f,
                editorArea);
        right.addPlaceholder(NormalTestView.ID);
        right.addPlaceholder(PerformanceTestView.ID);
        
//        IFolderLayout bottomRight = layout.createFolder(
//        		FOLDER_PROCESS,
//                IPageLayout.BOTTOM,
//                0.4f,
//                FOLDER_DETAIL);
//        bottomRight.addPlaceholder("com.oscar.dbtest.tool.routine.ScriptsToRunView");
//        bottomRight.addView(ScriptsToRunView.ID);
	}

}
