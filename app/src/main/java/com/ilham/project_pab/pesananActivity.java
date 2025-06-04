package com.ilham.project_pab;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class pesananActivity extends AppCompatActivity {

    Button btnSewaSekarang, btnChatToko;
    TextView  tvNamaProduk, tvHarga;
    ImageView ivProduk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pesanan);

        btnSewaSekarang = findViewById(R.id.btnSewaSekarang);
        btnChatToko = findViewById(R.id.btnChatToko);
        tvNamaProduk = findViewById(R.id.tvNamaProduk);
        tvHarga = findViewById(R.id.tvHarga);
        ivProduk = findViewById(R.id.ivProduk);

        String deskripsi = getIntent().getStringExtra("item_description");
        tvNamaProduk.setText(deskripsi);

        String harga = getIntent().getStringExtra("item_price");
        tvHarga.setText(harga);

        int imageResId = getIntent().getIntExtra("item_image", 0);
        ivProduk.setImageResource(imageResId);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        btnSewaSekarang.setOnClickListener(v -> {
            Intent intentCheckout = new Intent(pesananActivity.this, CheckoutActivity.class);
            intentCheckout.putExtra("item_description", deskripsi);
            intentCheckout.putExtra("item_price", harga);
            intentCheckout.putExtra("item_image", imageResId);
            startActivity(intentCheckout);
            finish();
        });

        btnChatToko.setOnClickListener(v -> {
            String nomorWhatsApp = "6285797208591";
            String url = "https://wa.me/" + nomorWhatsApp;

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));

            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            } else {
                Toast.makeText(this, "Tidak ada aplikasi yang dapat membuka link ini", Toast.LENGTH_SHORT).show();
            }
        });


    }

}
