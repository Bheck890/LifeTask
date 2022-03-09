package com.mobilegroup3.lifetaskhelper.task;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextWatcher;
import android.widget.EditText;

import com.mobilegroup3.lifetaskhelper.R;

public class TaskActivity extends AppCompatActivity {

    int noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        EditText editText = findViewById(R.id.edtxtTitle);

        // Fetch data that is passed from MainActivity
        Intent intent = getIntent();

        // Accessing the data using key and value
        noteId = intent.getIntExtra("noteId", -1);
        if (noteId != -1) {
            //editText.setText(h.notes.get(noteId));
        } else {

            //MainActivity.notes.add("");
            //noteId = MainActivity.notes.size() - 1;
            //MainActivity.arrayAdapter.notifyDataSetChanged();

        }
    }
}