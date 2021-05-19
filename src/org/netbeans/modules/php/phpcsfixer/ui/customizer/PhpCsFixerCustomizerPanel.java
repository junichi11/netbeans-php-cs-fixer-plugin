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
package org.netbeans.modules.php.phpcsfixer.ui.customizer;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle;
import org.netbeans.modules.php.api.phpmodule.PhpModule;
import org.netbeans.modules.php.phpcsfixer.options.PhpCsFixerOptionsPanel;
import org.netbeans.modules.php.phpcsfixer.preferences.PhpCsFixerPreferences;
import org.netbeans.spi.project.ui.support.ProjectCustomizer.Category;
import org.openide.awt.Mnemonics;
import org.openide.util.NbBundle;

/**
 *
 * @author junichi11
 */
public final class PhpCsFixerCustomizerPanel extends javax.swing.JPanel {

    private static final long serialVersionUID = -6326119172349277539L;
    private final Category catetgory;
    private final PhpModule phpModule;
    private boolean isGlobal;
    private boolean isProject;
    private int version;
    // 1.x
    private boolean useLevel;
    private String level;
    private boolean useConfig;
    private String config;
    private boolean useFixers;
    private String fixers;
    // 2.x
    private boolean useRules;
    private boolean isDiffFormatUdiff;
    private String rules;
    // common
    private boolean useCustom;
    private String custom;
    private boolean isRunOnSave;
    private boolean isVerbose;
    private boolean isDiff;

    /**
     * Creates new form PhpCsFixerCustomizerPanel
     */
    public PhpCsFixerCustomizerPanel(Category catetgory, PhpModule phpModule) {
        catetgory.setOkButtonListener((ActionEvent e) -> {
            save();
        });
        this.catetgory = catetgory;
        this.phpModule = phpModule;
        initComponents();
        // load
        load();
        // set enabled components
        setEnabledProject(isProject());
    }

    private void save() {
        if (isGlobal()) {
            if (isGlobal != isGlobal()) {
                PhpCsFixerPreferences.setGlobal(phpModule, isGlobal());
                PhpCsFixerPreferences.setProject(phpModule, isProject());
            }
        } else if (isProject()) {
            if (isProject != isProject()) {
                PhpCsFixerPreferences.setGlobal(phpModule, isGlobal());
                PhpCsFixerPreferences.setProject(phpModule, isProject());
            }
            if (version != optionsPanel.getVersion()) {
                PhpCsFixerPreferences.setVersion(phpModule, optionsPanel.getVersion());
            }

            // 1.x
            if (useLevel != optionsPanel.useLevel()) {
                PhpCsFixerPreferences.setLevel(phpModule, optionsPanel.useLevel());
            }
            if (!level.equals(optionsPanel.getLevel())) {
                PhpCsFixerPreferences.setLevel(phpModule, optionsPanel.getLevel());
            }
            if (useConfig != optionsPanel.useConfig()) {
                PhpCsFixerPreferences.setConfig(phpModule, optionsPanel.useConfig());
            }
            if (!config.equals(optionsPanel.getConfig())) {
                PhpCsFixerPreferences.setConfig(phpModule, optionsPanel.getConfig());
            }
            if (useFixers != optionsPanel.useFixers()) {
                PhpCsFixerPreferences.setFixers(phpModule, optionsPanel.useFixers());
            }
            if (!fixers.equals(optionsPanel.getFixers())) {
                PhpCsFixerPreferences.setFixers(phpModule, optionsPanel.getFixers());
            }

            // 2.x
            if (useRules != optionsPanel.useRules()) {
                PhpCsFixerPreferences.setRules(phpModule, optionsPanel.useRules());
            }
            if (!rules.equals(optionsPanel.getRules())) {
                PhpCsFixerPreferences.setRules(phpModule, optionsPanel.getRules());
            }
            if (isDiffFormatUdiff != optionsPanel.isDiffFormatUdiff()) {
                PhpCsFixerPreferences.setDiffFormatUdiff(phpModule, optionsPanel.isDiffFormatUdiff());
            }

            // common
            if (useCustom != optionsPanel.useCustom()) {
                PhpCsFixerPreferences.setCustom(phpModule, optionsPanel.useCustom());
            }
            if (!custom.equals(optionsPanel.getCustom())) {
                PhpCsFixerPreferences.setCustom(phpModule, optionsPanel.getCustom());
            }
            if (isRunOnSave != optionsPanel.isRunOnSave()) {
                PhpCsFixerPreferences.setRunOnSave(phpModule, optionsPanel.isRunOnSave());
            }
            if (isVerbose != optionsPanel.isVerbose()) {
                PhpCsFixerPreferences.setVerbose(phpModule, optionsPanel.isVerbose());
            }
            if (isDiff != optionsPanel.isDiff()) {
                PhpCsFixerPreferences.setDiff(phpModule, optionsPanel.isDiff());
            }
        } else {
            // do nothing
        }
    }

