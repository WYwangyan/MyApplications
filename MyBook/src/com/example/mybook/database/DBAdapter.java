package com.example.mybook.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {  
	
	static int count=0;
	
    static final String KEY_ROWID = "_id";  
    static final String KEY_ID = "id";
    static final String KEY_TITLE = "title";  
    static final String KEY_BODY = "body";  
    static final String TAG = "DBAdapter";  
  
    static final String DATABASE_NAME = "MyDB";  
    static final String DATABASE_TABLE = "diaries";  
    static final int DATABASE_VERSION = 1;  
  
    static final String DATABASE_CREATE =  
        "create table diaries (_id integer primary key autoincrement, id integer not null, title text not null, body text not null);";  
  
    final Context context;  
  
    DatabaseHelper DBHelper;  
    SQLiteDatabase db;  
      
    public DBAdapter(Context ctx)  
    {  
        this.context = ctx;  
        DBHelper = new DatabaseHelper(context);  
    }  
  
    private static class DatabaseHelper extends SQLiteOpenHelper  
    {  
        DatabaseHelper(Context context)  
        {  
            super(context, DATABASE_NAME, null, DATABASE_VERSION);  
        }  
  
        @Override  
        public void onCreate(SQLiteDatabase db)  
        {  
            try {  
                db.execSQL(DATABASE_CREATE);  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }  
        }  
  
        @Override  
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)  
        {  
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "  
                    + newVersion + ", which will destroy all old data");  
            db.execSQL("DROP TABLE IF EXISTS diaries");  
            onCreate(db);  
        }  
    }  
  
    //---opens the database---  
    public DBAdapter open() throws SQLException   
    {  
        db = DBHelper.getWritableDatabase();  
        return this;  
    }  
  
    //---closes the database---  
    public void close()   
    {  
        DBHelper.close();  
    }  
  
    //---insert a diary into the database---  
    public long insertDiary(String title, String body)   
    {  
        ContentValues initialValues = new ContentValues();  
        initialValues.put(KEY_ID, count);
        count++;
        initialValues.put(KEY_TITLE, title);  
        initialValues.put(KEY_BODY, body);  
        return db.insert(DATABASE_TABLE, null, initialValues);  
    }  
  
    //---deletes a particular diary---  
    public boolean deleteDiary(int id)   
    {  
        db.delete(DATABASE_TABLE, KEY_ID + "=" + id, null);
        count--;
        Cursor cursor=getAllDiaries();
        cursor.moveToPosition(id-1);
        while(cursor.moveToNext())
        	updateID(cursor.getInt(cursor.getColumnIndex(KEY_ID)), cursor.getInt(cursor.getColumnIndex(KEY_ID))-1);
        return true;
    }  
  
    //---retrieves all the diaries---  
    public Cursor getAllDiaries()  
    {  
        return db.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_ID, KEY_TITLE, KEY_BODY}, null, null, null, null, null);  
    }  
  
    //---retrieves a particular diary---  
    public Cursor getDiary(int id) throws SQLException   
    {
        Cursor mCursor = db.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_ID, KEY_TITLE, KEY_BODY}, KEY_ID + "=" + id, null, null, null, null);
        if (mCursor != null) {  
            mCursor.moveToFirst();  
        }  
        return mCursor;  
    }  
  
    //---updates a diary---  
    public boolean updateDiary(int id, String title, String body)   
    {  
        ContentValues args = new ContentValues();  
        args.put(KEY_TITLE, title);  
        args.put(KEY_BODY, body);  
        return db.update(DATABASE_TABLE, args, KEY_ID + "=" + id, null) > 0;  
    }  
    
    public boolean updateID(int oldId, int newId)   
    {  
        ContentValues args = new ContentValues();  
        args.put(KEY_ID, newId); 
        return db.update(DATABASE_TABLE, args, KEY_ID + "=" + oldId, null) > 0;  
    } 
  
}  