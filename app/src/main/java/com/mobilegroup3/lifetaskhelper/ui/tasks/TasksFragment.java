package com.mobilegroup3.lifetaskhelper.ui.tasks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

    private TasksViewModel homeViewModel;
    private FragmentHomeBinding binding;
    static TaskViewAdapter taskAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(TasksViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        TaskListView listView = (TaskListView) binding.taskList;

        if (tasks.isEmpty()) {
            tasks.add(new Task(0,"Example note"));
            tasks.add(new Task(1,"Example note", "6 days"));
            //System.out.println("TASKS ADDED");
        }

        taskAdapter = new TaskViewAdapter(getContext(), tasks);

        listView.setAdapter(taskAdapter);

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
               textView.setText(s);
            }
        });

        /*
        FloatingActionButton addButton = root.findViewById(R.id.fab);
        //root.findViewById(R.id.fab)
        addButton.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();

                Intent intent = new Intent(view.getContext(), NewTaskActivity.class);
                intent.putExtra("taskid", -1);
                view.getContext().startActivity(intent);

            }
        });
         */


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


}