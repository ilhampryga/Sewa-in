package com.ilham.project_pab;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CheckoutActivity extends AppCompatActivity {

    TextView tvNamaProduk, tvHarga, tvHargaPesanan;
    ImageView ivProduk;
    Button btnCheckout;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        tvNamaProduk = findViewById(R.id.tvNamaProduk);
        ivProduk = findViewById(R.id.ivProduk);
        tvHarga = findViewById(R.id.tvHarga);
        tvHargaPesanan = findViewById(R.id.tvHargaPesanan);
        btnCheckout = findViewById(R.id.btnCheckout);

        String deskripsi = getIntent().getStringExtra("item_description");
        tvNamaProduk.setText(deskripsi);

        String harga = getIntent().getStringExtra("item_price");
        tvHarga.setText(harga);
        tvHargaPesanan.setText(harga);

        int imageResId = getIntent().getIntExtra("item_image", 0);
        ivProduk.setImageResource(imageResId);

        btnCheckout.setOnClickListener(v ->{
            Intent intentCheckout = new Intent(CheckoutActivity.this, PembayaranActivity.class);
            startActivity(intentCheckout);
            finish();
        } );
    }
}
