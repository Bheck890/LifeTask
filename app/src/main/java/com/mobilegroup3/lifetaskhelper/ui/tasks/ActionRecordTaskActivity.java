package com.mobilegroup3.lifetaskhelper.ui.tasks;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
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
import com.mobilegroup3.lifetaskhelper.task.Task;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class ActionRecordTaskActivity extends AppCompatActivity {

    int taskId;
    private SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_record_task);

        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

        //Default Task stuff
        Intent intent = getIntent(); // Fetch data that is passed from MainActivity
        TextView editTitle = findViewById(R.id.recordTxtTitle);
        EditText editTextDescription = findViewById(R.id.recordTxtDescription);

        // Accessing the data using key and value
        taskId = intent.getIntExtra("taskid",-1);
        Task taskInstance = TasksFragment.tasks.get(taskId-1);

        System.out.println("@@@@@@@Began Action Record Instance For Task: \n" + taskInstance);

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

        if (taskId != -1) { //
            editTitle.setText(taskInstance.getTitle());
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

        buttonTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
    }

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