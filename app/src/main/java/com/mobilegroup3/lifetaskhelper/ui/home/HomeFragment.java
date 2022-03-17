package com.mobilegroup3.lifetaskhelper.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

public class HomeFragment extends Fragment {

    public static ArrayList<Task> tasks = new ArrayList<>();

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    static TaskViewAdapter taskAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //ListView listView = binding.taskList;
        TaskListView listView = (TaskListView) binding.taskList;

        if (tasks.isEmpty()) {
            tasks.add(new Task(0,"Example note"));
            tasks.add(new Task(1,"Example note", "6 days"));
            //System.out.println("TASKS ADDED");
        }

        taskAdapter = new TaskViewAdapter(getContext(), tasks);

        listView.setAdapter(taskAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                // Going from MainActivity to NotesEditorActivity
                //Intent intent = new Intent(getContext(), TaskActivity.class);
                //intent.putExtra("noteId", i);
                //startActivity(intent);
                System.out.println("Position: " + position);
            }
        });





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


}