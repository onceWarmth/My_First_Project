package com.example.andy.seg;

/**
 * Created by Andy on 2016/3/28.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.sql.SQLException;
import java.util.Calendar;

/**
 * Created by clover on 2016/2/17.
 */
public class DiaryDbAdapter {
    public static final String KEY_TITLE = "title";
    public static final String KEY_BODY = "body";
    public static final String KEY_ROWID = "_id";
    public static final String KEY_CREATED = "created";

    private DatabaseHelper databaseHelper;

    private Context context;

    private SQLiteDatabase sqliteDatabase;

    public DiaryDbAdapter(Context context) {
        this.context = context;
    }

    /**
     * Open the Database
     */
    public void open(){
        databaseHelper = new DatabaseHelper(context);

        try
        {
            sqliteDatabase = databaseHelper.getWritableDatabase();
        }catch(SQLiteException e){
            sqliteDatabase = databaseHelper.getReadableDatabase();
        }
    }

    /**
     * Close the Database
     */
    public void close()
    {
        sqliteDatabase.close();
    }

    /**
     * Insert the Data
     * @param title
     * @param body
     * @return
     */
    public long createDiary(String title,String body){

        ContentValues content = new ContentValues();
        content.put(KEY_TITLE, title);
        content.put(KEY_BODY, body);
        Calendar calendar = Calendar.getInstance();
        String created = calendar.get(Calendar.YEAR) + "/"
                + calendar.get(Calendar.MONTH) + "/"
                + calendar.get(Calendar.DAY_OF_MONTH) + " "
                + calendar.get(Calendar.HOUR_OF_DAY) + ":"
                + calendar.get(Calendar.MINUTE);
        content.put(KEY_CREATED, created);

        return sqliteDatabase.insert(databaseHelper.DATABSE_TABLE, null, content);
    }

    /**
     * Delete the record
     * @param rowId
     * @return
     */
    public boolean deleteDiary(long rowId){

        String whereString = KEY_ROWID + "=" + rowId;
        return sqliteDatabase.delete(databaseHelper.DATABSE_TABLE, whereString, null)>0;
    }

    /**
     * Get all Records
     * @return
     */
    public Cursor getAllNotes()
    {
        String[] searchResult =  {KEY_ROWID, KEY_TITLE,KEY_BODY, KEY_CREATED};
        return sqliteDatabase.query(databaseHelper.DATABSE_TABLE, searchResult, null, null, null, null, null);
    }

    /**
     * Get the record by condition
     * @param rowId
     * @return
     * @throws SQLException
     */
    public Cursor getDiary(long rowId) throws SQLException {

        String[] searchResult =  {KEY_ROWID, KEY_TITLE,KEY_BODY, KEY_CREATED};
        String whereString = KEY_ROWID + "=" + rowId;

        Cursor mCursor = sqliteDatabase.query(true, databaseHelper.DATABSE_TABLE, searchResult, whereString, null, null, null, null, null);
        if(mCursor!=null){
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public boolean updateDiary(long rowId ,String title,String body){

        ContentValues values = new ContentValues();
        values.put(KEY_ROWID, title);
        values.put(KEY_BODY,body);

        Calendar calendar = Calendar.getInstance();
        String created = calendar.get(Calendar.YEAR) + "/"
                + calendar.get(Calendar.MONTH) + "/"
                + calendar.get(Calendar.DAY_OF_MONTH) + " "
                + calendar.get(Calendar.HOUR_OF_DAY) + ":"
                + calendar.get(Calendar.MINUTE);
        values.put(KEY_CREATED, created);
        String whereString = KEY_ROWID + "=" + rowId;

        return sqliteDatabase.update(databaseHelper.DATABSE_TABLE, values, whereString, null)>0;
    }

    public void xinjianDiary(String title,String body){

        Calendar calendar = Calendar.getInstance();
        String created = calendar.get(Calendar.YEAR) + "/"
                + calendar.get(Calendar.MONTH) + "/"
                + calendar.get(Calendar.DAY_OF_MONTH) + " "
                + calendar.get(Calendar.HOUR_OF_DAY) + ":"
                + calendar.get(Calendar.MINUTE);

        String insertSQL = "INSERT INTO " + databaseHelper.DATABSE_TABLE
                +"(" + KEY_ROWID +"," + KEY_TITLE+"," + KEY_BODY +"," + KEY_CREATED + ")"
                + " values (?,?,?,?)" ;
        Object[] args = {null,title,body,created};
        sqliteDatabase.execSQL(insertSQL, args);
    }

    public void bianjiDiary(long rowId ,String title,String body){
        Calendar calendar = Calendar.getInstance();
        String created = calendar.get(Calendar.YEAR) + "/"
                + calendar.get(Calendar.MONTH) + "/"
                + calendar.get(Calendar.DAY_OF_MONTH) + " "
                + calendar.get(Calendar.HOUR_OF_DAY) + ":"
                + calendar.get(Calendar.MINUTE);

        String updateSQL = "update " + databaseHelper.DATABSE_TABLE
                +" set " + KEY_TITLE+"=? ," + KEY_BODY +"=? ," + KEY_CREATED + "=? "
                + " where " + KEY_ROWID + "= ?" ;
        Object[] args = {title,body,created,rowId};
        sqliteDatabase.execSQL(updateSQL, args);
    }

    public void shanchuDiary(long rowId ){
        String deleteSQL = "delete from "+ databaseHelper.DATABSE_TABLE +" where " + KEY_ROWID + "= ?" ;
        Object[] args = {rowId};
        sqliteDatabase.execSQL(deleteSQL, args);
    }

    public Cursor qudeAllNotes()
    {
        String searchSQL = "select _id , title , body ,created from "+ databaseHelper.DATABSE_TABLE;
        return sqliteDatabase.rawQuery(searchSQL, null);
    }
}