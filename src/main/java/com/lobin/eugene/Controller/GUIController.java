package com.lobin.eugene.Controller;

import com.lobin.eugene.View.View;
import com.lobin.eugene.model.ArrayTaskList;
import com.lobin.eugene.model.Model;
import org.apache.log4j.Logger;

import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

/**
 * Class for control gui
 *
 * @author Eugene Lobin
 * @version 1.1 29 Nov 2017
 */

class GUIController implements TaskConstant {
    private static final Logger LOG = Logger.getLogger(MainController.class.getName());
    private View view;
    private String focusTabled = "noSelect";
    private Model model;

    public GUIController(View viewStart, Model model) {
        view = viewStart;
        this.model = model;
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
                loafFromFile();
                writeTasks(GUIController.this.model.getTaskList());
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
                view.getCreateTaskPanel().
                        getNonRepTask().setVisible(true);
                view.getCreateTaskPanel().
                        getRepTask().setVisible(false);
            }
        });

        ListSelectionModel model1 = view.getStartPanel().getTableNonRep().getSelectionModel();

        model1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (view.getStartPanel().
                        getTableNonRep().getSelectedRow() != -1) {
                    focusTabled = "Non";
                    view.getStartPanel().getTableRep().clearSelection();
                }
            }
        });

        ListSelectionModel model2 = view.getStartPanel().getTableRep().getSelectionModel();

        model2.addListSelectionListener(new ListSelectionListener() {
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
                loafFromFile();
                writeTasks(GUIController.this.model.getTaskList());
                focusTabled = "noSelect";

            }
        });

        view.getStartPanel().addCalendarActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.getCardLayout().show(view.getContainer(), "Calendar");
            }
        });

        view.getCalendarPanel().addBackActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.getCardLayout().show(view.getContainer(), "Start form");
                loafFromFile();
            }
        });

    }

    /**
     * load tasks from file
     */
    public void loafFromFile() {
        try {
            model.loadFromFile();
        } catch (IOException ex) {
            LOG.error("IOException", ex);
        }
    }

    /**
     * @return focused tablw
     */
    public String getFocusTabled() {
        return focusTabled;
    }

    /**
     * @param arr write this task list in main table
     */
    public void writeTasks(ArrayTaskList arr) {
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
    public void clearTable() {
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
    public void successfullyMessage(String text) {
        JOptionPane.showMessageDialog(null, text,
                "Successfully", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * show message dialog with text
     *
     * @param text text for message dialog
     */
    public void errorMessage(String text) {
        JOptionPane.showMessageDialog(null,
                "Error: " + text, "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    /**
     * show message dialog with text
     *
     * @param text text for message dialog
     */
    public void infoMessage(String text) {
        JOptionPane.showMessageDialog(null, text,
                "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * show confirm dialog with text
     *
     * @param text text for confirm dialog
     * @return yes or no option
     */
    public int deleteMessage(String text) {
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
    public String inputDialog(String text) {
        return JOptionPane.showInputDialog(null,
                "Enter new task list name", text,
                JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * @return view
     */
    public View getView() {
        return view;
    }

    /**
     * @return model
     */
    public Model getModel() {
        return model;
    }

    /**
     * @param state param for all button in start panel
     */
    public void setButtonEnabled(boolean state) {
        view.getStartPanel().getCreateNewTask().setEnabled(state);
        view.getStartPanel().getRemove().setEnabled(state);
        view.getStartPanel().getCalendar().setEnabled(state);
        view.getStartPanel().getEdit().setEnabled(state);
    }

    /**
     * Clear repetitive and non-repetitive task filed
     */
    public void clearCreateTaskField() {
        view.getCreateTaskPanel().getRepTaskPanel().clearField();
        view.getCreateTaskPanel().getNonRepTaskPanel().clearField();
    }
}
