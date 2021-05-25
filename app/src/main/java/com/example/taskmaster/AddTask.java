package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.query.Where;
import com.amplifyframework.datastore.generated.model.TaskModel;

import java.util.List;


public class AddTask extends AppCompatActivity  {

    EditText add_title,add_desc;
    Button btn_addTask;
    Spinner add_state;
    TextView totalTasks;
    public TaskDatabase db;
    public List<Task> tasks;
    public TaskDao taskDao;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add Task");



        add_title= findViewById(R.id.addtitle);
        add_desc= findViewById(R.id.addDesc);
        add_state= findViewById(R.id.addState);
        btn_addTask= findViewById(R.id.add1);
        totalTasks= findViewById(R.id.total);



        String[] taskState = new String[]{"Select State","new", "in progress", "complete"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, taskState);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        add_state.setAdapter(adapter);

         tasks = TaskDatabase.getInstance(getApplicationContext()).taskDao().getAllTasks();
        totalTasks.setText("Total Tasks: "+ tasks.size());

        db= Room.databaseBuilder(getApplicationContext(),
                TaskDatabase.class, "task_database").allowMainThreadQueries().build();
        taskDao=db.taskDao();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent backArrow =new Intent (getApplicationContext(),MainActivity.class);
        startActivity(backArrow);
        return true;

    }


    public void subimt(View view) {

        String title = add_title.getText().toString();
        String description = add_desc.getText().toString();
        String state = add_state.getSelectedItem().toString();
        boolean tcheck= false;
        boolean dcheck= false;
        boolean scheck= false;

        if(title.isEmpty()){
            add_title.setError("title should not be empty");
        }else{
            add_title.setError(null);
            tcheck= true;

        }
        if(description.isEmpty()){
            add_desc.setError("the Description should not be empty");
        }else{
            add_desc.setError(null);
            dcheck=true;
        }

        if(state.equals("Select State"))
        {
            Toast.makeText(AddTask.this," select State ",Toast.LENGTH_SHORT).show();
        } else{
            scheck = true;
        }

//        Task task = new Task();
//        task.setTitle(title);
//        task.setBody(description);
//        task.setState(state);
//        taskDao.insertTask(task);
        TaskModel item = TaskModel.builder()
                .title(title)
                .body(description)
                .state(state)
                .build();
        if( tcheck && dcheck && scheck) {

            Amplify.DataStore.save(item,
                    success -> Log.i("Tutorial", "Saved item: " + success.item().getTitle()),
                    error -> Log.e("Tutorial", "Could not save item to DataStore", error)
            );


            Intent intent = new Intent(AddTask.this, MainActivity.class);
            Toast.makeText(AddTask.this, "Add Task Successfully", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }

    }
}
