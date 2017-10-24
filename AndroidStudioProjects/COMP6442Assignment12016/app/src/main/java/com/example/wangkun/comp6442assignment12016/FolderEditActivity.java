package com.example.wangkun.comp6442assignment12016;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

public class FolderEditActivity extends Activity {

    TextView textView;
    folderDataBase folderDataBase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder_edit);

        textView = (TextView) findViewById(R.id.editText);
        folderDataBase = new folderDataBase(this);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .8), (int) (height * .6));

    }

    public void saveFolder(View view){

        String newName = textView.getText().toString();
        folderDataBase.insertData(newName);
        launchMainActivity(textView);
        finish();

    }

    public void Cancel(View view) {
        launchMainActivity(textView);
        finish();
    }

    public void launchMainActivity(View view) {
        Intent launchLabIntent = new Intent(this, MainActivity.class);
        startActivity(launchLabIntent);
    }

}
