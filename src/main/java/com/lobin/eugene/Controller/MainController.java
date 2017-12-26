package com.lobin.eugene.Controller;

import com.lobin.eugene.View.View;
import com.lobin.eugene.model.Model;
import com.lobin.eugene.model.Task;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MainController {
    private Model model;
    private View view;
    private GUIController guiController;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private Task taskForComparison;
    private static final String SUCCESSFULLYADDED = "Task successfully added!";
    private static final String EMPTYTITLE = "Title can not be empty!";
    private static final String SUCCESSFULLYREMOVEING = "Removing was successful!";
    private static final String EMPRTYROWFORREMOVE = "To start removing, select row with task!";
    private static final String SUCCESSFULLYCHANGED = "Successfully changed task!";
    private static final String EPRTYROWFOREDIT = "To start editing, select row with task!";
    private static final String INFOCALENDAREMPTY = "Calendar of tasks for this date is empty!" +
            " Enter a new date and try again.";
    private static final String STARTDATEMUSTBEBEFORENDDATE = "Start date must be before end date!!!";

    private MainController(final Model model, final View view, GUIController guiController) {
        this.model = model;
        this.view = view;
        this.guiController = guiController;

        this.model.loadFromFile();
        this.guiController.writeTasks(model.getTaskList());
        this.view.getCreateTaskPanel().addCreateTaskListener(new AddCreateTaskListener());
        this.view.getStartPanel().addEditActionListener(new AddEditActionListener());
        this.view.getEditPanel().addSaveButtonListener(new AddSaveButtonListener());
        this.view.getStartPanel().addRemoveTaskListener(new AddRemoveTaskListener());
        this.view.getCalendarPanel().addShowActionListener(new StartActionListener());
    }

    class AddCreateTaskListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.getCreateTaskPanel().getTaskType()) {
                if (view.getCreateTaskPanel().getTaskTitleTwo().trim().length() > 0) {
                    Date start = view.getCreateTaskPanel().getStartTime();
                    Date end = view.getCreateTaskPanel().getEndTime();
                    if(start.compareTo(end) <= 0){
                        model.addTask(view.getCreateTaskPanel().getTaskTitleTwo(),
                                deleteSeconds(start),
                                deleteSeconds(end),
                                view.getCreateTaskPanel().getInterval(),
                                view.getCreateTaskPanel().getActiveTwo());
                        successfullyMessage(SUCCESSFULLYADDED);
                    } else {
                        errorMessage(STARTDATEMUSTBEBEFORENDDATE);
                    }

                } else {
                    errorMessage(EMPTYTITLE);
                }
            } else {
                if (view.getCreateTaskPanel().getTaskTitleOne().trim().length() > 0) {
                    model.addTask(view.getCreateTaskPanel().getTaskTitleOne(),
                            deleteSeconds(view.getCreateTaskPanel().getDateTime()),
                            view.getCreateTaskPanel().getActiveOne());
                    successfullyMessage(SUCCESSFULLYADDED);
                } else {
                    errorMessage(EMPTYTITLE);
                }
            }
        }
    }

    class AddRemoveTaskListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Task task;
            int row;
            String focus = guiController.getFocusTabled();
            switch (focus) {
                case "Rep":
                    try {
                        if ((row = view.getStartPanel().getTableRep().getSelectedRow()) != -1) {
                            task = new Task(view.getStartPanel().getTableRep().getModel()
                                    .getValueAt(row, 0).toString(),
                                    dateFormat.parse(view.getStartPanel().getTableRep().getModel()
                                            .getValueAt(row, 1).toString()),
                                    dateFormat.parse(view.getStartPanel().getTableRep().getModel()
                                            .getValueAt(row, 2).toString()),
                                    Integer.parseInt(view.getStartPanel().getTableRep().getModel()
                                            .getValueAt(row, 3).toString()));
                            task.setActive((boolean) view.getStartPanel().getTableRep().getModel().getValueAt(row, 4));
                            if (model.removeTask(task)) {
                                successfullyMessage(SUCCESSFULLYREMOVEING);
                                guiController.writeTasks(model.getTaskList());
                            }
                        } else {
                            errorMessage(EMPRTYROWFORREMOVE);
                        }
                        break;
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                case "Non":
                    try {
                        if ((row = view.getStartPanel().getTableNonRep().getSelectedRow()) != -1) {
                            task = new Task(view.getStartPanel().getTableNonRep().getModel()
                                    .getValueAt(row, 0).toString(),
                                    dateFormat.parse(view.getStartPanel().getTableNonRep().getModel()
                                            .getValueAt(row, 1).toString()));
                            task.setActive((boolean) view.getStartPanel().getTableNonRep().getModel().getValueAt(row, 2));
                            if (model.removeTask(task)) {
                                successfullyMessage(SUCCESSFULLYREMOVEING);
                                guiController.writeTasks(model.getTaskList());
                            }
                        } else {
                            errorMessage(EMPRTYROWFORREMOVE);
                        }
                        break;
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                default:
                    errorMessage(EMPRTYROWFORREMOVE);
                    break;
            }
        }
    }

    class StartActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date startDate = view.getCalendarPanel().getStartDate();
            Date endDate = view.getCalendarPanel().getEndDate();
            if(startDate.compareTo(endDate) <= 0){
                SortedMap<Date, Set<Task>> map = model.getCalendar(startDate, endDate);
                JTextArea text = view.getCalendarPanel().getTextArea();
                if(map.size() > 0) {
                    text.setText("");
                    for (Map.Entry<Date, Set<Task>> entry : map.entrySet()) {
                        text.append("Date - " + dateFormat.format(entry.getKey()) + " ");
                        int size = 0;
                        for (Task task : entry.getValue()){
                            text.append("Task - " + task.getTitle());
                            if(size < entry.getValue().size() - 1){
                                text.append(", ");
                            }
                        }
                        text.append("\n");
                    }
                } else {
                    infoMessage(INFOCALENDAREMPTY);
                    text.setText("");
                }
            } else {
                errorMessage(STARTDATEMUSTBEBEFORENDDATE);
            }


        }
    }

    class AddSaveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Task taskWithNewInfo;
            if (view.getEditPanel().getEditNonRepTask().isVisible()) {
                if (view.getEditPanel().getNonRepTaskPanel().getTitle().trim().length() > 0) {
                    taskWithNewInfo = new Task(view.getEditPanel().getNonRepTaskPanel().getTitle(),
                            view.getEditPanel().getNonRepTaskPanel().getDate()
                    );
                    taskWithNewInfo.setActive(view.getEditPanel().getNonRepTaskPanel().getActive());
                    if (model.editTask(taskForComparison, taskWithNewInfo)) {
                        successfullyMessage(SUCCESSFULLYCHANGED);
                    }
                } else {
                    errorMessage(EMPTYTITLE);
                }

            } else {
                if (view.getEditPanel().getRepTaskPanel().getTitle().trim().length() > 0) {
                    Date start = view.getEditPanel().getRepTaskPanel().getStartTime();
                    Date end = view.getEditPanel().getRepTaskPanel().getEndTime();
                    if(start.compareTo(end) <= 0){
                        taskWithNewInfo = new Task(view.getEditPanel().getRepTaskPanel().getTitle(),
                                start,
                                end,
                                view.getEditPanel().getRepTaskPanel().getInterval());
                        taskWithNewInfo.setActive(view.getEditPanel().getRepTaskPanel().getActive());
                        if (model.editTask(taskForComparison, taskWithNewInfo)) {
                            successfullyMessage(SUCCESSFULLYCHANGED);
                        }
                    } else {
                        errorMessage(STARTDATEMUSTBEBEFORENDDATE);
                    }
                } else {
                    errorMessage(EMPTYTITLE);
                }

            }
        }
    }

    class AddEditActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int row;
            String focus = guiController.getFocusTabled();
            switch (focus) {
                case "Rep": {
                    try {
                        row = view.getStartPanel().getTableRep().getSelectedRow();
                        taskForComparison = new Task(view.getStartPanel().getTableRep().getModel().getValueAt(row, 0).toString(),
                                dateFormat.parse(view.getStartPanel().getTableRep().getModel().getValueAt(row, 1).toString()),
                                dateFormat.parse(view.getStartPanel().getTableRep().getModel().getValueAt(row, 2).toString()),
                                Integer.parseInt(view.getStartPanel().getTableRep().getModel().getValueAt(row, 3).toString()));
                        taskForComparison.setActive((boolean) view.getStartPanel().getTableRep().getModel().getValueAt(row, 4));
                        view.getEditPanel().getRepTaskPanel().setTitle(taskForComparison.getTitle());
                        view.getEditPanel().getRepTaskPanel().setStartTime(taskForComparison.getStartTime());
                        view.getEditPanel().getRepTaskPanel().setEndTime(taskForComparison.getEndTime());
                        view.getEditPanel().getRepTaskPanel().setInterval(taskForComparison.getRepeatInterval());
                        view.getEditPanel().getRepTaskPanel().setActive(taskForComparison.isActive());
                        view.getCardLayout().show(view.getContainer(), "Edit task");

                        view.getEditPanel().getEditNonRepTask().setVisible(false);
                        view.getEditPanel().getEditRepTask().setVisible(true);
                        break;
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                }
                case "Non": {
                    try {
                        row = view.getStartPanel().getTableNonRep().getSelectedRow();
                        taskForComparison = new Task(view.getStartPanel().getTableNonRep().getModel()
                                .getValueAt(row, 0).toString(),
                                dateFormat.parse(view.getStartPanel().getTableNonRep().getModel()
                                        .getValueAt(row, 1).toString()));
                        taskForComparison.setActive((boolean) view.getStartPanel().getTableNonRep().getModel().getValueAt(row, 2));
                        view.getEditPanel().getNonRepTaskPanel().setTitle(taskForComparison.getTitle());
                        view.getEditPanel().getNonRepTaskPanel().setDate(taskForComparison.getTime());
                        view.getEditPanel().getNonRepTaskPanel().setActive(taskForComparison.isActive());
                        view.getCardLayout().show(view.getContainer(), "Edit task");
                        view.getEditPanel().getEditRepTask().setVisible(false);
                        view.getEditPanel().getEditNonRepTask().setVisible(true);
                        break;
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                }
                default:
                    errorMessage(EPRTYROWFOREDIT);
                    break;
            }
        }
    }

    private Date deleteSeconds(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        date = calendar.getTime();
        return date;
    }

    private void successfullyMessage(String text) {
        JOptionPane.showMessageDialog(null, text,
                "Successfully", JOptionPane.INFORMATION_MESSAGE);
    }

    private void errorMessage(String text) {
        JOptionPane.showMessageDialog(null, "Error: " + text, "Error Massage",
                JOptionPane.ERROR_MESSAGE);
    }

    private void infoMessage(String text) {
        JOptionPane.showMessageDialog(null, text,
                "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        View view = new View();
        Model model = new Model();
        GUIController guiController = new GUIController(view, model);
        MainController controller = new MainController(model, view, guiController);
        view.setVisible(true);
    }

}
