package com.example.co.pickit_app;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.Arrays;
import java.util.List;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class list_adapter extends RecyclerView.Adapter<list_adapter.MyViewHolder>{


    private final List<Pair<String, String>> characters = Arrays.asList(
            Pair.create("Baby", "Enabled"),
            Pair.create("Pepito", "Disabled"),
            Pair.create("Power supply", "Enabled"),
            Pair.create("Keyboard", "Disabled"),
            Pair.create("More Pepito", "Enabled"),
            Pair.create("Teddy Bear <3 ", "Enabled"),
            Pair.create("Color lens", "Enabled"),
            Pair.create("Wooden pencil", "Disabled"),
            Pair.create("Chocolate", "Disabled"),
            Pair.create("WSN paper", "Enabled")
    );

    @Override
    public int getItemCount() {
        return characters.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_list_cell, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Pair<String, String> pair = characters.get(position);
        holder.display(pair);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final TextView description;

        private Pair<String, String> currentPair;

        public MyViewHolder(final View itemView) {
            super(itemView);

            name = ((TextView) itemView.findViewById(R.id.name));
            description = ((TextView) itemView.findViewById(R.id.description));

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), Edit_list.class);
                    view.getContext().startActivity(intent);
                    /*new AlertDialog.Builder(itemView.getContext())
                            .setTitle(currentPair.first)
                            .setMessage(currentPair.second)
                            .show();*/
                }
            });
        }

        public void display(Pair<String, String> pair) {
            currentPair = pair;
            name.setText(pair.first);
            description.setText(pair.second);
        }
    }
}

