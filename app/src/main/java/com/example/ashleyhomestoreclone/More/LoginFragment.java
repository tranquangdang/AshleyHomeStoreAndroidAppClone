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
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginFragment extends BaseFragment {
    TextInputEditText email202, password202;
    Button btnSignIn202;

    FirebaseAuth auth202;
    FirebaseUser firebaseUser202;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view202 = inflater.inflate(R.layout.layout_login, container, false);

        Toolbar toolbar = view202.findViewById(R.id.toolbar_login);
        toolbar.setLogo(ContextCompat.getDrawable(getContext(), R.drawable.ic_baseline_arrow_back_ios_24_white));
        View logoView = toolbar.getChildAt(1);
        logoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(firebaseUser202 == null) {
                    Intent intent202 = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent202);
                } else {
                    mActivity202.popFragments();
                }

            }
        });
        auth202 = FirebaseAuth.getInstance();
        email202 = view202.findViewById(R.id.email_login);
        password202 = view202.findViewById(R.id.password_login);
        btnSignIn202 = view202.findViewById(R.id.btn_signIn);


        btnSignIn202.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_email202 = email202.getText().toString();
                String txt_password202 = password202.getText().toString();

                if (TextUtils.isEmpty(txt_email202) || TextUtils.isEmpty(txt_password202)) {
                    Toast.makeText(getActivity(), "All fields are required!", Toast.LENGTH_SHORT).show();
                } else {
                    auth202.signInWithEmailAndPassword(txt_email202,txt_password202)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Intent intent202 = new Intent(getActivity(), MainActivity.class);
                                        startActivity(intent202);
                                    } else {
                                        Toast.makeText(getActivity(), "Wrong password or email!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

        return view202;
    }
}

