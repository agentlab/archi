/*******************************************************************************
 * Copyright (c) 2011 Bolton University, UK.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the License
 * which accompanies this distribution in the file LICENSE.txt
 *******************************************************************************/
package uk.ac.bolton.archimate.editor.model.viewpoints;

import org.eclipse.emf.ecore.EClass;

/**
 * Total Viewpoint
 * 
 * @author Phillip Beauvoir
 */
public class TotalViewpoint extends AbstractViewpoint {
    
    @Override
    public String getName() {
        return Messages.TotalViewpoint_0;
    }
    
    @Override
    public int getIndex() {
        return TOTAL_VIEWPOINT;
    }

    @Override
    public EClass[] getAllowedTypes() {
        return null;
    }
}