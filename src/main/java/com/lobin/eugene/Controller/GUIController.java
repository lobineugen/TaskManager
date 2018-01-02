package com.lobin.eugene.Controller;

import com.lobin.eugene.View.View;
import com.lobin.eugene.model.ArrayTaskList;
import com.lobin.eugene.model.Model;

import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

class GUIController implements TaskConstant {
    private View view;
    private String focusTabled = "noSelect";
    private Model model;

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

        view.getCreateTaskPanel().
                addCreateNonReTaskListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        view.getCreateTaskPanel().
                                getNonRepTask().setVisible(true);
                        view.getCreateTaskPanel().
                                getRepTask().setVisible(false);
                    }
                });
        ListSelectionModel listSelectionModel1 =
                view.getStartPanel().getTableNonRep().getSelectionModel();
        listSelectionModel1.
                addListSelectionListener(new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        if (view.getStartPanel().
                                getTableNonRep().getSelectedRow() != -1) {
                            focusTabled = "Non";
                            view.getStartPanel().getTableRep().clearSelection();
                        }
                    }
                });

        ListSelectionModel listSelectionModel2 =
                view.getStartPanel().getTableRep().getSelectionModel();
        listSelectionModel2.
                addListSelectionListener(new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        if (view.getStartPanel().
                                getTableRep().getSelectedRow() != -1) {
                            focusTabled = "Rep";
                            view.getStartPanel().
                                    getTableNonRep().clearSelection();
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

    /**
     * @return focused tablw
     */
    String getFocusTabled() {
        return focusTabled;
    }

    /**
     * @param arr write this task list in main table
     */
    void writeTasks(ArrayTaskList arr) {
        clearTable();
        //заполняем таблицы
        DefaultTableModel modelRep = (DefaultTableModel) view.
                getStartPanel().getTableRep().getModel();
        DefaultTableModel modelNonRep = (DefaultTableModel) view.
                getStartPanel().getTableNonRep().getModel();
        for (int i = 0; i < arr.size(); i++) {
            if (arr.getTask(i).getRepeatInterval() > 0) {
                modelRep.addRow(new Object[]{arr.getTask(i).getTitle(),
                        DATE_FORMAT.format(arr.getTask(i).getStartTime()),
                        DATE_FORMAT.format(arr.getTask(i).getEndTime()),
                        arr.getTask(i).getRepeatInterval(),
                        arr.getTask(i).isActive()});
            } else {
                modelNonRep.addRow(new Object[]{arr.getTask(i).getTitle(),
                        DATE_FORMAT.format(arr.getTask(i).getTime()),
                        arr.getTask(i).isActive()});
            }
        }
    }

    /**
     * clear all table
     */
    void clearTable() {
        DefaultTableModel model = (DefaultTableModel) view.getStartPanel().
                getTableNonRep().getModel();
        model.setRowCount(0);
        model = (DefaultTableModel) view.getStartPanel().
                getTableRep().getModel();
        model.setRowCount(0);
    }

    /**
     * show message dialog with text
     *
     * @param text text for message dialog
     */
    void successfullyMessage(String text) {
        JOptionPane.showMessageDialog(null, text,
                "Successfully", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * show message dialog with text
     *
     * @param text text for message dialog
     */
    void errorMessage(String text) {
        JOptionPane.showMessageDialog(null,
                "Error: " + text, "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    /**
     * show message dialog with text
     *
     * @param text text for message dialog
     */
    void infoMessage(String text) {
        JOptionPane.showMessageDialog(null, text,
                "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * show confirm dialog with text
     *
     * @param text text for confirm dialog
     * @return yes or no option
     */
    int deleteMessage(String text) {
        return JOptionPane.showConfirmDialog(null,
                "Confirm deletion?", text,
                JOptionPane.YES_NO_OPTION);
    }

    /**
     * show input dialog with text
     *
     * @param text text for input dialog
     * @return task list name
     */
    String inputDialog(String text) {
        return JOptionPane.showInputDialog(null,
                "Enter new task list name", text,
                JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * @return view
     */
    View getView() {
        return view;
    }

    /**
     * @return model
     */
    Model getModel() {
        return model;
    }

    /**
     * @param state param for all button in start panel
     */
    void setButtonEnabled(boolean state) {
        view.getStartPanel().getCreateNewTask().setEnabled(state);
        view.getStartPanel().getRemove().setEnabled(state);
        view.getStartPanel().getCalendar().setEnabled(state);
        view.getStartPanel().getEdit().setEnabled(state);
    }
}
