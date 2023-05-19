package desta.si6b.kampuskitaa.Activity.activity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import desta.si6b.kampuskitaa.Activity.Model.ModelKampus;
import desta.si6b.kampuskitaa.R;

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
    }

    
}
