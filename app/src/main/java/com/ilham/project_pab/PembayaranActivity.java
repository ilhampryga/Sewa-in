package com.ilham.project_pab;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PembayaranActivity extends AppCompatActivity {

    Button btnKonfirmasi;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran);
        btnKonfirmasi = findViewById(R.id.btnKonfirmasi);

        btnKonfirmasi.setOnClickListener(v -> {
            Intent intentKonfirmasi = new Intent(PembayaranActivity.this, PesanankuActivity.class);
            startActivity(intentKonfirmasi);
            finish();
        });
    }
}
