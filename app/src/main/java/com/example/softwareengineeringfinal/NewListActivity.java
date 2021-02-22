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

<<<<<<< Updated upstream
import java.util.ArrayList;
import java.util.LinkedList;
=======
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
>>>>>>> Stashed changes

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
                    array_list.add(name.getText().toString());
                    arrayAdapter = new ArrayAdapter(NewListActivity.this, android.R.layout.simple_list_item_1, array_list);
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
                Intent intent = new Intent(NewListActivity.this, NewMapsActivity.class);
                intent.putExtra(EXTRA_LIST, array_list); /* put extra before sending to NewMapsActivity */
                startActivity(intent);
            }

            Intent intent = new Intent(NewListActivity.this, NewMapsActivity.class);
            intent.putExtra(EXTRA_LIST, (Serializable) array_list); /* put extra before sending to NewMapsActivity */
            startActivity(intent);
        });//end find items click listener

    }
}
