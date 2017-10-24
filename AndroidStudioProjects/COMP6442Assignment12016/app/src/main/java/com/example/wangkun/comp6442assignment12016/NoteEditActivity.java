package com.example.wangkun.comp6442assignment12016;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

public class NoteEditActivity extends Activity {

    static TextView title;
    static TextView content;
    noteDatabase noteDatabase;
    static boolean edit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);

        title = (TextView) findViewById(R.id.editText3);
        content = (TextView) findViewById(R.id.editText4);
        noteDatabase = new noteDatabase(this);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .8), (int) (height * .8));
    }

    public void saveNote(View view) {
        String newTitle = title.getText().toString();
        String newContent = content.getText().toString();
        String foldername = MainActivity.getFoldername();
        edit = true;
        noteDatabase.insertData(newTitle, newContent, foldername);
        launchNoteShowActivity(title);
        finish();
    }

    public void cancelEditNote(View view) {
        launchFolderShowActivity(view);
        finish();
    }

    public void launchNoteShowActivity(View view) {
        Intent launchNoteShowIntent = new Intent(this, NoteShowActivity.class);
        startActivity(launchNoteShowIntent);
    }

    public void launchFolderShowActivity(View view) {
        Intent launchFolderShowIntent = new Intent(this, FolderShowActivity.class);
        startActivity(launchFolderShowIntent);
    }

    public static boolean isEdit() {
        return edit;
    }

    public static String getTTT() {
        return title.getText().toString();
    }

    public static String getCCC() {
        return content.getText().toString();
    }

    public static void setEditFalse() {
        edit = false;
    }
}
