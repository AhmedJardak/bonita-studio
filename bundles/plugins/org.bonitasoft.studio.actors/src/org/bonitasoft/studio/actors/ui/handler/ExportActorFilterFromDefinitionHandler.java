/**
 * Copyright (C) 2018 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.actors.ui.handler;

import org.bonitasoft.studio.actors.repository.ActorFilterDefFileStore;
import org.bonitasoft.studio.actors.ui.wizard.ExportActorFilterWizard;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.filestore.FileStoreFinder;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;

public class ExportActorFilterFromDefinitionHandler extends AbstractHandler {

    private RepositoryAccessor repositoryAccessor;
    private FileStoreFinder fileStoreFinder;

    public ExportActorFilterFromDefinitionHandler() {
        repositoryAccessor = new RepositoryAccessor();
        repositoryAccessor.init();
        fileStoreFinder = new FileStoreFinder();
    }

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        fileStoreFinder.findSelectedFileStore(repositoryAccessor.getCurrentRepository())
                .filter(ActorFilterDefFileStore.class::isInstance)
                .map(ActorFilterDefFileStore.class::cast)
                .map(ActorFilterDefFileStore::getContent)
                .ifPresent(def -> {
                    WizardDialog dialog = new WizardDialog(Display.getDefault().getActiveShell(),
                            new ExportActorFilterWizard(def));
                    dialog.open();
                });
        return null;
    }

}
