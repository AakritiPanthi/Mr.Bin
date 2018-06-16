package com.example.dell.hackathon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Dell on 22/04/2018.
 */

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Thread t=new Thread() {
            public void run() {

                try {
                    //sleep thread for 10 seconds
                    sleep(2000);

                    //Call Main activity
                    Intent i=new Intent(SplashActivity.this, Main2Activity.class);
                    startActivity(i);

                    //destroying Splash activity
                    finish();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        //start thread
        t.start();
    }
}
