package com.example.wangkun.comp6442assignment12016;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class NoteShowActivity extends AppCompatActivity {

    TextView titleView, contentView;
    public static String title, content;
    noteDatabase noteDatabase;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_show);

        button = (Button) findViewById(R.id.buttontime);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String con = contentView.getText().toString();
                SimpleDateFormat sDateFormat = new SimpleDateFormat("\nyyyy-MM-dd    hh:mm:ss");
                String time = sDateFormat.format(new java.util.Date());
                con = con + time;
                contentView.setText(con);

            }
        });

        titleView = (TextView) findViewById(R.id.textView32);
        contentView = (TextView) findViewById(R.id.textView42);
        if (NoteEditActivity.isEdit()) {
            NoteEditActivity.setEditFalse();
            titleView.setText(NoteEditActivity.getTTT());
            contentView.setText(NoteEditActivity.getCCC());
        } else if (SearchActivity.isSearch()) {
            SearchActivity.setSearchFalse();
            titleView.setText(SearchActivity.getTTT());
            contentView.setText(SearchActivity.getCCC());
            getSupportActionBar().hide();
            TextView textView = (TextView) findViewById(R.id.textView4);
            textView.setText("Touch the screen to go back");
            View layout = findViewById(R.id.noteshow);
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                    launchMainActivity(titleView);
                }
            });

        } else {
            title = FolderShowActivity.getTitl();
            titleView.setText(title);
            noteDatabase = new noteDatabase(this);
            content = noteDatabase.getContent(MainActivity.getFoldername(), title);
            contentView.setText(content);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_back) {
            launchFolderShowActivity(titleView);
            finish();
            return true;
        } else if (id == R.id.action_edit) {
            editNote(titleView);
            return true;
        } else if (id == R.id.action_delete) {
            deleteNote(titleView);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void editNote(View view) {
        finish();
        Intent launchEditExistNoteActivity = new Intent(this, EditExistNoteActivity.class);
        startActivity(launchEditExistNoteActivity);
    }

    public void deleteNote(View view) {
        final String id = FolderShowActivity.getNoteid();
        String title = FolderShowActivity.getTitl();
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Alert!!");
        alert.setMessage("Are you sure to delete note " + title.toUpperCase());

        alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                noteDatabase.deleteData(id);
                launchFolderShowActivity(titleView);
                dialog.dismiss();

            }
        });
        alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alert.show();

    }

    public void launchNoteShowActivity(View view) {
        Intent launchNoteShowIntent = new Intent(this, NoteShowActivity.class);
        startActivity(launchNoteShowIntent);
    }

    public void launchFolderShowActivity(View view) {
        Intent launchFolderShowIntent = new Intent(this, FolderShowActivity.class);
        startActivity(launchFolderShowIntent);
    }

    public void launchMainActivity(View view) {
        Intent launchMainActivityIntent = new Intent(this, MainActivity.class);
        startActivity(launchMainActivityIntent);
    }

    public static String getContent() {
        return content;
    }
}
