package com.example.wangkun.comp6442assignment12016;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class FolderShowActivity extends AppCompatActivity {

    public static String noteid;
    String foldername;
    TextView textView;
    ListView listView;
    folderDataBase folderDataBase;
    noteDatabase noteDatabase;
    ArrayList<String> notenames = new ArrayList<>();
    public static String title, content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder);

        folderDataBase = new folderDataBase(this);

        foldername = MainActivity.getFoldername();
        textView = (TextView) findViewById(R.id.textView);
        listView = (ListView) findViewById(R.id.listView1);
        registerForContextMenu(listView);
        textView.setText(foldername);

        noteDatabase = new noteDatabase(this);
        notenames = noteDatabase.getAllData();
        notenames = noteDatabase.getNoteList(foldername);


        String[] values = notenames.toArray(new String[notenames.size()]);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                title = (String) listView.getItemAtPosition(position);
                noteid = noteDatabase.whatisid(foldername, title);
                launchNoteShowActivity(view);

            }

        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                title = (String) listView.getItemAtPosition(position);
                noteid = noteDatabase.whatisid(foldername, title);
                return false;
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_folder, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            addNote(listView);
            return true;
        } else if (id == R.id.action_delete) {
            deleteFolder(listView);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onCreateContextMenu (ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        getMenuInflater().inflate(R.menu.menu_folder, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            addNote(listView);
            return true;
        } else if (id == R.id.action_delete) {
            deleteNote(listView);
            return true;
        }
        return true;
    }

    private void deleteNote(View View) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Alert!!");
        alert.setMessage("Are you sure to delete note " + title.toUpperCase());
        alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                noteDatabase.deleteData(noteid);
                dialog.dismiss();
                finish();
                launchFolderShowActivity(listView);
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

    private void deleteFolder(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Alert!!");
        alert.setMessage("Are you sure to delete folder " + foldername.toUpperCase());
        alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                folderDataBase.deleteData(foldername);
                dialog.dismiss();
                launchMainActivity(listView);
                finish();

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

    public static String getTitl() {
        return title;
    }

    public void addNote(View view) {
        Intent launchNoteEditIntent = new Intent(this, NoteEditActivity.class);
        startActivity(launchNoteEditIntent);
        finish();
    }

    public static String getNoteid() {
        return noteid;
    }


}
