package com.ilham.project_pab;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import adapter.PesanankuPagerAdapter;

public class PesanankuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pesananku);

        String username = getIntent().getStringExtra("USERNAME_KEY");
        String role = getIntent().getStringExtra("ROLE_KEY");

        Bundle bundle = new Bundle();
        bundle.putString("USERNAME_KEY", username);
        bundle.putString("ROLE_KEY", role);

        PesanankuPagerAdapter adapter = new PesanankuPagerAdapter(this);
        adapter.setDataBundle(bundle); // <- tambahkan method ini di adapter

        ViewPager2 viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0: tab.setText("Dipesan"); break;
                case 1: tab.setText("Disewa"); break;
                case 2: tab.setText("Selesai"); break;
            }
        }).attach();
    }
}
