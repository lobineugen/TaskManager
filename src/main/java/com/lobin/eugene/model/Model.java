package com.lobin.eugene.model;


//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;
import java.util.SortedMap;

public class Model {
//    private static final Logger log = LoggerFactory.getLogger(Model.class);
    private ArrayTaskList<Task> taskList = new ArrayTaskList<>();
    private File dir = new File(".");
    private File[] fileList = dir.listFiles();
    private ArrayList<String> fileListName = new ArrayList<>();
    private File file;

    /**
     * load tasks into task list form file
     */
    public void loadFromFile() {
        taskList.clear();
        TaskIO.readBinary(taskList, file);
    }

    /**
     * write task list into file
     */
    private void writeInFile() {
        TaskIO.writeBinary(taskList, file);
    }

    /**
     * create new non-repetitive task
     *
     * @param title  task
     * @param date   task
     * @param active task
     */
    public void addTask(String title, Date date, Boolean active) {
        Task task = new Task(title, date);
        task.setActive(active);
        taskList.add(task);
        writeInFile();
    }

    /**
     * create new repetitive task
     *
     * @param title    task
     * @param start    task
     * @param end      task
     * @param interval task
     * @param active   task
     */
    public void addTask(String title, Date start, Date end,
                        int interval, Boolean active) {
        Task task = new Task(title, start, end, interval);
        task.setActive(active);
        taskList.add(task);
        writeInFile();
    }

    /**
     * @return task list
     */
    public ArrayTaskList getTaskList() {
        return taskList;
    }

    /**
     * @param taskForCopm     task with old info for edit
     * @param taskWithNewInfo task with new info for edit
     * @return true if  task is successfully edited
     */
    public boolean editTask(Task taskForCopm,
                            Task taskWithNewInfo) {
        for (int i = 0; i < taskList.size(); i++) {
            if (taskForCopm.equals(taskList.getTask(i))) {
                taskList.getTask(i).setTitle(taskWithNewInfo.getTitle());
                taskList.getTask(i).setActive(taskWithNewInfo.isActive());
                if (taskForCopm.getRepeatInterval() > 0) {
                    try {
                        taskList
                                .getTask(i).setTime(taskWithNewInfo.getStartTime(),
                                taskWithNewInfo.getEndTime(),
                                taskWithNewInfo.getRepeatInterval());
                    } catch (IllegalArgumentException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    taskList.getTask(i).setTime(taskWithNewInfo.getTime());
                }
                writeInFile();
                return true;
            }
        }
        return false;
    }

    /**
     * remove
     *
     * @param task from task list
     * @return true if task be deleted from task list
     */
    public boolean removeTask(Task task) {
        if (taskList.remove(task)) {
            writeInFile();
            return true;
        }
        return false;
    }

    /**
     * @param start time for calendar
     * @param end   time for calendar
     * @return tasks calendar
     */
    public SortedMap<Date, Set<Task>> getCalendar(Date start, Date end) {
        return Tasks.calendar(taskList, start, end);
    }

    /**
     * @return task list
     */
    public ArrayList<String> getFileListName() {
        return fileListName;
    }

    /**
     * @param file set file
     */
    public void setFile(File file) {
        this.file = file;
    }

    /**
     * @param filename removed file name
     * @return true if file be deleted
     */
    public boolean removeFileFromList(String filename) {
        File file = new File(filename);
        return file.delete();
    }

    /**
     * @param filename new file name
     * @return true if file be created
     */
    public boolean createNewFile(String filename) {
//        log.debug("Start processing");
//        if (log.isDebugEnabled()) {
//            log.debug("File name: "+ filename);
//        }
        try {
            if (new File(filename + ".bin").createNewFile()) {
                fileListName.add(filename);
//                log.debug("done");
                return true;
            }
        } catch (IOException e) {
//            log.error("IOException", e);
        }
        return false;

    }

    /**
     * @return file list
     */
    public File[] getFileList() {
        return fileList;
    }

    /**
     * @param fileListName set file list name
     */
    public void setFileListName(ArrayList<String> fileListName) {
        this.fileListName = fileListName;
    }
}
