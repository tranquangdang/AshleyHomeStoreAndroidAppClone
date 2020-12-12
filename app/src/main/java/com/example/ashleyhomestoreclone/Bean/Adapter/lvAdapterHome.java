package com.example.ashleyhomestoreclone.Bean.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ashleyhomestoreclone.Bean.lvBeanHome;
import com.example.ashleyhomestoreclone.R;

import java.util.ArrayList;
import java.util.List;

public class lvAdapterHome extends ArrayAdapter<lvBeanHome> {
    public lvAdapterHome(@NonNull Context context, int resource, @NonNull List<lvBeanHome> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;

        if(null == v){
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.layout_lv_adapter_home, null);
        }
        lvBeanHome mListView = getItem(position);
        ImageView img = v.findViewById(R.id.img_lv_home);

        img.setImageResource(mListView.getImg());

        return v;
    }
}
