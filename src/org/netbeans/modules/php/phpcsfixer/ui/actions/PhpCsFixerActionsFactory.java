/*
 * Copyright 2019 junichi11.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.netbeans.modules.php.phpcsfixer.ui.actions;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import org.netbeans.api.project.FileOwnerQuery;
import org.netbeans.api.project.Project;
import org.netbeans.modules.php.api.phpmodule.PhpModule;
import org.netbeans.modules.php.api.util.StringUtils;
import org.netbeans.modules.php.phpcsfixer.options.PhpCsFixerOptions;
import org.netbeans.spi.project.ui.support.ProjectConvertors;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.filesystems.FileObject;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.util.Utilities;
import org.openide.util.actions.Presenter;

/**
 *
 * @author junichi11
 */
@ActionID(
        category = "PHP",
        id = "org.netbeans.modules.php.phpcsfixer.ui.actions.PhpCsFixerActionsFactory")
@ActionRegistration(
        displayName = "#PhpCsFixerActionsFactory.name", lazy = false)
@ActionReferences({
    @ActionReference(path = "Loaders/folder/any/Actions", position = 1690),
    @ActionReference(path = "Loaders/text/x-php5/Actions", position = 1690),
    @ActionReference(path = "Editors/text/x-php5/Popup", position = 590),
    @ActionReference(path = "Projects/org-netbeans-modules-php-project/Actions", position = 1090)
})
@NbBundle.Messages("PhpCsFixerActionsFactory.name=PHP CS Fixer")
public class PhpCsFixerActionsFactory extends AbstractAction implements Presenter.Popup {

    private static final long serialVersionUID = -3056069945397188766L;
    private JMenu actions = null;

    @Override
    public void actionPerformed(ActionEvent e) {
        assert false;
    }

    @Override
    public JMenuItem getPopupPresenter() {
        if (!isInPhpModule()) {
            return new JMenuItem();
        }
        String phpCsFixerPath = PhpCsFixerOptions.getInstance().getPhpCsFixerPath();
        if (StringUtils.isEmpty(phpCsFixerPath)) {
            JMenuItem item = new JMenuItem();
            item.setVisible(false);
            return item;
        }
        if (actions == null) {
            actions = new PhpCsFixerActions();
        }
        return actions;
    }

    private boolean isInPhpModule() {
        Lookup context = Utilities.actionsGlobalContext();
        FileObject target = context.lookup(FileObject.class);
        if (target == null) {
            return false;
        }
        if (!target.isFolder()) {
            return true;
        }
        Project nonConvertorOwner = ProjectConvertors.getNonConvertorOwner(target);
        if (nonConvertorOwner == null) {
            nonConvertorOwner = FileOwnerQuery.getOwner(target);
            if (nonConvertorOwner == null) {
                return false;
            }
        }
        PhpModule phpModule = PhpModule.Factory.lookupPhpModule(nonConvertorOwner);
        if (phpModule != null) {
            return true;
        }
        phpModule = PhpModule.Factory.forFileObject(target);
        return phpModule != null;
    }

    //~ inner class
    private static class PhpCsFixerActions extends JMenu {

        private static final long serialVersionUID = 4460699854597307907L;

        public PhpCsFixerActions() {
            super(Bundle.PhpCsFixerActionsFactory_name());
            // add actions
            add(new FixAction());
            add(new FixDryRunAction());
            add(new SelfUpdateAction());
        }
    }
}
