package com.example.navbartest.ui.list;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.navbartest.R;
import com.example.navbartest.ui.map.MapFragment;

import java.util.ArrayList;

public class ListFragment extends Fragment {
   private EditText editUserText;
   Button addItemButton;
   Button addListButton;
   ListView listFragmentListView;
   ArrayAdapter<String> listFragmentArrayAdapter;
   ArrayList<String> shoppingList;
   Bundle bundle = new Bundle();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        shoppingList = new ArrayList<String>();

        View view = inflater.inflate(R.layout.fragment_list, container, false);
        addItemButton = view.findViewById(R.id.add_item);
        addListButton = view.findViewById(R.id.add_list);
        editUserText = view.findViewById(R.id.edit_user_list);
        refreshListButton = view.findViewById(R.id.refresh);


        listFragmentListView = (ListView) view.findViewById(R.id.idListFragmentListView);
        listFragmentArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, shoppingList);
        listFragmentListView.setAdapter(listFragmentArrayAdapter);


        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userItem = editUserText.getText().toString();
                String[] items = userItem.split("\n");
                for(String item : items){
                    shoppingList.add(item);
                }
                listFragmentArrayAdapter.notifyDataSetChanged();
            }
        });
        /* Will add the list items and navigate to the map fragment */
        addListButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                bundle.putStringArrayList("list", shoppingList);
                Navigation.findNavController(view).navigate(R.id.nav_view_maps, bundle);
            }
        });
        return view;


    }

}
