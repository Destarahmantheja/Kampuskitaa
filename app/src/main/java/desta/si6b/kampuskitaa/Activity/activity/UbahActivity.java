package desta.si6b.kampuskitaa.Activity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import desta.si6b.kampuskitaa.Activity.API.APIRequestData;
import desta.si6b.kampuskitaa.Activity.API.RetroServer;
import desta.si6b.kampuskitaa.Activity.Model.ModelRespon;
import desta.si6b.kampuskitaa.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UbahActivity extends AppCompatActivity {
    private String yId, yNama, yKota, yAlamat;
    private EditText etNama, etKota, etAlamat;
    private Button btnUbah;
    private String nama, kota, alamat;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah);

        //tangkap kiriman dari sbelah
        Intent ambil = getIntent();
        yId = ambil.getStringExtra("xId");
        yNama = ambil.getStringExtra("xNama");
        yKota = ambil.getStringExtra("xKota");
        yAlamat = ambil.getStringExtra("xAlamat");

        //findview kan semua view/widget yang dipakai
        etNama = findViewById(R.id.et_nama);
        etAlamat = findViewById(R.id.et_alamat);
        etKota = findViewById(R.id.et_kota);
        btnUbah = findViewById(R.id.btn_ubah);

        //set kiriman yang sudah ditangkap tadi, ke view/widget yang sudah difindview tadi
        etNama.setText(yNama);
        etKota.setText(yKota);
        etAlamat.setText(yAlamat);

        //mulai mainkan kalau tombol ubah diklik
        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama = etNama.getText().toString();
                kota = etKota.getText().toString();
                alamat = etAlamat.getText().toString();

                if (nama.trim().isEmpty()){
                    etNama.setError("Nama Tidak Boleh Kosong");

                }
                else if (kota.trim().isEmpty()){
                    etKota.setError("Kota Tidak Boleh Kosong");
                }
                else if (alamat.trim().isEmpty()){
                    etAlamat.setError("Halaman Tidak Boleh Kosong");
                }
                else {
                    ubahKampus();
                }
            }
        });
    }
    private void ubahKampus(){
        APIRequestData API = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ModelRespon> proses = API.ardUpdate(yId, nama, kota, alamat);

        proses.enqueue(new Callback<ModelRespon>() {
            @Override
            public void onResponse(Call<ModelRespon> call, Response<ModelRespon> response) {
                String kode, pesan;
                kode = response.body().getKode();
                pesan = response.body().getPesan();

                Toast.makeText(UbahActivity.this,"Kode : " + kode + "Pesan : " + pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ModelRespon> call, Throwable t) {
                Toast.makeText(UbahActivity.this, "Gagal Menghubungi Server!", Toast.LENGTH_SHORT).show();
                Log.d("Disini","Errornya itu: "+ t.getMessage());

            }
        });
    }
}
