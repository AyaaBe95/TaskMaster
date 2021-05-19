package com.example.taskmaster;

import android.content.Intent;
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
      private OnItemClickListener mListener;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView taskTitle;
        private TextView taskBody;
        private TextView taskState;
        private OnItemClickListener mListener;



        public ViewHolder( View itemView,OnItemClickListener listener) {
            super(itemView);
            this.taskTitle= itemView.findViewById(R.id.tTitle);
            this.taskBody=itemView.findViewById(R.id.tBody);
            this.taskState=itemView.findViewById(R.id.tState);
            this.mListener=listener;
            itemView.setOnClickListener(this);



        }


        @Override
        public void onClick(View view) {
         mListener.onItemClick(getAdapterPosition());
        }
    }


    public TaskAdapter(List<Task> tasks,OnItemClickListener listener) {
        this.tasks = tasks;
        this.mListener=listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.fragment_task, parent, false);

        ViewHolder viewHolder = new ViewHolder(v,mListener);
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

    public interface OnItemClickListener{
        void onItemClick(int position);
    }


}
