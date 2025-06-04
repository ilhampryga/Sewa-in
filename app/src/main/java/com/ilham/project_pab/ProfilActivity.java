package com.ilham.project_pab;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfilActivity extends AppCompatActivity {
    TextView tvHome, tvPesanan, tvUsername, role;
    LinearLayout profile;
    Button btnLogout, btnTambahProduk;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        tvHome = findViewById(R.id.tvHome);
        tvPesanan = findViewById(R.id.tvPesanan);
        profile = findViewById(R.id.profile);
        tvUsername = findViewById(R.id.tvUsername);
        btnLogout = findViewById(R.id.btnLogout);
        btnTambahProduk = findViewById(R.id.btnProduk);
        role = findViewById(R.id.role);

        Intent intentLogin = getIntent();
        if (intentLogin != null) {
            String usernameStr = intentLogin.getStringExtra("USERNAME_KEY");
            String roleStr = intentLogin.getStringExtra("ROLE_KEY");

            tvUsername.setText(usernameStr);
            role.setText(roleStr);

            if ("toko".equals(roleStr)) {
                btnTambahProduk.setVisibility(View.VISIBLE);
                btnTambahProduk.setOnClickListener(v -> {
                    Intent intent = new Intent(ProfilActivity.this, TambahProdukActivity.class);
                    startActivity(intent);
                });
            } else {
                btnTambahProduk.setVisibility(View.GONE);
            }
        }

        profile.setOnClickListener(v -> {
            Intent intentProfile = new Intent(ProfilActivity.this, EditProfilActivity.class);
            intentProfile.putExtra("USERNAME_KEY", getIntent().getStringExtra("USERNAME_KEY"));
            intentProfile.putExtra("ROLE_KEY", getIntent().getStringExtra("ROLE_KEY"));
            startActivity(intentProfile);
            finish();
        });

        tvPesanan.setOnClickListener(v -> {
            Intent intentPesanan = new Intent(ProfilActivity.this, PesanankuActivity.class);
            startActivity(intentPesanan);
            finish();
        });

        tvHome.setOnClickListener(v -> {
            Intent intentHome = new Intent(ProfilActivity.this, HomeActivity.class);
            intentHome.putExtra("USERNAME_KEY", getIntent().getStringExtra("USERNAME_KEY"));
            intentHome.putExtra("ROLE_KEY", getIntent().getStringExtra("ROLE_KEY"));
            startActivity(intentHome);
            finish();
        });
    }
}
