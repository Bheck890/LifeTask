package com.mobilegroup3.lifetaskhelper.task;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mobilegroup3.lifetaskhelper.R;
import com.mobilegroup3.lifetaskhelper.ui.tasks.EditTaskActivity;

import java.util.ArrayList;

public class TaskViewAdapter extends ArrayAdapter<Task> {

    private ArrayList<Task> tasks;

    // invoke the suitable constructor of the ArrayAdapter class
    public TaskViewAdapter(@NonNull Context context, ArrayList<Task> arrayList) {
        // pass the context and arrayList for the super
        // constructor of the ArrayAdapter class
        super(context, 0, arrayList);
        this.tasks = arrayList;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // convertView which is recyclable view
        View currentItemView = convertView;

        // of the recyclable view is null then inflate the custom layout for the same
        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.task_list_view, parent, false);
        }

        // get the position of the view from the ArrayAdapter
        Task currentNumberPosition = getItem(position);


        // then according to the position of the view assign the desired TextView 1 for the same
        TextView textView1 = currentItemView.findViewById(R.id.titleTxt);
        textView1.setText(currentNumberPosition.getTitle());

        // then according to the position of the view assign the desired TextView 2 for the same
        TextView textView2 = currentItemView.findViewById(R.id.sincePreformedTxt);
        textView2.setText(currentNumberPosition.getDateSinceUpdate());

        // then according to the position of the view assign the desired TextView 2 for the same
        Button buttonPreformed = currentItemView.findViewById(R.id.taskDoneBtn);
        buttonPreformed.setText("Preformed Task");

        // then according to the position of the view assign the desired TextView 2 for the same
        Button buttonSettings = currentItemView.findViewById(R.id.taskSettingsBtn);
        buttonSettings.setBackgroundResource(R.drawable.gear);
        buttonSettings.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //NewTaskFragment NewTask = (NewTaskFragment) getSupportFragmentManager().findFragmentById(R.id.New
                Intent intent = new Intent(getContext(), EditTaskActivity.class);
                intent.putExtra("taskid", currentNumberPosition.getId());
                getContext().startActivity(intent);
            }
        });

        // then return the recyclable view
        return currentItemView;
    }


}
