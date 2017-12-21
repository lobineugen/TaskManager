package com.lobin.eugene.Controller;

import com.lobin.eugene.View.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


class GUIController {
    private View view;

    GUIController(View viewStart) {
        this.view = viewStart;

        this.view.addCreateTaskListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.getCardLayout().show(view.getContainer(), "Create task");
            }
        });

        this.view.getCreateTaskPanel().addButtonBackListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.getCardLayout().show(view.getContainer(), "Start form");
            }
        });

        this.view.getCreateTaskPanel().addCreateReTaskListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                view.getCreateTaskPanel().getNonRepTask().setVisible(false);
                view.getCreateTaskPanel().getRepTask().setVisible(true);
            }
        });

        this.view.getCreateTaskPanel().addCreateNonReTaskListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                view.getCreateTaskPanel().getNonRepTask().setVisible(true);
                view.getCreateTaskPanel().getRepTask().setVisible(false);
            }
        });
    }
}
