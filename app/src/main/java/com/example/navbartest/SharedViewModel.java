package com.example.navbartest;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<ArrayList<String>> list = new MutableLiveData<ArrayList<String>>();

    public LiveData<ArrayList<String>> getListData() {
        return list;
    }
    public void addList(ArrayList<String> list){
        this.list.setValue(list);
    }
    public ArrayList<String> getList(){
        return list.getValue();
    }
}
