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
public class noteDatabase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Note.db";
    public static final String TABLE_NAME = "Note_table";
    public static final String COL1 = "ID";
    public static final String COL2 = "TITLE";
    public static final String COL3 = "CONTENT";
    public static final String COL4 = "FOLDERNAME";


    public noteDatabase(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, TITLE TEXT, CONTENT TEXT, FOLDERNAME TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String title, String content, String folderName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, title);
        contentValues.put(COL3, content);
        contentValues.put(COL4, folderName);
        long result = db.insert(TABLE_NAME,null,contentValues);
        return result != -1;
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
        arrayList.add(res.getCount() + "");
        while (res.moveToNext()){
            arrayList.add(res.getString(1));
        }

        return arrayList;
    }

    public ArrayList<String> getNoteList(String foldername) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<String> arrayList = new ArrayList<>();
        Cursor res =  db.rawQuery("select * from " + TABLE_NAME , null);
        if (foldername.equals("All Notes")) {
            while (res.moveToNext()){
                arrayList.add(res.getString(1));
            }
            return arrayList;
        }

        while (res.moveToNext()){
            if (res.getString(3).equals(foldername)){
                arrayList.add(res.getString(1));
            }
        }
        if (arrayList.isEmpty()){
            arrayList.add("There is No note, Please add one");
        }
        return arrayList;

    }

    public String getContent(String foldername,String title) {
        SQLiteDatabase db = this.getWritableDatabase();
        String content = "";
        Cursor res =  db.rawQuery("select * from " + TABLE_NAME, null);
        if (foldername.equals("")) {
            while (res.moveToNext()){
                if (res.getString(1).equals(title)){
                    content += res.getString(2);
                }
            }
            return content;
        }

        while (res.moveToNext()){
            if (res.getString(3).equals(foldername) && res.getString(1).equals(title)){
                content += res.getString(2);
            }
        }
        return content;
    }

    public String whatisid(String foldername ,String title) {
        SQLiteDatabase db = this.getWritableDatabase();
        String myid = null;
        Cursor res =  db.rawQuery("select * from " + TABLE_NAME, null);
        while (res.moveToNext()){
            if (res.getString(3).equals(foldername) && res.getString(1).equals(title)){
                myid = res.getString(0);
            }
        }
        return myid;
    }

    public boolean updataData(String id, String newTitle, String newContent, String foldername) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1,id);
        contentValues.put(COL2, newTitle);
        contentValues.put(COL3, newContent);
        contentValues.put(COL4, foldername);
        db.update(TABLE_NAME,contentValues,"ID = ?", new String[] { id });
        return true;

    }

    public Integer deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[]{id});
    }

    public Integer deleteFolderNotes(String foldername) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "FOLDERNAME = ?", new String[]{foldername});
    }

    public ArrayList<String> searchNote (String hint) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<String> arrayList = new ArrayList<>();
        Cursor res =  db.rawQuery("select * from " + TABLE_NAME , null);
        while (res.moveToNext()){
            if (res.getString(1).contains(hint) || res.getString(2).contains(hint))
            arrayList.add(res.getString(1));
        }
        return arrayList;
    }


}
