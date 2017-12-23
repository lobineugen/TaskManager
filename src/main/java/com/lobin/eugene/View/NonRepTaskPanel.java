package com.lobin.eugene.View;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NonRepTaskPanel {
    private JPanel panel = new JPanel();
    private JTextField title = new JTextField(30);
    private SpinnerDateModel model = new SpinnerDateModel();
    private JSpinner date = new JSpinner(model);
    private JRadioButton active = new JRadioButton();

    NonRepTaskPanel() {
        GroupLayout groupLayout = new GroupLayout(panel);
        panel.setLayout(groupLayout);

        // дата формат для спиннера
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        date.setEditor(new JSpinner.DateEditor(date, dateFormat.toPattern()));

        JLabel title = new JLabel("Title");
        title.setPreferredSize(new Dimension(70, 20));
        JLabel date = new JLabel("Date");
        JLabel active = new JLabel("Active");

        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);

        groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
                .addGroup(groupLayout.createParallelGroup()
                        .addComponent(title)
                        .addComponent(date)
                        .addComponent(active))
                .addGroup(groupLayout.createParallelGroup()
                        .addComponent(this.title)
                        .addComponent(this.date)
                        .addComponent(this.active)));
        groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(title)
                        .addComponent(this.title))
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(date)
                        .addComponent(this.date))
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(active)
                        .addComponent(this.active)));
        groupLayout.linkSize(SwingConstants.HORIZONTAL, title, date, active);
    }

    public JPanel getPanel() {
        return panel;
    }

    public String getTitle() {
        return title.getText();
    }

    public Date getDate() {
        return (Date) date.getValue();
    }

    public boolean getActive() {
        return active.isSelected();
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public void setDate(Object date) {
        this.date.setValue(date);
    }

    public void setActive(boolean active) {
        this.active.setSelected(active);
    }
}
