package com.example.navbartest.ui.list;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.navbartest.R;
import com.example.navbartest.SharedViewModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ListFragment extends Fragment implements EditListDialog.ListNameDialogListener{
   private EditText editUserText,editListName;
   Button addItemButton, refreshListButton, saveListButton;
   Button addListButton;
   ListView listFragmentListView;
   ArrayAdapter<String> listFragmentArrayAdapter;
   ArrayList<String> shoppingList;
   private DatabaseReference listDatabase;
   SharedViewModel shared;


   Bundle bundle = new Bundle();

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_list, container, false);

        return view;
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        shoppingList = new ArrayList<String>();
        shared = new ViewModelProvider(this).get(SharedViewModel.class);

        addItemButton = view.findViewById(R.id.add_item);
        addListButton = view.findViewById(R.id.add_list);
        editUserText = view.findViewById(R.id.edit_user_list);
        editListName = view.findViewById(R.id.enter_list_name);
        refreshListButton = view.findViewById(R.id.refresh);
        saveListButton = view.findViewById(R.id.find);



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
                editUserText.setText("");
                listFragmentArrayAdapter.notifyDataSetChanged();
            }
        });

        refreshListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoppingList.clear();
                listFragmentArrayAdapter.notifyDataSetChanged();
            }
        });

        saveListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listDatabase = FirebaseDatabase.getInstance().getReference();
                String listName = editListName.getText().toString();
                //openNameListDialog();
                //saveList();
                int i = 0;
                while(shoppingList.size() > i){
                    listDatabase.child(listName).child("item " + (i+1)).setValue(shoppingList.get(i));
                    i++;
                }
                listDatabase.push();

                listFragmentArrayAdapter.notifyDataSetChanged();
            }
        });


        /* Will add the list items and navigate to the map fragment */
        addListButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if (!shoppingList.isEmpty() ) {
                    bundle.putStringArrayList("list", shoppingList);
                    shared.addList(shoppingList);
                    Navigation.findNavController(view).navigate(R.id.nav_view_maps, bundle);
                }
            }
        });


    }

    public void openNameListDialog(){
        EditListDialog editListName = new EditListDialog();
        editListName.show(getParentFragmentManager(), "set list name");
    }

    @Override
    public void applyTexts(String listName) {
        
    }

    public void saveList(){
        int i = 0;
        while(shoppingList.size() > i){
            listDatabase.child("list name").child("item " + (i+1)).setValue(shoppingList.get(i));
            i++;
        }
        listDatabase.push();
    }
}
