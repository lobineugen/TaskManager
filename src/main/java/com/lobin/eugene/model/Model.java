package com.lobin.eugene.model;

import java.util.Date;

public class Model {

    public Task createTask(String title, Date date, Boolean active){
        Task task = new Task(title, date);
        task.setActive(active);
        return task;
    }

    public Task createTask(String title, Date start, Date end, int interval, Boolean active) {
        Task task = new Task(title, start, end, interval);
        task.setActive(active);
        return task;
    }

}
