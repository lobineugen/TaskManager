package com.lobin.eugene.Controller;

import java.text.SimpleDateFormat;

/**
 * A set of constants for the task manager
 *
 * @author Eugene Lobin
 * @version 1.0 02 Jan 2018
 */
public interface TaskConstant {
    String SUCCESSFULLY_ADDED = "Task successfully added!";
    String EMPTY_TITLE = "Title can not be empty!";
    String REMOVING_WAS_SUCCESSFUL = "Removing was successful!";
    String EMPTY_ROW_FOR_REMOVE = "To start removing, select row with task!";
    String SUCCESSFULLY_CHANGED = "Successfully changed task!";
    String EMPTY_ROW_FOR_EDIT = "To start editing, select row with task!";
    String INFO_CALENDAR_EMPTY = "Calendar of tasks for this date is empty!"
            + " Enter a new date and try again.";
    String START_DATE_MUST_BE_BEFORE_END_DATE = "Start date must" +
            " be before end date!!!";
    String REMOVE_TASK_LIST = "Remove task list";
    String TASK_LIST_WAS_DELETED = "Task list was deleted";
    String TASK_LIST_WAS_NOT_DELETED = "Task list was not deleted";
    String LIST_EMPTY = "List empty";
    String ADD_NEW_TASK_LIST = "Add new task list";
    String FILE_NAME_CANT_BE_EMPTY = "File name cant be empty!";
    String THIS_FILE_NAME_BE_USED = "A file with this name has already been created!";
    String TASK_LIST_WAS_CREATED = "Task list was created";
    String TASK_LIST_WAS_NOT_CREATED = "Task list wast not created";
    String NAME_MUST_CONSIST_ONLY_OF_NUMBERS_AND_LETTERS = "The name must" +
            " consist only of numbers and letters";
    String FILE_EXTENSION = ".bin";
    String FILE_MUST_BE_BIN_FORMAT = "File must be .bin format!";
    String TASK_TITLE = "Task Manager";
    String INCORRECT_CONTENTS = "The contents of the file are incorrect";
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

    int TITLE_WIDTH = 80;
    int TITLE_HEIGHT = 20;

    SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");

}
