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
    DotsIndicator indicator_vp202;
    ViewPager viewPager202;
    vpProductDetailsAdapter adapter202;
    List<ProductDetailsBean> productList202;

    public ProductDetailsFragment () {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view202 = inflater.inflate(R.layout.layout_fragment_product_details, container, false);

        Toolbar toolbar202 = view202.findViewById(R.id.toolbar_details);
        toolbar202.inflateMenu(R.menu.toobar_search_shop);
        toolbar202.setLogo(ContextCompat.getDrawable(getContext(), R.drawable.ic_baseline_arrow_back_ios_24));
        View logoView = toolbar202.getChildAt(2);
        logoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity202.popFragments();
            }
        });

        indicator_vp202 = (DotsIndicator) view202.findViewById(R.id.indicator_vp);
        viewPager202 = (ViewPager) view202.findViewById(R.id.viewPager_product_details);

        productList202 = new ArrayList<>();
        productList202.add(new ProductDetailsBean(R.drawable.tu1));
        productList202.add(new ProductDetailsBean(R.drawable.tu2));
        productList202.add(new ProductDetailsBean(R.drawable.tu3));
        productList202.add(new ProductDetailsBean(R.drawable.tu4));
        productList202.add(new ProductDetailsBean(R.drawable.tu5));
        productList202.add(new ProductDetailsBean(R.drawable.tu7));

        adapter202 = new vpProductDetailsAdapter(productList202, getContext());
        viewPager202.setAdapter(adapter202);
        indicator_vp202.setViewPager(viewPager202);
        return view202;
    }
}
