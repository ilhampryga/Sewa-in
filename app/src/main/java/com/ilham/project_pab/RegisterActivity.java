package com.ilham.project_pab;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    TextView tvLogin;
    CheckBox cbTerms;
    EditText etUsername, etPassword, etPhone;
    Button btnRegister, btnToko;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        cbTerms = findViewById(R.id.cbTerms);
        tvLogin = findViewById(R.id.tvLogin);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etPhone = findViewById(R.id.etPhone);
        btnToko = findViewById(R.id.btnToko);
        btnRegister = findViewById(R.id.btnRegister);
        db = FirebaseFirestore.getInstance();

        cbTerms.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cbTerms.setTextColor(isChecked ? Color.parseColor("#0C2A1A") : Color.parseColor("#000000"));
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLogin = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intentLogin);
                finish();
            }
        });

        btnToko.setOnClickListener(v -> {
            Intent intentToko = new Intent(RegisterActivity.this, RegisterTokoActivity.class);
            startActivity(intentToko);
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String phone = etPhone.getText().toString().trim();

                if (username.isEmpty() || password.isEmpty() || phone.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Semua field harus diisi", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!cbTerms.isChecked()) {
                    Toast.makeText(RegisterActivity.this, "Anda harus menyetujui syarat & ketentuan", Toast.LENGTH_SHORT).show();
                    return;
                }

                Map<String, Object> user = new HashMap<>();
                user.put("username", username);
                user.put("password", password);
                user.put("phone", phone);
                user.put("role", "user");

                db.collection("users").document(username)
                        .set(user)
                        .addOnSuccessListener(aVoid -> {
                            Toast.makeText(RegisterActivity.this, "Registrasi berhasil", Toast.LENGTH_SHORT).show();
                            Intent intentLogin = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(intentLogin);
                            finish();
                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(RegisterActivity.this, "Gagal registrasi: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        });
            }
        });
    }
}
