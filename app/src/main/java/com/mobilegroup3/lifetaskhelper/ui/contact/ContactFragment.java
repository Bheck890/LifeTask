package com.mobilegroup3.lifetaskhelper.ui.contact;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.mobilegroup3.lifetaskhelper.databinding.FragmentContactBinding;

public class ContactFragment extends Fragment {

    private ContactViewModel contactViewModel;
    private FragmentContactBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentContactBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        /*
        contactViewModel =
                new ViewModelProvider(this).get(ContactViewModel.class);
        final TextView textView = binding.textGallery;
        contactViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
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
}