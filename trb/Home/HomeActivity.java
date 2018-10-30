package com.astro.trb.Home;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.astro.trb.R;
import com.astro.trb.Utils.BottomNavViewHelper;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";
    private final int ACTIVITY_NUM = 0;
    private Context mContext = HomeActivity.this;

    private TextView feed;
    private ImageButton upvoteButton;
    private ImageButton downvoteButton;

    private int r_id = 1; // should be random between 1 and row_num+1

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.d(TAG, "onCreate: starting.");

        setUpBottomNavigationView();

        feed = findViewById(R.id.feed);
        upvoteButton = findViewById(R.id.upvoteButton);
        downvoteButton = findViewById(R.id.downvoteButton);

        setUpButtons();
    }

    /**
     * Buttons setup
     * 0 implies decrement heat, 1 implies increment
     */
    private void setUpButtons(){
        // downvote
        downvoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Code here executes on main thread after user submits a message
                Log.d(TAG, "onClick: downvote & get a random message");

                new HomeDBHelper(mContext, feed).execute(r_id++/*,0*/);
                if (r_id>10) r_id=1;
            }
        });

        // upvote
        upvoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Code here executes on main thread after user submits a message
                Log.d(TAG, "onClick: upvote & get a random message");

                feed.setText(new StringBuilder(feed.getText()).append(' ').append(r_id).toString());

                new HomeDBHelper(mContext, feed).execute(r_id++/*,1*/);
                if (r_id>10) r_id=1;
            }
        });
    }



    /**
     * BottomNavView Setup
     */
    private void setUpBottomNavigationView(){
        Log.d(TAG, "setUpBottomNavigationView: setting uo BottomNavView");
        BottomNavigationViewEx bottomNavigationViewEx = findViewById(R.id.bottomNavBar);
        BottomNavViewHelper.setupBottomNavView(bottomNavigationViewEx);
        BottomNavViewHelper.enableNavigation(mContext, bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }
}
