package com.example.dell.hackathon.firebase;

import android.app.Service;
import android.app.job.JobParameters;

import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.firebase.jobdispatcher.JobService;

/**
 * Created by Dell on 21/04/2018.
 */

public class MyService extends JobService {
    @Override
    public boolean onStartJob(com.firebase.jobdispatcher.JobParameters job) {
        Log.d("JobService","Task performing");
        return false;
    }

    @Override
    public boolean onStopJob(com.firebase.jobdispatcher.JobParameters job) {
        return false;
    }
}