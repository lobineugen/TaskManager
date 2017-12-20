package com.lobin.eugene.model;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Class for create arrayList with task.
 *
 * @version 1.0 11 Oct 2017
 * @author Eugene Lobin
 */

public class ArrayTaskList extends TaskList implements Cloneable {
    private static final int SIZE = 10;
    private Task[] arrayTask = new Task[SIZE];
    private int count = 0;



    /**
     * add
     * @param task to the task list
     */
    @Override
    public void add(Task task) {
        if (task == null) {
            try {
                throw new NullPointerException("Task can't be a null! "
                        + "Task not added");
            } catch (NullPointerException e) {
                System.out.println(e.getMessage());
            }
        } else {
            if (count < arrayTask.length) {
                arrayTask[count++]  = task;
            } else {
                int size = arrayTask.length + arrayTask.length / 2;
                arrayTask = Arrays.copyOf(arrayTask, size);
                arrayTask[count++] = task;
            }
        }
    }

    /**
     * @return arrayList without elements task
     * @param task (delete task from task list)
     */
    @Override
    public boolean remove(Task task) {
        int size = count;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(arrayTask[i], task)) {
                if (i != size - 1) {
                    System.arraycopy(arrayTask , i + 1 , arrayTask , i ,
                            size - i - 1);
                }
                arrayTask[count - 1] = null;
                count--;
                return true;
            }
        }
        return false;
    }

    /**
     * @return size task list
     */
    @Override
    public int size() {
        return count;
    }

    /**
     * @return task at a given index
     * @param index (return task from task list with index)
     */
    @Override
    public Task getTask(int index) {
        return arrayTask[index];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArrayTaskList)) return false;

        ArrayTaskList that = (ArrayTaskList) o;

        if (count != that.count) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(arrayTask, that.arrayTask);
    }

    @Override
    public int hashCode() {
        final int hashNumber = 31;
        int result = Arrays.hashCode(arrayTask);
        result = hashNumber * result + count;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        if (size() > 0) {
            for (int i = 0; i < size(); i++) {
                result.append(this.getTask(i));
            }
        }
        return "[" + result + "]";
    }

    @Override
    public ArrayTaskList clone() {
        try {
            ArrayTaskList array = (ArrayTaskList) super.clone();
            array.arrayTask = arrayTask.clone();
            return array;
        } catch (CloneNotSupportedException ex) {
            throw new InternalError();
        }
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     * The returned iterator is fail-fast.
     * @return an iterator over the elements in this list in proper sequence
     */
    public Iterator iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator {
        int cursor;
        int lastRet = -1;

        public boolean hasNext() {
            return cursor != size();
        }

        public Object next() {
            lastRet = cursor;
            if (cursor >= size()) {
                throw new NoSuchElementException();
            }
            cursor++;
            return arrayTask[lastRet];
        }

        public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();
            try {
                ArrayTaskList.this.remove(arrayTask[lastRet]);
                cursor = lastRet;
                lastRet = -1;
            } catch (IndexOutOfBoundsException ex) {
                ex.printStackTrace();
            }
        }
    }
}
