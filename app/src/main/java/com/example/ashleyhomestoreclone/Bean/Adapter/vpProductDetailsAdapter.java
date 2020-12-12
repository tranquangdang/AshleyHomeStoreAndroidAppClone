package com.example.ashleyhomestoreclone.Bean.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.ashleyhomestoreclone.Bean.ProductDetailsBean;
import com.example.ashleyhomestoreclone.R;

import java.util.List;

public class vpProductDetailsAdapter extends PagerAdapter {

    private List<ProductDetailsBean> product;
    private Context context;

    public vpProductDetailsAdapter(List<ProductDetailsBean> product, Context context) {
        this.product = product;
        this.context = context;
    }

    @Override
    public int getCount() {
        return product.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_viewpager_product, container,false);

        ImageView imageView;
        imageView = view.findViewById(R.id.img_vp_product);

        imageView.setImageResource(product.get(position).getImg());

        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
