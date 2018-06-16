package com.example.dell.hackathon;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dell on 22/04/2018.
 */

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.button)
    Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);
        ButterKnife.bind(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String token = FirebaseInstanceId.getInstance().getToken();
                //  String msg =getString(R.string.token ,token);
                String msg ="token :" + token;
                Log.d("FirebaseInstanceid","token : " +token);
               // Toast.makeText(LoginActivity.this,msg,Toast.LENGTH_SHORT).show();

                Intent b = new Intent(LoginActivity.this,PaymentActivity.class);
                startActivity(b);
            }
        });
    }
}
