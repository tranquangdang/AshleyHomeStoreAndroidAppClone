package com.example.ashleyhomestoreclone.Bean;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.example.ashleyhomestoreclone.MainActivity;

public class BaseFragment extends Fragment {
	public MainActivity mActivity202;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mActivity202		=	(MainActivity) this.getActivity();
	}

	public boolean onBackPressed(){
		return false;
	}
}
