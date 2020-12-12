package com.example.ashleyhomestoreclone.More;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.ashleyhomestoreclone.Bean.BaseFragment;
import com.example.ashleyhomestoreclone.Bean.UserBean;
import com.example.ashleyhomestoreclone.MainActivity;
import com.example.ashleyhomestoreclone.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MoreAfterLoginFragment extends BaseFragment {
    TextView name202;
    Button btnLogout202;

    FirebaseUser firebaseUser202;
    DatabaseReference databaseReference202;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view202 = inflater.inflate(R.layout.layout_fragment_more_after_login, container, false);

        name202 = view202.findViewById(R.id.cust_name);
        btnLogout202 = view202.findViewById(R.id.btn_logout);

        firebaseUser202 = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference202 = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser202.getUid());

        databaseReference202.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserBean user202 = snapshot.getValue(UserBean.class);
                name202.setText(user202.getFirstName() + " " + user202.getLastName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnLogout202.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent202 = new Intent(getActivity(), MainActivity.class);
                startActivity(intent202);
            }
        });
        return view202;
    }
}
