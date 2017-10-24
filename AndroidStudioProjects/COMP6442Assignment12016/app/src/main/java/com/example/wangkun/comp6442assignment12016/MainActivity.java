package com.example.wangkun.comp6442assignment12016;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private static String foldername;
    private ListView listView ;
    private ArrayList<String> foldernames = new ArrayList<>();
    folderDataBase folderDataBase;
    noteDatabase noteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        folderDataBase = new folderDataBase(this);
        noteDatabase = new noteDatabase(this);

        //folderDataBase.dropTable();

        foldernames = folderDataBase.getAllData();

        listView = (ListView) findViewById(R.id.listView);
        registerForContextMenu(listView);


        String[] values = foldernames.toArray(new String[foldernames.size()]);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                foldername = (String) listView.getItemAtPosition(position);
                launchFolderShowActivity(view);

            }

        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                foldername = (String) listView.getItemAtPosition(position);
                return false;
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            addFolder(listView);
            return true;
        }else if (id == R.id.action_search) {
            onSearchRequested();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onCreateContextMenu (ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        getMenuInflater().inflate(R.menu.menu_context, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            addFolder(listView);
            return true;
        }else if (id == R.id.action_search) {
            onSearchRequested();
            return true;
        }else if(id == R.id.action_delete){
            deleteFolder(listView);
        }
        return true;
    }



    public void launchMainActivity(View view) {
        Intent launchMainIntent = new Intent(this, MainActivity.class);
        startActivity(launchMainIntent);
    }


    public void launchFolderShowActivity(View view) {
        Intent launchFolderShowIntent = new Intent(this, FolderShowActivity.class);
        startActivity(launchFolderShowIntent);
    }

    public static String getFoldername(){
        return foldername;
    }

    public void addFolder(View view) {
        Intent launchFolderEditIntent = new Intent(this, FolderEditActivity.class);
        startActivity(launchFolderEditIntent);
        finish();
    }

    private void deleteFolder(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Alert!!");
        alert.setMessage("Are you sure to delete folder " + foldername.toUpperCase());
        alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                folderDataBase.deleteData(foldername);
                noteDatabase.deleteFolderNotes(foldername);
                finish();
                launchMainActivity(listView);
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


}
