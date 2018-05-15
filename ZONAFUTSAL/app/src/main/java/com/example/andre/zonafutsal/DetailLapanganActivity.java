package com.example.andre.zonafutsal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DetailLapanganActivity extends AppCompatActivity {

    public Button button100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_lapangan);

        button100 = (Button) findViewById(R.id.button100);
        button100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(DetailLapanganActivity.this, JadwalPesanLapanganActivity.class);
                startActivity(Intent);
            }
        });

    }
}
