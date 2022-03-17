package com.mobilegroup3.lifetaskhelper.task;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.mobilegroup3.lifetaskhelper.R;
import com.mobilegroup3.lifetaskhelper.ui.home.HomeFragment;

public class TaskActivity extends AppCompatActivity {

    int taskId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        EditText editText = findViewById(R.id.edtxtTitle);

        // Fetch data that is passed from MainActivity
        Intent intent = getIntent();

        // Accessing the data using key and value
        taskId = intent.getIntExtra("taskid",-1);
        if (taskId != -1) {
            editText.setText(HomeFragment.tasks.get(taskId).getTitle());
        } else {
            finish();
        }

        Button buttonSettings = this.findViewById(R.id.saveBtn);
        buttonSettings.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HomeFragment.tasks.get(taskId).setTitle(editText.getText().toString());
                        finish();
                        HomeFragment.getAdapter().notifyDataSetChanged();
                    }
                });

    }
}