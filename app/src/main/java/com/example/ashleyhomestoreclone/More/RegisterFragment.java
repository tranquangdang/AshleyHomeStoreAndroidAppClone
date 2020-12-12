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
    TextInputEditText  firstName, lastName, email, confirmEmail, password, confirmPass;
    Button btnReg;
    MaterialCheckBox allowAge;

    FirebaseAuth auth;
    DatabaseReference reference;

    private void Register(String firstName, String lastName, String email, String password) {
        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            assert firebaseUser != null;
                            String userid = firebaseUser.getUid();
                            reference = FirebaseDatabase.getInstance().getReference("Users").child(userid);

                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("id", userid);
                            hashMap.put("firstName", firstName);
                            hashMap.put("lastName", lastName);

                            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Intent intent = new Intent(getActivity(), MainActivity.class);
                                        startActivity(intent);
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
        View view = inflater.inflate(R.layout.layout_register_account, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar_register);
        toolbar.setLogo(ContextCompat.getDrawable(getContext(), R.drawable.ic_baseline_arrow_back_ios_24_white));
        View logoView = toolbar.getChildAt(1);
        logoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.popFragments();
            }
        });

        firstName = view.findViewById(R.id.firstname);
        lastName = view.findViewById(R.id.lastname);
        email = view.findViewById(R.id.email_register);
        confirmEmail = view.findViewById(R.id.confirm_email_register);
        password = view.findViewById(R.id.password_register);
        confirmPass = view.findViewById(R.id.confirm_password_register);
        btnReg = view.findViewById(R.id.sign_up);
        allowAge = view.findViewById(R.id.allow_age);

        auth = FirebaseAuth.getInstance();

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_firstName = firstName.getText().toString();
                String txt_lastName = lastName.getText().toString();
                String txt_email = email.getText().toString();
                String txt_confirmEmail = confirmEmail.getText().toString();
                String txt_password = password.getText().toString();
                String txt_confirmPass = confirmPass.getText().toString();

                if (TextUtils.isEmpty(txt_firstName) || TextUtils.isEmpty(txt_lastName) || TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)) {
                    Toast.makeText(getActivity(), "All fields are required!", Toast.LENGTH_SHORT).show();
                } else if (!txt_confirmEmail.equals(txt_email)) {
                    Toast.makeText(getActivity(), "Email does not match!", Toast.LENGTH_SHORT).show();
                } else if (!txt_confirmPass.equals(txt_password)) {
                    Toast.makeText(getActivity(), "Password does not match!", Toast.LENGTH_SHORT).show();
                } else if (!allowAge.isChecked()) {
                        Toast.makeText(getActivity(), "Verify your age before signing up!", Toast.LENGTH_SHORT).show();
                } else if (txt_password.length() < 6) {
                    Toast.makeText(getActivity(), "Password must be at least 6 characters!", Toast.LENGTH_SHORT).show();
                } else {
                    Register(txt_firstName, txt_lastName, txt_email, txt_password);
                }
            }
        });
        return view;
    }
}
