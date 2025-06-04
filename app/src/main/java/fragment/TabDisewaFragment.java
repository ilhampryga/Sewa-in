package fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.ilham.project_pab.R;

import java.util.ArrayList;

import adapter.BarangSewa;
import adapter.BarangSewaAdapter;

public class TabDisewaFragment extends Fragment {

    ListView listView;
    ArrayList<BarangSewa> listBarang;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewSewa = inflater.inflate(R.layout.activity_tab_disewa, container, false);
        listView = viewSewa.findViewById(R.id.listBarang);

        Bundle args = getArguments();
        String role = "";
        if (args != null) {
            role = args.getString("ROLE_KEY", "");
        }


        listBarang = new ArrayList<>();
        listBarang.add(new BarangSewa("Toko Kebaya Ilham", "Kebaya Burgundy", "Rp. 75.000", "Sewa 3 Hari : Rp. 225.000", R.drawable.item1));
        listBarang.add(new BarangSewa("Toko Jas Ilham", "Jas Formal Pria", "Rp. 70.000", "Sewa 3 Hari : Rp. 210.000", R.drawable.item4));
        listBarang.add(new BarangSewa("Toko Kamera Ilham", "Kamera DSLR Canon", "Rp. 95.000", "Sewa 3 Hari : Rp. 285.000", R.drawable.item5));


        BarangSewaAdapter adapter = new BarangSewaAdapter(requireContext(), listBarang, role);
        listView.setAdapter(adapter);

        return viewSewa;
    }
}
