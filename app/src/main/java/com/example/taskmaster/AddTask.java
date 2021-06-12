package com.example.taskmaster;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.FileUtils;
import android.provider.OpenableColumns;
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

import com.amplifyframework.AmplifyException;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.query.Where;
import com.amplifyframework.datastore.generated.model.TaskModel;
import com.amplifyframework.storage.s3.AWSS3StoragePlugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;


public class AddTask extends AppCompatActivity {

    EditText add_title, add_desc;
    Button btn_addTask;
    Spinner add_state;
    TextView totalTasks;
    public TaskDatabase db;
    public List<Task> tasks;
    public TaskDao taskDao;

    String filePath = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add Task");

        try {
            // Add these lines to add the AWSCognitoAuthPlugin and AWSS3StoragePlugin plugins
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.addPlugin(new AWSS3StoragePlugin());
            Amplify.configure(getApplicationContext());

            Log.i("MyAmplifyApp", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
        }


        add_title = findViewById(R.id.addtitle);
        add_desc = findViewById(R.id.addDesc);
        add_state = findViewById(R.id.addState);
        btn_addTask = findViewById(R.id.add1);
        totalTasks = findViewById(R.id.total);


        String[] taskState = new String[]{"Select State", "new", "in progress", "complete"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, taskState);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        add_state.setAdapter(adapter);

        tasks = TaskDatabase.getInstance(getApplicationContext()).taskDao().getAllTasks();
        totalTasks.setText("Total Tasks: " + tasks.size());

        db = Room.databaseBuilder(getApplicationContext(),
                TaskDatabase.class, "task_database").allowMainThreadQueries().build();
        taskDao = db.taskDao();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent backArrow = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(backArrow);
        return true;

    }


    public void subimt(View view) {

        String title = add_title.getText().toString();
        String description = add_desc.getText().toString();
        String state = add_state.getSelectedItem().toString();


//        Task task = new Task();
//        task.setTitle(title);
//        task.setBody(description);
//        task.setState(state);
//        taskDao.insertTask(task);
        TaskModel item = TaskModel.builder()
                .title(title)
                .body(description)
                .state(state)
                .file(filePath)
                .build();

        Amplify.DataStore.save(item,
                success -> Log.i("Tutorial", "Saved item: " + success.item().getTitle()),
                error -> Log.e("Tutorial", "Could not save item to DataStore", error)
        );


        Intent intent = new Intent(AddTask.this, MainActivity.class);
        Toast.makeText(AddTask.this, "Add Task Successfully", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

    public void uploadFile(Context context, Uri uri, String fileName) {
        File file = new File(context.getFilesDir(), fileName);
        File file2 = copy(uri, file);
        Amplify.Storage.uploadFile(
                fileName,
                file,
                result -> Log.i("MyAmplifyApp", "Successfully uploaded: " + result.getKey()),
                storageFailure -> Log.e("MyAmplifyApp", "Upload failed", storageFailure)
        );


    }
    public void getFileFromMobileStorage(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        startActivityForResult(intent,RESULT_OK);
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        filePath = getFileName(data.getData());
        if (requestCode == AWSCognitoAuthPlugin.WEB_UI_SIGN_IN_ACTIVITY_CODE){
            Amplify.Auth.handleWebUISignInResponse(data);
        }

        if (requestCode == RESULT_OK){
            File file = new File(getApplicationContext().getFilesDir(), "uploads");
            try {
                InputStream inputStream = getContentResolver().openInputStream(data.getData());
                FileUtils.copy(inputStream, new FileOutputStream(file));
                uploadFile(this, data.getData(), filePath);
                filePath = file.getName();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getFileName(Uri uri){
        Cursor result = getContentResolver().query(uri, null, null, null, null);
        int index = result.getColumnIndex(OpenableColumns.DISPLAY_NAME);
        result.moveToFirst();
        return result.getString(index);
    }

    public void getFile(View view) {
        getFileFromMobileStorage();
    }

    private File copy(Uri source, File destination){
        try{
            InputStream inputStream = getContentResolver().openInputStream(source);
            OutputStream outputStream = new FileOutputStream(destination);
            byte[] buffer = new byte[1024];
            int len;
            while((len = inputStream.read(buffer)) > 0){
                outputStream.write(buffer, 0, len);
            }
            outputStream.close();
            inputStream.close();
            return destination;
        }catch (IOException e){
            e.printStackTrace();
        }
        return new File(source.toString());
    }

}

