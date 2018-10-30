package com.astro.trb.Utils;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;

import com.astro.trb.Home.HomeActivity;
import com.astro.trb.Creation.CreationActivity;
import com.astro.trb.R;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class BottomNavViewHelper {
    private static final String TAG = "BottomNavViewHelper";

    public static void setupBottomNavView(BottomNavigationViewEx bottomNavigationViewEx){
        Log.d(TAG, "setupBottomNavView: Setting up BottomNavView");
        bottomNavigationViewEx.enableAnimation(false);
        bottomNavigationViewEx.enableItemShiftingMode(false);
        bottomNavigationViewEx.enableShiftingMode(false);
        Log.d(TAG, "setupBottomNavView: Done setting up BottomNavView");
    }

    public static void enableNavigation(final Context context, final BottomNavigationViewEx view){
        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.ic_home_nav:
                        Intent intent0 = new Intent(context, HomeActivity.class);
                        context.startActivity(intent0);
                        break;

                    case R.id.ic_creation_nav:
                        Intent intent1 = new Intent(context, CreationActivity.class);
                        context.startActivity(intent1);
                        break;

                }

                return false;
            }
        });
    }
}
