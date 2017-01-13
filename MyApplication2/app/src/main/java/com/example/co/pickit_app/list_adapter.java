package com.example.co.pickit_app;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class list_adapter extends RecyclerView.Adapter<list_adapter.MyViewHolder>{

    Data_list_list myList = new Data_list_list();
    ArrayList<Data_list> list = myList.getList_List();

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_list_cell, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.display(holder,list.get(position));
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final TextView description;
        final String EXTRA_NAME = "name_error";

        public MyViewHolder(final View itemView) {
            super(itemView);

            name = ((TextView) itemView.findViewById(R.id.name));
            description = ((TextView) itemView.findViewById(R.id.description));//State of the list

            // Listen to a click on a the list and open the details (Enable/disable; object in the list)
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), Edit_list.class);
                    intent.putExtra(EXTRA_NAME, name.getText().toString());
                    view.getContext().startActivity(intent);
                }
            });
        }

        public void display(MyViewHolder holder, Data_list list) {
            Log.d("display", list.getName());
            holder.name.setText(list.getName());
            if (list.getState())
                holder.description.setText("Enable");
            else
                holder.description.setText("Disable");
        }
    }
}

