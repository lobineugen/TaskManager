package com.lobin.eugene.model;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class Model {
    private ArrayTaskList taskList = new ArrayTaskList();
    private File file = new File("data.bin");

    public void loadFromFile() {
        taskList.clear();
        try {
            file.createNewFile();
            TaskIO.readBinary(taskList, file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    private void writeInFile() {
        TaskIO.writeBinary(taskList, file);
    }

    public void addTask(String title, Date date, Boolean active) {
        Task task = new Task(title, date);
        task.setActive(active);
        taskList.add(task);
        writeInFile();
    }

    public void addTask(String title, Date start, Date end, int interval, Boolean active) {
        Task task = new Task(title, start, end, interval);
        task.setActive(active);
        taskList.add(task);
        writeInFile();
    }

    public ArrayTaskList getTaskList() {
        return taskList;
    }

    public boolean editTask(Task taskForCopm, Task taskWithNewIfno) {
        for (int i = 0; i < taskList.size(); i++) {
            if (taskForCopm.equals(taskList.getTask(i))) {
                taskList.getTask(i).setTitle(taskWithNewIfno.getTitle());
                taskList.getTask(i).setActive(taskWithNewIfno.isActive());
                if (taskForCopm.getRepeatInterval() > 0) {
                    try {
                        taskList.getTask(i).setTime(taskWithNewIfno.getStartTime(), taskWithNewIfno.getEndTime(), taskWithNewIfno.getRepeatInterval());
                    } catch (TimeException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    taskList.getTask(i).setTime(taskWithNewIfno.getTime());
                }
                writeInFile();
                return true;
            }
        }
        return false;
    }

    public boolean removeTask(Task task) {
        if (taskList.remove(task)) {
            writeInFile();
            return true;
        }
        return false;
    }
}
