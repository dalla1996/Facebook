package com.example.facebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import static android.content.ContentValues.TAG;


public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "student.db";
    public static final int DB_VERSION=1;
    public static final String TABLE_NAME = "student_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "AGE";
    public static final String COL_4 = "MARKS";
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
            COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT ," + COL_2 + " VARCHAR(120) ," +
            COL_3 + " INTEGER ," +
            COL_4 + " INTEGER ) ; " ;
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static final String SELECT_STUDENT="SELECT * FROM "+TABLE_NAME;

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, 1);
       SQLiteDatabase db = this.getWritableDatabase();



    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }
    public boolean insertData(String Name,String Age,String Marks){
        try (SQLiteDatabase db = this.getWritableDatabase()) {
            ContentValues contentValues=new ContentValues();
            contentValues.put(COL_2,Name);
            contentValues.put(COL_3,Age);
            contentValues.put(COL_4,Marks);
        long result=db.insert(TABLE_NAME,null,contentValues);
        if (result== -1)
            return true;
        }
        return false;
    }
    public void getAllStudent(){
        SQLiteDatabase db=getWritableDatabase();
        Cursor cursor=db.rawQuery(SELECT_STUDENT,null);
        if (cursor.moveToFirst()){
            do {
                Log.d(TAG,"getStudent Name"+
                        "Name :"+cursor.getString(cursor.getColumnIndex(COL_2))+
                        "Age :" +cursor.getString(cursor.getColumnIndex(COL_3))+
                        "Marks :"+cursor.getString(cursor.getColumnIndex(COL_4)));



            }while (cursor.moveToNext());
        }
    }


    public Cursor getAllDetails() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor rs = db.rawQuery("select * from "+TABLE_NAME,null);
        return rs;
    }


}
