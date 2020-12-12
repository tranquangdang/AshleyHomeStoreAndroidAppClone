package com.example.ashleyhomestoreclone.More;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.ashleyhomestoreclone.Bean.BaseFragment;
import com.example.ashleyhomestoreclone.Home.HomeFragment;
import com.example.ashleyhomestoreclone.MainActivity;
import com.example.ashleyhomestoreclone.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static com.example.ashleyhomestoreclone.MainActivity.MORE_FRAGMENT;
import static com.example.ashleyhomestoreclone.MainActivity.SHOP_FRAGMENT;
import static com.example.ashleyhomestoreclone.MainActivity.bottomNavigationViewEx;
import static com.example.ashleyhomestoreclone.MainActivity.mStacks;

public class LoginFragment extends BaseFragment {
    TextInputEditText email, password;
    Button btnSignIn;

    FirebaseAuth auth;
    FirebaseUser firebaseUser;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_login, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar_login);
        toolbar.setLogo(ContextCompat.getDrawable(getContext(), R.drawable.ic_baseline_arrow_back_ios_24_white));
        View logoView = toolbar.getChildAt(1);
        logoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(firebaseUser == null) {
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                } else {
                    mActivity.popFragments();
                }

            }
        });
        auth = FirebaseAuth.getInstance();
        email = view.findViewById(R.id.email_login);
        password = view.findViewById(R.id.password_login);
        btnSignIn = view.findViewById(R.id.btn_signIn);


        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_email = email.getText().toString();
                String txt_password = password.getText().toString();

                if (TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)) {
                    Toast.makeText(getActivity(), "All fields are required!", Toast.LENGTH_SHORT).show();
                } else {
                    auth.signInWithEmailAndPassword(txt_email,txt_password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Intent intent = new Intent(getActivity(), MainActivity.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(getActivity(), "Wrong password or email!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

        return view;
    }
}

