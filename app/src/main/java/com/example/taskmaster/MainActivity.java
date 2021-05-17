package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button task1,task2,task3;
    String title1,title2,title3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.addtask);
        Button button2 = (Button) findViewById(R.id.alltask);
        task1=findViewById(R.id.task1);
        task2=findViewById(R.id.task2);
        task3=findViewById(R.id.task3);

        TextView welcomeMsg = findViewById(R.id.welcome);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        welcomeMsg.setText(sharedPreferences.getString("username", "User") + "'s Tasks");



        task1.setText("Task 1");
        task2.setText("Task 2");
        task3.setText("Task 3");

         title1= task1.getText().toString();
         title2= task2.getText().toString();
         title3= task3.getText().toString();

    }

    public void addTaskPage(View view) {
        Intent addTask = new Intent(MainActivity.this,AddTask.class);

        startActivity(addTask);
    }

    public void allTaskPage(View view) {
        Intent allTasks = new Intent(MainActivity.this,alltasks.class);
        startActivity(allTasks);

    }


    public void taskOne(View view) {
        Intent taskOne = new Intent(MainActivity.this,Details.class);
        taskOne.putExtra("title", title1);
        startActivity(taskOne);



    }

    public void task2(View view) {
        Intent taskTwo = new Intent(MainActivity.this,Details.class);
        taskTwo.putExtra("title", title2);
        startActivity(taskTwo);

    }


    public void task3(View view) {
        Intent taskThree = new Intent(MainActivity.this,Details.class);
        taskThree.putExtra("title", title3);
        startActivity(taskThree);

    }

    public void goToSettings(View view) {
        Intent settingActivity = new Intent(MainActivity.this,UserSetting.class);
        startActivity(settingActivity);

    }
}
