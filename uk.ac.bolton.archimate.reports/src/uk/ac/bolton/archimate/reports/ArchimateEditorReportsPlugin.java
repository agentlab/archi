/*******************************************************************************
 * Copyright (c) 2010 Bolton University, UK.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the License
 * which accompanies this distribution in the file LICENSE.txt
 *******************************************************************************/
package uk.ac.bolton.archimate.reports;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * Activator
 * Implement IStartup so that Action Delegates are initialised
 * 
 * @author Phillip Beauvoir
 */
public class ArchimateEditorReportsPlugin extends AbstractUIPlugin implements IStartup {
    
    public static final String PLUGIN_ID = "uk.ac.bolton.archimate.reports"; //$NON-NLS-1$

    /**
     * The shared instance
     */
    public static ArchimateEditorReportsPlugin INSTANCE;

    /**
     * The File location of this plugin folder
     */
    private static File fPluginFolder;

    public ArchimateEditorReportsPlugin() {
        INSTANCE = this;
    }

    @Override
    public void earlyStartup() {
        // Do nothing
    }

    /**
     * @return The File Location of this plugin
     */
    public File getPluginFolder() {
        if(fPluginFolder == null) {
            URL url = getBundle().getEntry("/"); //$NON-NLS-1$
            try {
                url = FileLocator.resolve(url);
            }
            catch(IOException ex) {
                ex.printStackTrace();
            }
            fPluginFolder = new File(url.getPath());
        }
        
        return fPluginFolder;
    }
}
