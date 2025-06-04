package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ilham.project_pab.R;

public class GridAdapter extends BaseAdapter {

    Context context;
    String[] descriptions;
    String[] prices;
    int[] images;
    LayoutInflater inflater;

    public GridAdapter(Context context, String[] descriptions, String[] prices, int[] images) {
        this.context = context;
        this.descriptions = descriptions;
        this.prices = prices;
        this.images = images;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return descriptions.length;
    }

    @Override
    public Object getItem(int position) {
        return descriptions[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder {
        ImageView image;
        TextView description, price;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_grid, parent, false);
            holder = new ViewHolder();
            holder.image = convertView.findViewById(R.id.image);
            holder.description = convertView.findViewById(R.id.description);
            holder.price = convertView.findViewById(R.id.price);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.image.setImageResource(images[position]);
        holder.description.setText(descriptions[position]);
        holder.price.setText(prices[position]);

        return convertView;
    }
}
