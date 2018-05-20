package com.belajar.jajalradiooi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    RadioButton rb1,rb2;
    RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rg = (RadioGroup)findViewById(R.id.rg);
        rb1 = (RadioButton)findViewById(R.id.rbbaso);
        rb2 = (RadioButton)findViewById(R.id.rbMie);
        rg.setOnCheckedChangeListener(this);
    }
    public void onCheckedChanged(RadioGroup group, int checkedId) {
    }
}