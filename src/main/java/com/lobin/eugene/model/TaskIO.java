package com.lobin.eugene.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class for binary input/output task and text input/output
 * task in threads and files
 *
 * @author Eugene Lobin
 * @version 1.0 9 Dec 2017
 */

public class TaskIO {
    private static final int SECINDAY = 86400;
    private static final int SECINHOUR = 3600;
    private static final int SECINMIN = 60;
    private static final int[] TIME = {SECINDAY, SECINHOUR, SECINMIN, 1};

    /**
     * @param tasks - task list with tasks
     * @param out   - output thread
     * @throw IOException if stream to out cannot be written to or closed.
     * Write tasks in output thread in binary format
     */
    public static void write(TaskList tasks, OutputStream out) {
        try (DataOutputStream data = new DataOutputStream(out)) {
            data.writeInt(tasks.size());
            for (Object temp : tasks) {
                Task task = (Task) temp;
                data.writeInt(task.getTitle().length());
                data.writeChars(task.getTitle());
                data.writeBoolean(task.isActive());
                data.writeInt(task.getRepeatInterval());
                if (task.getRepeatInterval() > 0) {
                    data.writeLong(task.getStartTime().getTime());
                    data.writeLong(task.getEndTime().getTime());
                } else {
                    data.writeLong(task.getTime().getTime());
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    /**
     * @param tasks - task list with tasks
     * @param in    - input thread
     * @throw IOException if stream to in cannot be read to or closed.
     * Read tasks from input thread in binary format
     */
    public static void read(TaskList tasks, InputStream in) throws IOException {
        try (DataInputStream data = new DataInputStream(in)) {
            int size = data.readInt();
            for (int i = 0; i < size; i++) {
                StringBuilder title = new StringBuilder();
                int stringLength = data.readInt();
                for (int j = 0; j < stringLength; j++) {
                    title.append(data.readChar());
                }
                boolean active = data.readBoolean();
                int interval = data.readInt();
                Task task;
                if (interval > 0) {
                    long start = data.readLong();
                    long end = data.readLong();
                    task = new Task(title.toString(), new Date(start),
                            new Date(end), interval);
                } else {
                    long time = data.readLong();
                    task = new Task(title.toString(), new Date(time));
                }
                if (active) {
                    task.setActive(true);
                }
                tasks.add(task);
            }
        }
    }

    /**
     * @param tasks - task list with tasks
     * @param file  - file for writing
     * @throw IOException if stream to file cannot be written to or closed.
     * Write task in file in binary format
     */
    public static void writeBinary(TaskList tasks, File file) {
        try (DataOutputStream data = new DataOutputStream(
                new FileOutputStream(file))) {
            write(tasks, data);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @param tasks - task list with tasks
     * @param file  - file for reading
     * @throw IOException if stream to file cannot be read to or closed.
     * Write task in file in binary format
     */
    public static void readBinary(TaskList tasks, File file) throws IOException {
        try (DataInputStream data = new DataInputStream(
                new FileInputStream(file))) {
            if (file.length() != 0) {
                read(tasks, data);
            }
        }
    }

    /**
     * @param tasks - task list with tasks
     * @param out   - output thread
     * @throw IOException if stream to out cannot be written to or closed.
     * Write tasks in output thread in text format
     */
    public static void write(TaskList tasks, Writer out) {
        try (BufferedWriter bf = new BufferedWriter(out)) {
            SimpleDateFormat dateFormat =
                    new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss.SSS]");
            int i = 0;
            String end;
            for (Object task : tasks) {
                Task temp = (Task) task;
                if (i == tasks.size() - 1) {
                    end = ".";
                } else {
                    end = ";";
                }
                StringBuilder result = new StringBuilder();
                StringBuilder title = new StringBuilder(temp.getTitle());
                for (int j = 0; j < temp.getTitle().length(); j++) {
                    if (title.charAt(j) == '\"') {
                        title.insert(j++, "\"");
                    }
                }
                String active = "";
                if (!temp.isActive()) {
                    active = " inactive";
                }
                if (temp.getRepeatInterval() > 0) {
                    result.append("\"").append(title).append("\" from ")
                            .append(dateFormat.format(temp.getStartTime()))
                            .append(" to ")
                            .append(dateFormat.format(temp.getEndTime()))
                            .append(" every ")
                            .append(setRepeatInterval(temp.getRepeatInterval()))
                            .append(active).append(end);
                } else {
                    result.append("\"").append(temp.getTitle()).append("\" at ")
                            .append(dateFormat.format(temp.getTime()))
                            .append(active).append(end);
                }
                bf.write(result.toString());
                if (i != tasks.size() - 1) {
                    bf.newLine();
                }
                i++;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static String setRepeatInterval(int temp) {
        int days = 0;
        int hours = 0;
        int minute = 0;
        int seconds = 0;
        String dN = " day ";
        String hN = " hour ";
        String mN = " minute ";
        String sN = " second";
        if (temp >= SECINDAY) {
            days = (temp - temp % SECINDAY) / SECINDAY;
            temp -= days * SECINDAY;
            if (days > 1) {
                dN = " days ";
            }
        }
        if (temp >= SECINHOUR) {
            hours = (temp - temp % SECINHOUR) / SECINHOUR;
            temp -= hours * SECINHOUR;
            if (hours > 1) {
                hN = " hours ";
            }
        }
        if (temp >= SECINMIN) {
            minute = (temp - temp % SECINMIN) / SECINMIN;
            temp -= minute * SECINMIN;
            if (minute > 1) {
                mN = " minutes ";
            }
        }
        if (temp > 0) {
            seconds = temp;
            if (seconds > 1) {
                sN = " seconds";
            }
        }
        StringBuilder b = new StringBuilder();
        if (days > 0) {
            b.append(days).append(dN).append(hours).append(hN)
                    .append(minute).append(mN).append(seconds).append(sN);
        } else if (hours > 0) {
            b.append(hours).append(hN).append(minute).append(mN)
                    .append(seconds).append(sN);
        } else if (minute > 0) {
            b.append(minute).append(mN).append(seconds).append(sN);
        } else {
            b.append(seconds).append(sN);
        }
        return "[" + b.toString() + "]";
    }

    /**
     * @param tasks - task list with tasks
     * @param in    - input thread
     * @throws ParseException if cannot be parse from string in
     *                        Read tasks from input thread in text format
     * @throw IOException if stream to in cannot be read to or closed.
     */
    public static void read(TaskList tasks, Reader in)
            throws ParseException {
        try (BufferedReader bufferedReader = new BufferedReader(in)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat();
            dateFormat.applyPattern("[yyyy-MM-dd HH:mm:ss.SSS]");
            String s;
            while ((s = bufferedReader.readLine()) != null) {
                String title = s.substring(s.indexOf("\"") + 1,
                        s.lastIndexOf("\""));
                Task task;
                int fBracket = s.indexOf("[");
                int lBracket = s.indexOf("]");
                if (s.contains("every")) {
                    Date start = dateFormat.parse(s.substring(fBracket,
                            lBracket + 1));
                    fBracket = s.indexOf("[", fBracket + 1);
                    lBracket = s.indexOf("]", fBracket);
                    Date end = dateFormat.parse(s.substring(s.indexOf("[",
                            fBracket), s.indexOf("]", lBracket + 2)));
                    int interval = getRepeatInterval(s);
                    task = new Task(title, start, end, interval);
                } else {
                    Date time = dateFormat.parse(s.substring(fBracket,
                            lBracket + 1));
                    task = new Task(title, time);
                }
                boolean active = true;
                if (s.contains("inactive")) {
                    active = false;
                }
                task.setActive(active);
                tasks.add(task);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static int getRepeatInterval(String s) {
        s = s.substring(s.lastIndexOf("["), s.lastIndexOf("]") + 1);
        StringBuilder sb = new StringBuilder();
        int[] array = {-1, -1, -1, -1};
        int[] afterArray = {0, 0, 0, 0};
        int count = 0;
        int i = 0;
        int result = 0;
        while (i < s.length()) {
            if (Character.isDigit(s.charAt(i))) {
                while (Character.isDigit(s.charAt(i))) {
                    sb.append(s.charAt(i));
                    i++;
                    if (i == s.length()) {
                        break;
                    }
                }
                array[count++] = Integer.parseInt(sb.toString());
                sb.delete(0, sb.length());
            } else {
                i++;
            }
        }
        count = array.length - 1;
        for (i = array.length - 1; i >= 0; i--) {
            if (array[i] != -1) {
                afterArray[count] = array[i];
                count--;
            }
        }
        for (i = 0; i < afterArray.length; i++) {
            result += afterArray[i] * TIME[i];
        }
        return result;
    }

    /**
     * @param tasks - task list with tasks
     * @param file  - file for writing
     * @throw IOException if stream to file cannot be written to or closed.
     * <p>
     * Write task in file in binary text
     */
    public static void writeText(TaskList tasks, File file) {
        try (BufferedWriter bf = new BufferedWriter(new FileWriter(file))) {
            write(tasks, bf);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @param tasks - task list with tasks
     * @param file  - file for reading
     * @throw ParseException if cannot be parse from string in int
     * Write task in file in text format
     * @throw IOException if stream to file cannot be read to or closed.
     */
    public static void readText(TaskList tasks, File file) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            read(tasks, br);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }
}
