/*******************************************************************************
 * Copyright (c) 2010 Bolton University, UK.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the License
 * which accompanies this distribution in the file LICENSE.txt
 *******************************************************************************/
package uk.ac.bolton.archimate.editor.diagram.figures;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Color;

import uk.ac.bolton.archimate.model.IDiagramModelObject;




/**
 * Figure that is backed by an IDiagramModelObject
 * 
 * @author Phillip Beauvoir
 */
public interface IDiagramModelObjectFigure extends IFigure {
    
    /**
     * @return The DiagramModelObject
     */
    IDiagramModelObject getDiagramModelObject();
    
    /**
     * Refresh the visual figure according to the model
     */
    void refreshVisuals();
    
    /**
     * @return The figure used to show text
     */
    IFigure getTextControl();
    
    /**
     * @param requestLoc
     * @return True if requestLoc is in the Text Control
     */
    boolean didClickTextControl(Point requestLoc);
    
    /**
     * Dispose of any resources
     */
    void dispose();
    
    /**
     * @return The default Size for this object
     */
    Dimension getDefaultSize();

    /**
     * @return The Fill Color if any
     */
    Color getFillColor();
}
