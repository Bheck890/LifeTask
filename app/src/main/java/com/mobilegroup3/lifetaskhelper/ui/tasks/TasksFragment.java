package com.mobilegroup3.lifetaskhelper.ui.tasks;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.mobilegroup3.lifetaskhelper.databinding.FragmentHomeBinding;
import com.mobilegroup3.lifetaskhelper.task.Task;
import com.mobilegroup3.lifetaskhelper.task.TaskListView;
import com.mobilegroup3.lifetaskhelper.task.TaskViewAdapter;

import java.util.ArrayList;

public class TasksFragment extends Fragment {

    public static ArrayList<Task> tasks = new ArrayList<>();
    private static SQLiteOpenHelper TaskDatabase;
    private static final String TB_NAME = "TASK"; // the name of our Table

    private TasksViewModel homeViewModel;
    private FragmentHomeBinding binding;
    static TaskViewAdapter taskAdapter;
    private SQLiteDatabase db;
    private Cursor cursor;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(TasksViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        TaskListView listView = (TaskListView) binding.taskList;

        TaskDatabase = new TaskDatabaseHelper(getContext());
        try {
            System.out.println("@@@@@@@@@@@@-Reading Database");

            db = TaskDatabase.getReadableDatabase();
            cursor = db.query ("TASK",
                    new String[] {
                            "_id",
                            "TITLE",
                            "LATITUDE",
                            "LONGITUDE",
                            "ADDRESS",
                            "ENABLE_ADDRESS",
                            "ADDRESS_VERIFIED",
                            "DATE",
                            "HOUR",
                            "MINUTE"
                    }, //
                    null, null, null, null, null);

            //Move to the first record in the Cursor
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    System.out.println("@@@@@@@@@@@@-Reading Database Line");
                    int _id = cursor.getInt(0);
                    String title = cursor.getString(1);
                    double latitude = cursor.getDouble(2);
                    double longitude = cursor.getInt(3);
                    String address = cursor.getString(4);
                    boolean enable_address = (cursor.getInt(5) == 1);
                    boolean address_verified = (cursor.getInt(6) == 1);
                    String date = cursor.getString(7);
                    int hour = cursor.getInt(8);
                    int min = cursor.getInt(9);

                    tasks.add(new Task(
                            _id,
                            title,
                            latitude,
                            longitude,
                            address,
                            enable_address,
                            address_verified,
                            date,
                            hour,
                            min
                    ));
                    cursor.moveToNext();
                }
            }

            taskAdapter = new TaskViewAdapter(getContext(), tasks);
            for (Task Tas: tasks) {
                System.out.println("@@@@@@@@@@@@-" + Tas);
            }
            //System.out.println(tasks);
            listView.setAdapter(taskAdapter);

            cursor.close();
            db.close();
        } catch (SQLiteException e) {
            System.out.println("@@@@@@@@@@@@-Error Reading Database");
            Toast toast = Toast.makeText(getContext(), "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
               textView.setText(s);
            }
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public static TaskViewAdapter getAdapter() {
        return taskAdapter;
    }

    public static void addLastAddedTask() {
        Task newTaskInstance = tasks.get(tasks.size()-1);

        System.out.println("@@@@@@@-NewTask" + newTaskInstance);

        ContentValues taskValues = new ContentValues();
        taskValues.put("TITLE", newTaskInstance.getTitle());
        taskValues.put("LATITUDE", newTaskInstance.getLatitude());
        taskValues.put("LONGITUDE", newTaskInstance.getLongitude());
        taskValues.put("ADDRESS", newTaskInstance.getAddress());
        taskValues.put("ENABLE_ADDRESS", newTaskInstance.getEnable_address());
        taskValues.put("ADDRESS_VERIFIED", newTaskInstance.getAddress_verified());
        taskValues.put("DATE", newTaskInstance.getDate());
        taskValues.put("HOUR", newTaskInstance.getHour());
        taskValues.put("MINUTE", newTaskInstance.getMinute());


        //Get a reference to the database and update the FAVORITE column
        try {
            //TaskDatabase = new TaskDatabaseHelper();
            SQLiteDatabase db = TaskDatabase.getWritableDatabase();
            db.insert(TB_NAME, null, taskValues);
            db.close();

        } catch(SQLiteException e) {
            System.out.println("@@@@@@@@@@@@@ - Issue adding the New Task to Database");
            //Toast toast = Toast.makeText(this.getContext(), "Database unavailable", Toast.LENGTH_SHORT);
            //toast.show();
        }
    }

    public static void updateTask(Task TaskInstance) {
        Task updatedTaskInstance = tasks.get(TaskInstance.getId()-1);

        System.out.println("@@@@@@@-UpdateTask" + updatedTaskInstance);

        ContentValues taskValues = new ContentValues();
        taskValues.put("TITLE", updatedTaskInstance.getTitle());
        taskValues.put("LATITUDE", updatedTaskInstance.getLatitude());
        taskValues.put("LONGITUDE", updatedTaskInstance.getLongitude());
        taskValues.put("ADDRESS", updatedTaskInstance.getAddress());
        taskValues.put("ENABLE_ADDRESS", updatedTaskInstance.getEnable_address());
        taskValues.put("ADDRESS_VERIFIED", updatedTaskInstance.getAddress_verified());
        taskValues.put("DATE", updatedTaskInstance.getDate());
        taskValues.put("HOUR", updatedTaskInstance.getHour());
        taskValues.put("MINUTE", updatedTaskInstance.getMinute());


        //Get a reference to the database and update the FAVORITE column
        try {
            //TaskDatabase = new TaskDatabaseHelper();
            SQLiteDatabase db = TaskDatabase.getWritableDatabase();
            db.update(TB_NAME, taskValues,"_id = ?",
                    new String[] {Integer.toString(TaskInstance.getId())});
            db.close();

        } catch(SQLiteException e) {
            System.out.println("@@@@@@@@@@@@@ - Issue updating the Task to the Database");
            //Toast toast = Toast.makeText(this.getContext(), "Database unavailable", Toast.LENGTH_SHORT);
            //toast.show();
        }
    }


}