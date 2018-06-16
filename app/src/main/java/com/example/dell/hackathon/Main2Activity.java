package com.example.dell.hackathon;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
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
//    @BindView(R.id.spinnerlang)
//    Spinner lang;
    Locale myLocale;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageAdapter adapterView = new ImageAdapter(this);
        viewPager.setAdapter(adapterView);
        scrollBanner();
        notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent notification = new Intent(Main2Activity.this,NotificationActivity.class);
                startActivity(notification);
            }
        });

        complain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent comp = new Intent(Main2Activity.this,ConplainActivity1.class);
                startActivity(comp);
            }
        });

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pay = new Intent(Main2Activity.this,LoginActivity.class);
                startActivity(pay);
            }
        });

        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sche = new Intent(Main2Activity.this,ScheduleActivity.class);
                startActivity(sche);
            }
        });
        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent near = new Intent(Main2Activity.this,MapsActivity.class);
                startActivity(near);

            }
        });
//        lang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                if (i ==1){
//                    Toast.makeText(Main2Activity.this, "You have selected eng", Toast.LENGTH_SHORT)
//                            .show();
//                    setLocale("en");
//
//                }
//                else if(i ==2){
//                    Toast.makeText(Main2Activity.this, "You have selected nepali", Toast.LENGTH_SHORT)
//                            .show();
//                    setLocale("ne");
//
//                }
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//
//
//        });




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setLocale(String np) {
        myLocale = new Locale(np);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this, Main2Activity.class);
        startActivity(refresh);

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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            setLocale("en");


        }
        else if(id ==R.id.action_settings1){
            setLocale("ne");

        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            Intent event = new Intent(Main2Activity.this,EventActivity.class);
            startActivity(event);

        } else if (id == R.id.nav_slideshow) {
            Intent what = new Intent(Main2Activity.this,WhatActivity.class);
            startActivity(what);

        } else if (id == R.id.nav_manage) {
            Intent fee = new Intent(Main2Activity.this,FeeActivity.class);
            startActivity(fee);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
