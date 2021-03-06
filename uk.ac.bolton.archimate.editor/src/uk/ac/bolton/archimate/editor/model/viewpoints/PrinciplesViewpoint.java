/*******************************************************************************
 * Copyright (c) 2012 Bolton University, UK.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the License
 * which accompanies this distribution in the file LICENSE.txt
 *******************************************************************************/
package uk.ac.bolton.archimate.editor.model.viewpoints;

import org.eclipse.emf.ecore.EClass;

import uk.ac.bolton.archimate.model.IArchimatePackage;

/**
 * Principles Viewpoint
 * 
 * @author Phillip Beauvoir
 */
public class PrinciplesViewpoint extends AbstractViewpoint {
    
    EClass[] allowed = new EClass[] {
            IArchimatePackage.eINSTANCE.getGoal(),
            IArchimatePackage.eINSTANCE.getPrinciple(),
            
            IArchimatePackage.eINSTANCE.getRealisationRelationship(),
            IArchimatePackage.eINSTANCE.getAggregationRelationship(),
            IArchimatePackage.eINSTANCE.getSpecialisationRelationship(),
            IArchimatePackage.eINSTANCE.getInfluenceRelationship(),
    };
    
    @Override
    public String getName() {
        return Messages.PrinciplesViewpoint_0;
    }

    @Override
    public int getIndex() {
        return PRINCIPLES_VIEWPOINT;
    }
    
    @Override
    public EClass[] getAllowedTypes() {
        return allowed;
    }
}