    public boolean isGlobal() {
        return useGlobalRadioButton.isSelected();
    }

    public boolean isProject() {
        return useProjectRadioButton.isSelected();
    }

    private void load() {
        isGlobal = PhpCsFixerPreferences.useGlobal(phpModule);
        isProject = PhpCsFixerPreferences.useProject(phpModule);

        version = PhpCsFixerPreferences.getVersion(phpModule);
        useLevel = PhpCsFixerPreferences.useLevel(phpModule);
        level = PhpCsFixerPreferences.getLevel(phpModule);
        useConfig = PhpCsFixerPreferences.useConfig(phpModule);
        config = PhpCsFixerPreferences.getConfig(phpModule);
        useFixers = PhpCsFixerPreferences.useFixers(phpModule);
        fixers = PhpCsFixerPreferences.getFixers(phpModule);
        // 2.x
        useRules = PhpCsFixerPreferences.useRules(phpModule);
        rules = PhpCsFixerPreferences.getRules(phpModule);
        isDiffFormatUdiff = PhpCsFixerPreferences.isDiffFormatUdiff(phpModule);
        // common
        useCustom = PhpCsFixerPreferences.useCustom(phpModule);
        custom = PhpCsFixerPreferences.getCustom(phpModule);
        isRunOnSave = PhpCsFixerPreferences.isRunOnSave(phpModule);
        isVerbose = PhpCsFixerPreferences.isVerbose(phpModule);
        isDiff = PhpCsFixerPreferences.isDiff(phpModule);
        // global, project
        useGlobalRadioButton.setSelected(isGlobal);
        useProjectRadioButton.setSelected(isProject);

        // options
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
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup = new ButtonGroup();
        useGlobalRadioButton = new JRadioButton();
        useProjectRadioButton = new JRadioButton();
        optionsPanel = new PhpCsFixerOptionsPanel();

        buttonGroup.add(useGlobalRadioButton);
        useGlobalRadioButton.setSelected(true);
        Mnemonics.setLocalizedText(useGlobalRadioButton, NbBundle.getMessage(PhpCsFixerCustomizerPanel.class, "PhpCsFixerCustomizerPanel.useGlobalRadioButton.text")); // NOI18N
        useGlobalRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                useGlobalRadioButtonActionPerformed(evt);
            }
        });

        buttonGroup.add(useProjectRadioButton);
        Mnemonics.setLocalizedText(useProjectRadioButton, NbBundle.getMessage(PhpCsFixerCustomizerPanel.class, "PhpCsFixerCustomizerPanel.useProjectRadioButton.text")); // NOI18N
        useProjectRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                useProjectRadioButtonActionPerformed(evt);
            }
        });

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(useGlobalRadioButton)
                    .addComponent(useProjectRadioButton))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(optionsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(useGlobalRadioButton)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(useProjectRadioButton)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(optionsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void useGlobalRadioButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_useGlobalRadioButtonActionPerformed
        setEnabledProject(false);
    }//GEN-LAST:event_useGlobalRadioButtonActionPerformed

    private void useProjectRadioButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_useProjectRadioButtonActionPerformed
        setEnabledProject(true);
    }//GEN-LAST:event_useProjectRadioButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private ButtonGroup buttonGroup;
    private PhpCsFixerOptionsPanel optionsPanel;
    private JRadioButton useGlobalRadioButton;
    private JRadioButton useProjectRadioButton;
    // End of variables declaration//GEN-END:variables

    private void setEnabledProject(boolean enabled) {
        for (Component component : optionsPanel.getComponents()) {
            component.setEnabled(enabled);
        }
        JComboBox<String> configComboBox = optionsPanel.getConfigComboBox();
        configComboBox.setEditable(enabled);
    }
}
