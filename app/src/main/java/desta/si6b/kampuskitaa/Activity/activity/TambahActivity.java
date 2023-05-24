package desta.si6b.kampuskitaa.Activity.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import desta.si6b.kampuskitaa.Activity.API.APIRequestData;
import desta.si6b.kampuskitaa.Activity.API.RetroServer;
import desta.si6b.kampuskitaa.Activity.Model.ModelRespon;
import desta.si6b.kampuskitaa.R;
import retrofit2.Call;

public class TambahActivity extends AppCompatActivity {
    private EditText etNama, etKota, etAlamat;
    private Button btnTambah;
    private String nama, kota, alamat;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        etNama = findViewById(R.id.et_nama);
        etAlamat = findViewById(R.id.et_alamat);
        etKota = findViewById(R.id.et_kota);
        btnTambah = findViewById(R.id.btn_tambah);

        btnTambah.setOnClickListener(new View.OnClickListener() {
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
                    tambahKampus();
                }
            }
        });

    }
    private void tambahKampus(){
        APIRequestData API = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ModelRespon> proses = API.ardCreate(nama, kota, alamat);

        
    }
}
