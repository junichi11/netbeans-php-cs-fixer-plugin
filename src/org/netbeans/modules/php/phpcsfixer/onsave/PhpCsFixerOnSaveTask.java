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
            if (phpModule != null && PhpCsFixerPreferences.useProject(phpModule)) {
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
