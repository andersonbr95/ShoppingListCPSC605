package com.example.navbartest.ui.aboutus;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AboutUsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AboutUsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("The inspiration for this app came from recently moving out of my house for the first time " +
                "and I kept on constantly forgetting what groceries I need for my home and was a tad " +
                "unfamiliar with the groceries stores around me. I needed a way to determine which would be the better location to get what I needed." +
                "\n -Bryan Anderson, Founder "
        );

    }

    public LiveData<String> getText() {
        return mText;
    }
}