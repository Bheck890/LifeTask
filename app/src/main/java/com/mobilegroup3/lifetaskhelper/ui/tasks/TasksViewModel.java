package com.mobilegroup3.lifetaskhelper.ui.tasks;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class TasksViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    //private MutableLiveData<Double> latit;
    //private MutableLiveData<Double> lon;

    //this is constantly being updated to the users cordinates
    public static double[] LocationValues = {0.0, 0.0};

    //somewhere in notifications, it will have to check these coordinates to notify the user
    //if they are at the pre determined location.
    public List<Double> LocationCordsToCheck = new ArrayList<>();

    public TasksViewModel() {
        mText = new MutableLiveData<>();
        //latit = new MutableLiveData<>();
        //lon = new MutableLiveData<>();

        //ViewModelProvider mainActivityViewModel = new ViewModelProvider((ViewModelStoreOwner) this);
        //mainActivityViewModel
        //mText.setValue("Tasks:");

    }

    public LiveData<String> getText() {
        return mText;
    }

    /*
    public double getLatitude() {
        return latit.getValue();
    }
    public double getLongitude() {
        return lon.getValue();
    }

    public void setLatitude(MutableLiveData<Double> latitu) {
        latit = latitu;
    }

    public void setLongitude(MutableLiveData<Double> longit) {
        lon = longit;
    }
     */

    //being updated from the main activity.
    public void updateLocation(double lat, double longitude) {
        LocationValues[0] = lat;
        LocationValues[1] = longitude;

        for (int i = 0; i < LocationValues.length; i++) {
            System.out.println("@@@@ Location " + LocationValues[i]);
        }
    }

    //added from the tasks that are added from the database
    public void addCoordinates(double lat, double longitude){
        LocationCordsToCheck.add(lat);
        LocationCordsToCheck.add(longitude);
    }
}