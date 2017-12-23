package com.lobin.eugene.Controller;

import com.lobin.eugene.View.View;

import java.awt.event.*;

class GUIController {
    private View view;
    private String focusTabled = "Non";

    GUIController(View viewStart) {
        view = viewStart;
        view.getStartPanel().addCreateTaskActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.getCardLayout().show(view.getContainer(), "Create task");
            }
        });

        view.getCreateTaskPanel().addButtonBackListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.getCardLayout().show(view.getContainer(), "Start form");
            }
        });

        view.getCreateTaskPanel().addCreateReTaskListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                view.getCreateTaskPanel().getNonRepTask().setVisible(false);
                view.getCreateTaskPanel().getRepTask().setVisible(true);
            }
        });

        view.getCreateTaskPanel().addCreateNonReTaskListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                view.getCreateTaskPanel().getNonRepTask().setVisible(true);
                view.getCreateTaskPanel().getRepTask().setVisible(false);
            }
        });

        view.getStartPanel().addTableNonRepFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (view.getStartPanel().getTableNonRep().getSelectedRow() != -1) {
                    focusTabled = "Non";
                    view.getStartPanel().getTableRep().clearSelection();
                }
            }
            @Override
            public void focusLost(FocusEvent e) {

            }
        });

        view.getStartPanel().addTableRepFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (view.getStartPanel().getTableRep().getSelectedRow() != -1) {
                    focusTabled = "Rep";
                    view.getStartPanel().getTableNonRep().clearSelection();
                }
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });
    }

    public String getFocusTabled() {
        return focusTabled;
    }
}
