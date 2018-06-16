package com.example.dell.hackathon;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConplainActivity1 extends AppCompatActivity{
    private static final int PERMISSION_REQUEST_CAMERA =1;
    private static String[] PERMISSIONS_CAMERA ={Manifest.permission.CAMERA};
    @BindView(R.id.gallery)
    Button camera1;
    @BindView(R.id.submit_btn)
    Button submit;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain);
        ButterKnife.bind(this);
        if(checkSelfPermission(android.Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED){
            Log.i("omPermission","\"Contact permissions have already been granted. Displaying contact details.\"");
        }else {
            Log.i("onPermission","Contact permissions has NOT been granted. Requesting permission.");
            requestPermissions(PERMISSIONS_CAMERA,PERMISSION_REQUEST_CAMERA);
        }
        ActivityCompat.requestPermissions(this, new String[] {
                android.Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE }, 0);

        camera1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent new1 = new Intent(ConplainActivity1.this,CameraUploadActivity.class);
                startActivity(new1);

            }
        });




    }
}
