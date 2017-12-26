package com.lobin.eugene.model;

import java.io.Serializable;

/**
 * Abstract super class for ArrayTaskList and LinkedTaskList
 *
 * @version 1.1 24 Nov 2017
 * @author Eugene Lobin
 */

public abstract class TaskList implements Iterable<Task>, Serializable {
    /**
     * add
     * @param task to the task list
     */
    public abstract void add(Task task);

    /**
     * @return arrayList without elements task
     * @param task (delete task from task list)
     */
    public abstract boolean remove(Task task);

    /**
     * @return size task list
     */
    public abstract int size();

    /**
     * @return task at a given index
     * @param index (return task from task list with index)
     */
    public abstract Task getTask(int index);
}
