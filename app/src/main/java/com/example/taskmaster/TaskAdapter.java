package com.example.taskmaster;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    public List<Task> tasks=new ArrayList<Task>();
    public TaskAdapter(List<Task> tasks ) {
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.fragment_task, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;

    }

    //pass information
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task task = tasks.get(position);

        TextView taskTitle = holder.taskTitle;
        taskTitle.setText(task.getTitle());

        TextView taskBody = holder.taskBody;
        taskBody.setText(task.getBody());

        TextView taskState = holder.taskState;
        taskState.setText(task.getState());


    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    //references
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView taskTitle;
        private TextView taskBody;
        private TextView taskState;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            taskTitle = itemView.findViewById(R.id.tTitle);
            taskBody = itemView.findViewById(R.id.tBody);
            taskState = itemView.findViewById(R.id.tState);
        }
    }
}
