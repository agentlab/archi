/*******************************************************************************
 * Copyright (c) 2011 Bolton University, UK.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the License
 * which accompanies this distribution in the file LICENSE.txt
 *******************************************************************************/
package uk.ac.bolton.archimate.templates.impl.wizard;

import uk.ac.bolton.archimate.templates.model.TemplateManager;
import uk.ac.bolton.archimate.templates.wizard.NewModelFromTemplateWizardPage;


/**
 * New Archimate Model From Template Wizard Page
 * 
 * @author Phillip Beauvoir
 */
public class NewArchimateModelFromTemplateWizardPage extends NewModelFromTemplateWizardPage {
    
    private static String HELP_ID = "uk.ac.bolton.archimate.help.NewArchimateModelFromTemplateWizardPage"; //$NON-NLS-1$

    public NewArchimateModelFromTemplateWizardPage(TemplateManager templateManager) {
        super("NewArchimateModelFromTemplateWizardPage", templateManager); //$NON-NLS-1$
    }

    @Override
    protected void init() {
        setTitle(Messages.NewArchimateModelFromTemplateWizardPage_1);
        setDescription(Messages.NewArchimateModelFromTemplateWizardPage_2);
    }
    
    @Override
    protected String getHelpID() {
        return HELP_ID;
    }

}
