/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.pagedesigner.ui.contribution;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.bonitasoft.studio.pagedesigner.i18n.Messages;
import org.bonitasoft.studio.pagedesigner.ui.handler.OpenUIDesignerHandler;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.commands.ICommandService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OpenUIDesignerCoolBarItemTest {

    @Rule
    public RealmWithDisplay realmWithDisplay = new RealmWithDisplay();
    @Mock
    private ICommandService commandService;
    @Mock
    private OpenUIDesignerHandler openDesignerHandler;

    @Test
    public void create_a_toolItem_to_open_ui_designer() throws Exception {
        final OpenUIDesignerCoolBarItem openUIDesignerCoolBarItem = new OpenUIDesignerCoolBarItem();

        final ToolBar toolbar = new ToolBar(realmWithDisplay.createComposite(), SWT.NONE);
        openUIDesignerCoolBarItem.fill(toolbar, 0, -1);

        assertThat(toolbar.getItemCount()).isEqualTo(1);
        final ToolItem toolItem = toolbar.getItem(0);
        assertThat(toolItem.getText()).isEqualTo(Messages.uiDesignerLabel);
    }

    @Test
    public void call_open_ui_designer_command_on_selection() throws Exception {
        final OpenUIDesignerCoolBarItem openUIDesignerCoolBarItem = spy(new OpenUIDesignerCoolBarItem());
        doReturn(openDesignerHandler).when(openUIDesignerCoolBarItem).getHandler();

        final ToolBar toolbar = new ToolBar(realmWithDisplay.createComposite(), SWT.NONE);
        openUIDesignerCoolBarItem.fill(toolbar, 0, -1);

        final ToolItem toolItem = toolbar.getItem(0);
        toolItem.notifyListeners(SWT.Selection, new Event());

        verify(openDesignerHandler).execute();
    }
}
