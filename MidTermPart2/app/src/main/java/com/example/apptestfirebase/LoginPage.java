package com.example.apptestfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginPage extends AppCompatActivity {

    private Button btnLogin;
    private FirebaseAuth mAuth;
    private TextInputEditText edtEmail;
    private TextInputEditText edtPass;
    private TextView btnSignupPage;
    private TextView resetPass;
    private CheckBox cbRemember;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        mAuth = FirebaseAuth.getInstance();

        btnLogin = (Button) findViewById(R.id.btnLoginPage);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
            }
        });
        edtEmail = (TextInputEditText) findViewById(R.id.inputEmail);
        edtPass = (TextInputEditText) findViewById(R.id.inputPassword);
        btnSignupPage = (TextView) findViewById(R.id.return_Signup);
        btnSignupPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginPage.this, SignUpPage.class);
                startActivity(intent);
            }
        });
        //resetpass
        resetPass = (TextView) findViewById(R.id.forgorPass);
        resetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginPage.this, ForgotPassword.class);
                startActivity(intent);
            }
        });
        // checked
        cbRemember = (CheckBox) findViewById(R.id.checkBox);
        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
        edtEmail.setText(sharedPreferences.getString("taikhoan", ""));
        edtPass.setText(sharedPreferences.getString("matkhau", ""));
        cbRemember.setChecked(sharedPreferences.getBoolean("checked", false));

    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }
    private void Login() {
        String emailLogin = edtEmail.getText().toString();
        String passLogin = edtPass.getText().toString();
        mAuth.signInWithEmailAndPassword(emailLogin, passLogin)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            if (cbRemember.isChecked()) {
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("taikhoan", emailLogin);
                                editor.putString("matkhau", passLogin);
                                editor.putBoolean("checked", true);
                                editor.commit();
                            } else {
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.remove("taikhoan");
                                editor.remove("matkhau");
                                editor.remove("checked");
                                editor.commit();
                            }
                            Intent intent = new Intent(LoginPage.this, MainActivity.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(LoginPage.this, "Login Fail!!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}