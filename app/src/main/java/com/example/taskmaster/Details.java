package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.widget.TextView;

public class Details extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView display = findViewById(R.id.display);
        TextView para = findViewById(R.id.para);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        String title=getIntent().getStringExtra("title");
//        display.setText(title + " Details");
        display.setText(sharedPreferences.getString("username", "User")+"'s "+ title + " Detail");

        para.setText("consectetur, iz Lorem Ipsum teksta, i prolazeći kroz citate te riječi u klasičnoj književnosti, otkrio nedvojbeni izvor. Lorem Ipsum dolazi iz odlomaka 1.10.32 i 1.10.33 Ciceronovog djela pod naslovom  (Krajnosti dobra i zla),");
        if(title==null){
            display.setText(sharedPreferences.getString("username", "User")+"'s Task Detail");
            para.setText("No details");
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent backArrow =new Intent (getApplicationContext(),MainActivity.class);
        startActivity(backArrow);
        return true;

    }
}
