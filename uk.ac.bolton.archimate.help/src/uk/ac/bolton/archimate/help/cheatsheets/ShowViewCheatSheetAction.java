/*******************************************************************************
 * Copyright (c) 2010 Bolton University, UK.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the License
 * which accompanies this distribution in the file LICENSE.txt
 *******************************************************************************/
package uk.ac.bolton.archimate.help.cheatsheets;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.cheatsheets.ICheatSheetAction;
import org.eclipse.ui.cheatsheets.ICheatSheetManager;

import uk.ac.bolton.archimate.editor.ui.services.ViewManager;


/**
 * Action to programmatically show a View from a Cheat Sheet
 * 
 * @author Phillip Beauvoir
 */
public class ShowViewCheatSheetAction
extends Action
implements ICheatSheetAction {
    
    public void run(String[] params, ICheatSheetManager manager) {
        if(params != null && params.length > 0) {
            ViewManager.showViewPart(params[0], true);
        }
    }
}
