package com.ilham.project_pab;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.GridView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import adapter.GridAdapter;

public class HomeActivity extends AppCompatActivity {
    AutoCompleteTextView ac;
    TextView tvPesanan, username, tvProfil;

    public String[] dataSearch = {"Baju Kebaya", "Baju Stelan Jas Pria", "Baju Gaun Pengantin"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        tvPesanan = findViewById(R.id.tvPesanan);
        username = findViewById(R.id.username);
        tvProfil = findViewById(R.id.tvProfil);
        GridView gridView = findViewById(R.id.gridView);

        Intent intentLogin = getIntent();
        if (intentLogin != null) {
            String usernameStr = intentLogin.getStringExtra("USERNAME_KEY");
            username.setText(usernameStr);
        }

        tvPesanan.setOnClickListener(v -> {
            Intent intentPesanan = new Intent(HomeActivity.this, PesanankuActivity.class);
            intentPesanan.putExtra("USERNAME_KEY", getIntent().getStringExtra("USERNAME_KEY"));
            intentPesanan.putExtra("ROLE_KEY", getIntent().getStringExtra("ROLE_KEY"));
            startActivity(intentPesanan);
        });

        tvProfil.setOnClickListener(v -> {
            Intent intentProfil = new Intent(HomeActivity.this, ProfilActivity.class);
            intentProfil.putExtra("USERNAME_KEY", getIntent().getStringExtra("USERNAME_KEY"));
            intentProfil.putExtra("ROLE_KEY", getIntent().getStringExtra("ROLE_KEY"));
            startActivity(intentProfil);
        });


        String[] descriptions = {
                "Kebaya Burgundy", "Sepatu Gunung", "Tenda Camping", "Jas Formal Pria", "Kamera DSLR Canon EOS", "Kursi Lipat"
        };

        String[] prices = {
                "Rp 75.000 / Hari", "Rp 30.000 / Hari", "Rp 60.000 / Hari", "Rp 70.000 / Hari", "Rp 95.000 / Hari", "Rp 10.000 / Hari"
        };

        int[] images = {
                R.drawable.item1, R.drawable.item2, R.drawable.item3, R.drawable.item4, R.drawable.item5, R.drawable.item6
        };

        GridAdapter adapter1 = new GridAdapter(this, descriptions, prices, images);
        gridView.setAdapter(adapter1);

        gridView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(HomeActivity.this, pesananActivity.class);
            intent.putExtra("item_description", descriptions[position]);
            intent.putExtra("item_price", prices[position]);
            intent.putExtra("item_image", images[position]);
            startActivity(intent);
        });

        ac = findViewById(R.id.etSearch);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, dataSearch);
        ac.setAdapter(adapter);
    }
}
