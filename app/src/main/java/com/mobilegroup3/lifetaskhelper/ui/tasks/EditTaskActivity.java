package com.mobilegroup3.lifetaskhelper.ui.tasks;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.mobilegroup3.lifetaskhelper.R;

public class EditTaskActivity extends AppCompatActivity {

    int taskId;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        EditText editText = findViewById(R.id.editEdtxtTitle);

        // Fetch data that is passed from MainActivity
        Intent intent = getIntent();

        // Accessing the data using key and value
        taskId = intent.getIntExtra("taskid",-1);
        if (taskId != -1) {
            editText.setText(TasksFragment.tasks.get(taskId).getTitle());
        } else {
            finish();
        }

        Button buttonSettings = this.findViewById(R.id.editSaveBtn);
        buttonSettings.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TasksFragment.tasks.get(taskId).setTitle(editText.getText().toString());
                        finish();
                        TasksFragment.getAdapter().notifyDataSetChanged();
                    }
                });

    }
}
