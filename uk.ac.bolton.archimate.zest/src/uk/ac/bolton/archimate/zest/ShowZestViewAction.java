/*******************************************************************************
 * Copyright (c) 2010-12 Bolton University, UK.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the License
 * which accompanies this distribution in the file LICENSE.txt
 *******************************************************************************/
package uk.ac.bolton.archimate.zest;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import uk.ac.bolton.archimate.editor.ui.services.ViewManager;


/**
 * Show Zest View wAction
 * 
 * @author Phillip Beauvoir
 */
public class ShowZestViewAction implements IWorkbenchWindowActionDelegate {

    public void dispose() {
    }

    public void init(IWorkbenchWindow window) {
    }

    public void run(IAction action) {
        ViewManager.toggleViewPart(IZestView.ID, false);
    }

    public void selectionChanged(IAction action, ISelection selection) {
    }

}
