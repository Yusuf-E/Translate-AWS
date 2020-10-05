package com.example.translate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText emailTextLogin, passwordTextLogin;
    String email,password;
    Button login;
    TextView register;
    boolean isEmailValid, isPasswordValid;
    TextInputLayout emailError, passError;
    private FirebaseAuth loginAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dologin();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // redirect to RegisterActivity
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        emailTextLogin = (EditText) findViewById(R.id.email);
        passwordTextLogin = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        register = (TextView) findViewById(R.id.register);
        emailError = (TextInputLayout) findViewById(R.id.emailError);
        passError = (TextInputLayout) findViewById(R.id.passError);
    }

    private void dologin() {
        loginAuth = FirebaseAuth.getInstance();
        email=emailTextLogin.getText().toString();
        password=passwordTextLogin.getText().toString();

        if(!(email.isEmpty())&&!(password.isEmpty())){
            loginAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        System.out.println("Giriş Başarılı");
                        Toast.makeText(LoginActivity.this,"Giriş Başarılı",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        System.out.println("Giriş Başarısız");
                        Toast.makeText(LoginActivity.this,"Giriş Başarısız",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}
