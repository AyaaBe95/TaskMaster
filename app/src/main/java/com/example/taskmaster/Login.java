package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.amplifyframework.core.Amplify;

public class Login extends AppCompatActivity {
    EditText username, password;
    Button login_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.logname);
        password = findViewById(R.id.logPass);
        login_btn = findViewById(R.id.login);
    }

    public void login(View view) {

        String str_username = username.getText().toString().trim();
        String str_password = password.getText().toString().trim();


            Amplify.Auth.signIn(
                    str_username,
                    str_password,
                    result -> Log.i("AuthQuickstart", result.isSignInComplete() ? "Sign in succeeded" : "Sign in not complete"),
                    error -> Log.e("AuthQuickstart", error.toString())
            );
            Intent i = new Intent(Login.this, MainActivity.class);
            i.putExtra("username", str_username);
            startActivity(i);
        }
    }




