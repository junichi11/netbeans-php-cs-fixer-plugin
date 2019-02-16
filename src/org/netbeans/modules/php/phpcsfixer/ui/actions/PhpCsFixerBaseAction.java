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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import static javax.swing.Action.NAME;
import static javax.swing.Action.SHORT_DESCRIPTION;
import org.netbeans.modules.php.api.executable.InvalidPhpExecutableException;
import org.netbeans.modules.php.api.phpmodule.PhpModule;
import org.netbeans.modules.php.api.util.StringUtils;
import org.netbeans.modules.php.phpcsfixer.options.PhpCsFixerOptions;
import org.netbeans.modules.php.phpcsfixer.preferences.PhpCsFixerPreferences;
import org.openide.filesystems.FileObject;
import static org.netbeans.modules.php.phpcsfixer.commands.PhpCsFixer.DIFF_FORMAT_UDIFF_PARAM;
import static org.netbeans.modules.php.phpcsfixer.commands.PhpCsFixer.DIFF_PARAM;
import static org.netbeans.modules.php.phpcsfixer.commands.PhpCsFixer.VERBOSE_PARAM;

/**
 *
 * @author junichi11
 */
public abstract class PhpCsFixerBaseAction extends AbstractAction {

    private static final long serialVersionUID = -8856272088582157210L;
    private static final Logger LOGGER = Logger.getLogger(PhpCsFixerBaseAction.class.getName());

    public PhpCsFixerBaseAction() {
        putValue("noIconInMenu", true); // NOI18N
        String name = getName();
        putValue(NAME, name);
        putValue(SHORT_DESCRIPTION, name);
        putValue("menuText", name); // NOI18N
    }

    protected abstract String getName();

    protected abstract void runCommand(PhpModule phpModule, List<String> options) throws InvalidPhpExecutableException;

    protected abstract void runCommand(PhpModule phpModule, List<String> options, FileObject targetFile) throws InvalidPhpExecutableException;

    @Override
    public void actionPerformed(ActionEvent e) {
        PhpModule phpModule = PhpModule.Factory.inferPhpModule();
        if (phpModule == null) {
            return;
        }
        try {
            runCommand(phpModule, getOptions());
        } catch (InvalidPhpExecutableException ex) {
            LOGGER.log(Level.WARNING, null, ex);
        }
    }

    public void actionPerformed(ActionEvent e, FileObject targetFile) {
        PhpModule phpModule = PhpModule.Factory.forFileObject(targetFile);
        if (phpModule == null) {
            return;
        }
        try {
            runCommand(phpModule, getOptions(), targetFile);
        } catch (InvalidPhpExecutableException ex) {
            LOGGER.log(Level.WARNING, null, ex);
        }
    }

    private List<String> getOptions() {
        PhpModule phpModule = PhpModule.Factory.inferPhpModule();
        List<String> options = new ArrayList<>();
        boolean isDryRun = isDryRun();
        boolean isVerbose;
        boolean isDiff;
        boolean isDiffFormatUdiff;

        if (phpModule == null || PhpCsFixerPreferences.useGlobal(phpModule)) {
            // use global
            PhpCsFixerOptions instance = PhpCsFixerOptions.getInstance();
            options.addAll(instance.getAllOptions());
            isVerbose = instance.isVerbose();
            isDiff = instance.isDiff();
            isDiffFormatUdiff = instance.isDiffFormatUdiff();
        } else {
            // use project
            options.addAll(PhpCsFixerPreferences.getAllOptions(phpModule));
            isVerbose = PhpCsFixerPreferences.isVerbose(phpModule);
            isDiff = PhpCsFixerPreferences.isDiff(phpModule);
            isDiffFormatUdiff = PhpCsFixerPreferences.isDiffFormatUdiff(phpModule);
        }

        if (isDryRun) {
            if (isVerbose) {
                options.add(VERBOSE_PARAM);
                if (isDiff) {
                    options.add(DIFF_PARAM);
                    if (isDiffFormatUdiff) {
                        options.add(DIFF_FORMAT_UDIFF_PARAM);
                    }
                }
            }
        }

        return options;
    }

    private boolean isDryRun() {
        String name = getName();
        if (StringUtils.isEmpty(name)) {
            return false;
        }
        return name.contains("--dry-run"); // NOI18N
    }
}
