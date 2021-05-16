package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.addtask);
        Button button2 = (Button) findViewById(R.id.alltask);




    }

    public void addTaskPage(View view) {
        Intent addTask = new Intent(MainActivity.this,AddTask.class);
        startActivity(addTask);
    }

    public void allTaskPage(View view) {
        Intent allTasks = new Intent(MainActivity.this,alltasks.class);
        startActivity(allTasks);

    }
}
