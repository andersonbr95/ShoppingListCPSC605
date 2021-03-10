package com.example.navbartest.ui.list;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.navbartest.R;

import java.util.Objects;

public class EditListDialog extends AppCompatDialogFragment {

    private EditText editListName;
    private ListNameDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_namelistdialog, null);

        builder.setView(view)
                .setTitle("Name Your List")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })

                .setPositiveButton("Save List", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String listName = editListName.getText().toString();
                        listener.applyTexts(listName);
                    }
                });
        editListName = view.findViewById(R.id.edit_list_name);
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (ListNameDialogListener) context;
        } catch (ClassCastException e) {
            new ClassCastException(context.toString() + "must implement List Dialog Listener");
        }

    }

    public interface ListNameDialogListener {
        void applyTexts(String listName);
    }
}
