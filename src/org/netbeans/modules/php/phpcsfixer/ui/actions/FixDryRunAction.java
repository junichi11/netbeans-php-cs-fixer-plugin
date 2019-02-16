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

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.netbeans.modules.php.api.executable.InvalidPhpExecutableException;
import org.netbeans.modules.php.api.phpmodule.PhpModule;
import org.netbeans.modules.php.phpcsfixer.commands.PhpCsFixer;
import org.openide.awt.ActionID;
import org.openide.awt.ActionRegistration;
import org.openide.filesystems.FileObject;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle;

/**
 *
 * @author junichi11
 */
@ActionID(
        category = "PHP",
        id = "org.netbeans.modules.php.phpcsfixer.ui.actions.FixDryRunAction"
)
@ActionRegistration(
        displayName = "#CTL_FixDryRunAction"
)
@NbBundle.Messages("CTL_FixDryRunAction=PHP CS Fixer: Fix --dry-run")
public class FixDryRunAction extends FixAction {

    private static final long serialVersionUID = 7751096230753168096L;

    @NbBundle.Messages("FixDryRunAction.name=Fix --dry-run")
    @Override
    protected String getName() {
        return Bundle.FixDryRunAction_name();
    }

    @Override
    protected void runCommand(PhpModule phpModule, List<String> options) throws InvalidPhpExecutableException {
        for (FileObject target : getTargetFiles()) {
            List<String> params = getAllParams(target, options);
            if (!params.isEmpty()) {
                Future<Integer> result = PhpCsFixer.getDefault().fixDryRun(phpModule, params.toArray(new String[0]));
                if (result != null) {
                    try {
                        result.get();
                    } catch (InterruptedException | ExecutionException ex) {
                        Exceptions.printStackTrace(ex);
                    }
                }
            }
        }
    }
}
