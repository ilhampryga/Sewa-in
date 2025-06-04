package com.ilham.project_pab;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class EditProfilActivity extends AppCompatActivity {

    private ImageView ivQRIS;
    private TextView etUsername;
    private LinearLayout QRIS;
    private Button btnSimpan;
    private Uri selectedImageUri;

    private ActivityResultLauncher<Intent> galleryLauncher;
    private FirebaseFirestore db;

    private String username;
    private String roleStr;
    EditText etWhatsApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil);

        ivQRIS = findViewById(R.id.ivQRIS);
        etUsername = findViewById(R.id.etUsername);
        etWhatsApp = findViewById(R.id.etWhatsApp);
        QRIS = findViewById(R.id.QRIS);
        btnSimpan = findViewById(R.id.btnSimpan);
        db = FirebaseFirestore.getInstance();

        Intent intentLogin = getIntent();
        if (intentLogin != null) {
            roleStr = intentLogin.getStringExtra("ROLE_KEY");
            username = intentLogin.getStringExtra("USERNAME_KEY");

            etUsername.setText(username);
            db.collection("users").document(username)
                    .get()
                    .addOnSuccessListener(documentSnapshot -> {
                        if (documentSnapshot.exists()) {
                            String phone = documentSnapshot.getString("phone");
                            if (phone != null) {
                                etWhatsApp.setText(phone);
                            }
                        }
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(EditProfilActivity.this, "Gagal mengambil nomor WhatsApp: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });



            if ("toko".equals(roleStr)) {
                QRIS.setVisibility(LinearLayout.VISIBLE);
                loadQRISFromFirestore(username);
            } else {
                QRIS.setVisibility(LinearLayout.GONE);
            }
        }

        galleryLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        selectedImageUri = result.getData().getData();
                        ivQRIS.setImageURI(selectedImageUri);
                    }
                });

        ivQRIS.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            galleryLauncher.launch(intent);
        });

        btnSimpan.setOnClickListener(v -> {
            if (selectedImageUri == null) {
                Toast.makeText(EditProfilActivity.this, "Pilih gambar QRIS terlebih dahulu", Toast.LENGTH_SHORT).show();
                return;
            }
            uploadImageToCloudinary(selectedImageUri);
        });
    }

    private void uploadImageToCloudinary(Uri imageUri) {
        MediaManager.get().upload(imageUri)
                .unsigned("projectPAB")
                .callback(new UploadCallback() {
                    @Override
                    public void onStart(String requestId) {
                        Toast.makeText(EditProfilActivity.this, "Upload dimulai...", Toast.LENGTH_SHORT).show();
                    }

                    private int lastProgressToast = 0;

                    @Override
                    public void onProgress(String requestId, long bytes, long totalBytes) {
                        int progress = (int) ((bytes * 100) / totalBytes);
                        if (progress - lastProgressToast >= 10) {
                            lastProgressToast = progress;
                            runOnUiThread(() ->
                                    Toast.makeText(EditProfilActivity.this, "Upload progress: " + progress + "%", Toast.LENGTH_SHORT).show()
                            );
                        }
                    }

                    @Override
                    public void onSuccess(String requestId, Map resultData) {
                        String imageUrl = (String) resultData.get("secure_url");
                        Toast.makeText(EditProfilActivity.this, "Upload berhasil", Toast.LENGTH_SHORT).show();
                        saveImageUrlToFirestore(imageUrl);
                    }

                    @Override
                    public void onError(String requestId, ErrorInfo error) {
                        Toast.makeText(EditProfilActivity.this, "Upload gagal: " + error.getDescription(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onReschedule(String requestId, ErrorInfo error) {
                        Toast.makeText(EditProfilActivity.this, "Upload dijadwalkan ulang", Toast.LENGTH_SHORT).show();
                    }
                })
                .dispatch();
    }

    private void saveImageUrlToFirestore(String imageUrl) {
        Map<String, Object> data = new HashMap<>();
        data.put("qrisUrl", imageUrl);

        DocumentReference docRef = db.collection("users").document(username);
        docRef.set(data, SetOptions.merge())
                .addOnSuccessListener(aVoid -> Toast.makeText(EditProfilActivity.this, "URL QRIS tersimpan", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> Toast.makeText(EditProfilActivity.this, "Gagal simpan URL: " + e.getMessage(), Toast.LENGTH_SHORT).show());
    }

    private void loadQRISFromFirestore(String username) {
        db.collection("users")
                .document(username)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        String imageUrl = documentSnapshot.getString("qrisUrl");
                        if (imageUrl != null && !imageUrl.isEmpty()) {
                            Glide.with(this)
                                    .load(imageUrl)
                                    .into(ivQRIS);
                        }
                    }
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Gagal mengambil QRIS: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
}
