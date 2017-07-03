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
package org.netbeans.modules.php.phpcsfixer.onsave;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.Document;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.modules.editor.NbEditorUtilities;
import org.netbeans.modules.php.api.phpmodule.PhpModule;
import org.netbeans.modules.php.api.util.FileUtils;
import org.netbeans.modules.php.api.util.StringUtils;
import org.netbeans.modules.php.phpcsfixer.options.PhpCsFixerOptions;
import org.netbeans.modules.php.phpcsfixer.preferences.PhpCsFixerPreferences;
import org.netbeans.modules.php.phpcsfixer.ui.actions.FixAction;
import org.netbeans.spi.editor.document.OnSaveTask;
import org.openide.filesystems.FileObject;
import org.openide.util.RequestProcessor;

/**
 *
 * @author junichi11
 */
public final class PhpCsFixerOnSaveTask implements OnSaveTask {

    private final Context context;
    private static final RequestProcessor RP = new RequestProcessor(PhpCsFixerOnSaveTask.class);
    private static final Logger LOGGER = Logger.getLogger(PhpCsFixerOnSaveTask.class.getName());

    private PhpCsFixerOnSaveTask(Context context) {
        this.context = context;
    }

    @Override
    public void performTask() {
        String path = PhpCsFixerOptions.getInstance().getPhpCsFixerPath();
        if (StringUtils.isEmpty(path)) {
            LOGGER.log(Level.WARNING, "Not found : PHP CS Fixer (Please, set PHP CS Fixer path to Options)"); // NOI18N
            return;
        }
        Document document = context.getDocument();
        final FileObject fileObject = NbEditorUtilities.getFileObject(document);
        if (fileObject != null) {
            // check run on save option
            boolean isRunOnSave;
            final PhpModule phpModule = PhpModule.Factory.forFileObject(fileObject);
            if (PhpCsFixerPreferences.useProject(phpModule)) {
                isRunOnSave = PhpCsFixerPreferences.isRunOnSave(phpModule);
            } else {
                isRunOnSave = PhpCsFixerOptions.getInstance().isRunOnSave();
            }
            if (!isRunOnSave) {
                return;
            }

            // XXX Run command after 1 second
            // Somethimes it may not work
            RP.schedule(() -> {
                FixAction fixAction = new FixAction();
                fixAction.actionPerformed(null, fileObject);
                LOGGER.log(Level.FINE, "Run php-cs-fixer: {0}", fileObject.getNameExt()); // NOI18N
            }, 1000, TimeUnit.MILLISECONDS);
        }
    }

    @Override
    public void runLocked(Runnable r) {
        r.run();
    }

    @Override
    public boolean cancel() {
        return true;
    }

    @MimeRegistration(mimeType = FileUtils.PHP_MIME_TYPE, service = OnSaveTask.Factory.class, position = 5000)
    public static final class FactoryImpl implements Factory {

        @Override
        public OnSaveTask createTask(Context context) {
            return new PhpCsFixerOnSaveTask(context);
        }
    }
}
