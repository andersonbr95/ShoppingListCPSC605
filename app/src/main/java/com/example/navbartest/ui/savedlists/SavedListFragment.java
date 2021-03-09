package com.example.navbartest.ui.savedlists;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.navbartest.R;

public class SavedListFragment extends Fragment {

    private SavedListViewModel savedListViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        savedListViewModel =
                new ViewModelProvider(this).get(SavedListViewModel.class);
        View root = inflater.inflate(R.layout.fragment_savedlist, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        savedListViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}