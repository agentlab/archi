/*******************************************************************************
 * Copyright (c) 2010 Bolton University, UK.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the License
 * which accompanies this distribution in the file LICENSE.txt
 *******************************************************************************/
package uk.ac.bolton.archimate.editor.diagram.editparts.business;

import org.eclipse.draw2d.IFigure;

import uk.ac.bolton.archimate.editor.diagram.editparts.AbstractArchimateEditableTextFlowEditPart;
import uk.ac.bolton.archimate.editor.diagram.figures.business.BusinessInteractionFigure;

/**
 * Business Interaction Edit Part
 * 
 * @author Phillip Beauvoir
 */
public class BusinessInteractionEditPart
extends AbstractArchimateEditableTextFlowEditPart {            
    
    @Override
    protected IFigure createFigure() {
        return new BusinessInteractionFigure(getModel());
    }
 
}