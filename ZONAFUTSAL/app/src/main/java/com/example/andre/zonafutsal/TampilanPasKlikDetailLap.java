package com.example.andre.zonafutsal;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class TampilanPasKlikDetailLap extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String TAG_ID = "id";
    public static final String TAG_USERNAME = "username";
    public final static String TAG_NAMA = "nama";
    SharedPreferences sharedpreferences;

    public Button detail1, detail2, detail3, detail4, detail5, detail6, detail7;
    public TextView nama, nohp;

    private String id, tNama, tNohp;

    public static final String my_shared_preferences = "my_shared_preferences";
    public static final String session_status = "session_status";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampilan_pas_klik_detail_lap);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tampilan_pas_klik_detail_lap, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.beranda) {
            Intent i =new Intent(getApplicationContext(),MainActivity.class);  startActivity(i);
        }  else if (id == R.id.keluar) {
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putBoolean(MasukActivity.session_status, false);
                    editor.putString(TAG_ID, null);
                    editor.putString(TAG_USERNAME, null);
                    editor.commit();

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    finish();
                    startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;


    }

    public void gambar(View view){
        Intent i =new Intent(getApplicationContext(),InfoProfilMember.class);
        startActivity(i);
    }
}
