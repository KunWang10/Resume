package com.example.wangkun.comp6442assignment12016;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class TestActivity extends AppCompatActivity {

    noteDatabase noteDatabase = new noteDatabase(this);;
    public static boolean insertTest;
    public static String contentTest;
    public static int sizeTest;
    public static ArrayList<String> titlesTest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        insertTest = noteDatabase.insertData("Basketball", "I want to play basketball this Monday", "Sports");
        contentTest = noteDatabase.getContent("Sports", "Basketball");
        noteDatabase.insertData("Swim", "I want to swim tonight", "Sports");
        //sizeTest = noteDatabase.ge
        titlesTest = noteDatabase.getNoteList("Sports");
    }


    public static boolean getInserTest(){
        return insertTest;
    }

    public static String getContentTest() {
        return contentTest;
    }

    public static ArrayList<String> getTitlesTest() {
        return titlesTest;
    }

    public static int getSizeTest() {
        return sizeTest;
    }
}
