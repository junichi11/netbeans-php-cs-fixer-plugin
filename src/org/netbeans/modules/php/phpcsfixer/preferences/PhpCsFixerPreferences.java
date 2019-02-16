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
package org.netbeans.modules.php.phpcsfixer.preferences;

import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;
import org.netbeans.modules.php.api.phpmodule.PhpModule;
import org.netbeans.modules.php.phpcsfixer.commands.PhpCsFixer;
import static org.netbeans.modules.php.phpcsfixer.options.PhpCsFixerOptions.LATEST_VERSION;

/**
 *
 * @author junichi11
 */
public final class PhpCsFixerPreferences {

    private static final String USE_GLOBAL = "use-global"; // NOI18N
    private static final String USE_PROJECT = "use-project"; // NOI18N
    private static final String RUN_ON_SAVE = "run-on-save"; // NOI18N
    // php-cs-fixer options
    private static final String VERSION = "version"; // NOI18N
    // 1.x
    private static final String USE_LEVEL = "use-level"; // NOI18N
    private static final String LEVEL = "level"; // NOI18N
    private static final String USE_CONFIG = "use-config"; // NOI18N
    private static final String CONFIG = "config"; // NOI18N
    private static final String USE_FIXERS = "use-fixers"; // NOI18N
    private static final String FIXERS = "fixers"; // NOI18N
    private static final String USE_CUSTOM = "use-custom"; // NOI18N
    // 2.x
    private static final String USE_RULES = "use-rules"; // NOI18N
    private static final String RULES = "rules"; // NOI18N
    private static final String DIFF_FORMAT_UDIFF = "diff-format-udiff"; // NOI18N
    // common
    private static final String CUSTOM = "custom"; // NOI18N
    private static final String VERBOSE = "verbose"; // NOI18N
    private static final String DIFF = "diff"; // NOI18N

    private PhpCsFixerPreferences() {
    }

    public static boolean useGlobal(PhpModule phpModule) {
        return getPreferences(phpModule).getBoolean(USE_GLOBAL, true);
    }

    public static void setGlobal(PhpModule phpModule, boolean use) {
        getPreferences(phpModule).putBoolean(USE_GLOBAL, use);
    }

    public static boolean useProject(PhpModule phpModule) {
        return getPreferences(phpModule).getBoolean(USE_PROJECT, false);
    }

    public static void setProject(PhpModule phpModule, boolean use) {
        getPreferences(phpModule).putBoolean(USE_PROJECT, use);
    }

    public static int getVersion(PhpModule phpModule) {
        int version = getPreferences(phpModule).getInt(VERSION, 2);
        if (version <= 0 || LATEST_VERSION < version) {
            version = LATEST_VERSION;
        }
        return version;
    }

    public static void setVersion(PhpModule phpModule, int version) {
        getPreferences(phpModule).putInt(VERSION, version);
    }

    // 1.x
    public static boolean useLevel(PhpModule phpModule) {
        return getPreferences(phpModule).getBoolean(USE_LEVEL, false);
    }

    public static void setLevel(PhpModule phpModule, boolean use) {
        getPreferences(phpModule).putBoolean(USE_LEVEL, use);
    }

    public static String getLevel(PhpModule phpModule) {
        return getPreferences(phpModule).get(LEVEL, ""); // NOI18N
    }

    public static void setLevel(PhpModule phpModule, String level) {
        getPreferences(phpModule).put(LEVEL, level);
    }

    public static boolean useConfig(PhpModule phpModule) {
        return getPreferences(phpModule).getBoolean(USE_CONFIG, false);
    }

    public static void setConfig(PhpModule phpModule, boolean use) {
        getPreferences(phpModule).putBoolean(USE_CONFIG, use);
    }

    public static String getConfig(PhpModule phpModule) {
        return getPreferences(phpModule).get(CONFIG, ""); // NOI18N
    }

    public static void setConfig(PhpModule phpModule, String config) {
        getPreferences(phpModule).put(CONFIG, config);
    }

    public static boolean useFixers(PhpModule phpModule) {
        return getPreferences(phpModule).getBoolean(USE_FIXERS, false);
    }

