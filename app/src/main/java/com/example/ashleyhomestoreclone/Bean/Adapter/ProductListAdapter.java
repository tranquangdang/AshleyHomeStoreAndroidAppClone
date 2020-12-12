package com.example.ashleyhomestoreclone.Bean.Adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ashleyhomestoreclone.Bean.ProductListBean;
import com.example.ashleyhomestoreclone.MainActivity;
import com.example.ashleyhomestoreclone.ProductDetailsFragment;
import com.example.ashleyhomestoreclone.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder> {
    Context context;
    ArrayList<ProductListBean> arrayList;

    public ProductListAdapter(Context context, ArrayList<ProductListBean> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ProductListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_product_list_adapter,parent,false);
        return new ProductListViewHolder(view);
    }

    public String formatNumberCurrency(double number)
    {
        DecimalFormat formatter = new DecimalFormat("###,###,###.##");
        return "$" + formatter.format(Double.parseDouble(String.valueOf(number)));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListViewHolder holder, int position) {
        ProductListBean productListBean = arrayList.get(position);
        holder.imgProduct.setImageResource(productListBean.getImg());
        holder.nameProduct.setText(productListBean.getName());
        holder.price.setText(formatNumberCurrency(productListBean.getPrice()));
        holder.disPrice.setText(formatNumberCurrency(productListBean.getDisPrice()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)context).pushFragments("HOME_FRAGMENT", new ProductDetailsFragment(),true);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ProductListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProduct;
        TextView nameProduct;
        TextView price, disPrice;
        public ProductListViewHolder(@NonNull View itemView) {
            super(itemView);

            imgProduct = itemView.findViewById(R.id.imgProductList);
            nameProduct = itemView.findViewById(R.id.nameProductList);
            price = itemView.findViewById(R.id.priceProductList);
            disPrice = itemView.findViewById(R.id.discountPriceProductList);
            disPrice.setPaintFlags(disPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }
}
