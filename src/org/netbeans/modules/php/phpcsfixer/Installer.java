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
package org.netbeans.modules.php.phpcsfixer;

import static java.util.logging.Level.INFO;
import static java.util.logging.Level.WARNING;
import java.util.logging.Logger;
import org.netbeans.modules.php.api.executable.InvalidPhpExecutableException;
import org.netbeans.modules.php.api.util.StringUtils;
import org.netbeans.modules.php.phpcsfixer.commands.PhpCsFixer;
import org.netbeans.modules.php.phpcsfixer.options.PhpCsFixerOptions;
import org.openide.modules.ModuleInstall;

public class Installer extends ModuleInstall {

    private static final Logger LOGGER = Logger.getLogger(Installer.class.getName());

    @Override
    public void restored() {
        PhpCsFixerOptions options = PhpCsFixerOptions.getInstance();
        String phpCsFixerPath = options.getPhpCsFixerPath();
        if (!StringUtils.isEmpty(phpCsFixerPath) && options.runSelfUpdateOnBoot()) {
            try {
                PhpCsFixer.newInstance(phpCsFixerPath, true).selfUpdate(null);
                LOGGER.log(INFO, "PHP-CS-FIXER: Run self-update"); // NOI18N
            } catch (InvalidPhpExecutableException ex) {
                LOGGER.log(WARNING, ex.getMessage());
            }
        }
    }

}
