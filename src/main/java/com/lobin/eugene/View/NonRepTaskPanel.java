package com.lobin.eugene.View;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;

public class NonRepTaskPanel {
    private JPanel panel = new JPanel();
    private GroupLayout groupLayout = new GroupLayout(panel);
    private JTextField title = new JTextField(30);
    private SpinnerDateModel model = new SpinnerDateModel();
    private JSpinner date = new JSpinner(model);
    private JRadioButton active = new JRadioButton();

    NonRepTaskPanel() {
        panel.setLayout(groupLayout);

        // дата формат для спиннера
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        date.setEditor(new JSpinner.DateEditor(date, dateFormat.toPattern()));

        JLabel title = new JLabel("Title");
        title.setPreferredSize(new Dimension(70,20));
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

    public JTextField getTitle() {
        return title;
    }

    public JSpinner getDate() {
        return date;
    }

    public JRadioButton getActive() {
        return active;
    }
}