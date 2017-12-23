package com.lobin.eugene.View;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

public class CreateTaskPanel {
    private JPanel createTaskPanel = new JPanel();
    private JButton bCreateTask = new JButton("Create task");
    private JButton bBack = new JButton("Back");
    private JRadioButton rbCreateNonReTask = new JRadioButton("Create a non repeatable task", true);
    private JRadioButton rbCreateReTask = new JRadioButton("Create a repeat task ");
    private ButtonGroup bgSelectTaskType = new ButtonGroup();
    private JPanel pRepTask;
    private JPanel pNonRepTask;
    private RepTaskPanel repTaskPanel = new RepTaskPanel();
    private NonRepTaskPanel nonRepTaskPanel = new NonRepTaskPanel();


    CreateTaskPanel() {
        GroupLayout glCreateTask = new GroupLayout(createTaskPanel);
        pRepTask = repTaskPanel.getPanel();
        pNonRepTask = nonRepTaskPanel.getPanel();
        rbCreateReTask.setActionCommand("Repeatable");
        rbCreateNonReTask.setActionCommand("NonRepeatable");

        pRepTask.setVisible(false);
        bgSelectTaskType.add(rbCreateNonReTask);
        bgSelectTaskType.add(rbCreateReTask);

        createTaskPanel.setLayout(glCreateTask);
        glCreateTask.setAutoCreateGaps(true);
        glCreateTask.setAutoCreateContainerGaps(true);

        glCreateTask.setHorizontalGroup(glCreateTask.createSequentialGroup()
                .addGroup(glCreateTask.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(rbCreateNonReTask)
                        .addComponent(rbCreateReTask)
                        .addComponent(pNonRepTask)
                        .addComponent(pRepTask))
                .addGroup(glCreateTask.createParallelGroup()
                        .addComponent(bCreateTask)
                        .addComponent(bBack))

        );
        glCreateTask.setVerticalGroup(glCreateTask.createSequentialGroup()
                .addGroup(glCreateTask.createParallelGroup()
                        .addGroup(glCreateTask.createSequentialGroup()
                                .addComponent(rbCreateNonReTask)
                                .addComponent(rbCreateReTask)
                                .addComponent(pNonRepTask)
                                .addComponent(pRepTask))
                        .addGroup(glCreateTask.createSequentialGroup()
                                .addComponent(bCreateTask)
                                .addComponent(bBack)))
        );

        glCreateTask.linkSize(SwingConstants.HORIZONTAL, bCreateTask, bBack);
    }

    public JPanel getCreateTaskPanel() {
        return createTaskPanel;
    }

    public JPanel getRepTask() {
        return pRepTask;
    }

    public JPanel getNonRepTask() {
        return pNonRepTask;
    }

    public JTextField getTaskTitleOne() {
        return nonRepTaskPanel.getTitle();
    }

    public JTextField getTaskTitleTwo() {
        return repTaskPanel.getTitle();
    }

    public Boolean getTaskType() {
        return bgSelectTaskType.getSelection().getActionCommand().equals("Repeatable");
    }

    public JSpinner getDateTime() {
        return nonRepTaskPanel.getDate();
    }

    public JSpinner getStartTime() {
        return repTaskPanel.getStartTime();
    }

    public JSpinner getEndTIme() {
        return repTaskPanel.getEndTime();
    }

    public JTextField getInterval() {
        return repTaskPanel.getInterval();
    }

    public JRadioButton getActiveOne() {
        return nonRepTaskPanel.getActive();
    }

    public JRadioButton getActiveTwo() {
        return repTaskPanel.getActive();
    }

    public void addCreateReTaskListener(ItemListener listener) {
        rbCreateReTask.addItemListener(listener);
    }

    public void addCreateNonReTaskListener(ItemListener listener) {
        rbCreateNonReTask.addItemListener(listener);
    }

    public void addButtonBackListener(ActionListener listener) {
        bBack.addActionListener(listener);
    }

    public void addCreateTaskListener(ActionListener listener) {
        bCreateTask.addActionListener(listener);
    }
}
