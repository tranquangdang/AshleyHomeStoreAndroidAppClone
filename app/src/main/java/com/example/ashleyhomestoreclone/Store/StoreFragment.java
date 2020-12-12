package com.example.ashleyhomestoreclone.Store;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.ashleyhomestoreclone.Bean.BaseFragment;
import com.example.ashleyhomestoreclone.Home.HomeFragment;
import com.example.ashleyhomestoreclone.R;

import static com.example.ashleyhomestoreclone.MainActivity.MORE_FRAGMENT;
import static com.example.ashleyhomestoreclone.MainActivity.mStacks;

public class StoreFragment extends BaseFragment {
    private Button btnUse;

    public StoreFragment () {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_store, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar_store);
        toolbar.setLogo(ContextCompat.getDrawable(getContext(), R.drawable.ic_baseline_arrow_back_ios_24_white));
        View logoView = toolbar.getChildAt(1);
        logoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.pushFragments("STORE_FRAGMENT", new HomeFragment(),true);
            }
        });

        btnUse = (Button) view.findViewById(R.id.btnCurrentLocation);
        btnUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mActivity.pushFragments("STORE_FRAGMENT", new StoreMapsFragment(),true);
            }
        });
        return view;
    }

    @Override
    public boolean onBackPressed() {
        return true;
    }
}
