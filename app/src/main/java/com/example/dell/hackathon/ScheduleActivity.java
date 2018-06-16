package com.example.dell.hackathon;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dell on 22/04/2018.
 */

public class ScheduleActivity extends Activity {
    @BindView(R.id.spinner1)
    Spinner spinner1;
    @BindView(R.id.scheduleimg)
    ImageView imgSche;
     @BindView(R.id.recycle)
        Button btnRecycle;
    int currentItem =0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        ButterKnife.bind(this);
        btnRecycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();


            }
        });

        addListenerOnSpinnerItemSelection();

    }

    private void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.AlertDialogTheme);
        builder
                .setMessage("Yellow bin: \n 1. Glass bottles and jars \n 2.Tins \n 3. Cans \n 4. Paper \n 5.Paper and Cardboards")
                .setPositiveButton("OK",  new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(ScheduleActivity.this,ScheduleActivity.class);
                        startActivity(intent);
                    }
                })
                .show();

    }


    private void addListenerOnSpinnerItemSelection() {
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 0) {
                    imgSche.setImageResource(R.drawable.c1);


                }
                else if(i == 1) {
                    imgSche.setImageResource(R.drawable.c3);
                }
                else if (i ==2){
                    imgSche.setImageResource(R.drawable.c4);
                }


                }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }


        });
    }
}
