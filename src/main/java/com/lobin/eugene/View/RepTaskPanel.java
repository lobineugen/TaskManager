package com.lobin.eugene.View;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

public class RepTaskPanel {
    private JPanel panel = new JPanel();
    private GroupLayout groupLayout = new GroupLayout(panel);
    private JTextField title = new JTextField(30);
    private SpinnerDateModel modelStart = new SpinnerDateModel();
    private SpinnerDateModel modelEnd = new SpinnerDateModel();
    private JSpinner startTime = new JSpinner(modelStart);
    private JSpinner endTime = new JSpinner(modelEnd);
    private JFormattedTextField interval;
    private JRadioButton active = new JRadioButton();

    RepTaskPanel() {
        // маска для ввода Интеравала only int
        NumberFormat format = NumberFormat.getIntegerInstance();
        format.setGroupingUsed(false);
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(1);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        interval = new JFormattedTextField(formatter);
        interval.setText("1");

        // дата формат для спиннеров
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        startTime.setEditor(new JSpinner.DateEditor(startTime, dateFormat.toPattern()));
        endTime.setEditor(new JSpinner.DateEditor(endTime, dateFormat.toPattern()));

        JLabel title = new JLabel("Title");
        title.setPreferredSize(new Dimension(70,20));
        JLabel startTime = new JLabel("Start date");
        JLabel endTime = new JLabel("End date");
        JLabel interval = new JLabel("Interval");
        JLabel active = new JLabel("Active");

        panel.setLayout(groupLayout);
        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);
        groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
                .addGroup(groupLayout.createParallelGroup()
                        .addComponent(title)
                        .addComponent(startTime)
                        .addComponent(endTime)
                        .addComponent(interval)
                        .addComponent(active))
                .addGroup(groupLayout.createParallelGroup()
                        .addComponent(this.title)
                        .addComponent(this.startTime)
                        .addComponent(this.endTime)
                        .addComponent(this.interval)
                        .addComponent(this.active)));
        groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(title)
                        .addComponent(this.title))
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(startTime)
                        .addComponent(this.startTime))
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(endTime)
                        .addComponent(this.endTime))
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(interval)
                        .addComponent(this.interval))
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(active)
                        .addComponent(this.active)));

        groupLayout.linkSize(SwingConstants.HORIZONTAL, title, startTime, endTime, interval, active);
    }

    public JPanel getPanel() {
        return panel;
    }

    public JTextField getTitle() {
        return title;
    }

    public JSpinner getStartTime() {
        return startTime;
    }

    public JSpinner getEndTime() {
        return endTime;
    }

    public JFormattedTextField getInterval() {
        return interval;
    }

    public JRadioButton getActive() {
        return active;
    }
}