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


public class obj_adapter extends RecyclerView.Adapter<obj_adapter.MyViewHolder>{


    Data data = new Data();
    public ArrayList<String> Data_obj = data.getList();

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;
        final String EXTRA_NAME = "name_error";
        private final TextView name;

        public MyViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.name_obj);
            name =(TextView) v.findViewById(R.id.name_obj);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), Del_obj.class);
                    intent.putExtra(EXTRA_NAME, name.getText().toString());
                    view.getContext().startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return Data_obj.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_obj_cell, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mTextView.setText(Data_obj.get(position));
    }


}

