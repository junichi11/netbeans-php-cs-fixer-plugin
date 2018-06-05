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
package org.netbeans.modules.php.phpcsfixer.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.netbeans.api.extexecution.ExecutionDescriptor;
import org.netbeans.api.extexecution.base.input.InputProcessor;
import org.netbeans.api.extexecution.base.input.InputProcessors;
import org.netbeans.api.extexecution.base.input.LineProcessor;
import org.netbeans.modules.php.api.executable.InvalidPhpExecutableException;
import org.netbeans.modules.php.api.executable.PhpExecutable;
import org.netbeans.modules.php.api.executable.PhpExecutableValidator;
import org.netbeans.modules.php.api.phpmodule.PhpModule;
import org.netbeans.modules.php.phpcsfixer.options.PhpCsFixerOptions;
import org.netbeans.modules.php.phpcsfixer.options.PhpCsFixerOptionsPanelController;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle;
import org.openide.windows.InputOutput;

/**
 * @see https://github.com/FriendsOfPHP/PHP-CS-Fixer
 * @author junichi11
 */
public final class PhpCsFixer {

    // PHP CS Fixer
    public static final String NAME = "php-cs-fixer"; // NOI18N
    public static final String NAME_LONG = NAME + ".phar"; // NOI18N
    public static final String DOWNLOAD_URL = "http://get.sensiolabs.org/php-cs-fixer.phar"; // NOI18N
    private final String phpcsfixerPath;
    private boolean isDryRun;
    // commands
    private static final String FIX_COMMAND = "fix"; // NOI18N
    private static final String SELF_UPDATE_COMMAND = "self-update"; // NOI18N
    //parameters
    private static final String VERSION_PARAM = "--version"; // NOI18N
    public static final String DRY_RUN_PARAM = "--dry-run"; // NOI18N
    public static final String VERBOSE_PARAM = "--verbose"; // NOI18N
    public static final String DIFF_PARAM = "--diff"; // NOI18N
    // 1.x
    public static final String CONFIG_PARAM = "--config=%s"; // NOI18N
    public static final String LEVEL_PARAM = "--level=%s"; // NOI18N
    public static final String FIXERS_PARAM = "--fixers=%s"; // NOI18N
    // 2.x
    public static final String RULES_PARAM = "--rules=%s"; // NOI18N
    public static final String DIFF_FORMAT_UDIFF_PARAM = "--diff-format=udiff"; // NOI18N
    private static final List<String> DEFAULT_PARAMS = Arrays.asList(
            "--ansi", // NOI18N
            "--no-interaction"); // NOI18N

    private static final ExecutionDescriptor DEFAULT_EXECUTION_DESCRIPTOR = new ExecutionDescriptor()
            .optionsPath(PhpCsFixerOptionsPanelController.getOptionsPath())
            .controllable(true)
            .frontWindow(true)
            .frontWindowOnError(true)
            .inputVisible(false)
            .showProgress(true);

    private static final ExecutionDescriptor NO_OUTPUT_EXECUTION_DESCRIPTOR = new ExecutionDescriptor()
            .inputOutput(InputOutput.NULL);
    private static final Logger LOGGER = Logger.getLogger(PhpCsFixer.class.getName());

    public PhpCsFixer(String phpcsfixerPath) {
        this.phpcsfixerPath = phpcsfixerPath;
        isDryRun = false;
    }

    public static PhpCsFixer getDefault() throws InvalidPhpExecutableException {
        String phpcsfixerPath = PhpCsFixerOptions.getInstance().getPhpCsFixerPath();
        String warning = validate(phpcsfixerPath);
        if (warning != null) {
            LOGGER.log(Level.WARNING, "PHP CS Fixer path is not set."); // NOI18N
            throw new InvalidPhpExecutableException(warning);
        }
        return new PhpCsFixer(phpcsfixerPath);
    }

    @NbBundle.Messages("PhpCsFixer.script.label=PHP CS Fixer")
    private static String validate(String phpcsfixerPath) {
        return PhpExecutableValidator.validateCommand(phpcsfixerPath, Bundle.PhpCsFixer_script_label());
    }

    @NbBundle.Messages({
        "# {0} - command name",
        "PhpCsFixer.run=PHP CS Fixer ({0})"
    })
    public Future<Integer> fix(PhpModule phpModule, String... params) {
        return runCommand(phpModule, FIX_COMMAND, Bundle.PhpCsFixer_run(FIX_COMMAND), Arrays.asList(params));
    }

