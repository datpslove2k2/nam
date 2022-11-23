package com.example.apptestfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.checkerframework.common.subtyping.qual.Bottom;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpPage extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mData;
    private Button btnSignup;
    private TextInputLayout layoutPassword;
    private TextInputEditText edtEmail;
    private TextInputEditText edtPass;
    private Button btnLoginPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        AnhXa();
        mAuth = FirebaseAuth.getInstance();
        mData = FirebaseDatabase.getInstance().getReference();

        btnLoginPage = (Button) findViewById(R.id.btn_LoginPage);
        btnLoginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpPage.this, LoginPage.class);
                startActivity(intent);
            }
        });

        edtPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String pass = charSequence.toString();
                if (pass.length() >= 8) {
                    Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
                    Matcher matcher = pattern.matcher(pass);
                    boolean isPassCheck = matcher.find();
                    if (isPassCheck) {
                        layoutPassword.setHelperText("Strong Password");
                        layoutPassword.setError("");
                    } else {
                        layoutPassword.setHelperText("");
                        layoutPassword.setError("Weak Password. Include minium 1 special char.");
                    }

                } else {
                    layoutPassword.setHelperText("Enter Minium 8 char");
                    layoutPassword.setError("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });



        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Signup();
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }
    private void Signup() {
        String email = edtEmail.getText().toString();
        String pass = edtPass.getText().toString();
        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(SignUpPage.this, "Create Account Success!!!", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(SignUpPage.this, "Create Account Fail!!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    private void AnhXa() {
        btnSignup = (Button) findViewById(R.id.btn_Signup);
        edtEmail = (TextInputEditText) findViewById(R.id.inputEmail);
        edtPass = (TextInputEditText) findViewById(R.id.inputPassword);
        layoutPassword = findViewById(R.id.textInputPassword);
    }
}