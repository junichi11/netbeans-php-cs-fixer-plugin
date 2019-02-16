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
package org.netbeans.modules.php.phpcsfixer.ui;

import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;

/**
 *
 * @author junichi11
 */
public final class UiUtils {

    private UiUtils() {
    }

    public static void showWarningMessage(String message) {
        showMessage(message, NotifyDescriptor.WARNING_MESSAGE);
    }

    public static void showErrorMessage(String message) {
        showMessage(message, NotifyDescriptor.ERROR_MESSAGE);
    }

    public static void showInfoMessage(String message) {
        showMessage(message, NotifyDescriptor.INFORMATION_MESSAGE);
    }

    public static void showMessage(String message, int messageType) {
        NotifyDescriptor.Message m = new NotifyDescriptor.Message(message, messageType);
        DialogDisplayer.getDefault().notify(m);
    }
}
