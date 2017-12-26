package com.lobin.eugene.View;

import org.omg.CORBA.PUBLIC_MEMBER;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
    private Container container = this.getContentPane();
    private CardLayout cardLayout = new CardLayout();

    private StartPanel startPanel = new StartPanel();
    private CreateTaskPanel createTaskPanel = new CreateTaskPanel();
    private EditPanel editPanel = new EditPanel();
    private CalendarPanel calendarPanel = new CalendarPanel();

    public View() {
        super("Task Manager");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        container.setLayout(cardLayout);
        container.add("Start form", startPanel.getStartPanel());
        container.add("Create task", createTaskPanel.getCreateTaskPanel());
        container.add("Edit task", editPanel.getEditPanel());
        container.add("Calendar", calendarPanel.getCalendarPanel());
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

    public StartPanel getStartPanel() {
        return startPanel;
    }

    public EditPanel getEditPanel() {
        return editPanel;
    }

    public CalendarPanel getCalendarPanel() {
        return calendarPanel;
    }
}
