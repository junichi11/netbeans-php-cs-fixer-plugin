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
import org.netbeans.modules.php.api.executable.InvalidPhpExecutableException;
import org.netbeans.modules.php.api.phpmodule.PhpModule;
import org.netbeans.modules.php.phpcsfixer.commands.PhpCsFixer;
import org.openide.filesystems.FileObject;
import org.openide.util.NbBundle;

/**
 *
 * @author junichi11
 */
public class SelfUpdateAction extends PhpCsFixerBaseAction {

    private static final long serialVersionUID = -6042811476506701499L;

    @NbBundle.Messages("SelfUpdateAction.name=self-update")
    @Override
    protected String getName() {
        return Bundle.SelfUpdateAction_name();
    }

    @Override
    protected void runCommand(PhpModule phpModule, List<String> options) throws InvalidPhpExecutableException {
        PhpCsFixer.getDefault().selfUpdate(phpModule);
    }

    @Override
    protected void runCommand(PhpModule phpModule, List<String> options, FileObject targetFile) throws InvalidPhpExecutableException {
        PhpCsFixer.getDefault().selfUpdate(phpModule);
    }
}
