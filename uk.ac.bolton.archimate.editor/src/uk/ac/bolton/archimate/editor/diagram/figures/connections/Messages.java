/*******************************************************************************
 * Copyright (c) 2012 Bolton University, UK.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the License
 * which accompanies this distribution in the file LICENSE.txt
 *******************************************************************************/
package uk.ac.bolton.archimate.editor.diagram.figures.connections;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {

    private static final String BUNDLE_NAME = "uk.ac.bolton.archimate.editor.diagram.figures.connections.messages"; //$NON-NLS-1$

    public static String AbstractArchimateConnectionFigure_0;

    public static String AccessConnectionFigure_0;

    public static String AccessConnectionFigure_1;

    public static String AccessConnectionFigure_2;

    public static String AccessConnectionFigure_3;

    public static String AccessConnectionFigure_4;
    static {
        // initialize resource bundle
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }

    private Messages() {
    }
}
