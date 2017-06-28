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

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;
import org.netbeans.modules.php.api.util.FileUtils;
import org.netbeans.modules.php.api.util.StringUtils;
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
    private static final String PHP_CS_FIXER_VERSION = "php-cs-fixer.version"; // NOI18N
    private static final String RUN_ON_SAVE = "run.on.save"; // NOI18N
    // php-cs-fixer options
    private static final String USE_LEVEL = "use.level"; // NOI18N
    private static final String LEVEL = "level"; // NOI18N
    private static final String USE_CONFIG = "use.config"; // NOI18N
    private static final String CONFIG = "config"; // NOI18N
    private static final String USE_FIXERS = "use.fixers"; // NOI18N
    private static final String FIXERS = "fixers"; // NOI18N
    // 2.x
    private static final String USE_RULES = "use.rules"; // NOI18N
    private static final String RULES = "rules"; // NOI18N
    // common
    private static final String USE_CUSTOM = "use.custom"; // NOI18N
    private static final String CUSTOM = "custom"; // NOI18N
    private static final String VERBOSE = "verbose"; // NOI18N
    private static final String DIFF = "diff"; // NOI18N
    private static final String SHOW_OUTPUT_WINDOW = "show.output.window"; // NOI18N
    private volatile boolean phpcsfixerSearched = false;

    public static final int LATEST_VERSION = 2;

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

        // check whether file exists
        if (!StringUtils.isEmpty(phpcsfixerPath)) {
            File file = new File(phpcsfixerPath);
            if (!file.exists()) {
                phpcsfixerPath = ""; // NOI18N
                setPhpCsFixerPath(phpcsfixerPath);
            }
        }

        return phpcsfixerPath;
    }

    public void setPhpCsFixerPath(String path) {
        getPreferences().put(PHP_CS_FIXER_PATH, path);
    }

    public int getVersion() {
        int version = getPreferences().getInt(PHP_CS_FIXER_VERSION, 1);
        if (version <= 0 || LATEST_VERSION < version) {
            version = LATEST_VERSION;
        }
        return version;
    }

    public void setVersion(int version) {
        getPreferences().putInt(PHP_CS_FIXER_VERSION, version);
    }

    // version 1.x
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

    public boolean useFixers() {
        return getPreferences().getBoolean(USE_FIXERS, false);
    }

    public void setFixers(boolean use) {
        getPreferences().putBoolean(USE_FIXERS, use);
    }

    public String getFixers() {
        return getPreferences().get(FIXERS, ""); // NOI18N
    }

    public void setFixers(String fixers) {
        getPreferences().put(FIXERS, fixers);
    }

    // 2.x
    public boolean useRules() {
        return getPreferences().getBoolean(USE_RULES, false);
    }

    public void setRules(boolean use) {
        getPreferences().putBoolean(USE_RULES, use);
    }

    public String getRules() {
        return getPreferences().get(RULES, ""); // NOI18N
    }

    public void setRules(String rules) {
        getPreferences().put(RULES, rules);
    }

    // common
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

    public boolean isVerbose() {
        return getPreferences().getBoolean(VERBOSE, false);
    }

    public void setVerbose(boolean isVerbose) {
        getPreferences().putBoolean(VERBOSE, isVerbose);
    }

    public boolean isDiff() {
        return getPreferences().getBoolean(DIFF, false);
    }

    public void setDiff(boolean isDiff) {
        getPreferences().putBoolean(DIFF, isDiff);
    }

    public boolean showOutputWindow() {
        return getPreferences().getBoolean(SHOW_OUTPUT_WINDOW, true);
    }

    public void setShowOutputWindow(boolean show) {
        getPreferences().putBoolean(SHOW_OUTPUT_WINDOW, show);
    }

    public List<String> getAllOptions() {
        List<String> all = new ArrayList<>();
        if (getVersion() == 2) {
            if (useRules() && !getRules().isEmpty()) {
                all.add(String.format(PhpCsFixer.RULES_PARAM, getRules()));
            }
        } else {
            if (useLevel() && !getLevel().isEmpty()) {
                all.add(String.format(PhpCsFixer.LEVEL_PARAM, getLevel()));
            }
            if (useConfig() && !getConfig().isEmpty()) {
                all.add(String.format(PhpCsFixer.CONFIG_PARAM, getConfig()));
            }
            if (useFixers() && !getFixers().isEmpty()) {
                all.add(String.format(PhpCsFixer.FIXERS_PARAM, getFixers()));
            }
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
