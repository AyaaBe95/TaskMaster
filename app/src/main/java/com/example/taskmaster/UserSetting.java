package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UserSetting extends AppCompatActivity {
    String nameOfuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_setting);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Settings");
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        nameOfuser = sharedPreferences.getString("username", "User");
        Button save = findViewById(R.id.save);
        save.setOnClickListener((view) ->{
            EditText username  = findViewById(R.id.username);
            nameOfuser =  username.getText().toString();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("username", nameOfuser);
            editor.apply();
            Intent main = new Intent(UserSetting.this, MainActivity.class);
            startActivity(main);
        });
    }

    public void signup(View view) {
        Intent intent = new Intent(UserSetting.this, SignUp.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent backArrow =new Intent (getApplicationContext(),MainActivity.class);
        startActivity(backArrow);
        return true;

    }
}
