package com.lobin.eugene.View;

import com.lobin.eugene.Controller.TaskConstant;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.CardLayout;
import java.awt.Container;

public class View extends JFrame
        implements TaskConstant{
    private Container container = this.getContentPane();
    private CardLayout cardLayout = new CardLayout();
    private StartPanel startPanel = new StartPanel();
    private CreateTaskPanel createTaskPanel = new CreateTaskPanel();
    private EditPanel editPanel = new EditPanel();
    private CalendarPanel calendarPanel = new CalendarPanel();


    public View() {
        super("Task Manager");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(FRAMESIZE, FRAMESIZE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        container.setLayout(cardLayout);
        container.add("Start form", startPanel.getStartPanel());
        container.add("Create task", createTaskPanel.getCreateTaskPanel());
        container.add("Edit task", editPanel.getEditPanel());
        container.add("Calendar", calendarPanel.getCalendarPanel());
    }
    /**
     * @return card layout object
     */
    public CardLayout getCardLayout() {
        return cardLayout;
    }
    /**
     * @return container object
     */
    public Container getContainer() {
        return container;
    }
    /**
     * @return create task panel
     */
    public CreateTaskPanel getCreateTaskPanel() {
        return createTaskPanel;
    }
    /**
     * @return start panel
     */
    public StartPanel getStartPanel() {
        return startPanel;
    }
    /**
     * @return edit panel
     */
    public EditPanel getEditPanel() {
        return editPanel;
    }
    /**
     * @return calendar panel
     */
    public CalendarPanel getCalendarPanel() {
        return calendarPanel;
    }
}
