package com.lobin.eugene.model;

import com.lobin.eugene.Controller.TaskConstant;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;
import java.util.SortedMap;

/**
 * Class for use libs task
 *
 * @author Eugene Lobin
 * @version 1.1 29 Nov 2017
 */

public class Model implements TaskConstant {
    private static final Logger LOG = Logger.getLogger(Model.class.getName());
    private ArrayTaskList<Task> taskList = new ArrayTaskList<>();
    private File dir = new File("data");
    private File[] fileList;
    private ArrayList<String> fileListName = new ArrayList<>();
    private File file;


    public Model() {
        System.setProperty("user.dir", "data");
        if (dir.mkdir()) {
            LOG.info("Folder was created");
        } else {
            LOG.info("Folder not wast created");
        }
        fileList = dir.listFiles();
    }

    /**
     * load tasks into task list form file
     *
     * @throws IOException if stream to file cannot be written to or closed.
     */
    public void loadFromFile() throws StringIndexOutOfBoundsException, IOException {
        taskList.clear();
        TaskIO.readText(taskList, file);
    }

    /**
     * write task list into file
     */
    private void writeInFile() {
        TaskIO.writeText(taskList, file);
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
    public boolean editTask(Task taskForCopm, Task taskWithNewInfo) {
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
                        LOG.error("IllegalArgumentException", ex);
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
     * @param fileName removed file name
     * @return true if file be deleted
     */
    public boolean removeFileFromList(String fileName) {
        File file = new File(dir + File.separator + fileName);
        boolean removed = file.delete();
        if (removed) {
            LOG.info("File " + fileName + " was removed");
        } else {
            LOG.info("File " + fileName + " was not removed");
        }
        return removed;
    }

    /**
     * @param fileName removed file name from file list name
     */
    public void removeFileNameFromList(String fileName) {
        fileListName.remove(fileName);
    }

    /**
     * @param fileName new file name
     * @return true if file be created
     */
    public boolean createNewFile(String fileName) {
        LOG.info("New file was created: " + fileName);
        try {
            if (new File(dir + File.separator + fileName + FILE_EXTENSION).createNewFile()) {
                fileListName.add(fileName);
                return true;
            }
        } catch (IOException e) {
            LOG.error("IOException", e);
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
