/*******************************************************************************
 * Copyright (c) 2010 Bolton University, UK.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the License
 * which accompanies this distribution in the file LICENSE.txt
 *******************************************************************************/
package uk.ac.bolton.archimate.editor.ui.components;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.part.CellEditorActionHandler;


/**
 * Manages activation and de-activation of CellEditorActionHandler
 * 
 * Hook into the Global Action Handlers
 * Note toolbar items don't work - https://bugs.eclipse.org/bugs/show_bug.cgi?id=321045
 * 
 * @author Phillip Beauvoir
 */
public class CellEditorActionHandlerManager {

    private CellEditorActionHandler fCellEditorActionHandler;
    private CellEditor fCellEditor;
    private IActionBars fActionBars;
    
    // Restore global actions
    private IAction copy, cut, paste, undo, redo, find, selectAll, delete;
    
    public CellEditorActionHandlerManager(IActionBars actionbars, CellEditor cellEditor) {
        fActionBars = actionbars;
        fCellEditor = cellEditor;

        saveCurrentActions(fActionBars);
        
        fCellEditorActionHandler = new CellEditorActionHandler(fActionBars);
        fCellEditorActionHandler.addCellEditor(fCellEditor);
        
        fActionBars.updateActionBars();
    }
    
    public void dispose() {
        if(fCellEditorActionHandler != null) {
            // Unhook and reset the Global Action Handlers
            fCellEditorActionHandler.dispose();
            fCellEditorActionHandler.removeCellEditor(fCellEditor);
            restoreSavedActions(fActionBars);
            fActionBars.updateActionBars();
        }
    }

    private void restoreSavedActions(IActionBars actionBars) {
        actionBars.setGlobalActionHandler(ActionFactory.COPY.getId(), copy);
        actionBars.setGlobalActionHandler(ActionFactory.PASTE.getId(), paste);
        actionBars.setGlobalActionHandler(ActionFactory.DELETE.getId(), delete);
        actionBars.setGlobalActionHandler(ActionFactory.SELECT_ALL.getId(), selectAll);
        actionBars.setGlobalActionHandler(ActionFactory.CUT.getId(), cut);
        actionBars.setGlobalActionHandler(ActionFactory.FIND.getId(), find);
        actionBars.setGlobalActionHandler(ActionFactory.UNDO.getId(), undo);
        actionBars.setGlobalActionHandler(ActionFactory.REDO.getId(), redo);
    }

    private void saveCurrentActions(IActionBars actionBars) {
        copy = actionBars.getGlobalActionHandler(ActionFactory.COPY.getId());
        paste = actionBars.getGlobalActionHandler(ActionFactory.PASTE.getId());
        delete = actionBars.getGlobalActionHandler(ActionFactory.DELETE.getId());
        selectAll = actionBars.getGlobalActionHandler(ActionFactory.SELECT_ALL.getId());
        cut = actionBars.getGlobalActionHandler(ActionFactory.CUT.getId());
        find = actionBars.getGlobalActionHandler(ActionFactory.FIND.getId());
        undo = actionBars.getGlobalActionHandler(ActionFactory.UNDO.getId());
        redo = actionBars.getGlobalActionHandler(ActionFactory.REDO.getId());
    }
}