    public static void setFixers(PhpModule phpModule, boolean use) {
        getPreferences(phpModule).putBoolean(USE_FIXERS, use);
    }

    public static String getFixers(PhpModule phpModule) {
        return getPreferences(phpModule).get(FIXERS, ""); // NOI18N
    }

    public static void setFixers(PhpModule phpModule, String fixers) {
        getPreferences(phpModule).put(FIXERS, fixers);
    }

    // 2.x
    public static boolean useRules(PhpModule phpModule) {
        return getPreferences(phpModule).getBoolean(USE_RULES, false);
    }

    public static void setRules(PhpModule phpModule, boolean use) {
        getPreferences(phpModule).putBoolean(USE_RULES, use);
    }

    public static String getRules(PhpModule phpModule) {
        return getPreferences(phpModule).get(RULES, ""); // NOI18N
    }

    public static void setRules(PhpModule phpModule, String rules) {
        getPreferences(phpModule).put(RULES, rules);
    }

    public static boolean useCustom(PhpModule phpModule) {
        return getPreferences(phpModule).getBoolean(USE_CUSTOM, false);
    }

    public static void setCustom(PhpModule phpModule, boolean use) {
        getPreferences(phpModule).putBoolean(USE_CUSTOM, use);
    }

    public static String getCustom(PhpModule phpModule) {
        return getPreferences(phpModule).get(CUSTOM, ""); // NOI18N
    }

    public static void setCustom(PhpModule phpModule, String custom) {
        getPreferences(phpModule).put(CUSTOM, custom);
    }

    public static boolean isRunOnSave(PhpModule phpModule) {
        return getPreferences(phpModule).getBoolean(RUN_ON_SAVE, false);
    }

    public static void setRunOnSave(PhpModule phpModule, boolean isRunOnSave) {
        getPreferences(phpModule).putBoolean(RUN_ON_SAVE, isRunOnSave);
    }

    public static boolean isVerbose(PhpModule phpModule) {
        return getPreferences(phpModule).getBoolean(VERBOSE, false);
    }

    public static void setVerbose(PhpModule phpModule, boolean isVerbose) {
        getPreferences(phpModule).putBoolean(VERBOSE, isVerbose);
    }

    public static boolean isDiff(PhpModule phpModule) {
        return getPreferences(phpModule).getBoolean(DIFF, false);
    }

    public static void setDiff(PhpModule phpModule, boolean isDiff) {
        getPreferences(phpModule).putBoolean(DIFF, isDiff);
    }

    public static boolean isDiffFormatUdiff(PhpModule phpModule) {
        return getPreferences(phpModule).getBoolean(DIFF_FORMAT_UDIFF, false);
    }

    public static void setDiffFormatUdiff(PhpModule phpModule, boolean isDiff) {
        getPreferences(phpModule).putBoolean(DIFF_FORMAT_UDIFF, isDiff);
    }

    public static List<String> getAllOptions(PhpModule phpModule) {
        List<String> all = new ArrayList<>();
        if (getVersion(phpModule) == 2) {
            if (useRules(phpModule) && !getRules(phpModule).isEmpty()) {
                all.add(String.format(PhpCsFixer.RULES_PARAM, getRules(phpModule)));
            }
        } else {
            if (useLevel(phpModule) && !getLevel(phpModule).isEmpty()) {
                all.add(String.format(PhpCsFixer.LEVEL_PARAM, getLevel(phpModule)));
            }
            if (useConfig(phpModule) && !getConfig(phpModule).isEmpty()) {
                all.add(String.format(PhpCsFixer.CONFIG_PARAM, getConfig(phpModule)));
            }
            if (useFixers(phpModule) && !getFixers(phpModule).isEmpty()) {
                all.add(String.format(PhpCsFixer.FIXERS_PARAM, getFixers(phpModule)));
            }
        }
        if (useCustom(phpModule) && !getCustom(phpModule).isEmpty()) {
            all.add(getCustom(phpModule));
        }
        return all;
    }

    private static Preferences getPreferences(PhpModule phpModule) {
        return phpModule.getPreferences(PhpCsFixerPreferences.class, true);
    }
}
