package com.example.ashleyhomestoreclone;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ashleyhomestoreclone.Bean.Adapter.ProductListAdapter;
import com.example.ashleyhomestoreclone.Bean.BaseFragment;
import com.example.ashleyhomestoreclone.Bean.ProductListBean;

import java.util.ArrayList;

public class ProductListFragment extends BaseFragment {
    RecyclerView recyclerView202;
    ProductListAdapter adapter202;
    ArrayList<ProductListBean> arrayList202;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view202 = inflater.inflate(R.layout.layout_product_list, container, false);

        Toolbar toolbar202 = view202.findViewById(R.id.toolbar_search);
        toolbar202.inflateMenu(R.menu.toobar_search_shop);
        toolbar202.setLogo(ContextCompat.getDrawable(getContext(), R.drawable.ic_baseline_arrow_back_ios_24));
        View logoView = toolbar202.getChildAt(3);
        logoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity202.popFragments();
            }
        });

        arrayList202 = new ArrayList<>();
        recyclerView202 = (RecyclerView)view202.findViewById(R.id.recyclerProductList);
        recyclerView202.setHasFixedSize(true);

        RecyclerView.LayoutManager manager = new GridLayoutManager(getContext(), 2);
        recyclerView202.setLayoutManager(manager);

        arrayList202.add(new ProductListBean(R.drawable.sp1, "Home Accents Industrial Iron Desk Lamp with Glass Shade, Rose Gold", 34.99, 34.99));
        arrayList202.add(new ProductListBean(R.drawable.sp2, "Home Accents Roast Pan with Grill Rack, Gray", 34.99, 34.99));
        arrayList202.add(new ProductListBean(R.drawable.sp3, "Preston Bay Outdoor Armless Chair with Cushion", 317.99, 317.99));
        arrayList202.add(new ProductListBean(R.drawable.sp4, "Honey Can Do Four Tier Shoe Rack", 34.99, 34.99));
        arrayList202.add(new ProductListBean(R.drawable.sp5, "Uttermost Seagrass 1 Light Dome Pendant", 334.40, 334.40));
        arrayList202.add(new ProductListBean(R.drawable.sp6, "Providence Art Giclee Black Dots Wall Art", 300, 300));
        arrayList202.add(new ProductListBean(R.drawable.sp7, "Honey-Can-Do Bamboo Bath Mat", 58.99, 58.99));
        arrayList202.add(new ProductListBean(R.drawable.sp8, "12 Inch Memory Foam Queen Mattress in a Box", 269.99, 269.99));
        arrayList202.add(new ProductListBean(R.drawable.sp9, "Delta Children Simmons Kids Paloma 4 Drawer Dresser with Changing Top", 400.39, 400.39));
        arrayList202.add(new ProductListBean(R.drawable.sp10, "Home Accents Chrome Plated Steel Bath Tissue Organizer", 174.99, 174.99));
        arrayList202.add(new ProductListBean(R.drawable.sp11, "Surya Home Accents Embossed Mirror", 344, 344));
        arrayList202.add(new ProductListBean(R.drawable.sp12, "Honey-Can-Do Bamboo 4-Piece Dispenser Set", 39.99, 39.99));

        adapter202 = new ProductListAdapter(getContext(),arrayList202);
        recyclerView202.setAdapter(adapter202);

        return view202;
    }
}
