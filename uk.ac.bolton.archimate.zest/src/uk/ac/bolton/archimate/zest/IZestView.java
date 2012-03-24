/*******************************************************************************
 * Copyright (c) 2010-12 Bolton University, UK.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the License
 * which accompanies this distribution in the file LICENSE.txt
 *******************************************************************************/
package uk.ac.bolton.archimate.zest;

import uk.ac.bolton.archimate.editor.views.IModelView;


/**
 * Interface for Zest View
 * 
 * @author Phillip Beauvoir
 */
public interface IZestView extends IModelView {

    String ID = ArchimateZestPlugin.PLUGIN_ID + ".zestView"; //$NON-NLS-1$
    String HELP_ID = ArchimateZestPlugin.PLUGIN_ID + ".zestViewHelp"; //$NON-NLS-1$
    String NAME = "Visualiser";
}