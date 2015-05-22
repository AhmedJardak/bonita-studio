/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.common.gmf.tools.tree.selection.provider;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.IEditorReference;

public class DefaultTabbedPropertyProvider implements ITabbedPropertySelectionProvider {

    private final String viewId;
    private final String tabId;

    private DefaultTabbedPropertyProvider(final String viewId, final String tabId) {
        this.viewId = viewId;
        this.tabId = tabId;
    }

    @Override
    public String tabId(final EObject element) {
        return tabId;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.gmf.tools.tree.selection.ITabbedPropertySelectionProvider#appliesTo(org.eclipse.emf.ecore.EObject)
     */
    @Override
    public boolean appliesTo(final EObject element, final IEditorReference activeEditor) {
        return false;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.gmf.tools.tree.selection.provider.ITabbedPropertySelectionProvider#viewId()
     */
    @Override
    public String viewId() {
        return viewId;
    }

    public static ITabbedPropertySelectionProvider defaultProvider(final IEditorReference activeEditor) {
        return isProcessDiagramEditor(activeEditor)
                ? new DefaultTabbedPropertyProvider("org.bonitasoft.studio.views.properties.process.general", "tab.general") :
                new DefaultTabbedPropertyProvider("org.bonitasoft.studio.views.properties.form.general", "Form.GeneralTab");
    }

    public static boolean isProcessDiagramEditor(final IEditorReference activeEditor) {
        return "org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditorID".equals(activeEditor.getId()) ||
                "org.bonitasoft.studio.model.process.diagram.custom.ex.part.ProcessDiagramEditorExID".equals(activeEditor.getId());
    }

    public static boolean isFormDiagramEditor(final IEditorReference activeEditor) {
        return "org.bonitasoft.studio.diagram.form.custom.ex.part.FormDiagramEditorEx".equals(activeEditor.getId()) ||
                "org.bonitasoft.studio.model.process.diagram.form.part.FormDiagramEditorID".equals(activeEditor.getId());
    }

}
