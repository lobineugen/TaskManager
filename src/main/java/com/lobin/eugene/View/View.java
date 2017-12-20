package com.lobin.eugene.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class View extends JFrame {
    private JTextArea textAreaTasks = new JTextArea(20, 35);
    private JButton bCreateNewTask = new JButton("Create new task");
    private JButton bCreateTask = new JButton("Create task");
    private JButton bBack = new JButton("Back");
    private Container container = this.getContentPane();
    private CardLayout cardLayout = new CardLayout();
    private JRadioButton rbCreateNonReTask = new JRadioButton("Create a non repeatable task", true);
    private JRadioButton rbCreateReTask = new JRadioButton("Create a repeat task ");
    private JPanel pRepTask = new JPanel();
    private JPanel pNonRepTask = new JPanel();

    public View() {
        super("Task Manager");
        JPanel startPanel = new JPanel();
        JPanel createTaskPanel = new JPanel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);

        GroupLayout glStart = new GroupLayout(startPanel);
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

        pNonRepTask.setLayout(new GridLayout(3,2));
        pNonRepTask.add(new JLabel("Task name"));
        pNonRepTask.add(new JTextField());
        pNonRepTask.add(new JLabel("Task start time"));
        pNonRepTask.add(new JTextField());
        pNonRepTask.add(new JLabel("Task end time"));
        pNonRepTask.add(new JTextField());
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        JFormattedTextField dateTextField = new JFormattedTextField(format);
        SpinnerDateModel model = new SpinnerDateModel();
        JSpinner spinner = new JSpinner(model);

        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "dd.MM.yyyy");
        pRepTask.setVisible(false);
        pRepTask.setLayout(new GridLayout(3,2));
        pRepTask.add(new JLabel("Task name"));
        pRepTask.add(new JTextField());
        pRepTask.add(new JLabel("Task time"));
        pRepTask.add(spinner);
        pRepTask.add(new JLabel("Task repeat"));
        pRepTask.add(new JTextField());
        rbCreateNonReTask.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                pNonRepTask.setVisible(true);
                pRepTask.setVisible(false);
            }
        });
        rbCreateReTask.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                pNonRepTask.setVisible(false);
                pRepTask.setVisible(true);
            }
        });


        ButtonGroup bgSelectTaskType = new ButtonGroup();
        bgSelectTaskType.add(rbCreateNonReTask);
        bgSelectTaskType.add(rbCreateReTask);

        GroupLayout glCreateTask = new GroupLayout(createTaskPanel);
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
//        glCreateTask.linkSize(SwingConstants.VERTICAL);
//        glCreateTask.linkSize(SwingConstants.HORIZONTAL);
        glCreateTask.linkSize(SwingConstants.HORIZONTAL, bCreateTask, bBack);

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


        container.setLayout(cardLayout);
        container.add("Start form", startPanel);
        container.add("Create task", createTaskPanel);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    public void addCreateTaskListener(ActionListener listener) {
        bCreateNewTask.addActionListener(listener);
    }

    public void addButtonBackListener(ActionListener listener) {
        bBack.addActionListener(listener);
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public Container getContainer() {
        return container;
    }
}
