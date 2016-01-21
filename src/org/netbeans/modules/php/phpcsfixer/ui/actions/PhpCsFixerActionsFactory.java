/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2013 Oracle and/or its affiliates. All rights reserved.
 *
 * Oracle and Java are registered trademarks of Oracle and/or its affiliates.
 * Other names may be trademarks of their respective owners.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common
 * Development and Distribution License("CDDL") (collectively, the
 * "License"). You may not use this file except in compliance with the
 * License. You can obtain a copy of the License at
 * http://www.netbeans.org/cddl-gplv2.html
 * or nbbuild/licenses/CDDL-GPL-2-CP. See the License for the
 * specific language governing permissions and limitations under the
 * License.  When distributing the software, include this License Header
 * Notice in each file and include the License file at
 * nbbuild/licenses/CDDL-GPL-2-CP.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the GPL Version 2 section of the License file that
 * accompanied this code. If applicable, add the following below the
 * License Header, with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * If you wish your version of this file to be governed by only the CDDL
 * or only the GPL Version 2, indicate your decision by adding
 * "[Contributor] elects to include this software in this distribution
 * under the [CDDL or GPL Version 2] license." If you do not indicate a
 * single choice of license, a recipient has the option to distribute
 * your version of this file under either the CDDL, the GPL Version 2 or
 * to extend the choice of license to its licensees as provided above.
 * However, if you add GPL Version 2 code and therefore, elected the GPL
 * Version 2 license, then the option applies only if the new code is
 * made subject to such option by the copyright holder.
 *
 * Contributor(s):
 *
 * Portions Copyrighted 2013 Sun Microsystems, Inc.
 */
package org.netbeans.modules.php.phpcsfixer.ui.actions;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import org.netbeans.modules.php.api.phpmodule.PhpModule;
import org.netbeans.modules.php.api.util.StringUtils;
import org.netbeans.modules.php.phpcsfixer.options.PhpCsFixerOptions;
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
    @ActionReference(path = "Loaders/folder/any/Actions", position = 1600),
    @ActionReference(path = "Loaders/text/x-php5/Actions", position = 1600),
    @ActionReference(path = "Editors/text/x-php5/Popup", position = 600),
    @ActionReference(path = "Projects/org-netbeans-modules-php-project/Actions", position = 1100)
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
            return null;
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
        PhpModule phpModule = PhpModule.Factory.forFileObject(target);
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