    public Future<Integer> fixDryRun(PhpModule phpModule, String... params) {
        isDryRun = true;
        List<String> allParams = new ArrayList<>(params.length + 1);
        allParams.addAll(Arrays.asList(params));
        allParams.add(DRY_RUN_PARAM);
        return runCommand(phpModule, FIX_COMMAND, Bundle.PhpCsFixer_run(FIX_COMMAND + " " + DRY_RUN_PARAM), allParams);
    }

    public Future<Integer> selfUpdate(PhpModule phpModule) {
        return runCommand(phpModule, SELF_UPDATE_COMMAND, Bundle.PhpCsFixer_run(SELF_UPDATE_COMMAND));
    }

    /**
     * Get version.
     *
     * @return version
     */
    public String getVersion() {
        DefaultLineProcessor lineProcessor = new DefaultLineProcessor();
        Future<Integer> result = getPhpExecutable(null, "version")
                .additionalParameters(Arrays.asList(VERSION_PARAM))
                .run(NO_OUTPUT_EXECUTION_DESCRIPTOR, getOutputProcessorFactory(lineProcessor));
        try {
            if (result != null) {
                result.get();
            }
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException ex) {
            Exceptions.printStackTrace(ex);
        }
        return lineProcessor.getResult();
    }

    private Future<Integer> runCommand(PhpModule phpModule, String command, String title) {
        return runCommand(phpModule, command, title, Collections.<String>emptyList());
    }

    private Future<Integer> runCommand(PhpModule phpModule, String command, String title, List<String> params) {
        PhpExecutable phpcsfixer = getPhpExecutable(phpModule, title);
        if (phpcsfixer == null) {
            return null;
        }
        return phpcsfixer
                .additionalParameters(mergeParameters(command, DEFAULT_PARAMS, params))
                .run(getDescriptor(phpModule));
    }

    private List<String> mergeParameters(String command, List<String> defaultParams, List<String> params) {
        List<String> allParams = new ArrayList<>(defaultParams.size() + params.size() + 1);
        allParams.add(command);
        allParams.addAll(params);
        allParams.addAll(defaultParams);
        return allParams;
    }

    private PhpExecutable getPhpExecutable(PhpModule phpModule, String title) {
        FileObject sourceDirectory = null;
        if (phpModule != null) {
            sourceDirectory = phpModule.getSourceDirectory();
        }
        PhpExecutable phpcsfixer = new PhpExecutable(phpcsfixerPath)
                .optionsSubcategory(PhpCsFixerOptionsPanelController.OPTIONS_SUBPATH)
                .displayName(title);
        if (sourceDirectory != null) {
            phpcsfixer.workDir(FileUtil.toFile(sourceDirectory));
        }
        return phpcsfixer;
    }

    private ExecutionDescriptor getDescriptor(PhpModule phpModule) {
        ExecutionDescriptor descriptor;
        if (PhpCsFixerOptions.getInstance().showOutputWindow() || isDryRun) {
            descriptor = DEFAULT_EXECUTION_DESCRIPTOR;
        } else {
            descriptor = NO_OUTPUT_EXECUTION_DESCRIPTOR;
        }
        descriptor = descriptor.postExecution(() -> {});
        if (phpModule != null) {
            final FileObject sourceDirectory = phpModule.getSourceDirectory();
            if (sourceDirectory != null) {
                descriptor = descriptor.postExecution(() -> {
                    // refresh sources after running command
                    sourceDirectory.refresh();
                });
            }
        }
        return descriptor;
    }

    /**
     * Get InputProcessorFactory.
     *
     * @param lineProcessor
     * @return InputProcessorFactory
     */
    private ExecutionDescriptor.InputProcessorFactory2 getOutputProcessorFactory(final LineProcessor lineProcessor) {
        return (InputProcessor defaultProcessor) -> InputProcessors.ansiStripping(InputProcessors.bridge(lineProcessor));
    }

    private static class DefaultLineProcessor implements LineProcessor {

        private final StringBuilder result = new StringBuilder();
        private final List<String> list = new ArrayList<>();

        @Override
        public void processLine(String line) {
            list.add(line);
            if (!list.isEmpty()) {
                result.append("\n"); // NOI18N
            }
            result.append(line);
        }

        @Override
        public void reset() {
        }

        @Override
        public void close() {
        }

        public String getResult() {
            return result.toString();
        }

        public List<String> asLines() {
            return list;
        }
    }
}
