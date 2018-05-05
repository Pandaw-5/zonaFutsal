package com.example.andre.zonafutsal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public Button button4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
<<<<<<< HEAD
                Intent Intent = new Intent(MainActivity.this, TampilanKlikButtonDetail.class);
                startActivity(Intent);
            }
        });

        button10 = (Button) findViewById(R.id.button10);
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(MainActivity.this, TampilanButtonDetailLapangan.class);
=======
                Intent Intent = new Intent(MainActivity.this, MenuAktivity.class);
>>>>>>> b1506868944a22e25eea5e2ba828053306d439ac
                startActivity(Intent);
            }
        });
    }
}
