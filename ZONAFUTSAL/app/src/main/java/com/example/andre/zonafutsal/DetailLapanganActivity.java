package com.example.andre.zonafutsal;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.andre.zonafutsal.app.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DetailLapanganActivity extends AppCompatActivity {

    private String id, nama_lapangan, keterangan;
    private ImageView gambar;
    private String url = Server.URL + "tampilLapangan.php";

    private static final String TAG = DetailLapanganActivity.class.getSimpleName();
    SharedPreferences sharedpreferences;

    public final static String TAG_LAPANGAN = "nama_lapangan";
    public final static String TAG_KETERANGAN = "keterangan";
    public static final String my_shared_preferences = "my_shared_lapangan";

    String tag_json_obj = "json_obj_req";
    ConnectivityManager conMgr;

    public Button button100;
    public TextView txt_view, txt_view2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_lapangan);

        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        txt_view = findViewById(R.id.textView2);
        txt_view2 = findViewById(R.id.textView6);
        id = getIntent().getStringExtra("id");
        gambar = findViewById(R.id.imageView2);
        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);


        switch(id){
            case "lap1":
                gambar.setImageResource(R.drawable.lap1);
                nama_lapangan = sharedpreferences.getString(TAG_LAPANGAN + "1", null);
                keterangan = sharedpreferences.getString(TAG_KETERANGAN + "1", null);
                txt_view.setText(nama_lapangan);
                txt_view2.setText(keterangan);
                break;
            case "lap2":
                gambar.setImageResource(R.drawable.lap2);
                nama_lapangan = sharedpreferences.getString(TAG_LAPANGAN + "2", null);
                keterangan = sharedpreferences.getString(TAG_KETERANGAN + "2", null);
                txt_view.setText(nama_lapangan);
                txt_view2.setText(keterangan);
                break;
            case "lap3":
                gambar.setImageResource(R.drawable.lap3);
                nama_lapangan = sharedpreferences.getString(TAG_LAPANGAN + "3", null);
                keterangan = sharedpreferences.getString(TAG_KETERANGAN + "3", null);
                txt_view.setText(nama_lapangan);
                txt_view2.setText(keterangan);
                break;
            case "lap4":
                gambar.setImageResource(R.drawable.lap4);
                nama_lapangan = sharedpreferences.getString(TAG_LAPANGAN + "4", null);
                keterangan = sharedpreferences.getString(TAG_KETERANGAN + "4", null);
                txt_view.setText(nama_lapangan);
                txt_view2.setText(keterangan);
                break;
            case "lap5":
                gambar.setImageResource(R.drawable.lap5);
                nama_lapangan = sharedpreferences.getString(TAG_LAPANGAN + "5", null);
                keterangan = sharedpreferences.getString(TAG_KETERANGAN + "5", null);
                txt_view.setText(nama_lapangan);
                txt_view2.setText(keterangan);
                break;
            case "lap6":
                gambar.setImageResource(R.drawable.lap6);
                nama_lapangan = sharedpreferences.getString(TAG_LAPANGAN + "6", null);
                keterangan = sharedpreferences.getString(TAG_KETERANGAN + "6", null);
                txt_view.setText(nama_lapangan);
                txt_view2.setText(keterangan);
                break;
            case "lap7":
                gambar.setImageResource(R.drawable.lap7);
                nama_lapangan = sharedpreferences.getString(TAG_LAPANGAN + "7", null);
                keterangan = sharedpreferences.getString(TAG_KETERANGAN + "7", null);
                txt_view.setText(nama_lapangan);
                txt_view2.setText(keterangan);
                break;
        }

        button100 = (Button) findViewById(R.id.button100);
        button100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(DetailLapanganActivity.this, JadwalPesanLapanganActivity.class);
                startActivity(Intent);
            }
        });

        conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        {
            if (conMgr.getActiveNetworkInfo() != null
                    && conMgr.getActiveNetworkInfo().isAvailable()
                    && conMgr.getActiveNetworkInfo().isConnected()) {
                tampilData(id);
            } else {
                Toast.makeText(getApplicationContext(), "No Internet Connection",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    private void tampilData(final String id) {

        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Data Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);

                        String nama_lapangan = jObj.getString(TAG_LAPANGAN);
                        String keterangan = jObj.getString(TAG_KETERANGAN);
                        SharedPreferences.Editor editor = sharedpreferences.edit();

                    switch(nama_lapangan){
                        case "Lapangan 1":
                            String test = sharedpreferences.getString(TAG_KETERANGAN + "1", null);
                            if(test == null || test != keterangan){
                                txt_view.setText(nama_lapangan);
                                txt_view2.setText(keterangan);
                            }
                            editor.putString(TAG_LAPANGAN + "1", nama_lapangan);
                            editor.putString(TAG_KETERANGAN + "1", keterangan);
                            editor.commit();

                            break;
                        case "Lapangan 2":
                            test = sharedpreferences.getString(TAG_KETERANGAN + "2", null);
                            if(test == null || test != keterangan){
                                txt_view.setText(nama_lapangan);
                                txt_view2.setText(keterangan);
                            }
                            editor.putString(TAG_LAPANGAN + "2", nama_lapangan);
                            editor.putString(TAG_KETERANGAN + "2", keterangan);
                            editor.commit();

                            break;
                        case "Lapangan 3":
                            test = sharedpreferences.getString(TAG_KETERANGAN + "3", null);
                            if(test == null || test != keterangan){
                                txt_view.setText(nama_lapangan);
                                txt_view2.setText(keterangan);
                            }
                            editor.putString(TAG_LAPANGAN + "3", nama_lapangan);
                            editor.putString(TAG_KETERANGAN + "3", keterangan);
                            editor.commit();

                            break;
                        case "Lapangan 4":
                            test = sharedpreferences.getString(TAG_KETERANGAN + "4", null);
                            if(test == null || test != keterangan){
                                txt_view.setText(nama_lapangan);
                                txt_view2.setText(keterangan);
                            }
                            editor.putString(TAG_LAPANGAN + "4", nama_lapangan);
                            editor.putString(TAG_KETERANGAN + "4", keterangan);
                            editor.commit();

                            break;
                        case "Lapangan 5":
                            test = sharedpreferences.getString(TAG_KETERANGAN + "5", null);
                            if(test == null || test != keterangan){
                                txt_view.setText(nama_lapangan);
                                txt_view2.setText(keterangan);
                            }
                            editor.putString(TAG_LAPANGAN + "5", nama_lapangan);
                            editor.putString(TAG_KETERANGAN + "5", keterangan);
                            editor.commit();

                            break;
                        case "Lapangan 6":
                            test = sharedpreferences.getString(TAG_KETERANGAN + "6", null);
                            if(test == null || test != keterangan){
                                txt_view.setText(nama_lapangan);
                                txt_view2.setText(keterangan);
                            }
                            editor.putString(TAG_LAPANGAN + "6", nama_lapangan);
                            editor.putString(TAG_KETERANGAN + "6", keterangan);
                            editor.commit();

                            break;
                        case "Lapangan 7":
                            test = sharedpreferences.getString(TAG_KETERANGAN + "7", null);
                            if(test == null || test != keterangan){
                                txt_view.setText(nama_lapangan);
                                txt_view2.setText(keterangan);
                            }
                            editor.putString(TAG_LAPANGAN + "7", nama_lapangan);
                            editor.putString(TAG_KETERANGAN + "7", keterangan);
                            editor.commit();

                            break;
                    }

                    Log.e("Successfully Get Data!", jObj.toString());

                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", id);


                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }
}
