/*******************************************************************************
 * Copyright (c) 2010 Bolton University, UK.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the License
 * which accompanies this distribution in the file LICENSE.txt
 *******************************************************************************/
package uk.ac.bolton.archimate.editor.propertysections;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

import uk.ac.bolton.archimate.editor.ui.components.StyledTextActionHandler;
import uk.ac.bolton.archimate.editor.views.properties.ICustomPropertiesView;


/**
 * Wrapper Control for SyledText Control to show hints and global action handler
 * 
 * @author Phillip Beauvoir
 */
public class PropertySectionStyledTextControl extends PropertySectionTextControl {
    
    private StyledText fTextControl;
    private IActionBars fActionBars;
    private StyledTextActionHandler fTextActionHandler;
    
    public PropertySectionStyledTextControl(StyledText text, EStructuralFeature feature) {
        fTextControl = text;
        init(feature);
    }
    
    @Override
    public StyledText getTextControl() {
        return fTextControl;
    }
    
    @Override
    protected void activate() {
        super.activate();
        
        // TODO - a better way to get the IViewSite of the Properties View
        IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
        IViewPart part = page.findView(ICustomPropertiesView.ID);
        fActionBars = part.getViewSite().getActionBars();
        
        fTextActionHandler = new StyledTextActionHandler(fActionBars);
        fTextActionHandler.addText(getTextControl());
        fActionBars.updateActionBars();
    }

    @Override
    protected void deactivate() {
        super.deactivate();
        
        if(fTextActionHandler != null) {
            fTextActionHandler.removeText(getTextControl());
            fTextActionHandler.dispose();
        }
    }
    
    @Override
    protected String getText() {
        return getTextControl().getText();
    }
    
    @Override
    protected void setText(String s) {
        getTextControl().setText(s);
    }
}
