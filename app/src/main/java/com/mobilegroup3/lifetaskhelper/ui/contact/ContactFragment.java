package com.mobilegroup3.lifetaskhelper.ui.contact;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.mobilegroup3.lifetaskhelper.R;
import com.mobilegroup3.lifetaskhelper.databinding.FragmentContactBinding;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ContactFragment extends Fragment {

    private ContactViewModel contactViewModel;
    private FragmentContactBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState, View view) {
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

//        Button btnSend = view.findViewById(R.id.button);
//        String recipient = "jesswaz24@gmail.com";
//        String host = "localhost";
//        View sender = view.findViewById(R.id.editEmail);
//        TextView txtMessageView = (TextView) view.findViewById(R.id.text_message);
//        String txtMessageValue = txtMessageView.getText().toString();
//
//        Properties properties = System.getProperties();
//
//        properties.setProperty("mail.smtp.port", host);
//        Session session = Session.getDefaultInstance(properties);
//
//        btnSend.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View v) {
//               try {
//                   MimeMessage message = new MimeMessage(session);
//                   message.setFrom(new InternetAddress(sender.toString()));
//                   message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
//                   message.setSubject("LifeTask Comments");
//                   message.setText(txtMessageValue);
//
//                   Transport.send(message);
//                   System.out.println("Sent message successfully!");
//               } catch (MessagingException e) {
//                   e.printStackTrace();
//               }
//           }
//        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}