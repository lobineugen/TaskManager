package com.lobin.eugene.model;

import java.io.File;
import java.util.Date;

public class Model {
    private ArrayTaskList taskList = new ArrayTaskList();
    private File file = new File("data.bin");

    public void loadFromFile() {
        TaskIO.readBinary(taskList, file);
//        for(Object q :taskList){
//            System.out.println(q.toString());
//        }
    }

    public void writeInFile() {
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
}
