package com.example.andre.zonafutsal;

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

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public final static String TAG_USERNAME = "nohp";
    public final static String TAG_ID = "id";
    public final static String TAG_NAMA = "nama";

    SharedPreferences sharedpreferences;
    Boolean session = false;
    String id, nohp, nama;

    public static final String my_shared_preferences = "my_shared_preferences";
    public static final String session_status = "session_status";

    public Button detail1, detail2, detail3, detail4, detail5, detail6, detail7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        detail1 = (Button) findViewById(R.id.detail1);
        detail1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(getApplicationContext(), DetailLapanganActivity.class);
                Intent.putExtra("id", "L01");
                startActivity(Intent);
            }
        });

        detail2 = (Button) findViewById(R.id.detail2);
        detail2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(getApplicationContext(), DetailLapanganActivity.class);
                Intent.putExtra("id", "L02");
                startActivity(Intent);
            }
        });

        detail3 = (Button) findViewById(R.id.detail3);
        detail3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(getApplicationContext(), DetailLapanganActivity.class);
                Intent.putExtra("id", "L03");
                startActivity(Intent);
            }
        });

        detail4 = (Button) findViewById(R.id.detail4);
        detail4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(getApplicationContext(), DetailLapanganActivity.class);
                Intent.putExtra("id", "L04");
                startActivity(Intent);
            }
        });

        detail5 = (Button) findViewById(R.id.detail5);
        detail5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(getApplicationContext(), DetailLapanganActivity.class);
                Intent.putExtra("id", "L05");
                startActivity(Intent);
            }
        });

        detail6 = (Button) findViewById(R.id.detail6);
        detail6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(getApplicationContext(), DetailLapanganActivity.class);
                Intent.putExtra("id", "L06");
                startActivity(Intent);
            }
        });

        detail7 = (Button) findViewById(R.id.detail7);
        detail7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(getApplicationContext(), DetailLapanganActivity.class);
                Intent.putExtra("id", "L07");
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

        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        session = sharedpreferences.getBoolean(session_status, false);
        id = sharedpreferences.getString(TAG_ID, null);
        nohp = sharedpreferences.getString(TAG_USERNAME, null);
        nama = sharedpreferences.getString(TAG_NAMA, null);

        if (session) {
            Intent intent = new Intent(getApplicationContext(), TampilanPasKlikDetailLap.class);
            intent.putExtra(TAG_ID, id);
            intent.putExtra(TAG_USERNAME, nohp);
            intent.putExtra(TAG_NAMA, nama);
            finish();
            startActivity(intent);
        }
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
        getMenuInflater().inflate(R.menu.main, menu);
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
        } else if (id == R.id.masuk) {
            Intent i =new Intent(getApplicationContext(),MasukActivity.class);  startActivity(i);
        } else if (id == R.id.daftar) {
            Intent i =new Intent(getApplicationContext(),VerificationActivity.class);  startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
