package com.example.anmol.myapplication;

import android.content.Context;
import android.graphics.Paint;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anmol on 01-05-2016.
 */
public class RecycleAdaptor extends RecyclerView.Adapter<RecycleAdaptor.ViewHolder>  {

    List<Task> todoList;
    DbHelper db;
    Context context;
    public EditNoteInterface myOnEditListener;



    RecycleAdaptor(List<Task> todoList,Context context) {
        this.todoList = todoList;
        this.context=context;
        db=new DbHelper(context);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        CardView cv;
        public TextView noteText;
        public TextView noteId;
        Toolbar toolbar;

        public ViewHolder(View itemView){
            super(itemView);
            noteText = (TextView) itemView.findViewById(R.id.note_text);
            noteId = (TextView) itemView.findViewById(R.id.note_id);
            toolbar = (Toolbar) itemView.findViewById(R.id.toolbar_card);
            if (toolbar != null)
                toolbar.inflateMenu(R.menu.card_toolbar);


        }
    }
    // Involves populating data into the item through holder


    @Override
    public int getItemCount() {
        return todoList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


        holder.noteText.setText(todoList.get(position).getTaskName());
        holder.noteId.setText(String.valueOf(position));

        holder.toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            int index;
            int note_id;

            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menu_edit:
                        index = holder.getAdapterPosition();
                        note_id = todoList.get(index).getId();
                        editNote(note_id);
                        return true;
                    case R.id.menu_delete:
                        index = holder.getAdapterPosition();
                        note_id = todoList.get(index).getId();
                        deleteNote(position,note_id);
                        return true;
                    default:
                        break;
                }
                return false;
            }
        });
    }


    public void editNote(int note_id)
    {
        myOnEditListener = ((MainActivity) context);
        myOnEditListener.editNote(note_id);
    }

    public void  deleteNote(int position,int note_id)
    {
        todoList.remove(position);
        notifyItemRemoved(note_id);
        db.delete(note_id);
    }
    public interface EditNoteInterface
    {
        public void editNote(int id);
    }

}
