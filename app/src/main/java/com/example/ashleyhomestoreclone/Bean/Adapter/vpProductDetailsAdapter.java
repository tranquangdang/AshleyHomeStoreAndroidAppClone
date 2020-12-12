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

    private List<ProductDetailsBean> product202;
    private Context context202;

    public vpProductDetailsAdapter(List<ProductDetailsBean> product202, Context context202) {
        this.product202 = product202;
        this.context202 = context202;
    }

    @Override
    public int getCount() {
        return product202.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view202 = LayoutInflater.from(context202).inflate(R.layout.item_viewpager_product, container,false);

        ImageView imageView202;
        imageView202 = view202.findViewById(R.id.img_vp_product);

        imageView202.setImageResource(product202.get(position).getImg());

        container.addView(view202, 0);
        return view202;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
