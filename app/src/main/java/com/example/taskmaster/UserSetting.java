package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class UserSetting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_setting);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Button save = findViewById(R.id.save);
        save.setOnClickListener((view) ->{
            EditText userNameField = findViewById(R.id.username);
            String name = userNameField.getText().toString();
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("username", name);
            editor.apply();
            Intent main = new Intent(UserSetting.this, MainActivity.class);
            startActivity(main);
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent backArrow =new Intent (getApplicationContext(),MainActivity.class);
        startActivity(backArrow);
        return true;

    }
}
