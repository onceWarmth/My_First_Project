package com.example.andy.seg;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by clover on 2016/2/14.
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {

    public static final String CREATE_USER = "create table User (id integer primary key autoincrement, userName text, userPass text, notePad text)";

    public static final String CREATE_SYLLABUS = "create table Syllabus (id integer primary key autoincrement, week text, title text, content text, time text)";

    private Context mContext;

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_USER);
        db.execSQL(CREATE_SYLLABUS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
}
