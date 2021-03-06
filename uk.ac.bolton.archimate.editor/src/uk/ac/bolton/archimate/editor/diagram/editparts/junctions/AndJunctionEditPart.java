/*******************************************************************************
 * Copyright (c) 2010 Bolton University, UK.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the License
 * which accompanies this distribution in the file LICENSE.txt
 *******************************************************************************/
package uk.ac.bolton.archimate.editor.diagram.editparts.junctions;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;

import uk.ac.bolton.archimate.editor.diagram.figures.junctions.AndJunctionFigure;

/**
 * Junction Edit Part
 * 
 * @author Phillip Beauvoir
 */
public class AndJunctionEditPart
extends JunctionEditPart {            
    
    @Override
    protected IFigure createFigure() {
        return new AndJunctionFigure(getModel());
    }

    @Override
    protected ConnectionAnchor getDefaultConnectionAnchor() {
        if(fAnchor == null) {
            fAnchor = new ChopboxAnchor(getFigure());
        }
        return fAnchor;
    }
}