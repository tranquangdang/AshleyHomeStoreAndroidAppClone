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
    FirebaseUser firebaseUser202;
    DatabaseReference reference202;

    ImageButton btnSend202;
    EditText textSend202;

    MessageAdapter messageAdapter202;
    ArrayList<ChatMessageBean> arrayList202;
    RecyclerView recyclerView202;

    public ChatFragment () {
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view202 = inflater.inflate(R.layout.layout_fragment_chat, container, false);
        firebaseUser202 = FirebaseAuth.getInstance().getCurrentUser();
        if(firebaseUser202 == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage("You should login!")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            mActivity202.gotoFragment(new LoginFragment());
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
        recyclerView202 = view202.findViewById(R.id.recyclerChat);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView202.setLayoutManager(linearLayoutManager);

        btnSend202 = view202.findViewById(R.id.btnSend);
        textSend202 = view202.findViewById(R.id.chat_input);

        firebaseUser202 = FirebaseAuth.getInstance().getCurrentUser();

        btnSend202.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat dateFormat202 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                String time202 = dateFormat202.format(Calendar.getInstance().getTime());
                String msg = textSend202.getText().toString();
                if (!msg.equals("")) {
                    sendMessage(firebaseUser202.getUid(),msg, time202);
                } else {
                    Toast.makeText(getActivity(), "You can't send empty message!", Toast.LENGTH_SHORT).show();
                }
                textSend202.setText("");
            }
        });

        if(firebaseUser202 != null) {
            readMessages();
        }

        return view202;
    }

    private void sendMessage(String sender, String message, String time) {
        DatabaseReference reference202 = FirebaseDatabase.getInstance().getReference();

        HashMap<String, Object> hashMap202 = new HashMap<>();
        hashMap202.put("sender", sender);
        hashMap202.put("receiver", "admin");
        hashMap202.put("message", message);
        hashMap202.put("time", time);

        reference202.child("Chats").push().setValue(hashMap202);
    }

    public void readMessages() {
        arrayList202 = new ArrayList<>();

        reference202 = FirebaseDatabase.getInstance().getReference("Chats");
        reference202.orderByChild("time").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList202.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ChatMessageBean chat202 = dataSnapshot.getValue(ChatMessageBean.class);
                    if(chat202.getReceiver().equals(firebaseUser202.getUid()) && chat202.getSender().equals("admin") ||
                            chat202.getReceiver().equals("admin") && chat202.getSender().equals(firebaseUser202.getUid()))
                        arrayList202.add(chat202);

                    messageAdapter202 = new MessageAdapter(getContext(), arrayList202);
                    recyclerView202.setAdapter(messageAdapter202);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
