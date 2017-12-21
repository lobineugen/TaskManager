package com.lobin.eugene.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View extends JFrame {
    private Container container = this.getContentPane();
    private CardLayout cardLayout = new CardLayout();

    private StartPanel startPanel = new StartPanel();
    private CreateTaskPanel createTaskPanel = new CreateTaskPanel();

    public View() {
        super("Task Manager");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        container.setLayout(cardLayout);
        container.add("Start form", startPanel.getStartPanel());
        container.add("Create task", createTaskPanel.getCreateTaskPanel());
    }

    public void addCreateTaskListener(ActionListener listener) {
        startPanel.getCreateNewTask().addActionListener(listener);
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public Container getContainer() {
        return container;
    }

    public CreateTaskPanel getCreateTaskPanel() {
        return createTaskPanel;
    }
}
