/*******************************************************************************
 * Copyright (c) 2012 Bolton University, UK.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the License
 * which accompanies this distribution in the file LICENSE.txt
 *******************************************************************************/
package uk.ac.bolton.archimate.editor.diagram.figures.extensions;

import org.eclipse.swt.graphics.Image;

import uk.ac.bolton.archimate.editor.ui.IArchimateImages;
import uk.ac.bolton.archimate.model.IDiagramModelArchimateObject;

/**
 * Figure for a Constraint
 * 
 * @author Phillip Beauvoir
 */
public class ConstraintFigure extends AbstractMotivationFigure {
    
    public ConstraintFigure(IDiagramModelArchimateObject diagramModelObject) {
        super(diagramModelObject);
    }

    @Override
    protected Image getImage() {
        return IArchimateImages.ImageFactory.getImage(IArchimateImages.ICON_CONSTRAINT_16);
    }
}