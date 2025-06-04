package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ilham.project_pab.R;

import java.util.List;

public class BarangSewaAdapter extends BaseAdapter {

    private Context context;
    private List<BarangSewa> listBarang;
    private String role;

    public BarangSewaAdapter(Context context, List<BarangSewa> listBarang, String role) {
        this.context = context;
        this.listBarang = listBarang;
        this.role = role;
    }

    @Override
    public int getCount() {
        return listBarang.size();
    }

    @Override
    public Object getItem(int position) {
        return listBarang.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BarangSewa barang = listBarang.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_item_tab_disewa, parent, false);
        }

        TextView seller = convertView.findViewById(R.id.seller);
        TextView description = convertView.findViewById(R.id.description);
        TextView price = convertView.findViewById(R.id.price);
        TextView totalPrice = convertView.findViewById(R.id.totalPrice);
        ImageView image = convertView.findViewById(R.id.image);
        Button btnKonfirmasi = convertView.findViewById(R.id.btnKonfirmasi);

        if (seller != null) seller.setText(barang.getSeller());
        if (description != null) description.setText(barang.getDescription());
        if (price != null) price.setText(barang.getPrice());
        if (totalPrice != null) totalPrice.setText(barang.getTotalPrice());
        if (image != null) image.setImageResource(barang.getImageResId());

        // Cegah NullPointerException
        if (btnKonfirmasi != null) {
            if ("user".equalsIgnoreCase(role)) {
                btnKonfirmasi.setVisibility(View.VISIBLE);
            } else {
                btnKonfirmasi.setVisibility(View.GONE);
            }
        }
        return convertView;
    }
}
