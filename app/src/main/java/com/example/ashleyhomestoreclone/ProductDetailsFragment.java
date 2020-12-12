package com.example.ashleyhomestoreclone;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.example.ashleyhomestoreclone.Bean.Adapter.vpProductDetailsAdapter;
import com.example.ashleyhomestoreclone.Bean.BaseFragment;
import com.example.ashleyhomestoreclone.Bean.ProductDetailsBean;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailsFragment extends BaseFragment {
    DotsIndicator indicator_vp;
    ViewPager viewPager;
    vpProductDetailsAdapter adapter;
    List<ProductDetailsBean> productList;

    public ProductDetailsFragment () {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_product_details, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar_details);
        toolbar.inflateMenu(R.menu.toobar_search_shop);
        toolbar.setLogo(ContextCompat.getDrawable(getContext(), R.drawable.ic_baseline_arrow_back_ios_24));
        View logoView = toolbar.getChildAt(2);
        logoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.popFragments();
            }
        });

        indicator_vp = (DotsIndicator) view.findViewById(R.id.indicator_vp);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager_product_details);

        productList = new ArrayList<>();
        productList.add(new ProductDetailsBean(R.drawable.tu1));
        productList.add(new ProductDetailsBean(R.drawable.tu2));
        productList.add(new ProductDetailsBean(R.drawable.tu3));
        productList.add(new ProductDetailsBean(R.drawable.tu4));
        productList.add(new ProductDetailsBean(R.drawable.tu5));
        productList.add(new ProductDetailsBean(R.drawable.tu7));

        adapter = new vpProductDetailsAdapter(productList, getContext());
        viewPager.setAdapter(adapter);
        indicator_vp.setViewPager(viewPager);
        return view;
    }
}
