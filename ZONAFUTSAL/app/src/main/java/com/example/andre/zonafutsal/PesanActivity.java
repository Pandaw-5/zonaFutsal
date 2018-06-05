package com.example.andre.zonafutsal;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PesanActivity extends AppCompatActivity {
    private String id, nama;
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

    public TextView txt_nama, txt_lapangan, txt_tanggal, txt_jam, txt_harga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesan);

        txt_nama = findViewById(R.id.iduser);
        txt_lapangan = findViewById(R.id.lapangan);
        txt_tanggal = findViewById(R.id.tanggal);
        txt_jam = findViewById(R.id.jam);
        txt_harga = findViewById(R.id.idharga);


        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        id = sharedpreferences.getString(TAG_ID, null);
        nama = sharedpreferences.getString(TAG_NAMA, null);

//        simpan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String nama = mNama.getText().toString();
//                String alamat = mAlamat.getText().toString();
//                String nohp = mNohp.getText().toString();
//                conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//                {
//                    if (conMgr.getActiveNetworkInfo() != null
//                            && conMgr.getActiveNetworkInfo().isAvailable()
//                            && conMgr.getActiveNetworkInfo().isConnected()) {
//                        simpan(id, nama, alamat, nohp);
//                    } else {
//                        Toast.makeText(getApplicationContext(), "No Internet Connection",
//                                Toast.LENGTH_LONG).show();
//                    }
//                }
//            }
//        });
    }
}
