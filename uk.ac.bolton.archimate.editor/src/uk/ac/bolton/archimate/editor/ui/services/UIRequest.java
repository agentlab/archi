/*******************************************************************************
 * Copyright (c) 2011 Bolton University, UK.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the License
 * which accompanies this distribution in the file LICENSE.txt
 *******************************************************************************/
package uk.ac.bolton.archimate.editor.ui.services;


/**
 * UIRequest
 * 
 * @author Phillip Beauvoir
 */
public class UIRequest {
    
    private Object fSource;
    private String fRequestName;
    private Object fTarget;

    /**
     * @param source The source of the request
     * @param requestName The name of the request type
     * @param target The target element on which to perform the action of the request
     */
    public UIRequest(Object source, String requestName, Object target) {
        fSource = source;
        fRequestName = requestName;
        fTarget = target;
    }
    
    /**
     * @return The source of the request
     */
    public Object getSource() {
        return fSource;
    }

    /**
     * @return The name of the request type
     */
    public String getRequestName() {
        return fRequestName;
    }

    /**
     * @return The target object on which to perform the action of the request
     */
    public Object getTarget() {
        return fTarget;
    }
}
