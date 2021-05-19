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

        TextView title = findViewById(R.id.textView);
        TextView para = findViewById(R.id.textView7);
        TextView state = findViewById(R.id.textView8);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        String Title = getIntent().getStringExtra("title");
        String Body = getIntent().getStringExtra("body");
        String State = getIntent().getStringExtra("state");

        title.setText(sharedPreferences.getString("username", "User")+"'s "+ Title + " Detail");
        para.setText(Body);
        state.setText(State);

        if(Title==null){
            title.setText(sharedPreferences.getString("username", "User")+"'s Task Detail");
            para.setText("Empty");
            state.setText("Empty");
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent backArrow =new Intent (getApplicationContext(),MainActivity.class);
        startActivity(backArrow);
        return true;

    }
}
