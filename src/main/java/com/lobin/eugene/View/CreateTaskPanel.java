package com.lobin.eugene.View;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

public class CreateTaskPanel {
    private JPanel createTaskPanel = new JPanel();
    private GroupLayout glCreateTask = new GroupLayout(createTaskPanel);
    private JButton bCreateTask = new JButton("Create task");
    private JButton bBack = new JButton("Back");
    private JRadioButton rbCreateNonReTask = new JRadioButton("Create a non repeatable task", true);
    private JRadioButton rbCreateReTask = new JRadioButton("Create a repeat task ");
    private ButtonGroup bgSelectTaskType = new ButtonGroup();
    private JPanel pRepTask = new JPanel();
    private JPanel pNonRepTask = new JPanel();

    private JTextField tfTaskTitleOne = new JTextField(30);
    private JTextField tfTaskTitleTwo = new JTextField(30);
    private SpinnerDateModel model = new SpinnerDateModel();
    private JSpinner tfDateTime = new JSpinner(model);
    private JSpinner tfStartTime = new JSpinner(model);
    private JSpinner tfEndTIme = new JSpinner(model);
    private JFormattedTextField  tfInterval;
    private JRadioButton rbActiveOne = new JRadioButton();
    private JRadioButton rbActiveTwo = new JRadioButton();

    CreateTaskPanel() {
        // маска для ввода Интеравала only int
        NumberFormat format = NumberFormat.getIntegerInstance();
        format.setGroupingUsed(false);
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(1);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        tfInterval = new JFormattedTextField(formatter);
        tfInterval.setText("1");

        //  значение для радио баттонов
        rbCreateReTask.setActionCommand("Repeatable");
        rbCreateNonReTask.setActionCommand("NonRepeatable");

        // дата формат для спиннеров
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        tfDateTime.setEditor(new JSpinner.DateEditor(tfDateTime, dateFormat.toPattern()));
        tfStartTime.setEditor(new JSpinner.DateEditor(tfDateTime, dateFormat.toPattern()));
        tfEndTIme.setEditor(new JSpinner.DateEditor(tfDateTime, dateFormat.toPattern()));

        JLabel lTitleOne = new JLabel("Title");
        JLabel lTitleTwo = new JLabel("Title");
        JLabel lDateTime = new JLabel("Date");
        JLabel lStartTime = new JLabel("Start date");
        JLabel lEndTime = new JLabel("End date");
        JLabel lInterval = new JLabel("Interval");
        JLabel lActiveOne = new JLabel("Active");
        JLabel lActiveTwo = new JLabel("Active");



        GroupLayout glNonRepTask = new GroupLayout(pNonRepTask);
        pNonRepTask.setLayout(glNonRepTask);

        glNonRepTask.setAutoCreateGaps(true);
        glNonRepTask.setAutoCreateContainerGaps(true);

        glNonRepTask.setHorizontalGroup(glNonRepTask.createSequentialGroup()
                .addGroup(glNonRepTask.createParallelGroup()
                        .addComponent(lTitleOne)
                        .addComponent(lDateTime)
                        .addComponent(lActiveOne))
                .addGroup(glNonRepTask.createParallelGroup()
                        .addComponent(tfTaskTitleOne)
                        .addComponent(tfDateTime)
                        .addComponent(rbActiveOne)));
        glNonRepTask.setVerticalGroup(glNonRepTask.createSequentialGroup()
                .addGroup(glNonRepTask.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lTitleOne)
                        .addComponent(tfTaskTitleOne))
                .addGroup(glNonRepTask.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lDateTime)
                        .addComponent(tfDateTime))
                .addGroup(glNonRepTask.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lActiveOne)
                        .addComponent(rbActiveOne)));

        GroupLayout glRepTask = new GroupLayout(pRepTask);
        pRepTask.setLayout(glRepTask);

        glRepTask.setAutoCreateGaps(true);
        glRepTask.setAutoCreateContainerGaps(true);

        glRepTask.setHorizontalGroup(glRepTask.createSequentialGroup()
                .addGroup(glRepTask.createParallelGroup()
                        .addComponent(lTitleTwo)
                        .addComponent(lStartTime)
                        .addComponent(lEndTime)
                        .addComponent(lInterval)
                        .addComponent(lActiveTwo))
                .addGroup(glRepTask.createParallelGroup()
                        .addComponent(tfTaskTitleTwo)
                        .addComponent(tfStartTime)
                        .addComponent(tfEndTIme)
                        .addComponent(tfInterval)
                        .addComponent(rbActiveTwo)));
        glRepTask.setVerticalGroup(glRepTask.createSequentialGroup()
                .addGroup(glRepTask.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lTitleTwo)
                        .addComponent(tfTaskTitleTwo))
                .addGroup(glRepTask.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lStartTime)
                        .addComponent(tfStartTime))
                .addGroup(glRepTask.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lEndTime)
                        .addComponent(tfEndTIme))
                .addGroup(glRepTask.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lInterval)
                        .addComponent(tfInterval))
                .addGroup(glRepTask.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lActiveTwo)
                        .addComponent(rbActiveTwo)));

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

    public GroupLayout getGlCreateTask() {
        return glCreateTask;
    }

    public JTextField getTaskTitleOne() {
        return tfTaskTitleOne;
    }

    public JTextField getTaskTitleTwo() {
        return tfTaskTitleTwo;
    }

    public Boolean getTaskType() {
        return bgSelectTaskType.getSelection().getActionCommand().equals("Repeatable");
    }

    public JSpinner getDateTime() {
        return tfDateTime;
    }

    public JSpinner getStartTime() {
        return tfStartTime;
    }

    public JSpinner getEndTIme() {
        return tfEndTIme;
    }

    public JTextField getInterval() {
        return tfInterval;
    }

    public JRadioButton getActiveOne() {
        return rbActiveOne;
    }

    public JRadioButton getActiveTwo() {
        return rbActiveTwo;
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
