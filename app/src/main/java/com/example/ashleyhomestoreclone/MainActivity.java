package com.example.ashleyhomestoreclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuItem;

import com.example.ashleyhomestoreclone.Chat.ChatFragment;
import com.example.ashleyhomestoreclone.Home.HomeFragment;
import com.example.ashleyhomestoreclone.More.MoreAfterLoginFragment;
import com.example.ashleyhomestoreclone.More.MoreFragment;
import com.example.ashleyhomestoreclone.Shop.ShopFragment;
import com.example.ashleyhomestoreclone.Store.StoreFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    public static HashMap<String, Stack<Fragment>> mStacks202;
    String SHOP_FRAGMENT202 = "SHOP_FRAGMENT";
    String STORE_FRAGMENT202 = "STORE_FRAGMENT";
    String HOME_FRAGMENT202 = "HOME_FRAGMENT";
    String CHAT_FRAGMENT202 = "CHAT_FRAGMENT";
    String MORE_FRAGMENT202 = "MORE_FRAGMENT";
    public static BottomNavigationViewEx bottomNavigationViewEx202;

    FirebaseUser firebaseUser202;

    private String mCurrentTab202;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationViewEx202 = (BottomNavigationViewEx) findViewById(R.id.bottom_navigation_main);
        bottomNavigationViewEx202.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mStacks202 = new HashMap<String, Stack<Fragment>>();
        mStacks202.put(SHOP_FRAGMENT202, new Stack<Fragment>());
        mStacks202.put(STORE_FRAGMENT202, new Stack<Fragment>());
        mStacks202.put(HOME_FRAGMENT202, new Stack<Fragment>());
        mStacks202.put(CHAT_FRAGMENT202, new Stack<Fragment>());
        mStacks202.put(MORE_FRAGMENT202, new Stack<Fragment>());

        bottomNavigationViewEx202.setSelectedItemId(R.id.action_home);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_shop:
                    selectedTab(SHOP_FRAGMENT202);
                    return true;
                case R.id.action_store:
                    selectedTab(STORE_FRAGMENT202);
                    return true;
                case R.id.action_home:
                    selectedTab(HOME_FRAGMENT202);
                    return true;
                case R.id.action_chat:
                    selectedTab(CHAT_FRAGMENT202);
                    return true;
                case R.id.action_more:
                    selectedTab(MORE_FRAGMENT202);
                    return true;

            }
            return false;
        }

    };

    public void gotoFragment(Fragment selectedFragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_container, selectedFragment);
        fragmentTransaction.commit();
    }

    private void selectedTab(String tabId202) {
        mCurrentTab202 = tabId202;

        if (mStacks202.get(tabId202).size() == 0) {
            if (tabId202.equals(SHOP_FRAGMENT202)) {
                pushFragments(tabId202, new ShopFragment(), true);
            } else if (tabId202.equals(STORE_FRAGMENT202)) {
                pushFragments(tabId202, new StoreFragment(), true);
            } else if (tabId202.equals(HOME_FRAGMENT202)) {
                pushFragments(tabId202, new HomeFragment(), true);
            } else if (tabId202.equals(CHAT_FRAGMENT202)) {
                pushFragments(tabId202, new ChatFragment(), true);
            } else if (tabId202.equals(MORE_FRAGMENT202)) {
                if(firebaseUser202 != null) {
                    pushFragments(tabId202, new MoreAfterLoginFragment(), true);
                } else {
                    pushFragments(tabId202, new MoreFragment(), true);
                }
            }
        } else {
            pushFragments(tabId202, mStacks202.get(tabId202).lastElement(), false);
        }
    }

    public void pushFragments(String tag, Fragment fragment, boolean shouldAdd) {
        if (shouldAdd)
            mStacks202.get(tag).push(fragment);
        FragmentManager manager202 = getSupportFragmentManager();
        FragmentTransaction ft202 = manager202.beginTransaction();
        ft202.replace(R.id.main_container, fragment);
        ft202.commit();
    }

    public void popFragments() {
        Fragment fragment202 = mStacks202.get(mCurrentTab202).elementAt(mStacks202.get(mCurrentTab202).size() - 2);

        mStacks202.get(mCurrentTab202).pop();

        FragmentManager manager202 = getSupportFragmentManager();
        FragmentTransaction ft = manager202.beginTransaction();
        ft.replace(R.id.main_container, fragment202);
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        if (mStacks202.get(mCurrentTab202).size() == 1) {
            bottomNavigationViewEx202.setSelectedItemId(R.id.action_home);
            gotoFragment(new HomeFragment());
            return;
        }

        popFragments();
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseUser202 = FirebaseAuth.getInstance().getCurrentUser();
        if(!isInternetAvailable()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("Please connect to the internet")
                    .setCancelable(false)
                    .setPositiveButton("Connect", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            onStart();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
        if(firebaseUser202 != null) {
            bottomNavigationViewEx202.setSelectedItemId(R.id.action_home);
        }
    }


    public boolean isInternetAvailable() {
        ConnectivityManager connectivityManager202 = (ConnectivityManager) MainActivity.this
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if ((connectivityManager202
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE) != null && connectivityManager202
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED)
                || (connectivityManager202
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI) != null && connectivityManager202
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                .getState() == NetworkInfo.State.CONNECTED)) {
            return true;
        } else {
            return false;
        }
    }
}