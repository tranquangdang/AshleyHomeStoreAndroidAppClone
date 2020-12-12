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
    Context context202;
    ArrayList<ProductListBean> arrayList202;

    public ProductListAdapter(Context context202, ArrayList<ProductListBean> arrayList202) {
        this.context202 = context202;
        this.arrayList202 = arrayList202;
    }

    @NonNull
    @Override
    public ProductListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_product_list_adapter,parent,false);
        return new ProductListViewHolder(view);
    }

    public String formatNumberCurrency(double number202)
    {
        DecimalFormat formatter202 = new DecimalFormat("###,###,###.##");
        return "$" + formatter202.format(Double.parseDouble(String.valueOf(number202)));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListViewHolder holder, int position) {
        ProductListBean productListBean202 = arrayList202.get(position);
        holder.imgProduct202.setImageResource(productListBean202.getImg());
        holder.nameProduct202.setText(productListBean202.getName());
        holder.price202.setText(formatNumberCurrency(productListBean202.getPrice()));
        holder.disPrice202.setText(formatNumberCurrency(productListBean202.getDisPrice()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)context202).pushFragments("HOME_FRAGMENT", new ProductDetailsFragment(),true);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList202.size();
    }

    public class ProductListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProduct202;
        TextView nameProduct202;
        TextView price202, disPrice202;
        public ProductListViewHolder(@NonNull View itemView) {
            super(itemView);

            imgProduct202 = itemView.findViewById(R.id.imgProductList);
            nameProduct202 = itemView.findViewById(R.id.nameProductList);
            price202 = itemView.findViewById(R.id.priceProductList);
            disPrice202 = itemView.findViewById(R.id.discountPriceProductList);
            disPrice202.setPaintFlags(disPrice202.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }
}
