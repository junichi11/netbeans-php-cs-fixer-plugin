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
package org.netbeans.modules.php.phpcsfixer.options;

import java.util.Arrays;
import java.util.List;
import javax.swing.JComboBox;

/**
 *
 * @author junichi11
 */
public class PhpCsFixerOptionsPanel extends javax.swing.JPanel {

    private static final long serialVersionUID = 388435168100294200L;
    private static final List<Integer> VERSIONS = Arrays.asList(1, 2);

    /**
     * Creates new form PhpCsFixerOptionsPanel
     */
    public PhpCsFixerOptionsPanel() {
        initComponents();
        init();
        // TODO see issue #2
        // hide run on save option
        runOnSaveCheckBox.setVisible(false);
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
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        levelCheckBox = new javax.swing.JCheckBox();
        levelComboBox = new javax.swing.JComboBox<>();
        configCheckBox = new javax.swing.JCheckBox();
        configComboBox = new javax.swing.JComboBox<>();
        fixersCheckBox = new javax.swing.JCheckBox();
        fixersTextField = new javax.swing.JTextField();
        customCheckBox = new javax.swing.JCheckBox();
        customTextField = new javax.swing.JTextField();
        runOnSaveCheckBox = new javax.swing.JCheckBox();
        dryRunLabel = new javax.swing.JLabel();
        dryRunSeparator = new javax.swing.JSeparator();
        verboseCheckBox = new javax.swing.JCheckBox();
        diffCheckBox = new javax.swing.JCheckBox();
        versionLabel = new javax.swing.JLabel();
        versionComboBox = new javax.swing.JComboBox<>();
        rulesCheckBox = new javax.swing.JCheckBox();
        rulesTextField = new javax.swing.JTextField();

        org.openide.awt.Mnemonics.setLocalizedText(levelCheckBox, org.openide.util.NbBundle.getMessage(PhpCsFixerOptionsPanel.class, "PhpCsFixerOptionsPanel.levelCheckBox.text")); // NOI18N

        levelComboBox.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] { "", "psr0", "psr1", "psr2", "symfony"}));

        org.openide.awt.Mnemonics.setLocalizedText(configCheckBox, org.openide.util.NbBundle.getMessage(PhpCsFixerOptionsPanel.class, "PhpCsFixerOptionsPanel.configCheckBox.text")); // NOI18N

        configComboBox.setEditable(true);
        configComboBox.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] { "", "default", "magento", "sf23"}));

        org.openide.awt.Mnemonics.setLocalizedText(fixersCheckBox, org.openide.util.NbBundle.getMessage(PhpCsFixerOptionsPanel.class, "PhpCsFixerOptionsPanel.fixersCheckBox.text")); // NOI18N

        fixersTextField.setText(org.openide.util.NbBundle.getMessage(PhpCsFixerOptionsPanel.class, "PhpCsFixerOptionsPanel.fixersTextField.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(customCheckBox, org.openide.util.NbBundle.getMessage(PhpCsFixerOptionsPanel.class, "PhpCsFixerOptionsPanel.customCheckBox.text")); // NOI18N

        customTextField.setText(org.openide.util.NbBundle.getMessage(PhpCsFixerOptionsPanel.class, "PhpCsFixerOptionsPanel.customTextField.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(runOnSaveCheckBox, org.openide.util.NbBundle.getMessage(PhpCsFixerOptionsPanel.class, "PhpCsFixerOptionsPanel.runOnSaveCheckBox.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(dryRunLabel, org.openide.util.NbBundle.getMessage(PhpCsFixerOptionsPanel.class, "PhpCsFixerOptionsPanel.dryRunLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(verboseCheckBox, org.openide.util.NbBundle.getMessage(PhpCsFixerOptionsPanel.class, "PhpCsFixerOptionsPanel.verboseCheckBox.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(diffCheckBox, org.openide.util.NbBundle.getMessage(PhpCsFixerOptionsPanel.class, "PhpCsFixerOptionsPanel.diffCheckBox.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(versionLabel, org.openide.util.NbBundle.getMessage(PhpCsFixerOptionsPanel.class, "PhpCsFixerOptionsPanel.versionLabel.text")); // NOI18N

        versionComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                versionComboBoxItemStateChanged(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(rulesCheckBox, org.openide.util.NbBundle.getMessage(PhpCsFixerOptionsPanel.class, "PhpCsFixerOptionsPanel.rulesCheckBox.text")); // NOI18N

        rulesTextField.setText(org.openide.util.NbBundle.getMessage(PhpCsFixerOptionsPanel.class, "PhpCsFixerOptionsPanel.rulesTextField.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(dryRunLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dryRunSeparator))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(runOnSaveCheckBox)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(verboseCheckBox)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(diffCheckBox)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(levelCheckBox)
                            .addComponent(configCheckBox)
                            .addComponent(fixersCheckBox)
                            .addComponent(customCheckBox)
                            .addComponent(versionLabel)
                            .addComponent(rulesCheckBox))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fixersTextField)
                            .addComponent(customTextField)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(levelComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(configComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(versionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 228, Short.MAX_VALUE))
                            .addComponent(rulesTextField))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(versionLabel)
                    .addComponent(versionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(levelComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(levelCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(configCheckBox)
                    .addComponent(configComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fixersCheckBox)
                    .addComponent(fixersTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rulesCheckBox)
                    .addComponent(rulesTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customCheckBox)
                    .addComponent(customTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(runOnSaveCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dryRunLabel)
                    .addComponent(dryRunSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(verboseCheckBox)
                    .addComponent(diffCheckBox))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void versionComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_versionComboBoxItemStateChanged
        Integer item = (Integer) evt.getItem();
        if (item == 2) {
            setVersion1ComponentsVisible(false);
            setVersion2ComponentsVisible(true);
        } else {
            setVersion1ComponentsVisible(true);
            setVersion2ComponentsVisible(false);
        }
    }//GEN-LAST:event_versionComboBoxItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox configCheckBox;
    private javax.swing.JComboBox<String> configComboBox;
    private javax.swing.JCheckBox customCheckBox;
    private javax.swing.JTextField customTextField;
    private javax.swing.JCheckBox diffCheckBox;
    private javax.swing.JLabel dryRunLabel;
    private javax.swing.JSeparator dryRunSeparator;
    private javax.swing.JCheckBox fixersCheckBox;
    private javax.swing.JTextField fixersTextField;
    private javax.swing.JCheckBox levelCheckBox;
    private javax.swing.JComboBox<String> levelComboBox;
    private javax.swing.JCheckBox rulesCheckBox;
    private javax.swing.JTextField rulesTextField;
    private javax.swing.JCheckBox runOnSaveCheckBox;
    private javax.swing.JCheckBox verboseCheckBox;
    private javax.swing.JComboBox<Integer> versionComboBox;
    private javax.swing.JLabel versionLabel;
    // End of variables declaration//GEN-END:variables
}
