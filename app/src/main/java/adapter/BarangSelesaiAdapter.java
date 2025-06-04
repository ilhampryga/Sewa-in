package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ilham.project_pab.R;

import java.util.List;

public class BarangSelesaiAdapter extends BaseAdapter {

    private Context context;
    private List<BarangSelesai> listBarang;

    public BarangSelesaiAdapter(Context context, List<BarangSelesai> listBarang) {
        this.context = context;
        this.listBarang = listBarang;
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
        BarangSelesai barang = listBarang.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_item_tab_selesai, parent, false);
        }

        TextView seller = convertView.findViewById(R.id.seller);
        TextView description = convertView.findViewById(R.id.description);
        TextView price = convertView.findViewById(R.id.price);
        TextView totalPrice = convertView.findViewById(R.id.totalPrice);
        ImageView image = convertView.findViewById(R.id.image);

        seller.setText(barang.getSeller());
        description.setText(barang.getDescription());
        price.setText(barang.getPrice());
        totalPrice.setText(barang.getTotalPrice());
        image.setImageResource(barang.getImageResId());

        return convertView;
    }
}
