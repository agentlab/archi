/*******************************************************************************
 * Copyright (c) 2010 Bolton University, UK.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the License
 * which accompanies this distribution in the file LICENSE.txt
 *******************************************************************************/
package uk.ac.bolton.archimate.editor.views.tree.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.IStructuredSelection;


/**
 * IViewerAction
 * 
 * @author Phillip Beauvoir
 */
public interface IViewerAction extends IAction {
    
    void update(IStructuredSelection selection);
    
}
