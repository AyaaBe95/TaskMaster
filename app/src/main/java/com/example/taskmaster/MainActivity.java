package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TaskAdapter.OnItemClickListener {

    String title1,title2,title3;
    private TextView taskTitle;
    private TextView taskBody;
    private TextView taskState;
    public TaskDatabase db;
    public TaskDao taskDao;


    ArrayList<Task> tasks = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        taskTitle = findViewById(R.id.tTitle);
        taskBody = findViewById(R.id.tBody);
        taskState =findViewById(R.id.tState);




        TextView welcomeMsg = findViewById(R.id.welcome);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        welcomeMsg.setText(sharedPreferences.getString("username", "User") + "'s Tasks");

        db= Room.databaseBuilder(getApplicationContext(),
                TaskDatabase.class, "task_database").allowMainThreadQueries().build();
        taskDao = db.taskDao();
        tasks = (ArrayList<Task>) taskDao.getAllTasks();


        RecyclerView recyclerView ;

        recyclerView = findViewById(R.id.recyclerView);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new TaskAdapter(tasks, this));



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


    @Override
    public void onItemClick(int position) {
        Intent intent =new Intent(this, Details.class);
        intent.putExtra("title",tasks.get(position).getTitle());
        intent.putExtra("body",tasks.get(position).getBody());
        intent.putExtra("state",tasks.get(position).getState());

        startActivity(intent);
    }


}
