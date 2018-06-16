package com.example.dell.hackathon;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private Handler handler;
    private Runnable runnable;
    private int page;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.cardview)
    CardView cardView;
    @BindView(R.id.mapbtn)
    Button mapBtn;
    @BindView(R.id.notifyBtn)
    Button notify;
    @BindView(R.id.helplineBtn)
    Button helpline;
    @BindView(R.id.paymentBtn)
    Button payment;
    @BindView(R.id.complainBtn)
    Button complain;
    @BindView(R.id.scheduleBtn)
    Button schedule;

    Locale myLocale;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ImageAdapter adapterView = new ImageAdapter(this);
        viewPager.setAdapter(adapterView);
        scrollBanner();

        notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent notification = new Intent(MainActivity.this,NotificationActivity.class);
                startActivity(notification);
            }
        });

        complain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent comp = new Intent(MainActivity.this,ComplainActivity.class);
                startActivity(comp);
            }
        });

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pay = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(pay);
            }
        });

        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sche = new Intent(MainActivity.this,ScheduleActivity.class);
                startActivity(sche);
            }
        });





    }

    private void scrollBanner() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                int numPages =viewPager.getAdapter().getCount();
                page = (page + 1) % numPages;
                viewPager.setCurrentItem(page);
                scrollBanner();
            }
        };
        handler.postDelayed(runnable, 2000);

    }
}
