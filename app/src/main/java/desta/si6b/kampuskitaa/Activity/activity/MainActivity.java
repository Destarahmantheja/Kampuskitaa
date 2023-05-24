package desta.si6b.kampuskitaa.Activity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import desta.si6b.kampuskitaa.Activity.API.APIRequestData;
import desta.si6b.kampuskitaa.Activity.API.RetroServer;
import desta.si6b.kampuskitaa.Activity.Adapter.AdapterKampus;
import desta.si6b.kampuskitaa.Activity.Model.ModelKampus;
import desta.si6b.kampuskitaa.Activity.Model.ModelRespon;
import desta.si6b.kampuskitaa.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvKampus;
    private FloatingActionButton fabTambah;
    private ProgressBar pbKampus;
    private RecyclerView.Adapter adKampus;

    private RecyclerView.LayoutManager lmKampus;
    private List<ModelKampus> listKampus = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvKampus = findViewById(R.id.rv_kampus);
        fabTambah = findViewById(R.id.fab_tambah);
        pbKampus = findViewById(R.id.pb_kampus);

        lmKampus = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvKampus.setLayoutManager(lmKampus);

        fabTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TambahActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        retrieveKampus();
    }

    public void retrieveKampus(){
        pbKampus.setVisibility(View.VISIBLE);

        APIRequestData API = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ModelRespon> proses = API.ardRetrieve();

        proses.enqueue(new Callback<ModelRespon>() {
            @Override
            public void onResponse(Call<ModelRespon> call, Response<ModelRespon> response) {
                String kode = response.body().getKode();
                String pesan = response.body().getPesan();
                listKampus = response.body().getData();

                adKampus = new AdapterKampus(MainActivity.this, listKampus);
                rvKampus.setAdapter(adKampus);
                adKampus.notifyDataSetChanged();

                pbKampus.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ModelRespon> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Gagal Menghubungi Server!", Toast.LENGTH_SHORT).show();
                pbKampus.setVisibility(View.GONE);

            }
        });

    }
    
}
