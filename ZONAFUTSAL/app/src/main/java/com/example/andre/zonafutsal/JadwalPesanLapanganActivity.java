package com.example.andre.zonafutsal;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.andre.zonafutsal.app.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class JadwalPesanLapanganActivity extends AppCompatActivity {
    Button btn12;
    String lapangan, radio, durasi;
    int success;
    int hour, minute, mYear, mMonth, mDay;

    ConnectivityManager conMgr;

    private String url = Server.URL + "register.php";

    private static final String TAG = DaftarActivity.class.getSimpleName();

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    public final static String TAG_USERNAME = "nohp";
    public final static String TAG_ID = "id";
    public final static String TAG_NAMA = "nama";

    SharedPreferences sharedpreferences;

    String tag_json_obj = "json_obj_req";

    Boolean session = false;

    public static final String my_shared_preferences = "my_shared_preferences";
    public static final String session_status = "session_status";

    static final int DATE_DIALOG_ID = 1;
    private TextView txtDate;
    private String[] arrMonth = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    private RadioGroup radiogrup;
    private RadioButton radio1, radio2, radio3, radio4;

    private Spinner spinner1, spinner2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_pesan_lapangan);

        radiogrup = findViewById(R.id.radioGroupJam);

        radio1 = findViewById(R.id.radioPilih1);
        radio2 = findViewById(R.id.radioPilih2);
        radio3 = findViewById(R.id.radioPilih3);
        radio4 = findViewById(R.id.radioPilih4);

            btn12 = (Button)findViewById(R.id.buttonPesan);

            btn12.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String jam = pilihradio();
                    String tanggal = txtDate.getText().toString();

                    if (conMgr.getActiveNetworkInfo() != null
                            && conMgr.getActiveNetworkInfo().isAvailable()
                            && conMgr.getActiveNetworkInfo().isConnected()) {
                        checkLapangan(lapangan, tanggal, jam, durasi);
                    } else {
                        Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        session = sharedpreferences.getBoolean(session_status, false);


        if (session == false) {
            Toast.makeText(getApplicationContext(), "Anda harus masuk terlebih dahulu", Toast.LENGTH_LONG).show();
            Intent a = new Intent(getApplicationContext(), MasukActivity.class);
            startActivity(a);
        }

        txtDate = (TextView) findViewById(R.id.txtDate);
        // get the current date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        txtDate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                // TODO Auto-generated method stub
                showDialog(DATE_DIALOG_ID);
                return false;
            }
        });
        spinner1 = (Spinner) findViewById(R.id.spinner);
        spinner1.setOnItemSelectedListener(new ItemSelectedListener());
        spinner2 = findViewById(R.id.spinner2);
        spinner2.setOnItemSelectedListener(new ItemSelectedListener2());
    }

    public class ItemSelectedListener implements AdapterView.OnItemSelectedListener {

        //get strings of first item
        String firstItem = String.valueOf(spinner1.getSelectedItem());

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            if (firstItem.equals(String.valueOf(spinner1.getSelectedItem()))) {
                // ToDo when first item is selected
            } else {
                lapangan = parent.getItemAtPosition(pos).toString();
                Toast.makeText(parent.getContext(),
                        "Kamu telah memilih : " + parent.getItemAtPosition(pos).toString(),
                        Toast.LENGTH_LONG).show();
                // Todo when item is selected by the user
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg) {

        }
    }

    public class ItemSelectedListener2 implements AdapterView.OnItemSelectedListener {

        //get strings of first item
        String firstItem = String.valueOf(spinner1.getSelectedItem());

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            if (firstItem.equals(String.valueOf(spinner1.getSelectedItem()))) {
                durasi = parent.getItemAtPosition(pos).toString();
            } else {
                durasi = parent.getItemAtPosition(pos).toString();
                Toast.makeText(parent.getContext(),
                        "Kamu telah memilih : " + parent.getItemAtPosition(pos).toString(),
                        Toast.LENGTH_LONG).show();
                // Todo when item is selected by the user
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg) {

        }
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(
                        this, mDateSetListener, mYear, mMonth, mDay);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;
                    String sdate = arrMonth[mMonth] + " " + LPad(mDay + "", "0", 2) + ", " + mYear;
                    txtDate.setText(sdate);
                }
            };

    private static String LPad(String schar, String spad, int len) {
        String sret = schar;
        for (int i = sret.length(); i < len; i++) {
            sret = spad + sret;
        }
        return new String(sret);
    }
    public void onCheckedChanged(RadioGroup group,
                                 int checkedId) {
    }

    public String pilihradio(){
        int selecId = radiogrup.getCheckedRadioButtonId();
        if(selecId == radio1.getId()){
            radio = radio1.getText().toString();
        } else if(selecId == radio2.getId()){
            radio = radio2.getText().toString();
        } else if(selecId == radio3.getId()){
            radio = radio3.getText().toString();
        } else if(selecId == radio4.getId()){
            radio = radio4.getText().toString();
        }
        return radio;

    }

    private void checkLapangan(final String lapangan, final String tanggal, final String jam, final String durasi) {


        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Register Response: " + response.toString());


                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Check for error node in json
                    if (success == 1) {
                        String harga = jObj.getString("harga");

                        Log.e("Successfully Register!", jObj.toString());

                        Intent intent = new Intent(getApplicationContext(), PesanActivity.class);
                        intent.putExtra("lapangan", lapangan);
                        intent.putExtra("tanggal", tanggal);
                        intent.putExtra("durasi", durasi);
                        intent.putExtra("jam", jam);
                        intent.putExtra("harga", harga);
                        finish();
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
                params.put("lapangan", lapangan);
                params.put("jam", jam);
                params.put("durasi", durasi);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }
}