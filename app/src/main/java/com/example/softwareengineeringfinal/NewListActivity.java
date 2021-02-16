package com.example.softwareengineeringfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.LinkedList;

public class NewListActivity extends AppCompatActivity {

    Button save, refresh;

    EditText name;
    ArrayAdapter arrayAdapter;
    ListView testList;
    LinkedList<String> link_list;
    private ListView listView;

    @Override
    protected void onCreate(Bundle readInstanceState){
        super.onCreate(readInstanceState);
        setContentView(R.layout.activity_main);
        link_list = new LinkedList<String>();
        findViewById(R.id.refresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayAdapter.notifyDataSetChanged();
                listView.invalidateViews();
                listView.refreshDrawableState();
            }
        });

        findViewById(R.id.save).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (!name.getText().toString().isEmpty()) {
                    link_list.add(name.getText().toString());
                    arrayAdapter = new ArrayAdapter(NewListActivity.this, android.R.layout.simple_list_item_1, link_list);
                    listView.setAdapter(arrayAdapter);
                    Toast.makeText(NewListActivity.this, "Inserted", Toast.LENGTH_LONG).show();
                } else{
                    name.setError("Enter Item");
                }
            }
        });//end save click listener

//Click Listener for new Maps Activity -- needs to be added still
        findViewById(R.id.find).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(NewListActivity.this, NewMapsActivity.class));
            }
        });//end find items click listener

    }
}