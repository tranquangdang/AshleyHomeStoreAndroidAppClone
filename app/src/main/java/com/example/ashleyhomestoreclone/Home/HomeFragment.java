package com.example.ashleyhomestoreclone.Home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.example.ashleyhomestoreclone.Bean.Adapter.lvAdapterHome;
import com.example.ashleyhomestoreclone.Bean.BaseFragment;
import com.example.ashleyhomestoreclone.Bean.lvBeanHome;
import com.example.ashleyhomestoreclone.ProductListFragment;
import com.example.ashleyhomestoreclone.R;

import java.util.ArrayList;

public class HomeFragment extends BaseFragment {

    public HomeFragment(){
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_home, container, false);
        ListView listView;
        ArrayList<lvBeanHome> arrayList = new ArrayList<>();
        lvAdapterHome adapterHome;

        Toolbar toolbar = view.findViewById(R.id.toolbar_search);
        toolbar.inflateMenu(R.menu.toobar_search_shop);

        listView = (ListView) view.findViewById(R.id.lvHome);
        arrayList.add(new lvBeanHome(R.drawable.sale1));
        arrayList.add(new lvBeanHome(R.drawable.sale2));
        arrayList.add(new lvBeanHome(R.drawable.sale3));
        arrayList.add(new lvBeanHome(R.drawable.sale4));
        arrayList.add(new lvBeanHome(R.drawable.sale5));
        arrayList.add(new lvBeanHome(R.drawable.sale6));
        arrayList.add(new lvBeanHome(R.drawable.sale7));
        arrayList.add(new lvBeanHome(R.drawable.sale8));
        arrayList.add(new lvBeanHome(R.drawable.sale9));
        arrayList.add(new lvBeanHome(R.drawable.sale10));

        adapterHome = new lvAdapterHome(getContext(),R.layout.layout_lv_adapter_home,arrayList);
        listView.setAdapter(adapterHome);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                /*Fragment fragment = new ProductListFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_container,fragment,"ProductList")
                        .addToBackStack(null)
                        .commit();*/
                mActivity.pushFragments("HOME_FRAGMENT", new ProductListFragment(),true);
            }
        });

        return view;
    }
}
