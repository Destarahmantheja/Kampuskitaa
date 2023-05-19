package desta.si6b.kampuskitaa.Activity.Adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import desta.si6b.kampuskitaa.R;

public class AdapterKampus {

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
