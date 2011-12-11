/*******************************************************************************
 * Copyright (c) 2010 Bolton University, UK.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the License
 * which accompanies this distribution in the file LICENSE.txt
 *******************************************************************************/
package uk.ac.bolton.archimate.editor.propertysections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import uk.ac.bolton.archimate.editor.utils.StringUtils;


/**
 * Wrapper Control for Text Control to show hints
 * 
 * @author Phillip Beauvoir
 */
public abstract class PropertySectionTextControl {
    
    private EObject fDataElement;
    private EStructuralFeature fFeature;
    
    private Color fTextForegroundColor;
    
    private String fHint;
    private boolean fHintShowing;
    
    private static final Color greyColor = new Color(null, 188, 188, 188);
    
    private Listener fTextControlListener = new TextControlListener();
    
    private class TextControlListener implements Listener {
        public void handleEvent(Event event) {
            switch(event.type) {
                case SWT.FocusIn:
                    activate();
                    break;
                case SWT.FocusOut:
                    deactivate();
                    break;
                default:
                    break;
            }
        }
    }
    
    protected void init(EStructuralFeature feature) {
        fFeature = feature;

        fTextForegroundColor = getTextControl().getForeground();
        
        // Use Focus events not Activate events. Activate events are sent twice.
        getTextControl().addListener(SWT.FocusIn, fTextControlListener);
        getTextControl().addListener(SWT.FocusOut, fTextControlListener);
        
        // Listen for Return keypress on SINGLE text controls
        if((getTextControl().getStyle() & SWT.SINGLE) != 0) {
            getTextControl().addListener(SWT.DefaultSelection, new Listener() {
                public void handleEvent(Event e) {
                    updateText();
                }
            });
        }
    }
    
    public void setHint(String hint) {
        fHint = hint;
    }
    
    public void refresh(EObject dataElement) {
        fDataElement = dataElement;
        
        String text = null;
        
        if(fDataElement != null) {
            text = (String)fDataElement.eGet(fFeature);
        }
        
        if(!StringUtils.isSet(text) && !getTextControl().isFocusControl()) { // Don't do this if text control has focus
            showHintText();
        }
        else if(!getText().equals(text)) {
            showNormalText(text);
        }
    }

    protected void activate() {
        if(fHintShowing) {
            showNormalText(""); // clear hint text
        }
    }

    protected void deactivate() {
        updateText();
        
        String newText = getText();
        if(!StringUtils.isSet(newText)) {
            showHintText();
        }
    }
    
    private void updateText() {
        String oldText = ""; // Text control has default of ""
        String newText = getText();
        
        if(fDataElement != null) {
            oldText = StringUtils.safeString((String)fDataElement.eGet(fFeature)); // compare like for like
        }
        
        if(!newText.equals(oldText)) {
            textChanged(oldText, newText);
        }
    }
    
    protected void showHintText() {
        if(fHint != null) {
            getTextControl().setForeground(greyColor);
            setText(fHint);
            fHintShowing = true;
        }
        else {
            setText(""); // clears previous text if no hint text
        }
    }
    
    protected void showNormalText(String text) {
        getTextControl().setForeground(fTextForegroundColor);
        setText(StringUtils.safeString(text));
        fHintShowing = false;
    }
    
    /**
     * Over-ride this to respond to text changes
     * @param oldText The old text in the text control
     * @param newText The new text in the text control
     */
    protected void textChanged(String oldText, String newText) {
    }

    /**
     * @return The actual Text Control used
     */
    public abstract Control getTextControl();
    
    /**
     * @return the text in the Text Control
     */
    protected abstract String getText();
    
    /**
     * Set the text in the Text Control
     * @param text
     */
    protected abstract void setText(String text);

}
