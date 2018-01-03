package com.lobin.eugene.Controller;

import com.lobin.eugene.View.View;
import com.lobin.eugene.model.Model;
import com.lobin.eugene.model.Task;
import org.apache.log4j.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;


public class MainController implements TaskConstant {
    private static final Logger log = Logger.getLogger(MainController.class);
    private Model model;
    private View view;
    private GUIController guiController;
    private Task taskForComparison;

    private MainController(GUIController guiController) {
        this.guiController = guiController;
        this.model = guiController.getModel();
        this.view = guiController.getView();
        ArrayList<String> fileListName = new ArrayList<>();
        for (File aFileList : this.model.getFileList()) {
            if (aFileList.isFile()
                    && aFileList.getName().endsWith(FILE_EXTENSION)) {
                fileListName.add(aFileList.getName().
                        substring(0, aFileList.getName().length() - DELETE_CHAR));
            }
        }
        this.model.setFileListName(fileListName);
        if (fileListName.size() > 0) {
            this.model.setFile(new File(fileListName.get(0) + FILE_EXTENSION));
            this.model.loadFromFile();
        } else {
            this.guiController.setButtonEnabled(false);
        }
        printFileList();
        printTaskList(view.getStartPanel().getTaskList());
        this.guiController.writeTasks(model.getTaskList());
        createTaskListener(view.getCreateTaskPanel().getCreateTask());
        editTask(view.getStartPanel().getEdit());
        saveTask(view.getEditPanel().getSave());
        removeTask(view.getStartPanel().getRemove());
        showCalendar(view.getCalendarPanel().getShow());
        removeTaskListListener(view.getStartPanel().getDeleteList());
        addTaskListListener(view.getStartPanel().getAddNewList());
    }

