package desta.si6b.kampuskitaa.Activity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import desta.si6b.kampuskitaa.R;

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

    }

}
