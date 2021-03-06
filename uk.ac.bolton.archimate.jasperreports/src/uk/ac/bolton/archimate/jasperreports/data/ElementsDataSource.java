/*******************************************************************************
 * Copyright (c) 2011 Bolton University, UK.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the License
 * which accompanies this distribution in the file LICENSE.txt
 *******************************************************************************/
package uk.ac.bolton.archimate.jasperreports.data;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRRewindableDataSource;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import uk.ac.bolton.archimate.model.FolderType;
import uk.ac.bolton.archimate.model.IArchimateElement;
import uk.ac.bolton.archimate.model.IArchimateModel;
import uk.ac.bolton.archimate.model.IArchimatePackage;
import uk.ac.bolton.archimate.model.IFolder;


/**
 * Elements DataSource
 * 
 * @author Phillip Beauvoir
 */
public class ElementsDataSource implements JRRewindableDataSource, IPropertiesDataSource {
    
    List<IArchimateElement> fElements = new ArrayList<IArchimateElement>();
    private IArchimateElement fCurrentElement;
    private int currentIndex = -1;

    public ElementsDataSource(IArchimateModel model, String type) {
        IFolder businessFolder = model.getFolder(FolderType.BUSINESS);
        IFolder applicationFolder = model.getFolder(FolderType.APPLICATION);
        IFolder technologyFolder = model.getFolder(FolderType.TECHNOLOGY);
        IFolder motivationFolder = model.getFolder(FolderType.MOTIVATION);
        IFolder implmigrationFolder = model.getFolder(FolderType.IMPLEMENTATION_MIGRATION);
        IFolder connectorsFolder = model.getFolder(FolderType.CONNECTORS);
        IFolder relationsFolder = model.getFolder(FolderType.RELATIONS);

        if("business_actors".equals(type)) { //$NON-NLS-1$
            getElements(businessFolder, IArchimatePackage.eINSTANCE.getBusinessActor());
            getElements(businessFolder, IArchimatePackage.eINSTANCE.getBusinessRole());
            getElements(businessFolder, IArchimatePackage.eINSTANCE.getBusinessInterface());
            getElements(businessFolder, IArchimatePackage.eINSTANCE.getBusinessCollaboration());
            getElements(businessFolder, IArchimatePackage.eINSTANCE.getLocation());
        }
        else if("business_functions".equals(type)) { //$NON-NLS-1$
            getElements(businessFolder, IArchimatePackage.eINSTANCE.getBusinessFunction());
        }
        else if("business_information".equals(type)) { //$NON-NLS-1$
            getElements(businessFolder, IArchimatePackage.eINSTANCE.getBusinessObject());
            getElements(businessFolder, IArchimatePackage.eINSTANCE.getRepresentation());
            getElements(businessFolder, IArchimatePackage.eINSTANCE.getMeaning());
        }
        else if("business_processes".equals(type)) { //$NON-NLS-1$
            getElements(businessFolder, IArchimatePackage.eINSTANCE.getBusinessEvent());
            getElements(businessFolder, IArchimatePackage.eINSTANCE.getBusinessInteraction());
            getElements(businessFolder, IArchimatePackage.eINSTANCE.getBusinessProcess());
        }
        else if("business_products".equals(type)) { //$NON-NLS-1$
            getElements(businessFolder, IArchimatePackage.eINSTANCE.getContract());
            getElements(businessFolder, IArchimatePackage.eINSTANCE.getProduct());
            getElements(businessFolder, IArchimatePackage.eINSTANCE.getBusinessService());
            getElements(businessFolder, IArchimatePackage.eINSTANCE.getValue());
        }
        else if("applications".equals(type)) { //$NON-NLS-1$
            getElements(applicationFolder, IArchimatePackage.eINSTANCE.getApplicationCollaboration());
            getElements(applicationFolder, IArchimatePackage.eINSTANCE.getApplicationComponent());
            getElements(applicationFolder, IArchimatePackage.eINSTANCE.getApplicationFunction());
            getElements(applicationFolder, IArchimatePackage.eINSTANCE.getApplicationInteraction());
            getElements(applicationFolder, IArchimatePackage.eINSTANCE.getApplicationInterface());
            getElements(applicationFolder, IArchimatePackage.eINSTANCE.getApplicationService());
        }
        else if("application_data".equals(type)) { //$NON-NLS-1$
            getElements(applicationFolder, IArchimatePackage.eINSTANCE.getDataObject());
        }
        else if("infrastructures".equals(type)) { //$NON-NLS-1$
            getElements(technologyFolder, IArchimatePackage.eINSTANCE.getArtifact());
            getElements(technologyFolder, IArchimatePackage.eINSTANCE.getCommunicationPath());
            getElements(technologyFolder, IArchimatePackage.eINSTANCE.getDevice());
            getElements(technologyFolder, IArchimatePackage.eINSTANCE.getNode());
            getElements(technologyFolder, IArchimatePackage.eINSTANCE.getInfrastructureFunction());
            getElements(technologyFolder, IArchimatePackage.eINSTANCE.getInfrastructureInterface());
            getElements(technologyFolder, IArchimatePackage.eINSTANCE.getNetwork());
            getElements(technologyFolder, IArchimatePackage.eINSTANCE.getInfrastructureService());
            getElements(technologyFolder, IArchimatePackage.eINSTANCE.getSystemSoftware());
        }
        else if("motivation".equals(type)) { //$NON-NLS-1$
            getElements(motivationFolder, IArchimatePackage.eINSTANCE.getStakeholder());
            getElements(motivationFolder, IArchimatePackage.eINSTANCE.getDriver());
            getElements(motivationFolder, IArchimatePackage.eINSTANCE.getAssessment());
            getElements(motivationFolder, IArchimatePackage.eINSTANCE.getGoal());
            getElements(motivationFolder, IArchimatePackage.eINSTANCE.getPrinciple());
            getElements(motivationFolder, IArchimatePackage.eINSTANCE.getRequirement());
            getElements(motivationFolder, IArchimatePackage.eINSTANCE.getConstraint());
        }
        else if("impl_migration".equals(type)) { //$NON-NLS-1$
            getElements(implmigrationFolder, IArchimatePackage.eINSTANCE.getWorkPackage());
            getElements(implmigrationFolder, IArchimatePackage.eINSTANCE.getDeliverable());
            getElements(implmigrationFolder, IArchimatePackage.eINSTANCE.getPlateau());
            getElements(implmigrationFolder, IArchimatePackage.eINSTANCE.getGap());
        }
        else if("connectors".equals(type)) { //$NON-NLS-1$
            getElements(connectorsFolder, null);
        }
        else if("relations".equals(type)) { //$NON-NLS-1$
            getElements(relationsFolder, null);
        }
        
        ArchimateModelDataSource.sort(fElements);
    }

    public PropertiesModelDataSource getPropertiesDataSource() {
        return new PropertiesModelDataSource(fCurrentElement);
    }
    
    @Override
    public Object getElement() {
        return fCurrentElement;
    }

    @Override
    public boolean next() throws JRException {
        if(currentIndex < fElements.size() - 1) {
            fCurrentElement = fElements.get(++currentIndex);
        }
        else {
            fCurrentElement = null;
        }
        
        return fCurrentElement != null;
    }

    @Override
    public Object getFieldValue(JRField jrField) throws JRException {
        return FieldDataFactory.getFieldValue(fCurrentElement, jrField.getName());
    }

    @Override
    public void moveFirst() throws JRException {
        currentIndex = -1;
    }

    private void getElements(IFolder folder, EClass type) {
        for(EObject object : folder.getElements()) {
            if(object instanceof IArchimateElement) {
                if(type == null || object.eClass() == type) {
                    fElements.add((IArchimateElement)object);
                }
            }
        }
        
        for(IFolder f : folder.getFolders()) {
            getElements(f, type);
        }
    }

}
