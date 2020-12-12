package com.example.ashleyhomestoreclone.Shop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.example.ashleyhomestoreclone.Bean.Adapter.gvAdapterShop;
import com.example.ashleyhomestoreclone.Bean.BaseFragment;
import com.example.ashleyhomestoreclone.Bean.gvBeanShop;
import com.example.ashleyhomestoreclone.ProductListFragment;
import com.example.ashleyhomestoreclone.R;

import java.util.ArrayList;

public class ShopFragment extends BaseFragment {

    public  ShopFragment () {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_shop, container, false);
        GridView gridView;
        ArrayList arrayList =new ArrayList<>();
        gvAdapterShop gvAdapterShop;

        Toolbar toolbar = view.findViewById(R.id.toolbar_search);
        toolbar.inflateMenu(R.menu.toobar_search_shop);

        gridView = (GridView) view.findViewById(R.id.gvShop);
        arrayList.add(new gvBeanShop(R.drawable.furniture,"Furniture"));
        arrayList.add(new gvBeanShop(R.drawable.mattresses,"Mattresses"));
        arrayList.add(new gvBeanShop(R.drawable.kids,"Kids"));
        arrayList.add(new gvBeanShop(R.drawable.baby,"Baby + Toddler"));
        arrayList.add(new gvBeanShop(R.drawable.outdoor,"Outdoor"));
        arrayList.add(new gvBeanShop(R.drawable.bath,"Bath"));
        arrayList.add(new gvBeanShop(R.drawable.bedding,"Bedding"));
        arrayList.add(new gvBeanShop(R.drawable.decor,"Decor + Pillows"));
        arrayList.add(new gvBeanShop(R.drawable.rugs,"Rugs"));
        arrayList.add(new gvBeanShop(R.drawable.lightning,"Lighting"));
        arrayList.add(new gvBeanShop(R.drawable.sales,"Deals"));


        gvAdapterShop = new gvAdapterShop(getContext(),R.layout.layout_gv_adapter_shop,arrayList);
        gridView.setAdapter(gvAdapterShop);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mActivity.pushFragments("SHOP_FRAGMENT", new ProductListFragment(),true);
            }
        });
        return view;
    }

}
