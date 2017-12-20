package com.lobin.eugene.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Class for create task.
 *
 * @version 1.0 11 Oct 2017
 * @author Eugene Lobin
 */

public class Task implements Cloneable, Serializable {
    private String title;
    private Date time;
    private Date start;
    private Date end;
    private int interval;
    private boolean active;
    private boolean repeat;

    /**
     * Constructor non-repeated task
     *
     * @param title (required) title task
     * @param time (required) time task
     */
    public Task(String title, Date time) {
        this.time = time;
        this.title = title;
        this.active = false;
        this.repeat = false;
    }

    /**
     * Constructor repeated task
     *
     * @param title (required) title task
     * @param start (required) start time task
     * @param end (required) end time task
     * @param interval (required)  recurrence interval time task
     */
    public Task(String title, Date start, Date end, int interval) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.active = false;
        this.repeat = true;
        this.interval = interval;
    }

    /**
     * @return title for task
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title (for task)
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return active for task
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @param active (for task)
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * @return time for task
     */
    public Date getTime() {
        if (!repeat) {
            return time;
        } else {
            return start;
        }
    }

    /**
     * @param time (if task is repeated then it becomes non-repeatable)
     */
    public void setTime(Date time) {
        if (repeat) {
            repeat = false;
            this.time = time;
            start = null;
            end = null;
            interval = 0;
        } else {
            this.time = time;
            start = null;
            end = null;
            interval = 0;
        }
    }

    /**
     * @return start time (if the task is non-repeatable, it returns time)
     */
    public Date getStartTime() {
        if (repeat) {
            return start;
        } else {
            return time;
        }
    }

    /**
     * @return end time (if the task is non-repeatable, it returns time)
     */
    public Date getEndTime() {
        if (repeat) {
            return end;
        } else {
            return time;
        }
    }

    /**
     * @return repeat interval (if the task is non-repeatable, it returns 0)
     */
    public int getRepeatInterval() {
        if (repeat) {
            return interval;
        } else {
            return 0;
        }
    }

    /**
     * if the task is non-repeatable, it becomes repeated with new parameters
     * @param start (start time)
     * @param end ( end time)
     * @param interval ( recurrence interval)
     * @throws TimeException if start < 0, end < 0 or interval <= 0
     * @exception TimeException if start < 0 ane
     */
    public void setTime(Date start, Date end, int interval)
            throws TimeException {
        if (interval <= 0) {
            throw  new TimeException("Interval must be > 0: "
                    + this + " - interval " + interval);
        } else {
            if (!repeat) {
                time = null;
                this.start = start;
                this.end = end;
                this.interval = interval;
                repeat = true;
            } else {
                time = null;
                this.start = start;
                this.end = end;
                this.interval = interval;
            }
        }
    }

    /**
     * @return repeat for task
     */
    public boolean isRepeated() {
        return repeat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;

        Task task = (Task) o;
        if (active != task.active) return false;
        if (time != null) {
            if (!time.equals(task.time)) return false;
        } else {
            if (!start.equals(task.start)) return false;
            if (!end.equals(task.end)) return false;
            if (interval != task.interval) return false;
            if (repeat != task.repeat) return false;
        }
        return title.equals(task.title);
    }

    @Override
    public int hashCode() {
        final int hashNumber = 31;
        int result = title.hashCode();
        result = hashNumber * result + (active ? 1 : 0);
        if (time != null) {
            result = hashNumber * result + time.hashCode();
        } else {
            result = hashNumber * result + start.hashCode();
            result = hashNumber * result + end.hashCode();
            result = hashNumber * result + interval;
            result = hashNumber * result + (repeat ? 1 : 0);
        }
        result = hashNumber * result + (active ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Task{"
                + "title = \'" + title + "\'"
                + ", time = " + time
                + ", start = " + start
                + ", end = " + end
                + ", interval = " + interval
                + ", active = " + active
                + ", repeat = " + repeat
                + "};";
    }

    /**
     * a value depending on the @param current (preset time)
     * @return -1, time, start or nextTimeAfter
     *
     */
    public Date nextTimeAfter(Date current) {
        if (this.active && !repeat) {
            if (!current.before(time) || current.equals(time)) {
                return null;
            } else {
                return time;
            }
        } else if (active) {
            if (start.after(end)) {
                return null;
            } else {
                if (current.before(start)) {
                    return start;
                } else {
                    Date nextTimeAfter = start;
                    while (!nextTimeAfter.after(current)) {
                        Calendar next = Calendar.getInstance();
                        next.setTime(nextTimeAfter);
                        next.add(Calendar.SECOND, interval);
                        nextTimeAfter = next.getTime();
                    }
                    if (nextTimeAfter.after(end)) {
                        return null;
                    } else {
                        return nextTimeAfter;
                    }
                }
            }
        } else {
            return null;
        }
    }

    @Override
    public Task clone() {
        try {
            return (Task) super.clone();
        } catch (CloneNotSupportedException ex) {
            throw new InternalError();
        }
    }
}
