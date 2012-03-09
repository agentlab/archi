/*******************************************************************************
 * Copyright (c) 2010-12 Bolton University, UK.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the License
 * which accompanies this distribution in the file LICENSE.txt
 *******************************************************************************/
package uk.ac.bolton.archimate.zest;

import java.beans.PropertyChangeEvent;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.help.HelpSystem;
import org.eclipse.help.IContext;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.zest.layouts.LayoutStyles;
import org.eclipse.zest.layouts.algorithms.SpringLayoutAlgorithm;

import uk.ac.bolton.archimate.editor.model.IEditorModelManager;
import uk.ac.bolton.archimate.editor.ui.IArchimateImages;
import uk.ac.bolton.archimate.editor.views.AbstractModelView;
import uk.ac.bolton.archimate.editor.views.navigator.Messages;
import uk.ac.bolton.archimate.editor.views.tree.actions.IViewerAction;
import uk.ac.bolton.archimate.editor.views.tree.actions.PropertiesAction;
import uk.ac.bolton.archimate.model.IArchimateElement;
import uk.ac.bolton.archimate.model.IArchimateModelElement;
import uk.ac.bolton.archimate.model.IArchimatePackage;


/**
 * Zest View
 * 
 * @author Phillip Beauvoir
 */
public class ZestView extends AbstractModelView
implements IZestView, ISelectionListener {
    
    private ZestGraphViewer fGraphViewer;
    
    private IAction fActionLayout;
    private IViewerAction fActionProperties;
    private IAction fActionPinContent;
    
    private DrillDownManager fDrillDownManager;
    
    @Override
    protected void doCreatePartControl(Composite parent) {
        GridLayout layout = new GridLayout();
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        layout.verticalSpacing = 0;
        parent.setLayout(layout);
        
        fGraphViewer = new ZestGraphViewer(parent, SWT.NONE);
        GridData gd = new GridData(GridData.FILL, GridData.FILL, true, true);
        fGraphViewer.getGraphControl().setLayoutData(gd);
        
        fGraphViewer.setLayoutAlgorithm(new SpringLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING), true);
        //fGraphViewer.setLayoutAlgorithm(new TreeLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING), true);
        //fGraphViewer.setLayoutAlgorithm(new RadialLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING), true);
        //fGraphViewer.setLayoutAlgorithm(new HorizontalTreeLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING), true);
        
        // Tree selection listener
        fGraphViewer.addSelectionChangedListener(new ISelectionChangedListener() {
            public void selectionChanged(SelectionChangedEvent event) {
                // Update actions
                updateActions();
                // Need to do this in order for Tabbed Properties View to update on Selection
                getSite().getSelectionProvider().setSelection(event.getSelection());
            }
        });
        
        // Double-click
        fGraphViewer.addDoubleClickListener(new IDoubleClickListener() {
            public void doubleClick(DoubleClickEvent event) {
                fDrillDownManager.goInto();
            }
        });

        fDrillDownManager = new DrillDownManager(fGraphViewer);

        makeActions();
        hookContextMenu();
        registerGlobalActions();
        makeLocalToolBar();

        // This will update previous Undo/Redo text if View was closed before
        updateActions();

        // Register selections
        getSite().setSelectionProvider(getViewer());
        
        // Listen to global selections to update the viewer
        getSite().getWorkbenchWindow().getSelectionService().addSelectionListener(this);
        
        // Help
        PlatformUI.getWorkbench().getHelpSystem().setHelp(parent, HELP_ID);
        
        // Initialise with whatever is selected in the workbench
        ISelection selection = getSite().getWorkbenchWindow().getSelectionService().getSelection();
        selectionChanged(null, selection);
    }
    
    @Override
    public void selectionChanged(IWorkbenchPart part, ISelection selection) {
        if(part == this) {
            return;
        }
        
        if(fActionPinContent.isChecked()) {
            return;
        }
        
        if(selection instanceof IStructuredSelection && !selection.isEmpty()) {
            Object object = ((IStructuredSelection)selection).getFirstElement();
            setElement(object);
        }
    }
    
    private void setElement(Object object) {
        Object selected = null;
        
        if(object instanceof IArchimateElement) {
            selected = object;
        }
        else if(object instanceof IAdaptable) {
            selected = ((IAdaptable)object).getAdapter(IArchimateElement.class);
        }
        
        fDrillDownManager.setNewInput(selected);
        updateActions();
    }

    /**
     * Update the Local Actions depending on the selection 
     */
    private void updateActions() {
        IStructuredSelection selection = (IStructuredSelection)getViewer().getSelection();
        fActionProperties.update(selection);
        updateUndoActions();
    }
    
    /**
     * Populate the ToolBar
     */
    private void makeLocalToolBar() {
        IActionBars bars = getViewSite().getActionBars();
        
        IToolBarManager manager = bars.getToolBarManager();
        
        fDrillDownManager.addNavigationActions(manager);
        manager.add(new Separator());
        manager.add(fActionPinContent);
        manager.add(new Separator());
        manager.add(fActionLayout);
    }

    private void reset() {
        fDrillDownManager.reset();
    }
    
    @Override
    public void setFocus() {
        if(fGraphViewer != null) {
            fGraphViewer.getControl().setFocus();
        }
    }
    
    @Override
    public StructuredViewer getViewer() {
        return fGraphViewer;
    }
    
    /**
     * Make local actions
     */
    private void makeActions() {
        fActionProperties = new PropertiesAction(getViewer());
        
        fActionLayout = new Action("Layout") {
            @Override
            public void run() {
                fGraphViewer.doApplyLayout();
            }
            
            @Override
            public String getToolTipText() {
                return getText();
            }
            
            @Override
            public ImageDescriptor getImageDescriptor() {
                return AbstractUIPlugin.imageDescriptorFromPlugin(ArchimateZestPlugin.PLUGIN_ID,
                        "img/layout.gif"); //$NON-NLS-1$
            }
        };
        
        fActionPinContent = new Action(Messages.NavigatorView_0, IAction.AS_CHECK_BOX) {
            {
                setToolTipText("Pins the current view");
                setImageDescriptor(IArchimateImages.ImageFactory.getImageDescriptor(IArchimateImages.ICON_PIN_16));
            }
        };
    }

    /**
     * Register Global Action Handlers
     */
    private void registerGlobalActions() {
        IActionBars actionBars = getViewSite().getActionBars();
        
        // Register our interest in the global menu actions
        actionBars.setGlobalActionHandler(ActionFactory.PROPERTIES.getId(), fActionProperties);
    }
    
    /**
     * Hook into a right-click menu
     */
    private void hookContextMenu() {
        MenuManager menuMgr = new MenuManager("#ZestViewPopupMenu"); //$NON-NLS-1$
        menuMgr.setRemoveAllWhenShown(true);
        
        menuMgr.addMenuListener(new IMenuListener() {
            public void menuAboutToShow(IMenuManager manager) {
                fillContextMenu(manager);
            }
        });
        
        Menu menu = menuMgr.createContextMenu(getViewer().getControl());
        getViewer().getControl().setMenu(menu);
        
        getSite().registerContextMenu(menuMgr, getViewer());
    }
    
    /**
     * Fill context menu when user right-clicks
     * @param manager
     */
    private void fillContextMenu(IMenuManager manager) {
        Object selected = ((IStructuredSelection)getViewer().getSelection()).getFirstElement();
        boolean isEmpty = selected == null;
        
        fDrillDownManager.addNavigationActions(manager);
        manager.add(new Separator());
        manager.add(fActionLayout);
        
        if(!isEmpty) {
            manager.add(new Separator());
            manager.add(fActionProperties);
        }
        
        // Other plug-ins can contribute their actions here
        manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
    }
    
    @Override
    public void dispose() {
        super.dispose();
        
        // Explicit dispose seems to be needed if the GraphViewer is displaying scrollbars
        // See https://bugs.eclipse.org/bugs/show_bug.cgi?id=373191
        // In fact Graph.dispose() seems never to be called
        fGraphViewer.getControl().dispose();
        
        // Unregister selection listener
        getSite().getWorkbenchWindow().getSelectionService().removeSelectionListener(this);
    }
    
    // =================================================================================
    //                       Listen to Editor Model Changes
    // =================================================================================
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String propertyName = evt.getPropertyName();
        Object newValue = evt.getNewValue();
        
        // Model Closed
        if(propertyName == IEditorModelManager.PROPERTY_MODEL_REMOVED) {
            Object input = getViewer().getInput();
            if(input instanceof IArchimateModelElement && ((IArchimateModelElement)input).getArchimateModel() == newValue) {
                reset();
            }
        }
        // Command Stack - update Actions
        else if(propertyName == IEditorModelManager.COMMAND_STACK_CHANGED) {
            updateActions();
        }
        else {
            super.propertyChange(evt);
        }
    }
    
    // =================================================================================
    //                       React to ECore Model Changes
    // =================================================================================
    
    @Override
    protected void eCoreChanged(Notification msg) {
        int type = msg.getEventType();
        
        // Attribute set
        if(type == Notification.SET) {
            Object feature = msg.getFeature();

            // Relationship/Connection changed - requires full refresh
            if(feature == IArchimatePackage.Literals.RELATIONSHIP__SOURCE ||
                                        feature == IArchimatePackage.Literals.RELATIONSHIP__TARGET) {
                getViewer().refresh();
            }
            else {
                super.eCoreChanged(msg);
            }
        }
        else {
            super.eCoreChanged(msg);
        }
    }

    // =================================================================================
    //                       Contextual Help support
    // =================================================================================
    
    public int getContextChangeMask() {
        return NONE;
    }

    public IContext getContext(Object target) {
        return HelpSystem.getContext(HELP_ID);
    }

    public String getSearchExpression(Object target) {
        return "Visualiser";
    }
}