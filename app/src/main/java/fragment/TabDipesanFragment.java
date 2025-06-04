package fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.ilham.project_pab.R;

import java.util.ArrayList;

import adapter.BarangPesan;
import adapter.BarangPesanAdapter;

public class TabDipesanFragment extends Fragment {

    ListView listView;
    ArrayList<BarangPesan> listBarang;
    Button btnKonfirmasi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_tab_dipesan, container, false);

        listView = view.findViewById(R.id.listBarang);

        Bundle args = getArguments();
        String role = "";
        if (args != null) {
            role = args.getString("ROLE_KEY", "");
        }


        listBarang = new ArrayList<>();
        listBarang.add(new BarangPesan("Toko Ilham", "Sepatu Gunung", "Rp. 30.000", "Sewa 2 Hari : Rp. 60.000", R.drawable.item2));
        listBarang.add(new BarangPesan("Toko Outdoor Ilham", "Tenda 2 Orang", "Rp. 50.000", "Sewa 3 Hari : Rp. 150.000", R.drawable.item3));

        BarangPesanAdapter adapter = new BarangPesanAdapter(requireContext(), listBarang, role);
        listView.setAdapter(adapter);

        return view;
    }
}
