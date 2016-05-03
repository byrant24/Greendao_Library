package com.example.anmol.myapplication;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Anmol on 01-05-2016.
 */
public class RecycleAdaptor extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<Task> todoList;

    RecycleAdaptor(ArrayList<Task> todoList) {
        this.todoList = todoList;
    }

    // a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public static class TaskHolder extends RecyclerView.ViewHolder {

        CardView cv;
        // holder should contain a member variable
        // for any view that will be set as you render a row
        public CheckBox isTaskDone;
        public TextView taskName;
        public TextView tasktime;
        //  holder should contain a member variable
        // for any view that will be set as you render a row
        public TaskHolder(View itemView){
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.card_view);
            isTaskDone = (CheckBox) itemView.findViewById(R.id.checkBox1);
            taskName = (TextView) itemView.findViewById(R.id.todotext);
            tasktime = (TextView) itemView.findViewById(R.id.tasktime);
        }
    }
    // Involves populating data into the item through holder


    @Override
    public int getItemCount() {
        return todoList.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        // Inflate the custom layout
        View taskView=inflater.inflate(R.layout.row_view,parent,false);
        // Return a new holder instance
        return new TaskHolder(taskView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TaskHolder viewHolder = (TaskHolder) holder;

        if (todoList.get(position).getStatus() == 0) {
            viewHolder.isTaskDone.setChecked(false);
        }
        else {
            viewHolder.isTaskDone.setChecked(true);
            //Striking through the todo task after notification
            viewHolder.taskName.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }

        viewHolder.taskName.setText(todoList.get(position).getTaskName());
        viewHolder.tasktime.setText(todoList.get(position).getTime());
    }

}
