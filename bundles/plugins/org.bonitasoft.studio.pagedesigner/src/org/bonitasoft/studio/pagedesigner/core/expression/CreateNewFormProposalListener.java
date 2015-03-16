/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.pagedesigner.core.expression;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;

import javax.inject.Inject;

import org.bonitasoft.studio.browser.operation.OpenBrowserOperation;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.expression.editor.provider.IProposalAdapter;
import org.bonitasoft.studio.model.process.PageFlow;
import org.bonitasoft.studio.pagedesigner.core.PageDesignerURLFactory;
import org.bonitasoft.studio.pagedesigner.core.operation.CreateFormOperation;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.eclipse.e4.core.di.annotations.Creatable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.progress.IProgressService;

/**
 * @author Romain Bioteau
 */
@Creatable
public class CreateNewFormProposalListener extends IProposalAdapter implements BonitaPreferenceConstants {

    @Inject
    private IProgressService progressService;

    @Inject
    private PageDesignerURLFactory pageDesignerURLFactory;

    CreateNewFormProposalListener() {

    }

    public CreateNewFormProposalListener(final PageDesignerURLFactory pageDesignerURLFactory, final IProgressService progressService) {
        this.progressService = progressService;
        this.pageDesignerURLFactory = pageDesignerURLFactory;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IProposalListener#handleEvent(org.eclipse.emf.ecore.EObject, java.lang.String)
     */
    @Override
    public String handleEvent(final EObject context, final String fixedReturnType) {
        final CreateFormOperation operation = doCreateFormOperation(pageDesignerURLFactory);
        final PageFlow pageFlow = pageFlowFor(context);
        if (pageFlow != null) {
            operation.setFormName(pageFlow.getName());
        }
        try {
            progressService.busyCursorWhile(operation);
        } catch (InvocationTargetException | InterruptedException e) {
            BonitaStudioLog.error(e);
        }

        final String newPageId = operation.getNewPageId();
        openPageDesigner(pageDesignerURLFactory, newPageId);
        return newPageId;
    }

    private static PageFlow pageFlowFor(final EObject context) {
        EObject pageflow = context;
        while (pageflow != null && !(pageflow instanceof PageFlow)) {
            pageflow = pageflow.eContainer();
        }
        return (PageFlow) pageflow;
    }

    protected void openPageDesigner(final PageDesignerURLFactory pageDesignerURLFactory, final String newPageId) {
        try {
            new OpenBrowserOperation(pageDesignerURLFactory.openPage(newPageId)).execute();
        } catch (final MalformedURLException e) {
            BonitaStudioLog.error(e);
        }
    }

    protected CreateFormOperation doCreateFormOperation(final PageDesignerURLFactory pageDesignerURLBuilder) {
        return new CreateFormOperation(pageDesignerURLBuilder);
    }

}
