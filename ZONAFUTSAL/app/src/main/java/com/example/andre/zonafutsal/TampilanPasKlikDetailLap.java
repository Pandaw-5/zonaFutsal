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

    SharedPreferences sharedpreferences;
    public static final String TAG_ID = "id";
    public static final String TAG_USERNAME = "username";
    public final static String TAG_NAMA = "nama";
    public static final String my_shared_preferences = "my_shared_preferences";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampilan_pas_klik_detail_lap);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.tampilan,new MainUser());
        ft.commit();

        navigationView.setCheckedItem(R.id.beranda);
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
            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.tampilan,new MainUser());
            ft.commit();
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
        else if (id == R.id.riwayat) {

            android.support.v4.app.FragmentTransaction ft =getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.tampilan,new RiwayatFragment());
            ft.commit();
        }
        else if (id == R.id.pemesanan) {
            android.support.v4.app.FragmentTransaction ft =getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.tampilan,new PemesananFragment());
            ft.commit();
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
