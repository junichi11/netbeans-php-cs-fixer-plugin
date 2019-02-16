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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.netbeans.modules.php.api.executable.InvalidPhpExecutableException;
import org.netbeans.modules.php.api.phpmodule.PhpModule;
import org.netbeans.modules.php.api.util.StringUtils;
import org.netbeans.modules.php.phpcsfixer.commands.PhpCsFixer;
import org.openide.*;
import org.openide.awt.ActionID;
import org.openide.awt.ActionRegistration;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.util.Utilities;

/**
 *
 * @author junichi11
 */
@ActionID(
        category = "PHP",
        id = "org.netbeans.modules.php.phpcsfixer.ui.actions.FixAction"
)
@ActionRegistration(
        displayName = "#CTL_FixAction"
)
@NbBundle.Messages("CTL_FixAction=PHP CS Fixer: Fix")
public class FixAction extends PhpCsFixerBaseAction {

    private static final long serialVersionUID = -3347012049948024185L;
    private static final Logger LOGGER = Logger.getLogger(FixAction.class.getName());

    @NbBundle.Messages("FixAction.name=Fix")
    @Override
    protected String getName() {
        return Bundle.FixAction_name();
    }

    @Override
    protected void runCommand(PhpModule phpModule, List<String> options) throws InvalidPhpExecutableException {
        Collection<? extends FileObject> targetFiles = getTargetFiles();
        for (FileObject target : targetFiles) {
            if (target.isFolder()) {
                Enumeration<? extends FileObject> children = target.getChildren(true);
                while (children.hasMoreElements()) {
                    if (isModifiedFile(children.nextElement())) {
                        return;
                    }
                }
            } else {
                if (isModifiedFile(target)) {
                    return;
                }
            }
            runCommand(phpModule, options, target);
        }
    }

    @NbBundle.Messages({
        "# {0} - file name",
        "FixAction.message.modified.file=There is a modified file({0})."
    })
    private boolean isModifiedFile(FileObject target) {
        try {
            DataObject dataObject = DataObject.find(target);
            if (!target.isFolder() && dataObject.isModified()) {
                // show message
                NotifyDescriptor descriptor = new NotifyDescriptor.Message(
                        Bundle.FixAction_message_modified_file(target.getNameExt()),
                        NotifyDescriptor.ERROR_MESSAGE
                );
                DialogDisplayer.getDefault().notifyLater(descriptor);
                return true;
            }
        }catch (DataObjectNotFoundException ex) {
            LOGGER.log(Level.WARNING, null, ex);
        }
        return false;
    }

    @Override
    public void runCommand(PhpModule phpModule, List<String> options, FileObject targetFile) throws InvalidPhpExecutableException {
        List<String> params = getAllParams(targetFile, options);
        if (!params.isEmpty()) {
            Future<Integer> result = PhpCsFixer.getDefault().fix(phpModule, params.toArray(new String[0]));
            if (result != null) {
                try {
                    result.get();
                } catch (InterruptedException | ExecutionException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        }
    }

    protected Collection<? extends FileObject> getTargetFiles() {
        Lookup context = Utilities.actionsGlobalContext();
        return context.lookupAll(FileObject.class);
    }

    protected List<String> getAllParams(FileObject target, List<String> options) {
        if (target == null) {
            return Collections.emptyList();
        }
        String targetPath = null;
        try {
            targetPath = FileUtil.toFile(target).getCanonicalPath();
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
        if (StringUtils.isEmpty(targetPath)) {
            return Collections.emptyList();
        }

        List<String> params = new ArrayList<>(Collections.singletonList(targetPath));
        params.addAll(options);
        return params;
    }
}
