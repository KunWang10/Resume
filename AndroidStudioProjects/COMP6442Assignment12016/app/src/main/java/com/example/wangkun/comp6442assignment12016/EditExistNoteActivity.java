package com.example.wangkun.comp6442assignment12016;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

public class EditExistNoteActivity extends Activity {

    TextView title,content;
    noteDatabase noteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_exist_note);

        title = (TextView) findViewById(R.id.editText31);
        content = (TextView) findViewById(R.id.editText41);
        noteDatabase = new noteDatabase(this);
        title.setText(FolderShowActivity.getTitl());
        content.setText(NoteShowActivity.getContent());

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
        noteDatabase.updataData(FolderShowActivity.getNoteid(),newTitle, newContent, foldername);
        launchNoteShowActivity(title);
        finish();
    }

    public void cancelEditNote(View view) {
        finish();
    }

    public void launchNoteShowActivity(View view) {
        Intent launchNoteShowIntent = new Intent(this, NoteShowActivity.class);

        startActivity(launchNoteShowIntent);
    }

}
