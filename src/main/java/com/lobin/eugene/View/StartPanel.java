package com.lobin.eugene.View;

import javax.swing.*;

public class StartPanel {
    private JPanel startPanel = new JPanel();
    private GroupLayout glStart = new GroupLayout(startPanel);
    private JTextArea textAreaTasks = new JTextArea(20, 35);
    private JButton bCreateNewTask = new JButton("Create new task");

    StartPanel() {
        startPanel.setLayout(glStart);
        glStart.setAutoCreateGaps(true);
        glStart.setAutoCreateContainerGaps(true);
        glStart.setHorizontalGroup(glStart.createSequentialGroup()
                .addComponent(textAreaTasks)
                .addComponent(bCreateNewTask)
        );
        glStart.setVerticalGroup(glStart.createSequentialGroup()
                .addGroup(glStart.createParallelGroup()
                        .addComponent(textAreaTasks)
                        .addComponent(bCreateNewTask))
        );

    }

    JPanel getStartPanel() {
        return startPanel;
    }

    JButton getCreateNewTask() {
        return bCreateNewTask;
    }
}
