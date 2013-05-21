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
package org.netbeans.modules.php.phpcsfixer.options;

import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;
import org.netbeans.modules.php.api.util.FileUtils;
import org.netbeans.modules.php.phpcsfixer.commands.PhpCsFixer;
import org.openide.util.NbPreferences;

/**
 *
 * @author junichi11
 */
public final class PhpCsFixerOptions {

    private static final PhpCsFixerOptions INSTANCE = new PhpCsFixerOptions();
    private static final String PREFERENCES_PATH = "php-cs-fixer"; // NOI18N
    private static final String PHP_CS_FIXER_PATH = "php-cs-fixer.path"; // NOI18N
    private static final String RUN_ON_SAVE = "run.on.save"; // NOI18N
    // php-cs-fixer options
    private static final String USE_LEVEL = "use.level"; // NOI18N
    private static final String LEVEL = "level"; // NOI18N
    private static final String USE_CONFIG = "use.config"; // NOI18N
    private static final String CONFIG = "config"; // NOI18N
    private static final String USE_FIXER = "use.fixer"; // NOI18N
    private static final String FIXER = "fixer"; // NOI18N
    private static final String USE_CUSTOM = "use.custom"; // NOI18N
    private static final String CUSTOM = "custom"; // NOI18N
    private volatile boolean phpcsfixerSearched = false;

    private PhpCsFixerOptions() {
    }

    public static PhpCsFixerOptions getInstance() {
        return INSTANCE;
    }

    public String getPhpCsFixerPath() {
        String phpcsfixerPath = getPreferences().get(PHP_CS_FIXER_PATH, null);
        if (phpcsfixerPath == null && !phpcsfixerSearched) {
            phpcsfixerSearched = true;
            List<String> paths = FileUtils.findFileOnUsersPath(PhpCsFixer.NAME, PhpCsFixer.NAME_LONG);
            if (!paths.isEmpty()) {
                phpcsfixerPath = paths.get(0);
                setPhpCsFixerPath(phpcsfixerPath);
            }
        }
        return phpcsfixerPath;
    }

    public void setPhpCsFixerPath(String path) {
        getPreferences().put(PHP_CS_FIXER_PATH, path);
    }

    public boolean useLevel() {
        return getPreferences().getBoolean(USE_LEVEL, false);
    }

    public void setLevel(boolean use) {
        getPreferences().putBoolean(USE_LEVEL, use);
    }

    public String getLevel() {
        return getPreferences().get(LEVEL, ""); // NOI18N
    }

    public void setLevel(String level) {
        getPreferences().put(LEVEL, level);
    }

    public boolean useConfig() {
        return getPreferences().getBoolean(USE_CONFIG, false);
    }

    public void setConfig(boolean use) {
        getPreferences().putBoolean(USE_CONFIG, use);
    }

    public String getConfig() {
        return getPreferences().get(CONFIG, ""); // NOI18N
    }

    public void setConfig(String config) {
        getPreferences().put(CONFIG, config);
    }

    public boolean useFixer() {
        return getPreferences().getBoolean(USE_FIXER, false);
    }

    public void setFixer(boolean use) {
        getPreferences().putBoolean(USE_FIXER, use);
    }

    public String getFixer() {
        return getPreferences().get(FIXER, ""); // NOI18N
    }

    public void setFixer(String fixer) {
        getPreferences().put(FIXER, fixer);
    }

    public boolean useCustom() {
        return getPreferences().getBoolean(USE_CUSTOM, false);
    }

    public void setCustom(boolean use) {
        getPreferences().putBoolean(USE_CUSTOM, use);
    }

    public String getCustom() {
        return getPreferences().get(CUSTOM, ""); // NOI18N
    }

    public void setCustom(String custom) {
        getPreferences().put(CUSTOM, custom);
    }

    public boolean isRunOnSave() {
        return getPreferences().getBoolean(RUN_ON_SAVE, false);
    }

    public void setRunOnSave(boolean isRunOnSave) {
        getPreferences().putBoolean(RUN_ON_SAVE, isRunOnSave);
    }

    public List<String> getAllOptions() {
        List<String> all = new ArrayList<String>();
        if (useLevel() && !getLevel().isEmpty()) {
            all.add(String.format(PhpCsFixer.LEVEL_PARAM, getLevel()));
        }
        if (useConfig() && !getConfig().isEmpty()) {
            all.add(String.format(PhpCsFixer.CONFIG_PARAM, getConfig()));
        }
        if (useFixer() && !getFixer().isEmpty()) {
            all.add(String.format(PhpCsFixer.FIXER_PARAM, getFixer()));
        }
        if (useCustom() && !getCustom().isEmpty()) {
            all.add(getCustom());
        }

        return all;
    }

    private Preferences getPreferences() {
        return NbPreferences.forModule(PhpCsFixerOptions.class).node(PREFERENCES_PATH);
    }
}
