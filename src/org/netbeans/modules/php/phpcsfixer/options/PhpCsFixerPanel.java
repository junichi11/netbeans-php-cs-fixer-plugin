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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import static java.util.logging.Level.INFO;
import static java.util.logging.Level.WARNING;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;
import org.netbeans.modules.php.api.executable.InvalidPhpExecutableException;
import org.netbeans.modules.php.api.util.FileUtils;
import org.netbeans.modules.php.api.util.StringUtils;
import org.netbeans.modules.php.phpcsfixer.commands.PhpCsFixer;
import org.netbeans.modules.php.phpcsfixer.ui.UiUtils;
import org.openide.awt.Mnemonics;
import org.openide.filesystems.FileChooserBuilder;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.NbBundle;
import org.openide.util.RequestProcessor;

final class PhpCsFixerPanel extends javax.swing.JPanel {

    private static final long serialVersionUID = 8727885479187122174L;
    private final PhpCsFixerOptionsPanelController controller;
    private static final String PHPCSFIXER_LAST_FOLDER_SUFFIX = ".phpcsfixer"; // NOI18N
    private static final RequestProcessor RP = new RequestProcessor(PhpCsFixerPanel.class);
    private static final Logger LOGGER = Logger.getLogger(PhpCsFixerPanel.class.getName());

    private int version;
    // 1.x
    private boolean useLevel;
    private boolean useConfig;
    private boolean useFixers;
    private String level;
    private String config;
    private String fixers;
    // 2.x
    private boolean useRules;
    private boolean isDiffFormatUdiff;
    private String rules;
    // common
    private boolean useCustom;
    private boolean showOutputWindow;
    private boolean runSelfUpdateOnBoot;
    private boolean isRunOnSave;
    private boolean isVerbose;
    private boolean isDiff;
    private String custom;

