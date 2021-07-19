package com.byted.camp.todolist.db;

import android.provider.BaseColumns;

public final class TodoContract {

    // TODO 1. 定义创建数据库以及升级的操作
    public static final String CREATE_TABLE = " CREATE TABLE " + TodoNote.TABLE_NAME + "("+TodoNote._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TodoNote.Date +" INTEGER, "+TodoNote.State+" INTEGER, "+ TodoNote.Content + " TEXT, "+TodoNote.Priority+" INTEGER);";
    public static final String addpriority = " ALTER TABLE "+TodoNote.TABLE_NAME+" ADD "+TodoNote.Priority+" INTEGER;";

    public static final String DELETE_ALL = "DELETE FROM " + TodoNote.TABLE_NAME;

    private TodoContract() {

    }

    public static class TodoNote implements BaseColumns {
        // TODO 2.此处定义表名以及列明
        public static final String TABLE_NAME = "ToDo";
        public static final String Date = "data";
        public static final String State = "state";
        public static final String Content = "content";
        public  static final String Priority = "priority";
    }

}
