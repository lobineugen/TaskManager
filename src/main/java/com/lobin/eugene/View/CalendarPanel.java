package com.lobin.eugene.View;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CalendarPanel {
    private JPanel calendarPanel = new JPanel();
    private JButton bBack = new JButton("Back");
    private JTextArea jTextArea = new JTextArea(5, 5);
    private SpinnerDateModel modelStart = new SpinnerDateModel();
    private SpinnerDateModel modelEnd = new SpinnerDateModel();
    private JSpinner startTime = new JSpinner(modelStart);
    private JSpinner endTime = new JSpinner(modelEnd);
    private JButton bShow = new JButton("Show");
    private JLabel lStart = new JLabel("Start date");
    private JLabel lEnd = new JLabel("End date");

    CalendarPanel() {
        GroupLayout glCalendar = new GroupLayout(calendarPanel);
        JScrollPane textArea = new JScrollPane(jTextArea);
        lStart.setSize(10,20);
        glCalendar.setAutoCreateGaps(true);
        glCalendar.setAutoCreateContainerGaps(true);

        // дата формат для спиннеров
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        startTime.setEditor(new JSpinner.DateEditor(startTime, dateFormat.toPattern()));
        endTime.setEditor(new JSpinner.DateEditor(endTime, dateFormat.toPattern()));

        calendarPanel.setLayout(glCalendar);
        glCalendar.setHorizontalGroup(glCalendar.createSequentialGroup()
                .addGroup(glCalendar.createParallelGroup()
                        .addGroup(glCalendar.createSequentialGroup()
                                .addComponent(lStart)
                                .addComponent(startTime)
                                .addComponent(bShow)
                        )
                        .addGroup(glCalendar.createSequentialGroup()
                                .addComponent(lEnd)
                                .addComponent(endTime)
                                .addComponent(bBack)
                        )
                        .addComponent(textArea)
                )
        );
        glCalendar.setVerticalGroup(glCalendar.createSequentialGroup()
                .addGroup(glCalendar.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lStart)
                        .addComponent(startTime)
                        .addComponent(bShow)
                )
                .addGroup(glCalendar.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lEnd)
                        .addComponent(endTime)
                        .addComponent(bBack)
                )
                .addComponent(textArea)
        );
        glCalendar.linkSize(SwingConstants.HORIZONTAL , bBack, bShow,startTime,endTime);
        glCalendar.linkSize(SwingConstants.HORIZONTAL,lEnd,lStart);
    }

    public JPanel getCalendarPanel() {
        return calendarPanel;
    }

    public void addBackActionListener(ActionListener listener) {
        bBack.addActionListener(listener);
    }

    public void addShowActionListener(ActionListener listener) {
        bShow.addActionListener(listener);
    }

    public Date getStartDate() {
        return (Date) startTime.getValue();
    }

    public Date getEndDate() {
        return (Date) endTime.getValue();
    }

    public JTextArea getTextArea() {
        return jTextArea;
    }
}
