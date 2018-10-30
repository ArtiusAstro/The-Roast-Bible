package com.astro.trb.Creation;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.astro.trb.R;
import com.astro.trb.Utils.BottomNavViewHelper;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class CreationActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";
    private final int ACTIVITY_NUM = 0;
    private Context mContext = CreationActivity.this;

    private EditText message;
    private EditText author;
    private ImageButton bookSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        Log.d(TAG, "onCreate: starting.");

        setUpBottomNavigationView();

        message = findViewById(R.id.message);
        author = findViewById(R.id.author);
        bookSubmit = findViewById(R.id.bookSubmit);

        setUpButtons();
    }

    /**
     * Buttons setup
     */
    private void setUpButtons() {
        bookSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user submits a message
                Log.d(TAG, "onClick: submitting a message");
                String messageText = message.getText().toString();
                String authorText = author.getText().toString();

                if (messageText.length() < 1) {
                    emptyEntry();
                    return;
                }
                if (author.toString().length() < 1) {
                    authorText = "anon";
                }

                // ready to POST
                new CreationDBHelper(mContext).execute(messageText, authorText);
                message.setText("");
                author.setText("");
            }

            private void emptyEntry() {
                // Create toast to alert user to empty messageText
                Toast.makeText(mContext, "Empty Roast", Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     * BottomNavView Setup
     */
    private void setUpBottomNavigationView() {
        Log.d(TAG, "setUpBottomNavigationView: setting uo BottomNavView");
        BottomNavigationViewEx bottomNavigationViewEx = findViewById(R.id.bottomNavBar);
        BottomNavViewHelper.setupBottomNavView(bottomNavigationViewEx);
        BottomNavViewHelper.enableNavigation(mContext, bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }

}
