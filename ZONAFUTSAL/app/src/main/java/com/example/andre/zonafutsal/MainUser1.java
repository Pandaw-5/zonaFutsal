package com.example.andre.zonafutsal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainUser1 extends AppCompatActivity {

        SharedPreferences sharedpreferences;

        public Button detail1, detail2, detail3, detail4, detail5, detail6, detail7;
        public TextView nama, nohp;

        private String id, tNama, tNohp;

        public static final String my_shared_preferences = "my_shared_preferences";
        public static final String session_status = "session_status";
        public static final String TAG_ID = "id";
        public static final String TAG_USERNAME = "username";
        public final static String TAG_NAMA = "nama";

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.fragment_main_user);

            nama = findViewById(R.id.gNama);
            nohp = findViewById(R.id.gNohp);

            sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
            tNama = sharedpreferences.getString(TAG_NAMA, null);
            tNohp = sharedpreferences.getString(TAG_USERNAME, null);
            id = sharedpreferences.getString(TAG_ID, null);


            detail1 = (Button) findViewById(R.id.detail1);
            detail1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent Intent = new Intent(getApplicationContext(), DetailLapanganActivity.class);
                    Intent.putExtra("id", "lap1");
                    startActivity(Intent);
                }
            });

            detail2 = (Button) findViewById(R.id.detail2);
            detail2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent Intent = new Intent(getApplicationContext(), DetailLapanganActivity.class);
                    Intent.putExtra("id", "lap2");
                    startActivity(Intent);
                }
            });

            detail3 = (Button) findViewById(R.id.detail3);
            detail3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent Intent = new Intent(getApplicationContext(), DetailLapanganActivity.class);
                    Intent.putExtra("id", "lap3");
                    startActivity(Intent);
                }
            });

            detail4 = (Button) findViewById(R.id.detail4);
            detail4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent Intent = new Intent(getApplicationContext(), DetailLapanganActivity.class);
                    Intent.putExtra("id", "lap4");
                    startActivity(Intent);
                }
            });

            detail5 = (Button) findViewById(R.id.detail5);
            detail5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent Intent = new Intent(getApplicationContext(), DetailLapanganActivity.class);
                    Intent.putExtra("id", "lap5");
                    startActivity(Intent);
                }
            });

            detail6 = (Button) findViewById(R.id.detail6);
            detail6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent Intent = new Intent(getApplicationContext(), DetailLapanganActivity.class);
                    Intent.putExtra("id", "lap6");
                    startActivity(Intent);
                }
            });

            detail7 = (Button) findViewById(R.id.detail7);
            detail7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent Intent = new Intent(getApplicationContext(), DetailLapanganActivity.class);
                    Intent.putExtra("id", "lap7");
                    startActivity(Intent);
                }
            });
        }
    }

