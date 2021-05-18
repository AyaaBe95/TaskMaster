package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String title1,title2,title3;
    private TextView taskTitle;
    private TextView taskBody;
    private TextView taskState;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        taskTitle = findViewById(R.id.tTitle);
        taskBody = findViewById(R.id.tBody);
        taskState =findViewById(R.id.tState);

        ArrayList<Task> tasks = new ArrayList<>();

        tasks.add(new Task("Task 1","Solve Homework 1", "Complete"));
        tasks.add(new Task("Task 2","Solve Homework 2", "In progress"));
        tasks.add(new Task("Task 3","Solve Homework 3", "New"));
        RecyclerView recyclerView ;

        recyclerView = findViewById(R.id.recyclerView);

        TaskAdapter adapter = new TaskAdapter(tasks);
        recyclerView.setAdapter(adapter);

        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linear=  new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linear);


        TextView welcomeMsg = findViewById(R.id.welcome);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        welcomeMsg.setText(sharedPreferences.getString("username", "User") + "'s Tasks");





    }

    public void addTaskPage(View view) {
        Intent addTask = new Intent(MainActivity.this,AddTask.class);

        startActivity(addTask);
    }

    public void allTaskPage(View view) {
        Intent allTasks = new Intent(MainActivity.this,alltasks.class);
        startActivity(allTasks);

    }


    public void goToSettings(View view) {
        Intent settingActivity = new Intent(MainActivity.this,UserSetting.class);
        startActivity(settingActivity);

    }
}
