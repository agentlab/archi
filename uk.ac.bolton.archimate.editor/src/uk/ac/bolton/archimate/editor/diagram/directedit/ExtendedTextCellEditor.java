/*******************************************************************************
 * Copyright (c) 2011 Bolton University, UK.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the License
 * which accompanies this distribution in the file LICENSE.txt
 *******************************************************************************/
package uk.ac.bolton.archimate.editor.diagram.directedit;

import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.swt.widgets.Composite;


/**
 * Text Cell Editor that monitors Paste enablement for Text on clipboard
 * 
 * @author Phillip Beauvoir
 */
public class ExtendedTextCellEditor extends TextCellEditor {
    
    public ExtendedTextCellEditor(Composite composite) {
        super(composite);
    }

    public ExtendedTextCellEditor(Composite composite, int style) {
        super(composite, style);
    }
    
    @Override
    public boolean isPasteEnabled() {
        if(!super.isPasteEnabled()) {
            return false;
        }
        
        boolean isPasteEnabled = false;
        
        if(text.getEditable()) {
            Clipboard clipboard = new Clipboard(text.getDisplay());
            TransferData[] td = clipboard.getAvailableTypes();
            for(int i = 0; i < td.length; ++i) {
                if(TextTransfer.getInstance().isSupportedType(td[i])) {
                    isPasteEnabled = true;
                    break;
                }
            }
            
            clipboard.dispose();
        }
        
        return isPasteEnabled;
    }
}
