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

    public static HashMap<String, Stack<Fragment>> mStacks;
    public static final String SHOP_FRAGMENT = "SHOP_FRAGMENT";
    public static final String STORE_FRAGMENT = "STORE_FRAGMENT";
    public static final String HOME_FRAGMENT = "HOME_FRAGMENT";
    public static final String CHAT_FRAGMENT = "CHAT_FRAGMENT";
    public static final String MORE_FRAGMENT = "MORE_FRAGMENT";
    public static BottomNavigationViewEx bottomNavigationViewEx;

    FirebaseUser firebaseUser;

    private String mCurrentTab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottom_navigation_main);
        bottomNavigationViewEx.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mStacks = new HashMap<String, Stack<Fragment>>();
        mStacks.put(SHOP_FRAGMENT, new Stack<Fragment>());
        mStacks.put(STORE_FRAGMENT, new Stack<Fragment>());
        mStacks.put(HOME_FRAGMENT, new Stack<Fragment>());
        mStacks.put(CHAT_FRAGMENT, new Stack<Fragment>());
        mStacks.put(MORE_FRAGMENT, new Stack<Fragment>());

        bottomNavigationViewEx.setSelectedItemId(R.id.action_home);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_shop:
                    selectedTab(SHOP_FRAGMENT);
                    return true;
                case R.id.action_store:
                    selectedTab(STORE_FRAGMENT);
                    return true;
                case R.id.action_home:
                    selectedTab(HOME_FRAGMENT);
                    return true;
                case R.id.action_chat:
                    selectedTab(CHAT_FRAGMENT);
                    return true;
                case R.id.action_more:
                    selectedTab(MORE_FRAGMENT);
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

    private void selectedTab(String tabId) {
        mCurrentTab = tabId;

        if (mStacks.get(tabId).size() == 0) {
            if (tabId.equals(SHOP_FRAGMENT)) {
                pushFragments(tabId, new ShopFragment(), true);
            } else if (tabId.equals(STORE_FRAGMENT)) {
                pushFragments(tabId, new StoreFragment(), true);
            } else if (tabId.equals(HOME_FRAGMENT)) {
                pushFragments(tabId, new HomeFragment(), true);
            } else if (tabId.equals(CHAT_FRAGMENT)) {
                pushFragments(tabId, new ChatFragment(), true);
            } else if (tabId.equals(MORE_FRAGMENT)) {
                if(firebaseUser != null) {
                    pushFragments(tabId, new MoreAfterLoginFragment(), true);
                } else {
                    pushFragments(tabId, new MoreFragment(), true);
                }
            }
        } else {
            pushFragments(tabId, mStacks.get(tabId).lastElement(), false);
        }
    }

    public void pushFragments(String tag, Fragment fragment, boolean shouldAdd) {
        if (shouldAdd)
            mStacks.get(tag).push(fragment);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.main_container, fragment);
        ft.commit();
    }

    public void popFragments() {
        Fragment fragment = mStacks.get(mCurrentTab).elementAt(mStacks.get(mCurrentTab).size() - 2);

        mStacks.get(mCurrentTab).pop();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.main_container, fragment);
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        if (mStacks.get(mCurrentTab).size() == 1) {
            bottomNavigationViewEx.setSelectedItemId(R.id.action_home);
            gotoFragment(new HomeFragment());
            return;
        }

        popFragments();
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
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
        if(firebaseUser != null) {
            bottomNavigationViewEx.setSelectedItemId(R.id.action_home);
        }
    }


    public boolean isInternetAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) MainActivity.this
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if ((connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE) != null && connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED)
                || (connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI) != null && connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                .getState() == NetworkInfo.State.CONNECTED)) {
            return true;
        } else {
            return false;
        }
    }
}