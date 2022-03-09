package com.mobilegroup3.lifetaskhelper.ui.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.mobilegroup3.lifetaskhelper.R;
import com.mobilegroup3.lifetaskhelper.databinding.FragmentHomeBinding;
import com.mobilegroup3.lifetaskhelper.task.Task;
import com.mobilegroup3.lifetaskhelper.task.TaskActivity;

import java.util.ArrayList;
import java.util.HashSet;

public class HomeFragment extends Fragment {

    static ArrayList<Task> notes = new ArrayList<>();

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    static ArrayAdapter arrayAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ListView listView = binding.taskList;
        //SharedPreferences sharedPreferences = getActivity().getSharedPreferences("com.mobilegroup3.lifetaskhelper.task", Context.MODE_PRIVATE);

        if (notes == null) {
            notes.add(new Task("Example note"));
        } else {
            notes.add(new Task("Example note"));
        }

        // Using custom listView Provided by Android Studio
        arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_expandable_list_item_1, notes);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                // Going from MainActivity to NotesEditorActivity
                Intent intent = new Intent(container.getContext(), TaskActivity.class);

                intent.putExtra("noteId", i);
                startActivity(intent);

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
}