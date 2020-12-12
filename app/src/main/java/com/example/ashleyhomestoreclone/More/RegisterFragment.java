package com.example.ashleyhomestoreclone.More;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import com.example.ashleyhomestoreclone.Bean.BaseFragment;
import com.example.ashleyhomestoreclone.MainActivity;
import com.example.ashleyhomestoreclone.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterFragment extends BaseFragment {
    TextInputEditText  firstName202, lastName202, email202, confirmEmail202, password202, confirmPass202;
    Button btnReg202;
    MaterialCheckBox allowAge202;

    FirebaseAuth auth202;
    DatabaseReference reference202;

    private void Register(String firstName, String lastName, String email, String password) {
        auth202.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser firebaseUser202 = auth202.getCurrentUser();
                            assert firebaseUser202 != null;
                            String userid202 = firebaseUser202.getUid();
                            reference202 = FirebaseDatabase.getInstance().getReference("Users").child(userid202);

                            HashMap<String, String> hashMap202 = new HashMap<>();
                            hashMap202.put("id", userid202);
                            hashMap202.put("firstName", firstName);
                            hashMap202.put("lastName", lastName);

                            reference202.setValue(hashMap202).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Intent intent202 = new Intent(getActivity(), MainActivity.class);
                                        startActivity(intent202);
                                        Toast.makeText(getActivity(), "Register Successful!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(getActivity(), "Error Register!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view202 = inflater.inflate(R.layout.layout_register_account, container, false);

        Toolbar toolbar202 = view202.findViewById(R.id.toolbar_register);
        toolbar202.setLogo(ContextCompat.getDrawable(getContext(), R.drawable.ic_baseline_arrow_back_ios_24_white));
        View logoView = toolbar202.getChildAt(1);
        logoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity202.popFragments();
            }
        });

        firstName202 = view202.findViewById(R.id.firstname);
        lastName202 = view202.findViewById(R.id.lastname);
        email202 = view202.findViewById(R.id.email_register);
        confirmEmail202 = view202.findViewById(R.id.confirm_email_register);
        password202 = view202.findViewById(R.id.password_register);
        confirmPass202 = view202.findViewById(R.id.confirm_password_register);
        btnReg202 = view202.findViewById(R.id.sign_up);
        allowAge202 = view202.findViewById(R.id.allow_age);

        auth202 = FirebaseAuth.getInstance();

        btnReg202.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_firstName202 = firstName202.getText().toString();
                String txt_lastName202 = lastName202.getText().toString();
                String txt_email202 = email202.getText().toString();
                String txt_confirmEmail202 = confirmEmail202.getText().toString();
                String txt_password202 = password202.getText().toString();
                String txt_confirmPass202 = confirmPass202.getText().toString();

                if (TextUtils.isEmpty(txt_firstName202) || TextUtils.isEmpty(txt_lastName202) || TextUtils.isEmpty(txt_email202) || TextUtils.isEmpty(txt_password202)) {
                    Toast.makeText(getActivity(), "All fields are required!", Toast.LENGTH_SHORT).show();
                } else if (!txt_confirmEmail202.equals(txt_email202)) {
                    Toast.makeText(getActivity(), "Email does not match!", Toast.LENGTH_SHORT).show();
                } else if (!txt_confirmPass202.equals(txt_password202)) {
                    Toast.makeText(getActivity(), "Password does not match!", Toast.LENGTH_SHORT).show();
                } else if (!allowAge202.isChecked()) {
                        Toast.makeText(getActivity(), "Verify your age before signing up!", Toast.LENGTH_SHORT).show();
                } else if (txt_password202.length() < 6) {
                    Toast.makeText(getActivity(), "Password must be at least 6 characters!", Toast.LENGTH_SHORT).show();
                } else {
                    Register(txt_firstName202, txt_lastName202, txt_email202, txt_password202);
                }
            }
        });
        return view202;
    }
}
