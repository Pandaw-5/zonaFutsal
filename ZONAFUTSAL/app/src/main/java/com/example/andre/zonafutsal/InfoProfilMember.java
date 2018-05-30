package com.example.andre.zonafutsal;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.andre.zonafutsal.app.AppController;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class InfoProfilMember extends AppCompatActivity {

    ProgressDialog pDialog;
    private String id;
    int success;
    private ImageView gambar;
    private String url = Server.URL + "infoMember.php";
    private String url2 = Server.URL + "editInfoMember.php";

    SharedPreferences sharedpreferences;

    private static final String TAG = DetailLapanganActivity.class.getSimpleName();

    private static final String TAG_SUCCESS = "success";
    public final static String TAG_NAMA = "nama";
    public final static String TAG_ALAMAT = "alamat";
    public final static String TAG_NOHP = "nohp";
    public static final String my_shared_preferences = "my_shared_preferences";
    public static final String TAG_ID = "id";

    String tag_json_obj = "json_obj_req";
    ConnectivityManager conMgr;

    public TextView txt_nama, txt_alamat, txt_nohp;
    public EditText mNama, mAlamat, mNohp;
    public Button  edit, simpan, batal;
    public LinearLayout one, two;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_profil_member);

        one = findViewById(R.id.linearLayout);
        two = findViewById(R.id.linearLayout1);

        txt_nama = findViewById(R.id.user_nama);
        txt_alamat = findViewById(R.id.user_alamat);
        txt_nohp = findViewById(R.id.user_nohp);

        mNama = findViewById(R.id.user_nama1);
        mAlamat = findViewById(R.id.user_alamat1);
        mNohp = findViewById(R.id.user_nohp1);

        edit = findViewById(R.id.ngedit);
        batal = findViewById(R.id.Batal);
        simpan = findViewById(R.id.edit);

        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        id = sharedpreferences.getString(TAG_ID, null);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUI("EDIT");
            }
        });

        batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUI("BATAL");
            }
        });

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = mNama.getText().toString();
                String alamat = mAlamat.getText().toString();
                String nohp = mNohp.getText().toString();
                conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                {
                    if (conMgr.getActiveNetworkInfo() != null
                            && conMgr.getActiveNetworkInfo().isAvailable()
                            && conMgr.getActiveNetworkInfo().isConnected()) {
                        simpan(id, nama, alamat, nohp);
                    } else {
                        Toast.makeText(getApplicationContext(), "No Internet Connection",
                                Toast.LENGTH_LONG).show();
                    }
                }
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
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Mengambil Data ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Data Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);

                    String nama = jObj.getString(TAG_NAMA);
                    String alamat = jObj.getString(TAG_ALAMAT);
                    String nohp = jObj.getString(TAG_NOHP);

                    txt_nama.setText(nama);
                    txt_alamat.setText(alamat);
                    txt_nohp.setText(nohp);

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

    private void simpan(final String id, final String nama, final String alamat, final String nohp) {
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Menyimpan ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Data Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);
                    if(success == 1){

                    String nama = jObj.getString(TAG_NAMA);
                    String alamat = jObj.getString(TAG_ALAMAT);
                    String nohp = jObj.getString(TAG_NOHP);

                    txt_nama.setText(nama);
                    txt_alamat.setText(alamat);
                    txt_nohp.setText(nohp);

                    updateUI("BATAL");

                    Log.e("Successfully Get Data!", jObj.toString());


                    }
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
                params.put("nama", nama);
                params.put("alamat", alamat);
                params.put("nohp", nohp);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    private void updateUI(String uiState) {
        switch (uiState) {
            case "EDIT":
                // Initialized state, show only the phone number field and start button
                one.setVisibility(View.GONE);
                two.setVisibility(View.VISIBLE);
                mNama.setText(txt_nama.getText().toString());
                mAlamat.setText(txt_alamat.getText().toString());
                mNohp.setText(txt_nohp.getText().toString());

                break;
            case "BATAL":
                // Code sent state, show the verification field, the
                one.setVisibility(View.VISIBLE);
                two.setVisibility(View.GONE);

                break;
        }
    }
}
