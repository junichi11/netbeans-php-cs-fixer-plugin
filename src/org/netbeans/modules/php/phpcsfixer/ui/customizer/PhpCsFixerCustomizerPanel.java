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
package org.netbeans.modules.php.phpcsfixer.ui.customizer;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.netbeans.modules.php.api.phpmodule.PhpModule;
import org.netbeans.modules.php.phpcsfixer.preferences.PhpCsFixerPreferences;
import org.netbeans.spi.project.ui.support.ProjectCustomizer.Category;

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
    private boolean useLevel;
    private String level;
    private boolean useConfig;
    private String config;
    private boolean useFixers;
    private String fixers;
    private boolean useCustom;
    private String custom;
    private boolean isRunOnSave;

    /**
     * Creates new form PhpCsFixerCustomizerPanel
     */
    public PhpCsFixerCustomizerPanel(Category catetgory, PhpModule phpModule) {
        catetgory.setOkButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }
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
            if (useCustom != optionsPanel.useCustom()) {
                PhpCsFixerPreferences.setCustom(phpModule, optionsPanel.useCustom());
            }
            if (!custom.equals(optionsPanel.getCustom())) {
                PhpCsFixerPreferences.setCustom(phpModule, optionsPanel.getCustom());
            }
            if (isRunOnSave != optionsPanel.isRunOnSave()) {
                PhpCsFixerPreferences.setRunOnSave(phpModule, optionsPanel.isRunOnSave());
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
        useLevel = PhpCsFixerPreferences.useLevel(phpModule);
        level = PhpCsFixerPreferences.getLevel(phpModule);
        useConfig = PhpCsFixerPreferences.useConfig(phpModule);
        config = PhpCsFixerPreferences.getConfig(phpModule);
        useFixers = PhpCsFixerPreferences.useFixers(phpModule);
        fixers = PhpCsFixerPreferences.getFixers(phpModule);
        useCustom = PhpCsFixerPreferences.useCustom(phpModule);
        custom = PhpCsFixerPreferences.getCustom(phpModule);
        isRunOnSave = PhpCsFixerPreferences.isRunOnSave(phpModule);
        // global, project
        useGlobalRadioButton.setSelected(isGlobal);
        useProjectRadioButton.setSelected(isProject);
        // options
        optionsPanel.setLevel(useLevel);
        optionsPanel.setLevel(level);
        optionsPanel.setConfig(useConfig);
        optionsPanel.setConfig(config);
        optionsPanel.setFixers(useFixers);
        optionsPanel.setFixers(fixers);
        optionsPanel.setCustom(useCustom);
        optionsPanel.setCustom(custom);
        optionsPanel.setRunOnSave(isRunOnSave);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup = new javax.swing.ButtonGroup();
        useGlobalRadioButton = new javax.swing.JRadioButton();
        useProjectRadioButton = new javax.swing.JRadioButton();
        optionsPanel = new org.netbeans.modules.php.phpcsfixer.options.PhpCsFixerOptionsPanel();

        buttonGroup.add(useGlobalRadioButton);
        useGlobalRadioButton.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(useGlobalRadioButton, org.openide.util.NbBundle.getMessage(PhpCsFixerCustomizerPanel.class, "PhpCsFixerCustomizerPanel.useGlobalRadioButton.text")); // NOI18N
        useGlobalRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                useGlobalRadioButtonActionPerformed(evt);
            }
        });

        buttonGroup.add(useProjectRadioButton);
        org.openide.awt.Mnemonics.setLocalizedText(useProjectRadioButton, org.openide.util.NbBundle.getMessage(PhpCsFixerCustomizerPanel.class, "PhpCsFixerCustomizerPanel.useProjectRadioButton.text")); // NOI18N
        useProjectRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                useProjectRadioButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(useGlobalRadioButton)
                    .addComponent(useProjectRadioButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(optionsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(useGlobalRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(useProjectRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(optionsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void useGlobalRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_useGlobalRadioButtonActionPerformed
        setEnabledProject(false);
    }//GEN-LAST:event_useGlobalRadioButtonActionPerformed

    private void useProjectRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_useProjectRadioButtonActionPerformed
        setEnabledProject(true);
    }//GEN-LAST:event_useProjectRadioButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup;
    private org.netbeans.modules.php.phpcsfixer.options.PhpCsFixerOptionsPanel optionsPanel;
    private javax.swing.JRadioButton useGlobalRadioButton;
    private javax.swing.JRadioButton useProjectRadioButton;
    // End of variables declaration//GEN-END:variables

    private void setEnabledProject(boolean enabled) {
        for (Component component : optionsPanel.getComponents()) {
            component.setEnabled(enabled);
        }
    }
}
