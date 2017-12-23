package com.lobin.eugene.Controller;

import com.lobin.eugene.View.View;
import com.lobin.eugene.model.ArrayTaskList;
import com.lobin.eugene.model.Model;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainController {
    private Model model;
    private View view;
    private GUIController guiController;

    private MainController(final Model m, final View v, GUIController g) {
        this.model = m;
        this.view = v;
        this.guiController = g;
        this.model.loadFromFile();
        writeTasks(model.getTaskList());
        this.view.getStartPanel().getTableNonRep().setRowSelectionInterval(0,0);
        this.view.getCreateTaskPanel().addCreateTaskListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getCreateTaskPanel().getTaskType()) {
                    if (view.getCreateTaskPanel().getTaskTitleTwo().getText().trim().length() > 0) {
                        model.addTask(view.getCreateTaskPanel().getTaskTitleTwo().getText(),
                                (Date) view.getCreateTaskPanel().getStartTime().getValue(),
                                (Date) view.getCreateTaskPanel().getEndTIme().getValue(),
                                Integer.parseInt(view.getCreateTaskPanel().getInterval().getText()),
                                view.getCreateTaskPanel().getActiveTwo().isSelected());
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Error: Title can not be empty", "Error Massage",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    if (view.getCreateTaskPanel().getTaskTitleOne().getText().trim().length() > 0) {
                        model.addTask(view.getCreateTaskPanel().getTaskTitleOne().getText(),
                                (Date) view.getCreateTaskPanel().getDateTime().getValue(),
                                view.getCreateTaskPanel().getActiveOne().isSelected());
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Error: Title can not be empty", "Error Massage",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        this.view.getStartPanel().addEditActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String focus = guiController.getFocusTabled();
                int row;
                switch (focus) {
                    case "Rep": {
                        row = view.getStartPanel().getTableRep().getSelectedRow();
                        String hashCode = view.getStartPanel().getTableRep().getModel().getValueAt(row, 5).toString();
                        System.out.println(hashCode);
                        break;
                    }
                    case "Non": {
                        row = view.getStartPanel().getTableNonRep().getSelectedRow();
                        String hashCode = view.getStartPanel().getTableNonRep().getModel().getValueAt(row, 3).toString();
                        System.out.println(hashCode);
                        break;
                    }
                    default:
                        JOptionPane.showMessageDialog(null,
                                "Error: To start editing, select row with task", "Error Massage",
                                JOptionPane.ERROR_MESSAGE);
                        break;
                }
            }
        });
    }

    public void writeTasks(ArrayTaskList arr) {
        DefaultTableModel modelRep = (DefaultTableModel) view.getStartPanel().getTableRep().getModel();
        DefaultTableModel modelNonRep = (DefaultTableModel) view.getStartPanel().getTableNonRep().getModel();
        SimpleDateFormat dateFormat =
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < arr.size(); i++) {
            if (arr.getTask(i).getRepeatInterval() > 0) {
                modelRep.addRow(new Object[]{arr.getTask(i).getTitle(), dateFormat.format(arr.getTask(i).getStartTime())
                        , dateFormat.format(arr.getTask(i).getEndTime()), arr.getTask(i).getRepeatInterval()
                        , arr.getTask(i).isActive(), arr.getTask(i).hashCode()});
            } else {
                modelNonRep.addRow(new Object[]{arr.getTask(i).getTitle(), dateFormat.format(arr.getTask(i).getTime())
                        , arr.getTask(i).isActive(), arr.getTask(i).hashCode()});
            }
        }
    }

    public static void main(String[] args) {
        View view = new View();
        Model model = new Model();
        GUIController guiController = new GUIController(view);
        MainController controller = new MainController(model, view, guiController);
        view.setVisible(true);
    }
}
