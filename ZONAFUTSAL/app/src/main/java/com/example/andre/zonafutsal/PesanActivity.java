package com.example.andre.zonafutsal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class PesanActivity extends AppCompatActivity {
    private String id, nama;
    int success;
    private ImageView gambar;
    private String url = Server.URL + "pemesananFix.php";

    SharedPreferences sharedpreferences;

    private static final String TAG = DetailLapanganActivity.class.getSimpleName();

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    public final static String TAG_NAMA = "nama";
    public final static String TAG_ALAMAT = "alamat";
    public final static String TAG_NOHP = "nohp";
    public static final String my_shared_preferences = "my_shared_preferences";
    public static final String TAG_ID = "id";

    String tag_json_obj = "json_obj_req";
    ConnectivityManager conMgr;

    public TextView txt_nama, txt_lapangan, txt_tanggal, txt_jam, txt_harga;
    public Button simpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesan);

        txt_nama = findViewById(R.id.iduser);
        txt_lapangan = findViewById(R.id.lapangan);
        txt_tanggal = findViewById(R.id.tanggal);
        txt_jam = findViewById(R.id.jam);
        txt_harga = findViewById(R.id.idharga);

        simpan = findViewById(R.id.btnTransfer);

        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        id = sharedpreferences.getString(TAG_ID, null);
        nama = sharedpreferences.getString(TAG_NAMA, null);

        txt_nama.setText(nama);
        txt_lapangan.setText(getIntent().getStringExtra("lapangan"));
        txt_tanggal.setText(getIntent().getStringExtra("tanggal"));
        txt_jam.setText(getIntent().getStringExtra("jam"));
        txt_harga.setText(getIntent().getStringExtra("harga"));

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lapangan = getIntent().getStringExtra("lapangan");
                String tanggal = getIntent().getStringExtra("tanggal");
                String jam = getIntent().getStringExtra("jam");
                String durasi = getIntent().getStringExtra("durasi");
                String harga = getIntent().getStringExtra("harga");
                conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                {
                    if (conMgr.getActiveNetworkInfo() != null
                            && conMgr.getActiveNetworkInfo().isAvailable()
                            && conMgr.getActiveNetworkInfo().isConnected()) {
                        simpan(id, nama, lapangan, tanggal, jam, durasi, harga);
                    } else {
                        Toast.makeText(getApplicationContext(), "No Internet Connection",
                                Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }

    private void simpan(final String id, final String nama, final String lapangan, final String tanggal, final String jam, final String durasi, final String harga) {


        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Register Response: " + response.toString());


                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Check for error node in json
                    if (success == 1) {


                        Log.e("Successfully Register!", jObj.toString());

                        Intent intent = new Intent(getApplicationContext(), TampilanPasKlikDetailLap.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(getApplicationContext(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", id);
                params.put("nama", nama);
                params.put("lapangan", lapangan);
                params.put("tanggal", tanggal);
                params.put("jam", jam);
                params.put("durasi", durasi);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }
}
