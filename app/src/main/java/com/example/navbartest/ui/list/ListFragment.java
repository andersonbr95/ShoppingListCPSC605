package com.example.navbartest.ui.list;

import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.navbartest.R;

import java.util.ArrayList;

public class ListFragment extends Fragment {
   private EditText editUserText;
   Button addItemButton, refreshListButton;
    ListView listFragmentListView;
    ArrayAdapter<String> listFragmentArrayAdapter;
    ArrayList<String> userShoppingList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        userShoppingList = new ArrayList<String>();

        View view = inflater.inflate(R.layout.fragment_list, container, false);
        addItemButton = view.findViewById(R.id.add_item);
        editUserText = view.findViewById(R.id.edit_user_list);
        refreshListButton = view.findViewById(R.id.refresh);


        listFragmentListView = (ListView) view.findViewById(R.id.idListFragmentListView);
        listFragmentArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, userShoppingList);
        listFragmentListView.setAdapter(listFragmentArrayAdapter);

        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userItem = editUserText.getText().toString();

                userShoppingList.add(userItem);
                listFragmentArrayAdapter.notifyDataSetChanged();
            }
        });

        refreshListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               userShoppingList.clear();
               listFragmentArrayAdapter.notifyDataSetChanged();
            }
        });
        return view;


    }

    public void updateEditText(String newText){
        editUserText.setText(newText);
    }


}