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

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Arrays;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import org.openide.awt.Mnemonics;
import org.openide.util.NbBundle;

/**
 *
 * @author junichi11
 */
public class PhpCsFixerOptionsPanel extends javax.swing.JPanel {

    private static final long serialVersionUID = 388435168100294200L;
    private static final List<Integer> VERSIONS = Arrays.asList(1, 2, 3);

    /**
     * Creates new form PhpCsFixerOptionsPanel
     */
    public PhpCsFixerOptionsPanel() {
        initComponents();
        init();
    }

    private void init() {
        VERSIONS.forEach((version) -> {
            versionComboBox.addItem(version);
        });
    }

    public int getVersion() {
        return (Integer) versionComboBox.getSelectedItem();
    }

    public void setVersion(int version) {
        versionComboBox.setSelectedItem(version);
    }

    public boolean useConfig() {
        return configCheckBox.isSelected();
    }

    public void setConfig(boolean use) {
        configCheckBox.setSelected(use);
    }

    public String getConfig() {
        return (String) configComboBox.getSelectedItem();
    }

    public void setConfig(String config) {
        configComboBox.setSelectedItem(config);
    }

    public JComboBox<String> getConfigComboBox() {
        return configComboBox;
    }

    // 1.x
    public boolean useFixers() {
        return fixersCheckBox.isSelected();
    }

    public void setFixers(boolean use) {
        fixersCheckBox.setSelected(use);
    }

    public String getFixers() {
        return fixersTextField.getText();
    }

    public void setFixers(String fixersText) {
        fixersTextField.setText(fixersText);
    }

    public boolean useLevel() {
        return levelCheckBox.isSelected();
    }

    public void setLevel(boolean use) {
        levelCheckBox.setSelected(use);
    }

    public String getLevel() {
        return (String) levelComboBox.getSelectedItem();
    }

    public void setLevel(String levelItem) {
        levelComboBox.setSelectedItem(levelItem);
    }

    // 2.x
    public boolean useRules() {
        return rulesCheckBox.isSelected();
    }

    public void setRules(boolean use) {
        rulesCheckBox.setSelected(use);
    }

    public String getRules() {
        return rulesTextField.getText();
    }

    public void setRules(String rulesText) {
        rulesTextField.setText(rulesText);
    }

    // common
    public boolean useCustom() {
        return customCheckBox.isSelected();
    }

    public void setCustom(boolean use) {
        customCheckBox.setSelected(use);
    }

    public String getCustom() {
        return customTextField.getText();
    }

    public void setCustom(String options) {
        customTextField.setText(options);
    }

    public boolean isRunOnSave() {
        return runOnSaveCheckBox.isSelected();
    }

    public void setRunOnSave(boolean use) {
        runOnSaveCheckBox.setSelected(use);
    }

    public boolean isVerbose() {
        return verboseCheckBox.isSelected();
    }

    public void setVerbose(boolean isVerbose) {
        verboseCheckBox.setSelected(isVerbose);
    }

    public boolean isDiff() {
        return diffCheckBox.isSelected();
    }

    public void setDiff(boolean isDiff) {
        diffCheckBox.setSelected(isDiff);
    }

    public boolean isDiffFormatUdiff() {
        return diffFormatUdiffCheckBox.isSelected();
    }

    public void setDiffFormatUdiff(boolean isUdiff) {
        diffFormatUdiffCheckBox.setSelected(isUdiff);
    }

    private void setVersion1ComponentsVisible(boolean isVisible) {
        levelCheckBox.setVisible(isVisible);
        levelComboBox.setVisible(isVisible);
        configCheckBox.setVisible(isVisible);
        configComboBox.setVisible(isVisible);
        fixersCheckBox.setVisible(isVisible);
        fixersTextField.setVisible(isVisible);
    }

    private void setVersion2ComponentsVisible(boolean isVisible) {
        rulesCheckBox.setVisible(isVisible);
        rulesTextField.setVisible(isVisible);
        diffFormatUdiffCheckBox.setVisible(isVisible);
    }