    PhpCsFixerPanel(PhpCsFixerOptionsPanelController controller) {
        this.controller = controller;
        initComponents();
        setVersion(""); // NOI18N
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pathLabel = new JLabel();
        pathTextField = new JTextField();
        browseButton = new JButton();
        phpCsFixerNameLabel = new JLabel();
        optionsPanel = new PhpCsFixerOptionsPanel();
        downloadButton = new JButton();
        showOutputWindowCheckBox = new JCheckBox();
        versionLabel = new JLabel();
        selfUpdateButton = new JButton();
        runSelfUpdateOnBootCheckBox = new JCheckBox();

        Mnemonics.setLocalizedText(pathLabel, NbBundle.getMessage(PhpCsFixerPanel.class, "PhpCsFixerPanel.pathLabel.text")); // NOI18N

        pathTextField.setText(NbBundle.getMessage(PhpCsFixerPanel.class, "PhpCsFixerPanel.pathTextField.text")); // NOI18N

        Mnemonics.setLocalizedText(browseButton, NbBundle.getMessage(PhpCsFixerPanel.class, "PhpCsFixerPanel.browseButton.text")); // NOI18N
        browseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                browseButtonActionPerformed(evt);
            }
        });

        Mnemonics.setLocalizedText(phpCsFixerNameLabel, NbBundle.getMessage(PhpCsFixerPanel.class, "PhpCsFixerPanel.phpCsFixerNameLabel.text")); // NOI18N

        Mnemonics.setLocalizedText(downloadButton, NbBundle.getMessage(PhpCsFixerPanel.class, "PhpCsFixerPanel.downloadButton.text")); // NOI18N
        downloadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                downloadButtonActionPerformed(evt);
            }
        });

        Mnemonics.setLocalizedText(showOutputWindowCheckBox, NbBundle.getMessage(PhpCsFixerPanel.class, "PhpCsFixerPanel.showOutputWindowCheckBox.text")); // NOI18N

        Mnemonics.setLocalizedText(versionLabel, "VERSION"); // NOI18N

        Mnemonics.setLocalizedText(selfUpdateButton, NbBundle.getMessage(PhpCsFixerPanel.class, "PhpCsFixerPanel.selfUpdateButton.text")); // NOI18N
        selfUpdateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                selfUpdateButtonActionPerformed(evt);
            }
        });

        Mnemonics.setLocalizedText(runSelfUpdateOnBootCheckBox, NbBundle.getMessage(PhpCsFixerPanel.class, "PhpCsFixerPanel.runSelfUpdateOnBootCheckBox.text")); // NOI18N

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(optionsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pathLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(phpCsFixerNameLabel)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(pathTextField)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(browseButton, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(downloadButton)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(selfUpdateButton))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(showOutputWindowCheckBox)
                            .addComponent(versionLabel)
                            .addComponent(runSelfUpdateOnBootCheckBox))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(pathLabel)
                    .addComponent(pathTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(browseButton)
                    .addComponent(downloadButton)
                    .addComponent(selfUpdateButton))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(phpCsFixerNameLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(versionLabel)
                .addGap(10, 10, 10)
                .addComponent(showOutputWindowCheckBox)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(runSelfUpdateOnBootCheckBox)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(optionsPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    @NbBundle.Messages("LBL_SelectPhpCsFixer=Select PHP CS Fixer")
    private void browseButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_browseButtonActionPerformed
        File phpcsfixer = new FileChooserBuilder(PhpCsFixerPanel.class.getName() + PHPCSFIXER_LAST_FOLDER_SUFFIX)
                .setTitle(Bundle.LBL_SelectPhpCsFixer())
                .setFilesOnly(true)
                .setFileFilter(new FileFilterImpl())
                .showOpenDialog();
        if (phpcsfixer != null) {
            phpcsfixer = FileUtil.normalizeFile(phpcsfixer);
            if (phpcsfixer.getName().equals(PhpCsFixer.NAME_LONG)
                    || phpcsfixer.getName().equals(PhpCsFixer.NAME)
                    || phpcsfixer.getName().equals(PhpCsFixer.NAME + FileUtils.getScriptExtension(true))) {
                setPath(phpcsfixer.getAbsolutePath());
            } else {
                setPath(""); // NOI18N
            }
        }
    }//GEN-LAST:event_browseButtonActionPerformed

    @NbBundle.Messages("LBL_SelectDonwloadFolder=Select download folder")
    private void downloadButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_downloadButtonActionPerformed
        File downloadDirectory = new FileChooserBuilder(PhpCsFixerPanel.class.getName() + PHPCSFIXER_LAST_FOLDER_SUFFIX)
                .setTitle(Bundle.LBL_SelectDonwloadFolder())
                .setDirectoriesOnly(true)
                .showOpenDialog();
        if (downloadDirectory != null) {
            downloadDirectory = FileUtil.normalizeFile(downloadDirectory);
            // has phpcsfixer file?
            FileObject downloadFileObject = FileUtil.toFileObject(downloadDirectory);
            FileObject[] children = downloadFileObject.getChildren();
            for (FileObject child : children) {
                if (!child.isFolder() && child.getNameExt().equals(PhpCsFixer.NAME_LONG)) {
                    LOGGER.log(INFO, PhpCsFixer.NAME_LONG + " already exists in {0} directory.", downloadFileObject.getName()); // NOI18N
                    try {
                        setPath(FileUtil.toFile(child).getCanonicalPath());
                    } catch (IOException ex) {
                        LOGGER.log(WARNING, ex.getMessage());
                    }
                    return;
                }
            }

            // create file
            File file = new File(downloadDirectory, PhpCsFixer.NAME_LONG);
            try (FileOutputStream outputStream = new FileOutputStream(file)) {
                URL downloadUrl = new URL(PhpCsFixer.DOWNLOAD_URL);
                InputStream inputStream = downloadUrl.openStream();
                int data;
                while ((data = inputStream.read()) != -1) {
                    outputStream.write(data);
                }
                String filePath = file.getCanonicalPath();
                setPath(filePath);
                // run self-update because it might be an old version
                selfUpdate(filePath);
            } catch (MalformedURLException ex) {
                if (file.exists()) {
                    file.delete();
                }
                LOGGER.log(WARNING, "Download URL may be changed.", ex); // NOI18N
            } catch (IOException ex) {
                if (file.exists()) {
                    file.delete();
                }
                downloadButton.setEnabled(false);
            }
        }
    }//GEN-LAST:event_downloadButtonActionPerformed

    @NbBundle.Messages("PhpCsFixerPanel.selfupdate.warning.message=Please set a file path")
    private void selfUpdateButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_selfUpdateButtonActionPerformed
        if (StringUtils.isEmpty(getPath())) {
            UiUtils.showWarningMessage(Bundle.PhpCsFixerPanel_selfupdate_warning_message());
        } else {
            selfUpdate(getPath());
        }
    }//GEN-LAST:event_selfUpdateButtonActionPerformed

    void load() {
        PhpCsFixerOptions options = getOptions();
        setPath(options.getPhpCsFixerPath());
        setShowOutputWindow(options.showOutputWindow());

        // original options
        version = options.getVersion();
        showOutputWindow = options.showOutputWindow();
        runSelfUpdateOnBoot = options.runSelfUpdateOnBoot();

        // 1.x
        useLevel = options.useLevel();
        level = options.getLevel();
        useConfig = options.useConfig();
        config = options.getConfig();
        useFixers = options.useFixers();
        fixers = options.getFixers();

        // 2.x
        useRules = options.useRules();
        rules = options.getRules();
        isDiffFormatUdiff = options.isDiffFormatUdiff();

        // common
        useCustom = options.useCustom();
        custom = options.getCustom();
        isRunOnSave = options.isRunOnSave();
        isVerbose = options.isVerbose();
        isDiff = options.isDiff();

        optionsPanel.setVersion(version);
        // 1.x
        optionsPanel.setLevel(useLevel);
        optionsPanel.setLevel(level);
        optionsPanel.setConfig(useConfig);
        optionsPanel.setConfig(config);
        optionsPanel.setFixers(useFixers);
        optionsPanel.setFixers(fixers);

        // 2.x
        optionsPanel.setRules(useRules);
        optionsPanel.setRules(rules);
        optionsPanel.setDiffFormatUdiff(isDiffFormatUdiff);

        // common
        optionsPanel.setCustom(useCustom);
        optionsPanel.setCustom(custom);
        optionsPanel.setRunOnSave(isRunOnSave);
        optionsPanel.setVerbose(isVerbose);
        optionsPanel.setDiff(isDiff);
        downloadButton.setEnabled(true);
    }

    void store() {
        PhpCsFixerOptions options = getOptions();
        String path = getPath();
        if (path.endsWith(PhpCsFixer.NAME)
                || path.endsWith(PhpCsFixer.NAME_LONG)
                || path.endsWith(PhpCsFixer.NAME + FileUtils.getScriptExtension(true))) {
            options.setPhpCsFixerPath(getPath());
        } else {
            options.setPhpCsFixerPath(""); // NOI18N
        }

        if (showOutputWindow != showOutputWindow()) {
            options.setShowOutputWindow(showOutputWindow());
        }

        if (runSelfUpdateOnBoot != runSelfUpdateOnBoot()) {
            options.setRunSelfUpdateOnBoot(runSelfUpdateOnBoot());
        }

        if (version != optionsPanel.getVersion()) {
            options.setVersion(optionsPanel.getVersion());
        }
        // 1.x
        if (useLevel != optionsPanel.useLevel()) {
            options.setLevel(optionsPanel.useLevel());
        }
        if (!level.equals(optionsPanel.getLevel())) {
            options.setLevel(optionsPanel.getLevel());
        }
        if (useConfig != optionsPanel.useConfig()) {
            options.setConfig(optionsPanel.useConfig());
        }
        if (!config.equals(optionsPanel.getConfig())) {
            options.setConfig(optionsPanel.getConfig());
        }
        if (useFixers != optionsPanel.useFixers()) {
            options.setFixers(optionsPanel.useFixers());
        }
        if (!fixers.equals(optionsPanel.getFixers())) {
            options.setFixers(optionsPanel.getFixers());
        }
        // 2.x
        if (useRules != optionsPanel.useRules()) {
            options.setRules(optionsPanel.useRules());
        }
        if (!rules.equals(optionsPanel.getRules())) {
            options.setRules(optionsPanel.getRules());
        }
        if (useCustom != optionsPanel.useCustom()) {
            options.setCustom(optionsPanel.useCustom());
        }
        if (isDiffFormatUdiff != optionsPanel.isDiffFormatUdiff()) {
            options.setDiff(optionsPanel.isDiffFormatUdiff());
        }
        // common
        if (!custom.equals(optionsPanel.getCustom())) {
            options.setCustom(optionsPanel.getCustom());
        }
        if (isRunOnSave != optionsPanel.isRunOnSave()) {
            options.setRunOnSave(optionsPanel.isRunOnSave());
        }
        if (isVerbose != optionsPanel.isVerbose()) {
            options.setVerbose(optionsPanel.isVerbose());
        }
        if (isDiff != optionsPanel.isDiff()) {
            options.setDiff(optionsPanel.isDiff());
        }
    }

    private PhpCsFixerOptions getOptions() {
        return PhpCsFixerOptions.getInstance();
    }

    public void setPath(String path) {
        pathTextField.setText(path);
        // set version
        if (!StringUtils.isEmpty(path)) {
            reloadVersion(path);
        } else {
            setVersion(""); // NOI18N
        }
    }

    public String getPath() {
        return pathTextField.getText().trim();
    }

    public void setVersion(String version) {
        versionLabel.setText(String.format("<html><b>%s</b></html>", version)); // NOI18N
    }

    public String getVersion() {
        return versionLabel.getText();
    }

    public void setShowOutputWindow(boolean show) {
        showOutputWindowCheckBox.setSelected(show);
    }

    public boolean showOutputWindow() {
        return showOutputWindowCheckBox.isSelected();
    }

    public void setRunSelfUpdateOnBoot(boolean run) {
        runSelfUpdateOnBootCheckBox.setSelected(run);
    }

    public boolean runSelfUpdateOnBoot() {
        return runSelfUpdateOnBootCheckBox.isSelected();
    }

    private void selfUpdate(String filePath) {
        RP.post(() -> {
            try {
                Future<Integer> future = PhpCsFixer.newInstance(filePath).selfUpdate(null);
                Integer get = future.get();
                reloadVersion(filePath);
            } catch (InvalidPhpExecutableException ex) {
                UiUtils.showWarningMessage(ex.getMessage());
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            } catch (ExecutionException ex) {
                LOGGER.log(WARNING, null, ex);
            }
        });
    }

    private void reloadVersion(String path) {
        RP.post(() -> {
            try {
                String ver = PhpCsFixer.newInstance(path).getVersion();
                SwingUtilities.invokeLater(() -> setVersion(ver));
            } catch (InvalidPhpExecutableException ex) {
                LOGGER.log(WARNING, ex.getMessage());
            }
        });
    }

    boolean valid() {
        // TODO check whether form is consistent and complete
        String path = pathTextField.getText();
        return !StringUtils.isEmpty(path);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton browseButton;
    private JButton downloadButton;
    private PhpCsFixerOptionsPanel optionsPanel;
    private JLabel pathLabel;
    private JTextField pathTextField;
    private JLabel phpCsFixerNameLabel;
    private JCheckBox runSelfUpdateOnBootCheckBox;
    private JButton selfUpdateButton;
    private JCheckBox showOutputWindowCheckBox;
    private JLabel versionLabel;
    // End of variables declaration//GEN-END:variables

    private static class FileFilterImpl extends FileFilter {

        public FileFilterImpl() {
        }

        @Override
        public boolean accept(File f) {
            if (f.isDirectory()) {
                return true;
            }
            String name = f.getName();
            return f.isFile() && name.startsWith(PhpCsFixer.NAME);
        }

        @Override
        public String getDescription() {
            return PhpCsFixer.NAME + " or " + PhpCsFixer.NAME_LONG;  // NOI18N
        }
    }
}
