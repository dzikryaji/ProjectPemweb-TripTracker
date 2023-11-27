package das.mobile.triptracker.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import das.mobile.triptracker.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding binding;
    private DatabaseReference firebaseDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseDB = FirebaseDatabase.getInstance().getReferenceFromUrl("https://project-papb-7de12-default-rtdb.asia-southeast1.firebasedatabase.app/");

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstName = binding.etFirstName.getText().toString();
                String lastName = binding.etLastName.getText().toString();
                String phone = binding.etPhone.getText().toString();
                String age = binding.etAge.getText().toString();
                String email = binding.etEmail.getText().toString();
                String password = binding.etPassword.getText().toString();

                if (firstName.isEmpty() || lastName.isEmpty() || phone.isEmpty() || age.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Ada data yang masih kosong", Toast.LENGTH_SHORT).show();
                } else {
                    firebaseDB = FirebaseDatabase.getInstance().getReference("users");
                    firebaseDB.child(email).child("firstName").setValue(firstName);
                    firebaseDB.child(email).child("lastName").setValue(lastName);
                    firebaseDB.child(email).child("phone").setValue(phone);
                    firebaseDB.child(email).child("age").setValue(age);
                    firebaseDB.child(email).child("email").setValue(email);
                    firebaseDB.child(email).child("password").setValue(password);

                    Toast.makeText(getApplicationContext(), "Register berhasil", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });
    }
}