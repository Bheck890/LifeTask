package com.mobilegroup3.lifetaskhelper.ui.tasks;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TasksViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public TasksViewModel() {
        mText = new MutableLiveData<>();
        //mText.setValue("Tasks:");
    }

    public LiveData<String> getText() {
        return mText;
    }
}