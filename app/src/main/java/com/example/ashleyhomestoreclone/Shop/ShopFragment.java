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
        View view202 = inflater.inflate(R.layout.layout_fragment_shop, container, false);
        GridView gridView202;
        ArrayList arrayList202 =new ArrayList<>();
        gvAdapterShop gvAdapterShop202;

        Toolbar toolbar = view202.findViewById(R.id.toolbar_search);
        toolbar.inflateMenu(R.menu.toobar_search_shop);

        gridView202 = (GridView) view202.findViewById(R.id.gvShop);
        arrayList202.add(new gvBeanShop(R.drawable.furniture,"Furniture"));
        arrayList202.add(new gvBeanShop(R.drawable.mattresses,"Mattresses"));
        arrayList202.add(new gvBeanShop(R.drawable.kids,"Kids"));
        arrayList202.add(new gvBeanShop(R.drawable.baby,"Baby + Toddler"));
        arrayList202.add(new gvBeanShop(R.drawable.outdoor,"Outdoor"));
        arrayList202.add(new gvBeanShop(R.drawable.bath,"Bath"));
        arrayList202.add(new gvBeanShop(R.drawable.bedding,"Bedding"));
        arrayList202.add(new gvBeanShop(R.drawable.decor,"Decor + Pillows"));
        arrayList202.add(new gvBeanShop(R.drawable.rugs,"Rugs"));
        arrayList202.add(new gvBeanShop(R.drawable.lightning,"Lighting"));
        arrayList202.add(new gvBeanShop(R.drawable.sales,"Deals"));


        gvAdapterShop202 = new gvAdapterShop(getContext(),R.layout.layout_gv_adapter_shop,arrayList202);
        gridView202.setAdapter(gvAdapterShop202);

        gridView202.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mActivity202.pushFragments("SHOP_FRAGMENT", new ProductListFragment(),true);
            }
        });
        return view202;
    }

}
