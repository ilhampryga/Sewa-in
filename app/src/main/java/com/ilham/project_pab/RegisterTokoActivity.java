package com.ilham.project_pab;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterTokoActivity extends AppCompatActivity {

    EditText etUsername, etPassword, etPhone, etNamaToko;
    Button btnRegister;
    CheckBox cbTerms;
    FirebaseFirestore db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_toko);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etPhone = findViewById(R.id.etPhone);
        etNamaToko = findViewById(R.id.etNamaToko);
        btnRegister = findViewById(R.id.btnRegister);
        cbTerms = findViewById(R.id.cbTerms);
        db = FirebaseFirestore.getInstance();

        cbTerms.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cbTerms.setTextColor(isChecked ? Color.parseColor("#0C2A1A") : Color.parseColor("#000000"));
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String phone = etPhone.getText().toString().trim();
                String namaToko = etNamaToko.getText().toString().trim();

                if (username.isEmpty() || password.isEmpty() || phone.isEmpty() || namaToko.isEmpty()) {
                    Toast.makeText(RegisterTokoActivity.this, "Semua field harus diisi", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!cbTerms.isChecked()) {
                    Toast.makeText(RegisterTokoActivity.this, "Anda harus menyetujui syarat & ketentuan", Toast.LENGTH_SHORT).show();
                    return;
                }

                Map<String, Object> user = new HashMap<>();
                user.put("username", username);
                user.put("password", password);
                user.put("phone", phone);
                user.put("namaToko", namaToko);
                user.put("role", "toko");

                db.collection("users").document(username)
                        .set(user)
                        .addOnSuccessListener(aVoid -> {
                            Toast.makeText(RegisterTokoActivity.this, "Registrasi toko berhasil", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterTokoActivity.this, MainActivity.class));
                            finish();
                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(RegisterTokoActivity.this, "Gagal registrasi: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        });
            }
        });
    }
}
