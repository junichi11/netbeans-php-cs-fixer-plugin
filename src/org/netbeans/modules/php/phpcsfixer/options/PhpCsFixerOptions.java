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
    private static final String DIFF_FORMAT_UDIFF = "diff.format.udiff"; // NOI18N
    // common
    private static final String USE_CUSTOM = "use.custom"; // NOI18N
    private static final String CUSTOM = "custom"; // NOI18N
    private static final String VERBOSE = "verbose"; // NOI18N
    private static final String DIFF = "diff"; // NOI18N
    private static final String SHOW_OUTPUT_WINDOW = "show.output.window"; // NOI18N
    private static final String RUN_SELF_UPDATE_ON_BOOT = "run.self.update.on.boot"; // NOI18N
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
        int version = getPreferences().getInt(PHP_CS_FIXER_VERSION, 2);
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

    public boolean isDiffFormatUdiff() {
        return getPreferences().getBoolean(DIFF_FORMAT_UDIFF, false);
    }

    public void setDiffFormatUdiff(boolean isUdiff) {
        getPreferences().putBoolean(DIFF_FORMAT_UDIFF, isUdiff);
    }

    public boolean showOutputWindow() {
        return getPreferences().getBoolean(SHOW_OUTPUT_WINDOW, true);
    }

    public void setShowOutputWindow(boolean show) {
        getPreferences().putBoolean(SHOW_OUTPUT_WINDOW, show);
    }

    public boolean runSelfUpdateOnBoot() {
        return getPreferences().getBoolean(RUN_SELF_UPDATE_ON_BOOT, true);
    }

    public void setRunSelfUpdateOnBoot(boolean run) {
        getPreferences().putBoolean(RUN_SELF_UPDATE_ON_BOOT, run);
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
