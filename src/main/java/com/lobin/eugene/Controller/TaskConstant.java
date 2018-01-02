package com.lobin.eugene.Controller;

import java.text.SimpleDateFormat;

/**
 * A set of constants for the task manager
 *
 * @author Eugene Lobin
 * @version 1.0 02 Jan 2018
 */
public interface TaskConstant {
    String SUCCESSFULLYADDED = "Task successfully added!";
    String EMPTYTITLE = "Title can not be empty!";
    String SUCCESSFULLYREMOVEING = "Removing was successful!";
    String EMPRTYROWFORREMOVE = "To start removing, select row with task!";
    String SUCCESSFULLYCHANGED = "Successfully changed task!";
    String EPRTYROWFOREDIT = "To start editing, select row with task!";
    String INFOCALENDAREMPTY = "Calendar of tasks for this date is empty!"
            + " Enter a new date and try again.";
    String STARTDATEMUSTBEBEFORENDDATE = "Start date must be before end date!!!";
    String REMOVETASKLIST = "Remove task list";
    String TASKLISTWASDELETED = "Task list was deleted";
    String TASKLISTWASNOTDELET = "Task list was not deleted";
    String LISTEMPTY = "List empty";
    String ADDNEWTASKLIST = "Add new task list";
    String FILENAMECANTBEEMPTY = "File name cant be empty!";
    String THISFILENAMEBEUSED = "A file with this name has already been created!";
    String TASKLISTWASCREATED = "Task list was created";
    String TASKLISTWASNOTCREATED = "Task list wast not created";
    String FILENAMEONLYLETTERSANDNUBMERS = "The name must consist only of numbers and letters";
    String FILEEXTENSOPN = ".bin";
    int COLUMN_INDEX_ZERO = 0;
    int COLUMN_INDEX_ONE = 1;
    int COLUMN_INDEX_TWO = 2;
    int COLUMN_INDEX_THREE = 3;
    int COLUMN_INDEX_FOUR = 4;
    int DELETE_CHAR = 4;

    int ROWS = 5;
    int COLUMNS = 5;

    int PIXEL = 10;

    int FRAMESIZE = 500;

    int TITLE_WIDTH = 70;
    int TITLE_HEIGHT = 20;
    SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");

}
