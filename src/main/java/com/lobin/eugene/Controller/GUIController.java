package com.lobin.eugene.Controller;

import com.lobin.eugene.View.View;
import com.lobin.eugene.model.ArrayTaskList;
import com.lobin.eugene.model.Model;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.text.SimpleDateFormat;

class GUIController {
    private View view;
    private String focusTabled = "noSelect";
    private Model model;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    GUIController(View viewStart, Model modell) {
        view = viewStart;
        this.model = modell;
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
                model.loadFromFile();
                writeTasks(model.getTaskList());
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
        ListSelectionModel listSelectionModel1 = view.getStartPanel().getTableNonRep().getSelectionModel();
        listSelectionModel1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (view.getStartPanel().getTableNonRep().getSelectedRow() != -1) {
                    focusTabled = "Non";
                    view.getStartPanel().getTableRep().clearSelection();
                }
            }
        });

        ListSelectionModel listSelectionModel2 = view.getStartPanel().getTableRep().getSelectionModel();
        listSelectionModel2.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (view.getStartPanel().getTableRep().getSelectedRow() != -1) {
                    focusTabled = "Rep";
                    view.getStartPanel().getTableNonRep().clearSelection();
                }
            }
        });

        view.getEditPanel().addCancelButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.getCardLayout().show(view.getContainer(), "Start form");
                model.loadFromFile();
                writeTasks(model.getTaskList());
                focusTabled = "noSelect";

            }
        });

        view.getStartPanel().addCalendarActionListened(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.getCardLayout().show(view.getContainer(), "Calendar");
            }
        });

        view.getCalendarPanel().addBackActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.getCardLayout().show(view.getContainer(), "Start form");
                model.loadFromFile();
            }
        });

    }

    String getFocusTabled() {
        return focusTabled;
    }

    void writeTasks(ArrayTaskList arr) {
        //очищаем таблицы
        DefaultTableModel defaultTableModel = (DefaultTableModel) view.getStartPanel().getTableNonRep().getModel();
        defaultTableModel.setRowCount(0);
        defaultTableModel = (DefaultTableModel) view.getStartPanel().getTableRep().getModel();
        defaultTableModel.setRowCount(0);

        //заполняем таблицы
        DefaultTableModel modelRep = (DefaultTableModel) view.getStartPanel().getTableRep().getModel();
        DefaultTableModel modelNonRep = (DefaultTableModel) view.getStartPanel().getTableNonRep().getModel();
        for (int i = 0; i < arr.size(); i++) {
            if (arr.getTask(i).getRepeatInterval() > 0) {
                modelRep.addRow(new Object[]{arr.getTask(i).getTitle(), dateFormat.format(arr.getTask(i).getStartTime())
                        , dateFormat.format(arr.getTask(i).getEndTime()), arr.getTask(i).getRepeatInterval()
                        , arr.getTask(i).isActive()});
            } else {
                modelNonRep.addRow(new Object[]{arr.getTask(i).getTitle(), dateFormat.format(arr.getTask(i).getTime())
                        , arr.getTask(i).isActive()});
            }
        }
    }
}
