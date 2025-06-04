package fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.ilham.project_pab.R;

import java.util.ArrayList;

import adapter.BarangSelesai;
import adapter.BarangSelesaiAdapter;

public class TabSelesaiFragment extends Fragment {

    ListView listView;
    ArrayList<BarangSelesai> listBarang;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_tab_selesai, container, false);

        listView = view.findViewById(R.id.listBarang);

        listBarang = new ArrayList<>();
        listBarang.add(new BarangSelesai("Toko Outdoor Ilham", "Kursi Lipat", "Rp. 10.000", "Selesai 1 Hari : Rp. 10.000", R.drawable.item6));
        listBarang.add(new BarangSelesai("Toko Kamera Ilham", "Kamera DSLR Canon", "Rp. 95.000", "Selesai 3 Hari : Rp. 285.000", R.drawable.item5));
        listBarang.add(new BarangSelesai("Toko Kamera Ilham", "Kamera DSLR Canon", "Rp. 95.000", "Selesai 3 Hari : Rp. 285.000", R.drawable.item5));
        listBarang.add(new BarangSelesai("Toko Kamera Ilham", "Kamera DSLR Canon", "Rp. 95.000", "Selesai 3 Hari : Rp. 285.000", R.drawable.item5));
        listBarang.add(new BarangSelesai("Toko Kamera Ilham", "Kamera DSLR Canon", "Rp. 95.000", "Selesai 3 Hari : Rp. 285.000", R.drawable.item5));
        listBarang.add(new BarangSelesai("Toko Kamera Ilham", "Kamera DSLR Canon", "Rp. 95.000", "Selesai 3 Hari : Rp. 285.000", R.drawable.item5));
        listBarang.add(new BarangSelesai("Toko Kamera Ilham", "Kamera DSLR Canon", "Rp. 95.000", "Selesai 3 Hari : Rp. 285.000", R.drawable.item5));
        listBarang.add(new BarangSelesai("Toko Kamera Ilham", "Kamera DSLR Canon", "Rp. 95.000", "Selesai 3 Hari : Rp. 285.000", R.drawable.item5));
        listBarang.add(new BarangSelesai("Toko Kamera Ilham", "Kamera DSLR Canon", "Rp. 95.000", "Selesai 3 Hari : Rp. 285.000", R.drawable.item5));
        listBarang.add(new BarangSelesai("Toko Kamera Ilham", "Kamera DSLR Canon", "Rp. 95.000", "Selesai 3 Hari : Rp. 285.000", R.drawable.item5));
        listBarang.add(new BarangSelesai("Toko Kamera Ilham", "Kamera DSLR Canon", "Rp. 95.000", "Selesai 3 Hari : Rp. 285.000", R.drawable.item5));
        listBarang.add(new BarangSelesai("Toko Kamera Ilham", "Kamera DSLR Canon", "Rp. 95.000", "Selesai 3 Hari : Rp. 285.000", R.drawable.item5));
        listBarang.add(new BarangSelesai("Toko Kamera Ilham", "Kamera DSLR Canon", "Rp. 95.000", "Selesai 3 Hari : Rp. 285.000", R.drawable.item5));
        listBarang.add(new BarangSelesai("Toko Kamera Ilham", "Kamera DSLR Canon", "Rp. 95.000", "Selesai 3 Hari : Rp. 285.000", R.drawable.item5));
        listBarang.add(new BarangSelesai("Toko Kamera Ilham", "Kamera DSLR Canon", "Rp. 95.000", "Selesai 3 Hari : Rp. 285.000", R.drawable.item5));


        BarangSelesaiAdapter adapter = new BarangSelesaiAdapter(requireContext(), listBarang);
        listView.setAdapter(adapter);

        return view;
    }
}
