package com.example.crosshelper.ui.pixeling;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PixelingViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public PixelingViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}