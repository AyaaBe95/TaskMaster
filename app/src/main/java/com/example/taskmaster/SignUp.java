package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;

public class SignUp extends AppCompatActivity {
    EditText username, email, password;
    String newName, newEmail, newPassword, nameOfuser;
    Button signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        username = findViewById(R.id.userInput);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        signUp = findViewById(R.id.signUp);
    }

    public void signup(View view) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        nameOfuser = sharedPreferences.getString("username", "User");
        nameOfuser = username.getText().toString();
        newName = ( username.getText()).toString();
        newEmail = ( email.getText()).toString();
        newPassword = ( password.getText()).toString();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", nameOfuser);
        editor.apply();
        AuthSignUpOptions options = AuthSignUpOptions.builder()
                .userAttribute(AuthUserAttributeKey.email(), newEmail)
                .build();
        Amplify.Auth.signUp(newName, newPassword, options,
                result -> Log.i("AuthQuickStart", "Result: " + result.toString()),
                error -> Log.e("AuthQuickStart", "Sign up failed", error)
        );

        Intent intent = new Intent(SignUp.this, ConfirmEmail.class);
        intent.putExtra("username", newName);
        startActivity(intent);

    }
}