    private void setVersion3ComponentsVisible(boolean isVisible) {
        rulesCheckBox.setVisible(isVisible);
        rulesTextField.setVisible(isVisible);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        levelCheckBox = new JCheckBox();
        levelComboBox = new JComboBox<>();
        configCheckBox = new JCheckBox();
        configComboBox = new JComboBox<>();
        fixersCheckBox = new JCheckBox();
        fixersTextField = new JTextField();
        customCheckBox = new JCheckBox();
        customTextField = new JTextField();
        runOnSaveCheckBox = new JCheckBox();
        dryRunLabel = new JLabel();
        dryRunSeparator = new JSeparator();
        verboseCheckBox = new JCheckBox();
        diffCheckBox = new JCheckBox();
        versionLabel = new JLabel();
        versionComboBox = new JComboBox<>();
        rulesCheckBox = new JCheckBox();
        rulesTextField = new JTextField();
        diffFormatUdiffCheckBox = new JCheckBox();

        Mnemonics.setLocalizedText(levelCheckBox, NbBundle.getMessage(PhpCsFixerOptionsPanel.class, "PhpCsFixerOptionsPanel.levelCheckBox.text")); // NOI18N

        levelComboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "", "psr0", "psr1", "psr2", "symfony"}));

        Mnemonics.setLocalizedText(configCheckBox, NbBundle.getMessage(PhpCsFixerOptionsPanel.class, "PhpCsFixerOptionsPanel.configCheckBox.text")); // NOI18N

        configComboBox.setEditable(true);
        configComboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "", "default", "magento", "sf23"}));

        Mnemonics.setLocalizedText(fixersCheckBox, NbBundle.getMessage(PhpCsFixerOptionsPanel.class, "PhpCsFixerOptionsPanel.fixersCheckBox.text")); // NOI18N

        fixersTextField.setText(NbBundle.getMessage(PhpCsFixerOptionsPanel.class, "PhpCsFixerOptionsPanel.fixersTextField.text")); // NOI18N

        Mnemonics.setLocalizedText(customCheckBox, NbBundle.getMessage(PhpCsFixerOptionsPanel.class, "PhpCsFixerOptionsPanel.customCheckBox.text")); // NOI18N

        customTextField.setText(NbBundle.getMessage(PhpCsFixerOptionsPanel.class, "PhpCsFixerOptionsPanel.customTextField.text")); // NOI18N

        Mnemonics.setLocalizedText(runOnSaveCheckBox, NbBundle.getMessage(PhpCsFixerOptionsPanel.class, "PhpCsFixerOptionsPanel.runOnSaveCheckBox.text")); // NOI18N

        Mnemonics.setLocalizedText(dryRunLabel, NbBundle.getMessage(PhpCsFixerOptionsPanel.class, "PhpCsFixerOptionsPanel.dryRunLabel.text")); // NOI18N

        Mnemonics.setLocalizedText(verboseCheckBox, NbBundle.getMessage(PhpCsFixerOptionsPanel.class, "PhpCsFixerOptionsPanel.verboseCheckBox.text")); // NOI18N

        Mnemonics.setLocalizedText(diffCheckBox, NbBundle.getMessage(PhpCsFixerOptionsPanel.class, "PhpCsFixerOptionsPanel.diffCheckBox.text")); // NOI18N

        Mnemonics.setLocalizedText(versionLabel, NbBundle.getMessage(PhpCsFixerOptionsPanel.class, "PhpCsFixerOptionsPanel.versionLabel.text")); // NOI18N

        versionComboBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent evt) {
                versionComboBoxItemStateChanged(evt);
            }
        });

        Mnemonics.setLocalizedText(rulesCheckBox, NbBundle.getMessage(PhpCsFixerOptionsPanel.class, "PhpCsFixerOptionsPanel.rulesCheckBox.text")); // NOI18N

        rulesTextField.setText(NbBundle.getMessage(PhpCsFixerOptionsPanel.class, "PhpCsFixerOptionsPanel.rulesTextField.text")); // NOI18N

        Mnemonics.setLocalizedText(diffFormatUdiffCheckBox, NbBundle.getMessage(PhpCsFixerOptionsPanel.class, "PhpCsFixerOptionsPanel.diffFormatUdiffCheckBox.text")); // NOI18N

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(dryRunLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dryRunSeparator))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(levelCheckBox)
                            .addComponent(configCheckBox)
                            .addComponent(fixersCheckBox)
                            .addComponent(customCheckBox)
                            .addComponent(versionLabel)
                            .addComponent(rulesCheckBox))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(fixersTextField)
                            .addComponent(customTextField)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(levelComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(configComboBox, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(versionComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 228, Short.MAX_VALUE))
                            .addComponent(rulesTextField)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(runOnSaveCheckBox)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(verboseCheckBox)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(diffCheckBox)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(diffFormatUdiffCheckBox)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(versionLabel)
                    .addComponent(versionComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(levelComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(levelCheckBox))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(configCheckBox)
                    .addComponent(configComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(fixersCheckBox)
                    .addComponent(fixersTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(rulesCheckBox)
                    .addComponent(rulesTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(customCheckBox)
                    .addComponent(customTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(runOnSaveCheckBox)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(dryRunLabel)
                    .addComponent(dryRunSeparator, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(verboseCheckBox)
                    .addComponent(diffCheckBox)
                    .addComponent(diffFormatUdiffCheckBox))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void versionComboBoxItemStateChanged(ItemEvent evt) {//GEN-FIRST:event_versionComboBoxItemStateChanged
        Integer item = (Integer) evt.getItem();
        if (item == 2) {
            setVersion1ComponentsVisible(false);
            setVersion3ComponentsVisible(false);
            setVersion2ComponentsVisible(true);
        } else if (item == 3) {
            setVersion1ComponentsVisible(false);
            setVersion2ComponentsVisible(false);
            setVersion3ComponentsVisible(true);
        } else {
            setVersion1ComponentsVisible(true);
            setVersion2ComponentsVisible(false);
            setVersion3ComponentsVisible(false);
        }
    }//GEN-LAST:event_versionComboBoxItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JCheckBox configCheckBox;
    private JComboBox<String> configComboBox;
    private JCheckBox customCheckBox;
    private JTextField customTextField;
    private JCheckBox diffCheckBox;
    private JCheckBox diffFormatUdiffCheckBox;
    private JLabel dryRunLabel;
    private JSeparator dryRunSeparator;
    private JCheckBox fixersCheckBox;
    private JTextField fixersTextField;
    private JCheckBox levelCheckBox;
    private JComboBox<String> levelComboBox;
    private JCheckBox rulesCheckBox;
    private JTextField rulesTextField;
    private JCheckBox runOnSaveCheckBox;
    private JCheckBox verboseCheckBox;
    private JComboBox<Integer> versionComboBox;
    private JLabel versionLabel;
    // End of variables declaration//GEN-END:variables
}
