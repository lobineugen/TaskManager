package com.lobin.eugene.View;

import javax.swing.*;
import java.awt.event.ActionListener;

public class EditPanel {
    private JPanel editPanel = new JPanel();
    private JPanel pEditRepTask;
    private JPanel pEditNonRepTask;
    private RepTaskPanel repTaskPanel = new RepTaskPanel();
    private NonRepTaskPanel nonRepTaskPanel = new NonRepTaskPanel();
    private JButton bSave = new JButton("Save");
    private JButton bCancel = new JButton("Cancel");

    EditPanel() {
        GroupLayout glEdit = new GroupLayout(editPanel);
        pEditRepTask = repTaskPanel.getPanel();
        pEditNonRepTask = nonRepTaskPanel.getPanel();
        editPanel.setLayout(glEdit);
        glEdit.setAutoCreateGaps(true);
        glEdit.setAutoCreateContainerGaps(true);
        glEdit.setVerticalGroup(glEdit.createSequentialGroup()
                .addGroup(glEdit.createParallelGroup()
                        .addGroup(glEdit.createSequentialGroup()
                                .addComponent(pEditNonRepTask)
                                .addComponent(pEditRepTask))
                        .addGroup(glEdit.createSequentialGroup()
                                .addComponent(bSave)
                                .addComponent(bCancel))));

        glEdit.setHorizontalGroup(glEdit.createSequentialGroup()
                .addGroup(glEdit.createParallelGroup()
                        .addComponent(pEditNonRepTask)
                        .addComponent(pEditRepTask))
                .addGroup(glEdit.createParallelGroup()
                        .addComponent(bSave)
                        .addComponent(bCancel)));
        glEdit.linkSize(SwingConstants.HORIZONTAL, bSave, bCancel);

    }

    public JPanel getEditPanel() {
        return editPanel;
    }

    public JPanel getEditNonRepTask() {
        return pEditNonRepTask;
    }

    public JPanel getEditRepTask() {
        return pEditRepTask;
    }

    public void addSaveButtonListener(ActionListener listener) {
        bSave.addActionListener(listener);
    }

    public void addCancelButtonListener(ActionListener listener) {
        bCancel.addActionListener(listener);
    }

    public NonRepTaskPanel getNonRepTaskPanel() {
        return nonRepTaskPanel;
    }

    public RepTaskPanel getRepTaskPanel() {
        return repTaskPanel;
    }
}
