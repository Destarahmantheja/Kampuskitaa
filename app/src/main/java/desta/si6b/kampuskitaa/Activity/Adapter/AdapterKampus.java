package desta.si6b.kampuskitaa.Activity.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import desta.si6b.kampuskitaa.Activity.Model.ModelKampus;
import desta.si6b.kampuskitaa.R;


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

        public VHKampus(@NonNull View itemView) {
            super(itemView);

            tvId = itemView.findViewById(R.id.tv_id);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvKota = itemView.findViewById(R.id.tv_kota);
            tvAlamat = itemView.findViewById(R.id.tv_alamat);
        }
    }
}