    /**
     * create new task listener for create button
     *
     * @param create button
     */
    private void createTaskListener(JButton create) {
        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getCreateTaskPanel().getTaskType()) {
                    if (view.getCreateTaskPanel().
                            getTaskTitleTwo().trim().length() > 0) {
                        Date start = view.getCreateTaskPanel().getStartTime();
                        Date end = view.getCreateTaskPanel().getEndTime();
                        if (start.compareTo(end) <= 0) {
                            model.addTask(
                                    view.getCreateTaskPanel()
                                            .getTaskTitleTwo(),
                                    deleteSeconds(start),
                                    deleteSeconds(end),
                                    view.getCreateTaskPanel()
                                            .getInterval(),
                                    view.getCreateTaskPanel()
                                            .getActiveTwo()
                            );
                            guiController
                                    .successfullyMessage(SUCCESSFULLY_ADDED);
                            guiController.clearCreateTaskField();
                            view.getCardLayout().show(view.getContainer(), "Start form");
                            model.loadFromFile();
                            guiController.writeTasks(model.getTaskList());
                        } else {
                            guiController
                                    .errorMessage(START_DATE_MUST_BE_BEFORE_END_DATE);
                        }

                    } else {
                        guiController.errorMessage(EMPTY_TITLE);
                    }
                } else {
                    if (view.getCreateTaskPanel()
                            .getTaskTitleOne().trim().length() > 0) {
                        model.addTask(view.getCreateTaskPanel().getTaskTitleOne(),
                                deleteSeconds(view.getCreateTaskPanel().getDateTime()),
                                view.getCreateTaskPanel().getActiveOne());
                        guiController.successfullyMessage(SUCCESSFULLY_ADDED);
                        guiController.clearCreateTaskField();
                        view.getCardLayout().show(view.getContainer(), "Start form");
                        model.loadFromFile();
                        guiController.writeTasks(model.getTaskList());
                    } else {
                        guiController.errorMessage(EMPTY_TITLE);
                    }
                }
            }
        });
    }

    /**
     * create new task listener for remove button
     *
     * @param remove button
     */
    private void removeTask(JButton remove) {
        remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                log.debug("Start processing");
                Task task;
                int row;
                String focus = guiController.getFocusTabled();
                switch (focus) {
                    case "Rep":
                        if (log.isDebugEnabled()) {
                            log.debug("Focus table: " + focus);
                        }
                        try {
                            if ((row = view.getStartPanel().getTableRep().getSelectedRow()) != -1) {
                                task = new Task(view.getStartPanel().getTableRep().getModel()
                                        .getValueAt(row, COLUMN_INDEX_ZERO).toString(),
                                        DATE_FORMAT.parse(view.getStartPanel().getTableRep().getModel()
                                                .getValueAt(row, COLUMN_INDEX_ONE).toString()),
                                        DATE_FORMAT.parse(view.getStartPanel().getTableRep().getModel()
                                                .getValueAt(row, COLUMN_INDEX_TWO).toString()),
                                        Integer.parseInt(view.getStartPanel().getTableRep().getModel()
                                                .getValueAt(row, COLUMN_INDEX_THREE).toString()));
                                task.setActive((boolean) view.getStartPanel().getTableRep().getModel()
                                        .getValueAt(row, COLUMN_INDEX_FOUR));
                                if (model.removeTask(task)) {
                                    guiController.successfullyMessage(REMOVING_WAS_SUCCESSFUL);
                                    guiController.writeTasks(model.getTaskList());
                                }
                            } else {
                                guiController.errorMessage(EMPTY_ROW_FOR_REMOVE);
                            }
                            log.debug("done");
                            break;
                        } catch (ParseException ex) {
                            log.error("Parse exception", ex);
                        }
                    case "Non":
                        if (log.isDebugEnabled()) {
                            log.debug("Focus table: " + focus);
                        }
                        try {
                            if ((row = view.getStartPanel().getTableNonRep().getSelectedRow()) != -1) {
                                task = new Task(view.getStartPanel().getTableNonRep().getModel()
                                        .getValueAt(row, 0).toString(),
                                        DATE_FORMAT.parse(view.getStartPanel().getTableNonRep().getModel()
                                                .getValueAt(row, 1).toString()));
                                task.setActive((boolean) view.getStartPanel().getTableNonRep()
                                        .getModel().getValueAt(row, 2));
                                if (model.removeTask(task)) {
                                    guiController.successfullyMessage(REMOVING_WAS_SUCCESSFUL);
                                    guiController.writeTasks(model.getTaskList());
                                }
                            } else {
                                guiController.errorMessage(EMPTY_ROW_FOR_REMOVE);
                            }
                            log.debug("Done");
                            break;
                        } catch (ParseException ex) {
                            log.error("Parse exception", ex);
                        }
                    default:
                        guiController.errorMessage(EMPTY_ROW_FOR_REMOVE);
                        break;
                }
            }
        });
    }

    /**
     * create new task listener for show button
     *
     * @param show button
     */
    private void showCalendar(JButton show) {
        show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date startDate = view.getCalendarPanel().getStartDate();
                Date endDate = view.getCalendarPanel().getEndDate();
                if (startDate.compareTo(endDate) <= 0) {
                    SortedMap<Date, Set<Task>> map = model.getCalendar(startDate, endDate);
                    JTextArea text = view.getCalendarPanel().getTextArea();
                    if (map.size() > 0) {
                        text.setText("");
                        for (Map.Entry<Date, Set<Task>> entry : map.entrySet()) {
                            text.append("Date - " + DATE_FORMAT.format(entry.getKey()) + "; ");
                            int size = 0;
                            for (Task task : entry.getValue()) {
                                text.append("Task - " + task.getTitle());
                                if (size < entry.getValue().size() - 1) {
                                    text.append(", ");
                                }
                            }
                            text.append("\n");
                        }
                    } else {
                        guiController.infoMessage(INFO_CALENDAR_EMPTY);
                        text.setText("");
                    }
                } else {
                    guiController.errorMessage(START_DATE_MUST_BE_BEFORE_END_DATE);
                }
            }
        });
    }

    /**
     * create new task listener for save button
     *
     * @param save button
     */
    private void saveTask(JButton save) {
        save.addActionListener(new ActionListener() {
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
                            guiController.successfullyMessage(SUCCESSFULLY_CHANGED);
                        }
                    } else {
                        guiController.errorMessage(EMPTY_TITLE);
                    }

                } else {
                    if (view.getEditPanel().getRepTaskPanel().getTitle().trim().length() > 0) {
                        Date start = view.getEditPanel().getRepTaskPanel().getStartTime();
                        Date end = view.getEditPanel().getRepTaskPanel().getEndTime();
                        if (start.compareTo(end) <= 0) {
                            taskWithNewInfo = new Task(view.getEditPanel().getRepTaskPanel().getTitle(),
                                    start,
                                    end,
                                    view.getEditPanel().getRepTaskPanel().getInterval());
                            taskWithNewInfo.setActive(view.getEditPanel().getRepTaskPanel().getActive());
                            if (model.editTask(taskForComparison, taskWithNewInfo)) {
                                guiController.successfullyMessage(SUCCESSFULLY_CHANGED);
                            }
                        } else {
                            guiController.errorMessage(START_DATE_MUST_BE_BEFORE_END_DATE);
                        }
                    } else {
                        guiController.errorMessage(EMPTY_TITLE);
                    }

                }
            }
        });
    }

    /**
     * create new task listener for edit button
     *
     * @param edit button
     */
    private void editTask(JButton edit) {
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row;
                String focus = guiController.getFocusTabled();
                switch (focus) {
                    case "Rep": {
                        try {
                            row = view.getStartPanel().getTableRep().getSelectedRow();
                            if (row != -1) {
                                taskForComparison = new Task(view.getStartPanel().getTableRep().getModel()
                                        .getValueAt(row, COLUMN_INDEX_ZERO).toString(),
                                        DATE_FORMAT.parse(view.getStartPanel().getTableRep().getModel()
                                                .getValueAt(row, COLUMN_INDEX_ONE).toString()),
                                        DATE_FORMAT.parse(view.getStartPanel().getTableRep().getModel()
                                                .getValueAt(row, COLUMN_INDEX_TWO).toString()),
                                        Integer.parseInt(view.getStartPanel().getTableRep().getModel()
                                                .getValueAt(row, COLUMN_INDEX_THREE).toString()));
                                taskForComparison.setActive((boolean) view.getStartPanel().getTableRep()
                                        .getModel().getValueAt(row, COLUMN_INDEX_FOUR));

                                view.getEditPanel().getRepTaskPanel()
                                        .setTitle(taskForComparison.getTitle());
                                view.getEditPanel().getRepTaskPanel()
                                        .setStartTime(taskForComparison.getStartTime());
                                view.getEditPanel().getRepTaskPanel()
                                        .setEndTime(taskForComparison.getEndTime());
                                view.getEditPanel().getRepTaskPanel()
                                        .setInterval(taskForComparison.getRepeatInterval());
                                view.getEditPanel().getRepTaskPanel()
                                        .setActive(taskForComparison.isActive());
                                view.getCardLayout().show(view.getContainer(), "Edit task");
                                view.getEditPanel().getEditNonRepTask().setVisible(false);
                                view.getEditPanel().getEditRepTask().setVisible(true);
                                break;
                            } else {
                                guiController.errorMessage(EMPTY_ROW_FOR_EDIT);
                            }

                        } catch (ParseException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    }
                    case "Non": {
                        try {
                            row = view.getStartPanel().getTableNonRep().getSelectedRow();
                            if (row != -1) {
                                taskForComparison = new Task(view.getStartPanel().getTableNonRep().getModel()
                                        .getValueAt(row, 0).toString(),
                                        DATE_FORMAT.parse(view.getStartPanel().getTableNonRep().getModel()
                                                .getValueAt(row, 1).toString()));
                                taskForComparison.setActive((boolean) view.getStartPanel()
                                        .getTableNonRep().getModel().getValueAt(row, 2));

                                view.getEditPanel().getNonRepTaskPanel()
                                        .setTitle(taskForComparison.getTitle());
                                view.getEditPanel().getNonRepTaskPanel()
                                        .setDate(taskForComparison.getTime());
                                view.getEditPanel().getNonRepTaskPanel()
                                        .setActive(taskForComparison.isActive());
                                view.getCardLayout().show(view.getContainer(), "Edit task");
                                view.getEditPanel().getEditRepTask().setVisible(false);
                                view.getEditPanel().getEditNonRepTask().setVisible(true);
                                break;
                            } else {
                                guiController.errorMessage(EMPTY_ROW_FOR_EDIT);
                            }
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    }
                    default:
                        guiController.errorMessage(EMPTY_ROW_FOR_EDIT);
                        break;
                }
            }
        });
    }

    /**
     * delete MILLISECOND and SECOND from date
     *
     * @param date for editing
     * @return edited date
     */
    private Date deleteSeconds(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        date = calendar.getTime();
        return date;
    }

    /**
     * print file list in task list
     */
    private void printFileList() {
        view.getStartPanel().getTaskList().removeAllItems();
        if (model.getFileListName().size() > 0) {
            for (String name : model.getFileListName()) {
                view.getStartPanel().getTaskList().addItem(name);
            }
        }
    }

    /**
     * create new task listener for removeList button
     *
     * @param removeList button
     */
    private void removeTaskListListener(JButton removeList) {
        removeList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = (String) view.getStartPanel().getTaskList().getSelectedItem();
                if (name != null) {
                    int delete = guiController.deleteMessage(REMOVE_TASK_LIST);
                    if (delete == 0) {
                        if (model.removeFileFromList(name + FILE_EXTENSION)) {
                            model.removeFileNameFromList(name);
                            guiController.successfullyMessage(TASK_LIST_WAS_DELETED);
                            view.getStartPanel().getTaskList().removeItem(name);
                        } else {
                            guiController.errorMessage(TASK_LIST_WAS_NOT_DELETED);
                        }
                    }

                } else {
                    guiController.infoMessage(LIST_EMPTY);
                }
            }
        });
    }

    /**
     * create new task listener for addList button
     *
     * @param addList button
     */
    private void addTaskListListener(JButton addList) {
        addList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = guiController.inputDialog(ADD_NEW_TASK_LIST);
                if (name == null || name.isEmpty()) {
                    guiController.errorMessage(FILE_NAME_CANT_BE_EMPTY);
                } else {
                    if (name.matches("^[a-zA-Z0-9\\s]*$")) {
                        if (model.getFileListName().contains(name)) {
                            guiController.errorMessage(THIS_FILE_NAME_BE_USED);
                        } else {
                            if (model.createNewFile(name)) {
                                view.getStartPanel().getTaskList().addItem(name);
                                guiController.successfullyMessage(TASK_LIST_WAS_CREATED);
                                guiController.setButtonEnabled(true);
                            } else {
                                guiController.errorMessage(TASK_LIST_WAS_NOT_CREATED);
                            }
                        }
                    } else {
                        guiController.errorMessage(NAME_MUST_CONSIST_ONLY_OF_NUMBERS_AND_LETTERS);
                    }
                }
            }
        });
    }

    /**
     * print task list in file list
     *
     * @param fileList list for task list
     */
    private void printTaskList(JComboBox<String> fileList) {
        fileList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getStartPanel().getTaskList().getSelectedItem() != null) {
                    model.setFile(new File(view.getStartPanel()
                            .getTaskList().getSelectedItem() + ".bin"));
                    model.loadFromFile();
                    guiController.writeTasks(model.getTaskList());
                } else {
                    guiController.clearTable();
                    guiController.setButtonEnabled(false);
                }
            }
        });
    }

    public static void main(String[] args) {
        View view = new View();
        Model model = new Model();
        GUIController guiController = new GUIController(view, model);
        new MainController(guiController);
        view.setVisible(true);
    }

}
