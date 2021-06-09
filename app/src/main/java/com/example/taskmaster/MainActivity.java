package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.generated.model.TaskModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TaskAdapter.OnItemClickListener {

    String title1,title2,title3;
    private TextView taskTitle;
    private TextView taskBody;
    private TextView taskState;
    public TaskDatabase db;
    public TaskDao taskDao;
    TaskAdapter taskAdapter;
    RecyclerView recyclerView ;

    List<Task> tasks = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("My Tasks");

        taskTitle = findViewById(R.id.tTitle);
        taskBody = findViewById(R.id.tBody);
        taskState =findViewById(R.id.tState);

        try {
            Amplify.addPlugin(new AWSDataStorePlugin());
            Amplify.configure(getApplicationContext());

            Log.i("Tutorial", "Initialized Amplify");
        } catch (AmplifyException e) {
            Log.e("Tutorial", "Could not initialize Amplify", e);
        }

        TextView welcomeMsg = findViewById(R.id.welcome);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        welcomeMsg.setText(sharedPreferences.getString("username", "User") + "'s Tasks");

//        db= Room.databaseBuilder(getApplicationContext(),
//                TaskDatabase.class, "task_database").allowMainThreadQueries().build();
//        taskDao = db.taskDao();
//        tasks = (ArrayList<Task>) taskDao.getAllTasks();
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Amplify.DataStore.query(TaskModel.class,
                todos -> {
                    List<Task> taskList = new ArrayList<>();
                    while (todos.hasNext()) {
                        TaskModel todo = todos.next();
                        Task t = new Task();
                        Log.i("Tutorial", "==== Todo ====");
                        Log.i("Tutorial", "Name: " + todo.getTitle());
                        Log.i("Tutorial", "Name: " + todo.getBody());
                        Log.i("Tutorial", "Name: " + todo.getState());
                        t.setTitle(todo.getTitle());
                        t.setBody(todo.getBody());
                        t.setState(todo.getState());
                        taskList.add(t);
                        taskAdapter = new TaskAdapter(MainActivity.class,taskList);

                    }
                    recyclerView.setAdapter(taskAdapter);
                },
                failure -> Log.e("Tutorial", "Could not query DataStore", failure)
        );


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
