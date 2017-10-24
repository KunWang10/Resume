package com.example.wangkun.comp6442assignment12016;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class BackgroundActivity extends AppCompatActivity {

    ImageView background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_background);

        background = (ImageView) findViewById(R.id.imageView);

        background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchMainActivity();
            }
        });


        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                launchMainActivity();
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, 3000);


    }

    private void launchMainActivity() {

        Intent launchMainActivity = new Intent(this, MainActivity.class);
        startActivity(launchMainActivity);
    }
}
