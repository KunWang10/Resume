package com.example.wangkun.comp6442assignment12016;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by wangkun on 20/03/16.
 */
public class folderDataBase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Folder.db";
    public static final String TABLE_NAME = "Folder_table";
    public static final String COL1 = "ID";
    public static final String COL2 = "NAME";


    public folderDataBase(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public boolean insertData(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, name);
        long result = db.insert(TABLE_NAME,null,contentValues);
        return result != -1;
    }

    public Integer deleteData(String foldername) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "NAME = ?", new String[]{foldername});
    }


    public void dropTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public ArrayList<String> getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<String> arrayList = new ArrayList<>();
        Cursor res =  db.rawQuery("select * from " + TABLE_NAME, null);
        arrayList.add("All Notes");
        while (res.moveToNext()){
            arrayList.add(res.getString(1));
        }

        return arrayList;
    }


}
