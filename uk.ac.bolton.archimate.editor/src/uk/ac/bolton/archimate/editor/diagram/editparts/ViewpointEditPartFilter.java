/*******************************************************************************
 * Copyright (c) 2011 Bolton University, UK.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the License
 * which accompanies this distribution in the file LICENSE.txt
 *******************************************************************************/
package uk.ac.bolton.archimate.editor.diagram.editparts;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;

import uk.ac.bolton.archimate.editor.model.viewpoints.IViewpoint;
import uk.ac.bolton.archimate.editor.model.viewpoints.ViewpointsManager;
import uk.ac.bolton.archimate.model.IArchimateDiagramModel;
import uk.ac.bolton.archimate.model.IDiagramModelConnection;
import uk.ac.bolton.archimate.model.IDiagramModelObject;

/**
 * Viewpoint Filter for EditParts
 * 
 * This will query the current viewpoint (if any) as to whether the child object is
 * to be shown in the parent EditPart.
 * 
 * @author Phillip Beauvoir
 */
public class ViewpointEditPartFilter implements IChildEditPartFilter, IConnectionEditPartFilter {
    
    @Override
    public boolean isChildElementVisible(EditPart parentEditPart, Object childObject) {
        IViewpoint viewPoint = null;
        
        if(childObject instanceof IDiagramModelObject) {
            IArchimateDiagramModel dm = (IArchimateDiagramModel)((IDiagramModelObject)childObject).getDiagramModel();
            if(dm != null) {
                int index = dm.getViewpoint();
                viewPoint = ViewpointsManager.INSTANCE.getViewpoint(index);
            }
        }
        
        if(viewPoint != null && childObject instanceof EObject) {
            return viewPoint.isElementVisible((EObject)childObject);
        }
        
        return true;
    }

    @Override
    public boolean isConnectionVisible(EditPart editPart, IDiagramModelConnection connection) {
        // This ensures that there are no dangling connections if this filter has removed an editpart
        return isChildElementVisible(editPart, connection.getSource()) && isChildElementVisible(editPart, connection.getTarget());
    }
}