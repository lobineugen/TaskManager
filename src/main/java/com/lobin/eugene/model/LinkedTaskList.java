package com.lobin.eugene.model;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Class for create LinkedList with task.
 *
 * @version 1.0 17 Nov 2017
 * @author Eugene Lobin
 */

public class LinkedTaskList extends TaskList implements Cloneable {
    private Node first;
    private Node last;
    private int size = 0;


    /**
     * add
     * @param task to the task list
     */
    @Override
    public void add(Task task) {
        if (task == null) {
            try {
                throw new NullPointerException("Task can't be a null!"
                        + " Task not added");
            } catch (NullPointerException e) {
                System.out.println(e.getMessage());
            }
        } else {
            Node node = new Node(null, task, null);
            if (size() == 0) {
                first = node;
            } else {
                last.next = node;
                node.previous = last;
            }
            last = node;
            size++;
        }
    }

    /**
     * add task to the task list before non-null node
     * @param e to the task list
     */
    private void addBefore(Task e, Node temp) {
        Node previous = temp.previous;
        Node node = new Node(previous, e, temp);
        temp.previous = node;
        if (previous == null) {
            first = node;
        } else {
            previous.next = node;
        }
        size++;
    }

    /**
     * @return arrayList without elements task
     * @param task (delete task from task list)
     */
    @Override
    public boolean remove(Task task) {
        Node previous = null;
        Node current = first;
        while (current != null) {
            if (current.value.equals(task)) {
                if (previous != null) {
                    previous.next = current.next;
                    if (current.next == null) {

                        last = previous;
                    } else {
                        current.next.previous = previous;
                    }
                    size--;
                } else {
                    if (size() != 0) {
                        first = first.next;
                        size--;
                        if (size == 0) {
                            last = null;
                        } else {
                            first.previous = null;
                        }
                    }
                }
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    /**
     * @return size task list
     */
    @Override
    public int size() {
        return size;
    }

    private class Node {
        Node next;
        Node previous;
        Task value;

        Node(Node previous, Task value, Node next) {
            this.value = value;
            this.previous = previous;
            this.next = next;
        }
    }

    /**
     * @return task at a given index
     * @param index (return task from task list with index)
     */
    @Override
    public Task getTask(int index) {
        Task task = null;
        if (index >= 0 && index < size()) {
            task = getByIndex(index).value;
        }
        return task;
    }

    private Node getByIndex(int index) {
        Node node = null;
        if (index >= 0 && index < size()) {
            node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        }
        return node;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LinkedTaskList)) return false;

        LinkedTaskList list = (LinkedTaskList) o;

        if (list.size() != 0 && this.size() != 0) {
            Node that = this.first;
            Node node = ((LinkedTaskList) o).first;
            while (that != null && node != null) {
                if (!that.value.equals(node.value)) {
                    return false;
                }
                that = that.next;
                node = node.next;
            }
            return true;
        } else {
            return true;
        }
    }

    @Override
    public int hashCode() {
        final int hashNumber = 31;
        int result = hashNumber;
        if (first != null) {
            Node temp = first;
            result = temp.value != null ? temp.value.hashCode() : 0;
            while (temp.next != null) {
                if(temp.next.value != null){
                    result = temp.value.hashCode();
                } else {
                    result = 0;
                }
            }
        }
        result = hashNumber * result + size;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        if (size() > 0) {
            Node current = first;
            result.append(current.value);
            while (current.next != null) {
                current = current.next;
                result.append(current.value).append(", ");
            }
        }
        return "[" + result + "]";
    }

    @Override
    public LinkedTaskList clone() {
        try {
            return (LinkedTaskList) super.clone();
        } catch (CloneNotSupportedException ex) {
            throw new InternalError();
        }
    }


    @Override
    public Iterator iterator() {
        return iterator(0);
    }

    /**
     * Returns a list-iterator of the elements in this list
     * @param index index of the first element to be returned from the
     * list-iterator (by a call to {@code next})
     * @return a ListIterator of the elements in this list (in proper
     * sequence), starting at the specified position in the list
     */
    public ListIterator iterator(int index) {
        if (!(index >= 0 && index <= size)) {
            throw new IndexOutOfBoundsException();
        }
        return new ListItr(index);
    }


    private class ListItr implements ListIterator<Task> {
        private Node lastReturned;
        private Node next;
        private int nextIndex;

        ListItr(int index) {
            if (index == size) {
                next = null;
            } else {
                next = LinkedTaskList.this.getByIndex(index);
            }
            nextIndex = index;
        }

        public boolean hasNext() {
            return nextIndex < size;
        }

        public Task next() {
            if (!hasNext())
                throw new NoSuchElementException();
            lastReturned = next;
            next = next.next;
            nextIndex++;
            return lastReturned.value;
        }

        public void remove() {
            if (lastReturned == null)
                throw new IllegalStateException();
            Node lastNext = lastReturned.next;
            if (next == lastReturned) {
                next = lastNext;
            } else {
                nextIndex--;
            }
            LinkedTaskList.this.remove(lastReturned.value);
            lastReturned = null;
        }

        public boolean hasPrevious() {
            return nextIndex > 0;
        }

        public Task previous() {
            if (!hasPrevious())
                throw new NoSuchElementException();
            if (next == null) {
                lastReturned = last;
                next = last;
            } else {
                next = next.previous;
                lastReturned = next;
            }
            nextIndex--;
            return lastReturned.value;
        }

        public int nextIndex() {
            return nextIndex;
        }

        public int previousIndex() {
            return nextIndex - 1;
        }

        public void set(Task e) {
            if (lastReturned == null)
                throw new IllegalStateException();
            lastReturned.value = e;
        }

        public void add(Task e) {
            lastReturned = null;
            if (next == null) {
                LinkedTaskList.this.add(e);
            } else {
                addBefore(e, next);
            }
            nextIndex++;
        }
    }

    /**
     * Returns a list-iterator of the elements in this list use
     * ListItr.previous
     * @return new DescendingItr;
     */
    public Iterator descendingIterator() {
        return new DescendingItr();
    }

    private class DescendingItr implements Iterator {
        private ListItr itr = new ListItr(size());
        public boolean hasNext() {
            return itr.hasPrevious();
        }
        public Task next() {
            return itr.previous();
        }
        public void remove() {
            itr.remove();
        }
    }
}
