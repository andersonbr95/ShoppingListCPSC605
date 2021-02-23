package com.example.softwareengineeringfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import com.google.android.gms.maps.GoogleMap;

import java.util.ArrayList;
import java.util.LinkedList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class NewListActivity extends AppCompatActivity {

    Button save, refresh;

    EditText name;
    public static final String EXTRA_LIST = "array_list";
    ArrayAdapter arrayAdapter;
    ArrayList<String> array_list; /* changed to arraylist to be used with intent */
    private ListView listView;
    private GoogleMap mMap;



    @Override
    protected void onCreate(Bundle readInstanceState){
        super.onCreate(readInstanceState);
        setContentView(R.layout.activity_main);
        array_list = new ArrayList<>();
        findViewById(R.id.add_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayAdapter.notifyDataSetChanged();
                listView.invalidateViews();
                listView.refreshDrawableState();
            }
        });

        //end save click listener

//Click Listener for new Maps Activity -- needs to be added still
        findViewById(R.id.find).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(NewListActivity.this, NewMapsActivity.class);
                intent.putExtra(EXTRA_LIST, array_list); /* put extra before sending to NewMapsActivity */
                startActivity(intent);
            }

            Intent intent = new Intent(NewListActivity.this, NewMapsActivity.class);
        });//end find items click listener

    }
}
