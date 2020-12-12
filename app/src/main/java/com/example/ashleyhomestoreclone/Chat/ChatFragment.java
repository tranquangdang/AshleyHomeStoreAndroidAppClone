package com.example.ashleyhomestoreclone.Chat;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ashleyhomestoreclone.Bean.Adapter.MessageAdapter;
import com.example.ashleyhomestoreclone.Bean.BaseFragment;
import com.example.ashleyhomestoreclone.Bean.ChatMessageBean;
import com.example.ashleyhomestoreclone.Bean.UserBean;
import com.example.ashleyhomestoreclone.Home.HomeFragment;
import com.example.ashleyhomestoreclone.MainActivity;
import com.example.ashleyhomestoreclone.More.LoginFragment;
import com.example.ashleyhomestoreclone.More.MoreFragment;
import com.example.ashleyhomestoreclone.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;


public class ChatFragment extends BaseFragment {
    FirebaseUser firebaseUser;
    DatabaseReference reference;

    ImageButton btnSend;
    EditText textSend;

    MessageAdapter messageAdapter;
    ArrayList<ChatMessageBean> arrayList;
    RecyclerView recyclerView;

    public ChatFragment () {
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_chat, container, false);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if(firebaseUser == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage("You should login!")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            mActivity.gotoFragment(new LoginFragment());
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
        recyclerView = view.findViewById(R.id.recyclerChat);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        btnSend = view.findViewById(R.id.btnSend);
        textSend = view.findViewById(R.id.chat_input);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                String time = dateFormat.format(Calendar.getInstance().getTime());
                String msg = textSend.getText().toString();
                if (!msg.equals("")) {
                    sendMessage(firebaseUser.getUid(),msg, time);
                } else {
                    Toast.makeText(getActivity(), "You can't send empty message!", Toast.LENGTH_SHORT).show();
                }
                textSend.setText("");
            }
        });

        if(firebaseUser != null) {
            readMessages();
        }

        return view;
    }

    private void sendMessage(String sender, String message, String time) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("sender", sender);
        hashMap.put("receiver", "admin");
        hashMap.put("message", message);
        hashMap.put("time", time);

        reference.child("Chats").push().setValue(hashMap);
    }

    public void readMessages() {
        arrayList = new ArrayList<>();

        reference = FirebaseDatabase.getInstance().getReference("Chats");
        reference.orderByChild("time").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ChatMessageBean chat = dataSnapshot.getValue(ChatMessageBean.class);
                    if(chat.getReceiver().equals(firebaseUser.getUid()) && chat.getSender().equals("admin") ||
                            chat.getReceiver().equals("admin") && chat.getSender().equals(firebaseUser.getUid()))
                        arrayList.add(chat);

                    messageAdapter = new MessageAdapter(getContext(), arrayList);
                    recyclerView.setAdapter(messageAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
