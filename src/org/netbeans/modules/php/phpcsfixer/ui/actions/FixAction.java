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
package org.netbeans.modules.php.phpcsfixer.ui.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.netbeans.modules.php.api.executable.InvalidPhpExecutableException;
import org.netbeans.modules.php.api.phpmodule.PhpModule;
import org.netbeans.modules.php.api.util.StringUtils;
import org.netbeans.modules.php.phpcsfixer.commands.PhpCsFixer;
import org.openide.awt.ActionID;
import org.openide.awt.ActionRegistration;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
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

    @NbBundle.Messages("FixAction.name=Fix")
    @Override
    protected String getName() {
        return Bundle.FixAction_name();
    }

    @Override
    protected void runCommand(PhpModule phpModule, List<String> options) throws InvalidPhpExecutableException {
        for (FileObject target : getTargetFiles()) {
            runCommand(phpModule, options, target);
        }
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
