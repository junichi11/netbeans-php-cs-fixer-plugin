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
package org.netbeans.modules.php.phpcsfixer.ui.customizer;

import javax.swing.JComponent;
import org.netbeans.modules.php.api.phpmodule.PhpModule;
import org.netbeans.spi.project.ui.support.ProjectCustomizer;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;

/**
 *
 * @author junichi11
 */
public final class PhpCsFixerCustomizerProvider implements ProjectCustomizer.CompositeCategoryProvider {

    @NbBundle.Messages("LBL.PhpCsFixerCustomizerProvider.name=PHP CS Fixer")
    @Override
    public ProjectCustomizer.Category createCategory(Lookup context) {
        return ProjectCustomizer.Category.create("PHP", Bundle.LBL_PhpCsFixerCustomizerProvider_name(), null);
    }

    @ProjectCustomizer.CompositeCategoryProvider.Registration(projectType = "org-netbeans-modules-php-project", position = 4500)
    public static PhpCsFixerCustomizerProvider create() {
        return new PhpCsFixerCustomizerProvider();
    }

    private PhpCsFixerCustomizerProvider() {
    }

    @Override
    public JComponent createComponent(ProjectCustomizer.Category category, Lookup context) {
        PhpModule phpModule = PhpModule.Factory.lookupPhpModule(context);
        return new PhpCsFixerCustomizerPanel(category, phpModule);
    }
}
