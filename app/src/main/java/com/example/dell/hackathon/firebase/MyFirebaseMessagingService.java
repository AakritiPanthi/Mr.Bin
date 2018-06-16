package com.example.dell.hackathon.firebase;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.dell.hackathon.NotificationActivity;
import com.example.dell.hackathon.R;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Random;

/**
 * Created by Dell on 21/04/2018.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService{
    public static final String NOTIFICATION_BROADCAST ="BroadcastNotification";
    public static final String NOTIFICATION_TITLE = "NotificationTitle";
    public static final String NOTIFICATION_MSG = "NotificationMessage";
    public static final String NOTIFICATION_DATE_TIME = "NotificationDate";
    public static final String NOTIFICATION_ID = "NotificationId";

    public  static SharedPreferences sharedPreferences;
    public static final String PREFERENCES = "preferences";



    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.d("FirebaseMessaging","From: " +remoteMessage.getFrom());
        if(remoteMessage.getData().size()>0){
            Log.d("FirebaseMessaging","Message data payload: " +remoteMessage.getData());

            if(true){
                scheduleJob();
            }else {

                // int notificationId = new Random().nextInt();
//                sendNotification(notificationId,remoteMessage.getData().get("title"),remoteMessage.getData().get("message"),
//                        remoteMessage.getData().get("date"));
//                broadcastNotifications(remoteMessage.getData().get("title"),remoteMessage.getData().get("message"),
//                        remoteMessage.getData().get("date"),notificationId);
            }
        }

        if(remoteMessage.getNotification()!= null){
            Log.d("FirebaseMessaging","Notification payload:  " +remoteMessage.getNotification().getBody());

            int notificationId = new Random().nextInt();
            sendNotification(notificationId,remoteMessage.getData().get("title"),remoteMessage.getData().get("message"),
                    remoteMessage.getData().get("date"));

            broadcastNotifications(remoteMessage.getData().get("title"),remoteMessage.getData().get("message"),
                    remoteMessage.getData().get("date"),notificationId);
        }
    }

    private void sendNotification(int notificationId, String title, String message, String date) {
        Intent intent = new Intent(this,NotificationActivity.class);
        intent.putExtra(NOTIFICATION_TITLE,title);
        intent.putExtra(NOTIFICATION_MSG,message);
        intent.putExtra(NOTIFICATION_DATE_TIME,date);
        intent.putExtra(NOTIFICATION_ID,notificationId);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);


        NotificationCompat.Builder notificationbuilder = new NotificationCompat.Builder(this,getString(R.string.default_notification_channel_id))
                .setSmallIcon(R.drawable.notify)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager)getSystemService(NotificationManager.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            notificationManager.createNotificationChannel(new NotificationChannel(getString(R.string.default_notification_channel_id),
                    getString(R.string.default_notification_channel_name), NotificationManager.IMPORTANCE_HIGH));
        }
        notificationManager.notify(notificationId,notificationbuilder.build());

//        sharedPreferences = getSharedPreferences(MainActivity.MYPREFERENCES, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString(NOTIFICATION_TITLE,title);
//        editor.putString(NOTIFICATION_MSG,message);
//        editor.commit();
    }
    private void broadcastNotifications(String title, String message, String date, int notificationId) {
        Intent intent = new Intent(NOTIFICATION_BROADCAST);
        intent.putExtra(NOTIFICATION_TITLE,title);
        intent.putExtra(NOTIFICATION_MSG,message);
        intent.putExtra(NOTIFICATION_DATE_TIME,date);
        intent.putExtra(NOTIFICATION_ID,notificationId);
        getApplicationContext().sendBroadcast(intent);
    }

    private void scheduleJob() {
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(this));
        Job myJob = dispatcher.newJobBuilder().setService(MyService.class).setTag("my_job_string").build();
        dispatcher.schedule(myJob);
    }





}
