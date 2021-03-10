package com.example.navbartest.ui.savedlists;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SavedListViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SavedListViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is our Saved List Placeholder");
    }

    public LiveData<String> getText() {
        return mText;
    }
}