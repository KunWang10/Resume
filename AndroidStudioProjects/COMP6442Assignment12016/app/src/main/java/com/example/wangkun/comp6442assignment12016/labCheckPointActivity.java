package com.example.wangkun.comp6442assignment12016;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class labCheckPointActivity extends AppCompatActivity {


    EditText textEntered;
    TextView textSaved;
    static final String filename = "lab4";
    FileInputStream inputStream;
    FileOutputStream outputStream;
    File persistentFile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_check_point);

        textSaved = (TextView) findViewById(R.id.textView2);
        textEntered = (EditText) findViewById(R.id.editText2);

        persistentFile = new File(getFilesDir(),filename);

        if (persistentFile.exists()){
            try{
                inputStream = openFileInput(filename);
                BufferedReader input = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                StringBuilder buffer = new StringBuilder();
                while ((line = input.readLine()) != null){
                    buffer.append(line);
                }
                textSaved.setText(buffer.toString());
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public void saveText(View view){
        String textBuffer = textSaved.getText().toString();
        String appendText = textEntered.getText().toString();
        textBuffer += "［";
        textBuffer += appendText;
        textBuffer += "］";
        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(textBuffer.getBytes());
            outputStream.close();
            textSaved.setText(textBuffer);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void clearAll(View view) {
        String textBuffer = "";
        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(textBuffer.getBytes());
            outputStream.close();
            textSaved.setText(textBuffer);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void selfDestruct(View view) {
        int id = view.getId();
        switch (id){
            case 2131492973:
                System.out.println("Click on New Button");
                break;
            case 2131492974:
                System.out.println("Click on Save Button");
                break;
            case 2131492975:
                System.out.println("Click on Edit Button");
                break;
            case 2131492976:
                System.out.println("Click on Delete Button");
                break;
        }

    }

}
