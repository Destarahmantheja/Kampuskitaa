package desta.si6b.kampuskitaa.Activity.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import desta.si6b.kampuskitaa.Activity.API.APIRequestData;
import desta.si6b.kampuskitaa.Activity.API.RetroServer;
import desta.si6b.kampuskitaa.Activity.Model.ModelKampus;
import desta.si6b.kampuskitaa.Activity.Model.ModelRespon;
import desta.si6b.kampuskitaa.Activity.activity.MainActivity;
import desta.si6b.kampuskitaa.Activity.activity.UbahActivity;
import desta.si6b.kampuskitaa.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AdapterKampus extends RecyclerView.Adapter<AdapterKampus.VHKampus>{

    private Context ctx;
    private List<ModelKampus> listKampus;

    public AdapterKampus(Context ctx, List<ModelKampus> listKampus){
        this.ctx = ctx;
        this.listKampus = listKampus;
    }
    @NonNull
    @Override
    public VHKampus onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View varView = LayoutInflater.from(ctx).inflate(R.layout.list_item_kampus, parent, false);
        return new VHKampus(varView);
    }

    @Override
    public void onBindViewHolder(@NonNull VHKampus holder, int position) {
        ModelKampus MK = listKampus.get(position);
        holder.tvId.setText(MK.getId());
        holder.tvNama.setText(MK.getNama());
        holder.tvKota.setText(MK.getKota());
        holder.tvAlamat.setText(MK.getAlamat());
    }

    @Override
    public int getItemCount() {
        return listKampus.size();
    }

    public class VHKampus extends RecyclerView.ViewHolder{
        TextView tvId, tvNama, tvKota, tvAlamat;
        Button btnHapus, btnUbah;

        public VHKampus(@NonNull View itemView) {
            super(itemView);

            tvId = itemView.findViewById(R.id.tv_id);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvKota = itemView.findViewById(R.id.tv_kota);
            tvAlamat = itemView.findViewById(R.id.tv_alamat);
            btnHapus = itemView.findViewById(R.id.btn_hapus);
            btnUbah = itemView.findViewById(R.id.btn_ubah);

            btnHapus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteKampus(tvId.getText().toString());
                }
            });

            btnUbah.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent pindah = new Intent(ctx, UbahActivity.class);
                    pindah.putExtra("xId",tvId.getText().toString());
                    pindah.putExtra("xNama",tvNama.getText().toString());
                    pindah.putExtra("xKota",tvKota.getText().toString());
                    pindah.putExtra("xAlamat",tvAlamat.getText().toString());
                    ctx.startActivity(pindah);
                }
            });
        }

        void deleteKampus(String id){
            APIRequestData API = RetroServer.konekRetrofit().create(APIRequestData.class);
            Call<ModelRespon> proses = API.ardDelete(id);

            proses.enqueue(new Callback<ModelRespon>() {
                @Override
                public void onResponse(Call<ModelRespon> call, Response<ModelRespon> response) {
                    String kode = response.body().getKode();
                    String pesan = response.body().getPesan();

                    Toast.makeText(ctx, "Kode: " + kode + " Pesan: " + pesan, Toast.LENGTH_SHORT).show();
                    ((MainActivity) ctx).retrieveKampus();
                }

                @Override
                public void onFailure(Call<ModelRespon> call, Throwable t) {
                    Toast.makeText(ctx, "Error! Gagal Menghubungi Server!", Toast.LENGTH_SHORT).show();

                }
            });
        }
    }
}
