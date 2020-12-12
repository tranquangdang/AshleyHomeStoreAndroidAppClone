package com.example.ashleyhomestoreclone.More;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.ashleyhomestoreclone.Bean.BaseFragment;
import com.example.ashleyhomestoreclone.R;

public class MoreFragment extends BaseFragment {
    private Button btnRegister, btnLogin;

    public MoreFragment () {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_more, container, false);

        btnRegister = (Button) view.findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mActivity.pushFragments("MORE_FRAGMENT", new RegisterFragment(),true);
            }
        });

        btnLogin = (Button) view.findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mActivity.pushFragments("MORE_FRAGMENT", new LoginFragment(),true);
            }
        });
        return view;

    }

    @Override
    public boolean onBackPressed() {
        return true;
    }
}
