package com.mobilegroup3.lifetaskhelper.ui.tasks;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mobilegroup3.lifetaskhelper.R;
import com.mobilegroup3.lifetaskhelper.task.Actions;
import com.mobilegroup3.lifetaskhelper.task.Task;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

// This class Creates Actions that are preformed that are linked to a TaskID that already exists
public class ActionRecordTaskActivity extends AppCompatActivity {

    private static final String A_TB_NAME = ActionDatabaseHelper.TB_NAME;
    //task action Variables
    int taskId;
    private SimpleDateFormat dateFormatter;

    //adding Task to the DataBase
    private static SQLiteOpenHelper actionDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_record_task);

        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        actionDatabase = new ActionDatabaseHelper(ActionRecordTaskActivity.this);

        //Default Task stuff
        Intent intent = getIntent(); // Fetch data that is passed from MainActivity
        TextView editTextViewTitle = findViewById(R.id.recordTxtTitle);
        EditText editTextDescription = findViewById(R.id.recordTxtDescription);

        // Accessing the data using key and value
        taskId = intent.getIntExtra("taskid",-1);
        Task taskInstance = TasksFragment.tasks.get(taskId-1);
        System.out.println("@@@@@@@ Begin Action Record Instance, For Task: \n" + taskInstance);

        //Set Elements For Time
        //https://www.journaldev.com/9976/android-date-time-picker-dialog
        EditText editTextDate = findViewById(R.id.recordTxtDate);
        EditText editTextTime = findViewById(R.id.recordTxtTime);
        ImageButton buttonDate = this.findViewById(R.id.recordImageButtonDate);
        ImageButton buttonTime = this.findViewById(R.id.recordImageButtonTime);
        final int[] hour = {0};
        final int[] min = {0};

        //Set Elements For Location
        EditText editTextLocation = findViewById(R.id.recordTxtLocation);
        Button buttonVerify = this.findViewById(R.id.recordButtonVerifyAddress);
        CheckBox CheckboxAddressVerify = this.findViewById(R.id.recordCheckBoxAddressCheck);
        //checkBoxEnableLocation
        CheckboxAddressVerify.setClickable(false);
        final boolean[] location = {false};
        final Address[] Location = {null};
        //editTextLocation.setClickable(false);
        editTextLocation.setEnabled(false);
        buttonVerify.setEnabled(false);

        //Data Information Notice
        TextView editDataNotice = findViewById(R.id.recordTextViewDataNotice);
        editDataNotice.setText(R.string.record_today_date);

        //Setup to set what the Fields have in them.
        if (taskId != -1) { //
            editTextViewTitle.setText(taskInstance.getTitle());
            editTextDate.setText(taskInstance.getDate());
            editTextTime.setText(taskInstance.getHour() + ":" + taskInstance.getMinute());
            editTextLocation.setText(taskInstance.getAddress());
            location[0] = taskInstance.getAddress_verified();

            if((taskInstance.getHour() == 0) && (taskInstance.getMinute() == 0)){
                editTextTime.setText("");
                System.out.println("@@@@@@@@@@@@@-Empty Time1");
            }
            else{
                hour[0] = taskInstance.getHour();
                min[0] = taskInstance.getMinute();
            }

        } else {
            finish();
        }

        //Control flow on what to show based on what Type of Task it is.
        if(location[0]){ //Location Exists
            //Enable Location Reminder Options
            editTextLocation.setEnabled(true);
            buttonVerify.setEnabled(true);
            editTextLocation.setText(""); //taskInstance.getAddress()
            editTextLocation.setHint(R.string.locationBox);

            //Disable Date Reminder Options
            editTextDate.setEnabled(false);
            editTextTime.setEnabled(false);
            buttonDate.setEnabled(false);
            buttonTime.setEnabled(false);
            editTextDate.setVisibility(View.INVISIBLE);
            editTextTime.setVisibility(View.INVISIBLE);
            buttonDate.setVisibility(View.INVISIBLE);
            buttonTime.setVisibility(View.INVISIBLE);

            editDataNotice.setText(R.string.record_all_data);
        }
        else if(!taskInstance.getDate().isEmpty()) { //Date Exists
            //Disable Location Reminder Options
            editTextLocation.setEnabled(false);
            buttonVerify.setEnabled(false);
            CheckboxAddressVerify.setChecked(false);
            editTextLocation.setVisibility(View.INVISIBLE);
            buttonVerify.setVisibility(View.INVISIBLE);
            CheckboxAddressVerify.setVisibility(View.INVISIBLE);

            //Enable Date Reminder Options
            editTextDate.setEnabled(true);
            editTextTime.setEnabled(true);
            buttonDate.setEnabled(true);
            buttonTime.setEnabled(true);
            editTextDate.setText(""); //taskInstance.getDate()
            editTextTime.setText("");
            editTextDate.setHint(R.string.dateBox);
            editTextTime.setHint(R.string.timeBox);
        }
        else{ //No Location or Date Set in the Task
            //Disable Location Reminder Options
            editTextLocation.setEnabled(false);
            buttonVerify.setEnabled(false);
            CheckboxAddressVerify.setChecked(false);
            editTextLocation.setVisibility(View.INVISIBLE);
            buttonVerify.setVisibility(View.INVISIBLE);
            CheckboxAddressVerify.setVisibility(View.INVISIBLE);

            //Disable Date Reminder Options
            editTextDate.setEnabled(false);
            editTextTime.setEnabled(false);
            buttonDate.setEnabled(false);
            buttonTime.setEnabled(false);
            editTextDate.setVisibility(View.INVISIBLE);
            editTextTime.setVisibility(View.INVISIBLE);
            buttonDate.setVisibility(View.INVISIBLE);
            buttonTime.setVisibility(View.INVISIBLE);
        }

        //Inputs Date Helper
        buttonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance(Locale.getDefault());
                DatePickerDialog datePickerDialog = new DatePickerDialog(ActionRecordTaskActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                //todo
                                Calendar newDate = Calendar.getInstance();
                                newDate.set(year, month, dayOfMonth);
                                editTextDate.setText(dateFormatter.format(newDate.getTime()));
                            }
                        },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        //Clock Time Helper Button
        buttonTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Get Date;
                //Calendar calendar = Calendar.getInstance(Locale.getDefault());
                //Calendar newDate = Calendar.getInstance();
                //dateFormatter.format(newDate.getTime()) // = is the date
                // Get Current Time
                final Calendar calendar = Calendar.getInstance();
                int mHour = calendar.get(Calendar.HOUR_OF_DAY);
                int mMinute = calendar.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(ActionRecordTaskActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                if(minute < 10){
                                    String minS = String.format("%02d", minute);
                                    editTextTime.setText(hourOfDay + ":" + minS);
                                    System.out.println("@@@@@@@@@@@@@-Time Under 10");
                                }
                                else{
                                    editTextTime.setText(hourOfDay + ":" + minute);
                                    System.out.println("@@@@@@@@@@@@@-Time Default");
                                }

                                hour[0] = hourOfDay;
                                min[0] = minute;
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }
        });

        //Verify that it is a valid address that is to replace the old address.
        buttonVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckboxAddressVerify.setChecked(false);
                String Address = editTextLocation.getText().toString();

                try {
                    Location[0] = getLocationFromAddress(Address);
                    System.out.println("Latitude: " + Location[0].getLatitude());
                    System.out.println("Longitude: " + Location[0].getLongitude());
                    CheckboxAddressVerify.setChecked(true);

                }catch (Exception e) {
                    System.out.println("Not enough Information please Try again.");

                    Toast toast = Toast.makeText(ActionRecordTaskActivity.this,
                            "Not enough address information, please try again.",
                            Toast.LENGTH_LONG);
                    toast.show();
                    //e.printStackTrace();
                }
            }
        });

        //Save Button
        Button buttonSave = this.findViewById(R.id.recordSaveBtn);
        buttonSave.setOnClickListener(
                new View.OnClickListener() {

                    //Get Date;
                    final Calendar calendar = Calendar.getInstance(Locale.getDefault());
                    final Calendar newDate = Calendar.getInstance();
                    final String date = dateFormatter.format(newDate.getTime()); // = is the date
                    // Get Current Time
                    final int Hour = calendar.get(Calendar.HOUR_OF_DAY);
                    final int minute = calendar.get(Calendar.MINUTE);

                    @Override
                    public void onClick(View v) {

                        if (location[0]){ //Exit with new Location to Set
                            //System.out.println("Location based Notification Set");
                            if(CheckboxAddressVerify.isChecked()){
                                System.out.println("Valid Address Entered");

                                //----------------------------------------------------------------

                                //Variables to put into Database
                                editTextViewTitle.getText().toString(); //String Title
                                System.out.println("@@@@@Action Task Location" +
                                        "\nTitle: " + editTextViewTitle.getText().toString() +
                                        "\nLatitude: " + Location[0].getLatitude() +
                                        "\nLongitude: " + Location[0].getLongitude() +
                                        "\nAddress: " + editTextLocation.getText().toString()
                                );

                                /*
                                TasksFragment.tasks.add(new Task(
                                        TasksFragment.tasks.size()+1,
                                        editTextViewTitle.getText().toString(),
                                        Location[0].getLatitude(),
                                        Location[0].getLongitude(),
                                        editTextLocation.getText().toString(),
                                        true,
                                        CheckboxAddressVerify.isChecked(),
                                        "",
                                        0,
                                        0)
                                );

                                finish();
                                TasksFragment.getAdapter().notifyDataSetChanged();
                                //Update the Task function is Below
                                TasksFragment.addLastAddedTask();
                                 */

                                //----------------------------------------------------------------
                            }
                            else{
                                Toast toast = Toast.makeText(ActionRecordTaskActivity.this,
                                        "Valid Address not inputted",
                                        Toast.LENGTH_LONG);
                                toast.show();
                            }

                        }
                        else{ //Exit with just setting the Title and optional Date

                            if(editTextDate.getText().toString().length() > 1 ||
                                    editTextTime.getText().toString().length() > 1){

                                if(editTextDate.getText().toString().length() >= 10) {
                                    //System.out.println("Valid Date: " + editTextDate.getText().toString());
                                    if(editTextTime.getText().toString().length() >= 4) {
                                        System.out.println("Valid Time: " + editTextTime.getText().toString());

                                        //----------------------------------------------------------------
                                        //Variables to put into Database
                                        editTextViewTitle.getText().toString(); //String Title
                                        editTextDate.getText().toString(); //Get Date to set
                                        String Time = hour[0] + ":" + min[0]; //hour and min to put into database

                                        System.out.println("@@@@@@@@@Action Date " +
                                                "\nTitle: " + editTextViewTitle.getText().toString() +
                                                "\nDate: " + editTextDate.getText().toString() +
                                                "\nTime: " + Time
                                        );

                                        //Boolean for Date True

                                        /*
                                        TasksFragment.tasks.add(new Task(
                                                TasksFragment.tasks.size()+1,
                                                editTextViewTitle.getText().toString(),
                                                0,
                                                0,
                                                "",
                                                false,
                                                false,
                                                editTextDate.getText().toString(),
                                                hour[0],
                                                min[0])
                                        );

                                        //Update the Task function is Below
                                        //TasksFragment.tasks.get(taskId).setTitle(editText.getText().toString());
                                        finish();
                                        TasksFragment.getAdapter().notifyDataSetChanged();
                                        TasksFragment.addLastAddedTask();
                                         */
                                        //----------------------------------------------------------------
                                    }
                                    else{
                                        Toast toast = Toast.makeText(ActionRecordTaskActivity.this,
                                                "Time Not Inputted or valid",
                                                Toast.LENGTH_LONG);
                                        toast.show();
                                    }
                                }
                                else{
                                    Toast toast = Toast.makeText(ActionRecordTaskActivity.this,
                                            "Date Not Inputted or valid",
                                            Toast.LENGTH_LONG);
                                    toast.show();
                                }
                            }
                            else{ //only Title for Task (Manual Task System)
                                //----------------------------------------------------------------
                                //Variables to put into Database
                                editTextViewTitle.getText().toString(); //String Title
                                System.out.println("@@@@ Action Default: "
                                        + "\nTitle: " + editTextViewTitle.getText().toString()
                                        + "\nDesc: " + editTextDescription.getText().toString()
                                );

                                Actions action = new Actions(
                                        taskId,
                                        editTextDescription.getText().toString(),
                                        date,
                                        Hour,
                                        minute
                                );


                                addAction(action);

                                //Update the Task function is Below
                                //TasksFragment.tasks.get(taskId).setTitle(editText.getText().toString());
                                finish();
                                //----------------------------------------------------------------


                            } //Title
                        } // if (Location) else (Date Reminder)/else (Title)
                    } // Save Click
                });
    }

    //Adds Action to the Database
    public static void addAction(Actions action) {

        System.out.println("@@@@@@@-NewAction" + action);

        ContentValues actionValues = new ContentValues();
        actionValues.put("TASK_ID", action.getTaskID());
        actionValues.put("DESCRIPTION", action.getActionNote());
        actionValues.put("DATE", action.getDate());
        actionValues.put("LOCATION", action.getLocation());

        //Get a reference to the database and update the FAVORITE column
        try {
            //TaskDatabase = new TaskDatabaseHelper();
            SQLiteDatabase db = actionDatabase.getWritableDatabase();
            db.insert(A_TB_NAME, null, actionValues);
            db.close();

        } catch(SQLiteException e) {
            System.out.println("@@@@@@@@@@@@@ - Issue adding the New Task to Database");
            //Toast toast = Toast.makeText(this.getContext(), "Database unavailable", Toast.LENGTH_SHORT);
            //toast.show();
        }
    }


    //Turn the address into coordinates.
    public Address getLocationFromAddress(String strAddress) {
        //https://stackoverflow.com/questions/3574644/how-can-i-find-the-latitude-and-longitude-from-address/3574792#3574792
        Geocoder coder = new Geocoder(this);
        List<Address> address;
        try {
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }
            Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();

            return location;
        } catch (IOException e) {
            System.out.println("Not enough Information please Try again.");
            e.printStackTrace();
        }
        return null;
    }
}