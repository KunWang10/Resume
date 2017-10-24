package com.example.wangkun.comp6442assignment12016;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class SearchActivity extends Activity {

    public static String noteid;
    String foldername;

    static boolean search = false;

    public static String title, content;

    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Get the intent, verify the action and get the query
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            doMySearch(query);
        }

        Bundle appData = intent.getBundleExtra(SearchManager.APP_DATA);
        if (appData != null) {
            String testValue = appData.getString("KEY");
            System.out.println("extra data = " + testValue);
        }

    }

    private void doMySearch(String query) {
        final noteDatabase noteDatabase = new noteDatabase(this);
        ArrayList<String> titles = noteDatabase.searchNote(query);
        final ListView listView = (ListView) findViewById(R.id.listView2);
        search = true;
        String[] tls = titles.toArray(new String[titles.size()]);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, tls);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                title = (String) listView.getItemAtPosition(position);
                content = noteDatabase.getContent("",title);
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

    public void launchNoteShowActivity(View view) {
        Intent launchNoteShowIntent = new Intent(this, NoteShowActivity.class);
        startActivity(launchNoteShowIntent);
    }


    public static boolean isSearch() {
        return search;
    }

    public static String getTTT() {
        return title;
    }

    public static String getCCC() {
        return content;
    }

    public static void setSearchFalse() {
        search = false;
    }



}